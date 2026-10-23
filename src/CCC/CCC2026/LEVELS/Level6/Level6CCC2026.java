package CCC.CCC2026.LEVELS.Level6;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Level6CCC2026 {

    static final String DIR = "weekly-contests-java-vs-code/src/CCC/CCC2026/LEVELS/Level6/";
    static final String INPUT = "in_level-6_0-example.txt";

    static final int[] DR = {0, 0, 1, 1, 0, -1, -1};
    static final int[] DC = {0, 2, 1, -1, -2, -1, 1};
    static final int[] FAMILY = {0, 1, 2, 3, 1, 2, 3};
    static final int[] NR = {0, 0, -1, -1, 1, 1};
    static final int[] NC = {-2, 2, -1, 1, -1, 1};

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(DIR, INPUT));
        int n = Integer.parseInt(lines.get(0).trim());

        Path outFile = Path.of(DIR, "output", INPUT.replace("in_", "out_"));
        Files.createDirectories(outFile.getParent());
        Map<Integer, Board> boards = new HashMap<>();
        long score = 0;
        try (BufferedWriter out = Files.newBufferedWriter(outFile)) {
            int idx = 1;
            for (int p = 0; p < n; ++p) {
                // "3 7 13 3" = radius, rows, columns, required count; then the pattern drawn with 'M'.
                String[] info = lines.get(idx).trim().split("\\s+");
                ++idx;
                int radius = Integer.parseInt(info[0]);
                int rows = Integer.parseInt(info[1]);
                int required = Integer.parseInt(info[3]);
                List<int[]> pattern = new ArrayList<>();
                for (int r = 0; r < rows; ++r) {
                    String row = lines.get(idx);
                    ++idx;
                    for (int c = 0; c < row.length(); ++c) {
                        if (row.charAt(c) == 'M') {
                            pattern.add(new int[]{r, c});
                        }
                    }
                }
                ++idx;   // empty line between patterns

                Board board = boards.computeIfAbsent(radius, Board::new);
                List<char[]> program = solve(board, pattern, required, p);
                for (char[] t : program) {
                    out.write(t[0] + " " + t[1] + " " + t[2] + " " + t[3] + " " + t[4] + " " + t[5]);
                    out.newLine();
                }
                out.newLine();
                score += program.size();
            }
        }
        System.out.println(INPUT + ": " + score + " instruction lines");
    }

    /** Tries every gate/sweeper/order combination, shortest first, and returns the first one the simulator accepts. */
    static List<char[]> solve(Board b, List<int[]> pattern, int required, int index) {
        boolean[][] isPattern = new boolean[b.empty.length][];
        for (int r = 0; r < isPattern.length; ++r) {
            isPattern[r] = new boolean[b.empty[r].length];
        }
        for (int[] cell : pattern) {
            isPattern[cell[0]][cell[1]] = true;
        }

        List<List<char[]>> candidates = new ArrayList<>();
        for (int gate = 1; gate <= 6; ++gate) {
            for (int sweeper = 1; sweeper <= 6; ++sweeper) {
                if (FAMILY[gate] == FAMILY[sweeper]) {
                    continue;
                }
                candidates.add(repeat(buildOnce(b, pattern, gate, sweeper, false), required));
                candidates.add(repeat(buildOnce(b, pattern, gate, sweeper, true), required));
            }
        }
        candidates.sort(Comparator.comparingInt(List::size));
        List<char[]> best = null;
        for (List<char[]> candidate : candidates) {
            if (simulate(b, pattern, isPattern, candidate) >= required) {
                best = candidate;
                break;
            }
        }

        // The multi-beam greedy usually creates several cells per tick; keep the shortest verified program.
        Greedy searcher = new Greedy(b, pattern, 1.0, 8, 8, 0, 0);
        Chain prefix = searcher.bestChain();
        for (int[] variant : VARIANTS) {
            for (int usePrefix = 0; usePrefix < (prefix == null ? 1 : 2); ++usePrefix) {
                Greedy greedy = new Greedy(b, pattern, variant[0] / 100.0, variant[1], variant[2], variant[3] / 100.0, variant[4]);
                greedy.prefix = usePrefix == 1 ? prefix : null;
                greedy.buildPasses(Math.min(required, 3));
                if (greedy.passEnd.isEmpty()) {
                    continue;
                }
                List<char[]> program = greedy.bestProgram(required);
                if (program != null && (best == null || program.size() < best.size())
                        && simulate(b, pattern, isPattern, program) >= required) {
                    best = program;
                }
            }
        }
        // A short cycle of configurations repeats the pattern in very few lines; keep it when shorter and verified.
        List<char[]> cycle = searcher.cycleProgram(required);
        if (cycle != null && (best == null || cycle.size() < best.size()) && simulate(b, pattern, isPattern, cycle) >= required) {
            best = cycle;
        }
        if (best != null) {
            return best;
        }
        System.err.println("WARNING: pattern " + index + " has no verified program, writing the shortest candidate anyway");
        return candidates.get(0);
    }

    static final char[] OPS = {'-', 'F', 'B', 'T'};
    // Greedy variants: {travel bias %, targets checked by dry run, single-cell targets, potential weight %, random seed (0 = none)}
    static final int[][] VARIANTS = {
        {100, 8, 8, 0, 0}, {140, 8, 8, 0, 0}, {75, 8, 8, 0, 0}, {100, 16, 16, 0, 0},
        {100, 8, 8, 15, 1}, {120, 8, 8, 10, 2}, {100, 12, 12, 30, 3}, {85, 8, 8, 20, 4},
        {100, 8, 8, 15, 5}, {100, 8, 8, 15, 6}, {110, 10, 10, 20, 7}, {90, 10, 10, 10, 8},
        {130, 8, 8, 25, 9}, {100, 12, 12, 5, 10}, {100, 8, 8, 40, 11}, {120, 12, 12, 15, 12},
        {100, 8, 8, 15, 13}, {95, 8, 8, 20, 14}, {105, 10, 10, 10, 15}, {100, 16, 16, 20, 16}};

    /** A configuration to travel to: for every emitter, on at tpos[e], or off. */
    static final class Target {
        final int[] tpos = new int[7];
        final boolean[] ton = new boolean[7];
        final boolean[] park = new boolean[7];   // position matters although the emitter stays off
        int[] covers = new int[0];   // pattern cells this configuration creates
    }

    static final class Conf {
        final int[] pos = new int[7];
        final boolean[] on = new boolean[7], parked = new boolean[7];
        final long[] cov;
        int count;

        Conf(int words) {
            cov = new long[words];
            Arrays.fill(pos, -1);
        }

        Conf copy() {
            Conf c = new Conf(cov.length);
            System.arraycopy(pos, 0, c.pos, 0, 7);
            System.arraycopy(on, 0, c.on, 0, 7);
            System.arraycopy(parked, 0, c.parked, 0, 7);
            System.arraycopy(cov, 0, c.cov, 0, cov.length);
            c.count = count;
            return c;
        }
    }

    /** Configurations one tick apart, with the union of what they create. */
    static final class Chain {
        final List<Conf> confs = new ArrayList<>();
        final long[] cov;
        int count;

        Chain(Conf first) {
            confs.add(first);
            cov = first.cov.clone();
            count = first.count;
        }

        Chain(Chain base, Conf next) {
            for (Conf c : base.confs) {
                confs.add(c.copy());
            }
            confs.add(next.copy());
            // an emitter switching on for the first time has been parked at that position all along
            for (int e = 1; e <= 6; ++e) {
                if (next.on[e] && !base.confs.get(base.confs.size() - 1).parked[e]) {
                    for (int i = 0; i < confs.size() - 1; ++i) {
                        confs.get(i).parked[e] = true;
                        confs.get(i).pos[e] = next.pos[e];
                    }
                }
            }
            cov = base.cov.clone();
            count = 0;
            for (int w = 0; w < cov.length; ++w) {
                cov[w] |= next.cov[w];
                count += Long.bitCount(cov[w]);
            }
        }
    }

    static final class Greedy {
        final int S, n, pCount;
        final int[][][] beamIds;   // [emitter][ring pos] -> ids of the cells on the beam
        final int[][] fire;        // [emitter][cell id] -> ring pos whose beam crosses the cell
        final boolean[] inP, allow2, covered;
        final int[] hit, touched;
        final int[] pos;
        final boolean[] on = new boolean[7];
        final List<Integer> pCells = new ArrayList<>(), skirt = new ArrayList<>();   // pattern cells, cells touching it
        final List<Target> fills = new ArrayList<>();
        final List<char[]> ticks = new ArrayList<>();
        final List<int[]> created = new ArrayList<>();   // pattern cells created by each tick
        final double bias, potWeight;
        final java.util.Random rnd;
        final int checkedTargets, singleTargets;
        final int[] startPos;
        int uncovered, lastPot, bestImmediateGain;
        char[] bestImmediate;

        Greedy(Board b, List<int[]> pattern, double bias, int checkedTargets, int singleTargets, double potWeight, int seed) {
            this.bias = bias;
            this.potWeight = potWeight;
            this.rnd = seed == 0 ? null : new java.util.Random(seed);
            this.checkedTargets = checkedTargets;
            this.singleTargets = singleTargets;
            S = b.size;
            int[][] id = new int[b.empty.length][];
            List<int[]> cells = new ArrayList<>();
            for (int r = 0; r < b.empty.length; ++r) {
                id[r] = new int[b.empty[r].length];
                Arrays.fill(id[r], -1);
                for (int c = 0; c < b.empty[r].length; ++c) {
                    if (b.empty[r][c] == 'O') {
                        id[r][c] = cells.size();
                        cells.add(new int[]{r, c});
                    }
                }
            }
            n = cells.size();
            beamIds = new int[7][S][];
            fire = new int[7][n];
            for (int e = 1; e <= 6; ++e) {
                for (int p = 0; p < S; ++p) {
                    int[] pairs = b.beam[e][p];
                    int[] ids = new int[pairs.length / 2];
                    for (int k = 0; k < ids.length; ++k) {
                        ids[k] = id[pairs[2 * k]][pairs[2 * k + 1]];
                    }
                    beamIds[e][p] = ids;
                }
                for (int i = 0; i < n; ++i) {
                    fire[e][i] = b.firePos[e][cells.get(i)[0]][cells.get(i)[1]];
                }
            }
            inP = new boolean[n];
            for (int[] cell : pattern) {
                inP[id[cell[0]][cell[1]]] = true;
            }
            allow2 = new boolean[n];
            for (int i = 0; i < n; ++i) {
                boolean touching = false;
                int r = cells.get(i)[0], c = cells.get(i)[1];
                for (int k = 0; k < 6; ++k) {
                    int rr = r + NR[k], cc = c + NC[k];
                    if (rr >= 0 && rr < id.length && cc >= 0 && cc < id[rr].length && id[rr][cc] >= 0 && inP[id[rr][cc]]) {
                        touching = true;
                    }
                }
                allow2[i] = inP[i] || !touching;
                if (inP[i]) {
                    pCells.add(i);
                } else if (touching) {
                    skirt.add(i);
                }
            }
            pCount = pCells.size();
            uncovered = pCount;
            covered = new boolean[n];
            hit = new int[n];
            touched = new int[n];
            pos = new int[]{0, 5 * b.radius, 0, b.radius, 2 * b.radius, 3 * b.radius, 4 * b.radius};
            startPos = pos.clone();
            buildFills(cells);
        }

        // Whole-line fills: for every line, every way to veto its (at most 4) cells that touch the pattern.
        void buildFills(List<int[]> cells) {
            for (int f = 1; f <= 3; ++f) {
                int[] veto = new int[4];
                int k = 0;
                for (int e = 1; e <= 6; ++e) {
                    if (FAMILY[e] != f) {
                        veto[k] = e;
                        ++k;
                    }
                }
                Map<Integer, List<Integer>> lines = new TreeMap<>();
                for (int i = 0; i < n; ++i) {
                    lines.computeIfAbsent(lineCoord(f, cells.get(i)), x -> new ArrayList<>()).add(i);
                }
                for (List<Integer> line : lines.values()) {
                    boolean hasP = false;
                    List<Integer> garbage = new ArrayList<>();
                    for (int i : line) {
                        if (inP[i]) {
                            hasP = true;
                        } else if (!allow2[i]) {
                            garbage.add(i);
                        }
                    }
                    if (hasP && garbage.size() <= 4) {
                        assignVetoes(f, line.get(0), veto, garbage, 0, new int[garbage.size()], new boolean[7]);
                    }
                }
            }
        }

        void assignVetoes(int f, int anyCell, int[] veto, List<Integer> garbage, int i, int[] chosen, boolean[] used) {
            if (i == garbage.size()) {
                Target t = new Target();
                for (int e : new int[]{f, f + 3}) {
                    t.tpos[e] = fire[e][anyCell];
                    t.ton[e] = true;
                }
                for (int j = 0; j < chosen.length; ++j) {
                    t.tpos[chosen[j]] = fire[chosen[j]][garbage.get(j)];
                    t.ton[chosen[j]] = true;
                }
                int[] covers = twoHitPattern(t.tpos, t.ton);
                if (covers != null && covers.length > 0) {
                    t.covers = covers;
                    fills.add(t);
                }
                return;
            }
            for (int v : veto) {
                if (used[v]) {
                    continue;
                }
                used[v] = true;
                chosen[i] = v;
                assignVetoes(f, anyCell, veto, garbage, i + 1, chosen, used);
                used[v] = false;
            }
        }

        int spread(int[] p, boolean[] o) {
            int tn = 0;
            for (int e = 1; e <= 6; ++e) {
                if (!o[e]) {
                    continue;
                }
                for (int c : beamIds[e][p[e]]) {
                    if (hit[c] == 0) {
                        touched[tn] = c;
                        ++tn;
                    }
                    ++hit[c];
                }
            }
            return tn;
        }

        /** Pattern cells with exactly 2 beams, or null if the configuration is not safe. */
        int[] twoHitPattern(int[] p, boolean[] o) {
            int tn = spread(p, o), cnt = 0;
            boolean safe = true;
            int[] out = new int[tn];
            for (int k = 0; k < tn; ++k) {
                int c = touched[k], h = hit[c];
                hit[c] = 0;
                if (h == 2) {
                    if (!allow2[c]) {
                        safe = false;
                    }
                    else if (inP[c]) {
                        out[cnt] = c;
                        ++cnt;
                    }
                } else if (h >= 3 && inP[c]) {
                    safe = false;
                }
            }
            return safe ? Arrays.copyOf(out, cnt) : null;
        }

        /** -1 if not safe, else the number of new pattern cells; lastPot = uncovered pattern cells on exactly one beam. */
        int eval(int[] p, boolean[] o) {
            int tn = spread(p, o), gain = 0, pot = 0;
            boolean safe = true;
            for (int k = 0; k < tn; ++k) {
                int c = touched[k], h = hit[c];
                hit[c] = 0;
                if (h == 2) {
                    if (!allow2[c]) {
                        safe = false;
                    }
                    else if (inP[c] && !covered[c]) {
                        ++gain;
                    }
                } else if (h >= 3) {
                    if (inP[c]) {
                        safe = false;
                    }
                } else if (inP[c] && !covered[c]) {
                    ++pot;
                }
            }
            lastPot = pot;
            return safe ? gain : -1;
        }

        /**
         * Idle emitters (off, staying off, not part of the current trip) move for free: each one steps toward the
         * line where switching it on would create the most uncovered pattern cells next to the beams of this tick.
         */
        char[] preposition(char[] line, boolean[] fixed) {
            char[] l = line.clone();
            int[] np = new int[7];
            boolean[] no = new boolean[7];
            for (int e = 1; e <= 6; ++e) {
                char op = l[e - 1];
                np[e] = op == 'F' ? (pos[e] + 1) % S : op == 'B' ? (pos[e] + S - 1) % S : pos[e];
                no[e] = op == 'T' ? !on[e] : on[e];
            }
            int tn = spread(np, no);   // hit[] holds this tick's hits while scoring
            for (int e = 1; e <= 6; ++e) {
                if (no[e] || l[e - 1] != '-' || (fixed != null && fixed[e])) {
                    continue;
                }
                int bestP = pos[e];
                double bestScore = prepositionScore(e, pos[e], pos[e]);
                for (int p = 0; p < S; ++p) {
                    double score = prepositionScore(e, p, pos[e]);
                    if (score > bestScore) {
                        bestScore = score;
                        bestP = p;
                    }
                }
                int d = signedDist(pos[e], bestP, S);
                if (d != 0) {
                    l[e - 1] = d > 0 ? 'F' : 'B';
                }
            }
            for (int k = 0; k < tn; ++k) {
                hit[touched[k]] = 0;
            }
            return l;
        }

        double prepositionScore(int e, int p, int from) {
            int value = 0, lineValue = 0;
            boolean safe = true;
            for (int c : beamIds[e][p]) {
                int h = hit[c];
                boolean open = inP[c] && !covered[c] && h != 2;   // still missing after this tick
                if (open) {
                    ++lineValue;
                }
                if (h == 1) {
                    if (!allow2[c]) {
                        safe = false;
                    }
                    else if (open) {
                        ++value;
                    }
                } else if (h == 2 && inP[c]) {
                    safe = false;
                }
            }
            return (safe ? 4 * value : -4) + lineValue - ringDist(from, p);
        }

        boolean apply(char[] line) {
            for (int e = 1; e <= 6; ++e) {
                char op = line[e - 1];
                if (op == 'F') {
                    pos[e] = (pos[e] + 1) % S;
                }
                else if (op == 'B') {
                    pos[e] = (pos[e] + S - 1) % S;
                }
                else if (op == 'T') {
                    on[e] = !on[e];
                }
            }
            int[] made = twoHitPattern(pos, on);
            if (made == null) {
                return false;
            }
            for (int c : made) {
                if (!covered[c]) {
                    covered[c] = true;
                    --uncovered;
                }
            }
            ticks.add(line);
            created.add(made);
            return true;
        }

        static char[] line(int[] op) {
            char[] l = new char[6];
            for (int e = 1; e <= 6; ++e) {
                l[e - 1] = OPS[op[e]];
            }
            return l;
        }

        void decode(int[] op, int[] np, boolean[] no) {
            for (int e = 1; e <= 6; ++e) {
                np[e] = op[e] == 1 ? (pos[e] + 1) % S : op[e] == 2 ? (pos[e] + S - 1) % S : pos[e];
                no[e] = op[e] == 3 ? !on[e] : on[e];
            }
        }

        /** Best single tick: most new pattern cells, then most uncovered cells already on a beam, then fewest changes. */
        void findImmediate() {
            int[] nopts = new int[7];
            int total = 1;
            for (int e = 1; e <= 6; ++e) {
                nopts[e] = on[e] ? 4 : 2;   // an off emitter only matters this tick if it switches on
                total *= nopts[e];
            }
            int[] op = new int[7], np = new int[7], bestOp = null;
            boolean[] no = new boolean[7];
            int bestG = 0, bestPot = -1, bestCh = 99;
            double bestScore = -1;
            for (int idx = 0; idx < total; ++idx) {
                int rest = idx, ch = 0;
                for (int e = 1; e <= 6; ++e) {
                    int k = rest % nopts[e];
                    rest /= nopts[e];
                    op[e] = on[e] ? k : (k == 0 ? 0 : 3);
                    if (op[e] != 0) {
                        ++ch;
                    }
                }
                decode(op, np, no);
                int g = eval(np, no);
                if (g < 1) {
                    continue;
                }
                double score = g + potWeight * lastPot + (rnd == null ? 0 : 0.01 * rnd.nextDouble());
                if (score > bestScore || (score == bestScore && (lastPot > bestPot || (lastPot == bestPot && ch < bestCh)))) {
                    bestScore = score;
                    bestG = g;
                    bestPot = lastPot;
                    bestCh = ch;
                    bestOp = op.clone();
                }
            }
            bestImmediateGain = bestG;
            bestImmediate = bestOp == null ? null : line(bestOp);
        }

        static boolean[] fixedOf(Target t) {
            boolean[] f = new boolean[7];
            for (int e = 1; e <= 6; ++e) {
                f[e] = t.ton[e] || t.park[e];
            }
            return f;
        }

        double noise() {
            return rnd == null ? 1.0 : 1.0 + 0.08 * rnd.nextDouble();
        }

        int ringDist(int a, int b) {
            int d = Math.abs(a - b);
            return Math.min(d, S - d);
        }

        int travelCost(Target t) {
            int cost = 1;
            for (int e = 1; e <= 6; ++e) {
                if (t.ton[e]) {
                    cost = Math.max(cost, ringDist(pos[e], t.tpos[e]) + (on[e] ? 0 : 1));
                }
                else if (t.park[e]) {
                    cost = Math.max(cost, ringDist(pos[e], t.tpos[e]) + (on[e] ? 1 : 0));
                }
                else if (on[e]) {
                    cost = Math.max(cost, 1);
                }
            }
            return cost;
        }


        /**
         * The fill or single-cell crossing with the most new pattern cells per tick of travel. Candidates are ranked
         * by an optimistic cost (a lower bound), and the best few get their exact cost from a dry run of the mover.
         */
        Target chooseTarget(int immediateGain, double[] ratioOut) {
            List<Target> cand = new ArrayList<>();
            List<double[]> score = new ArrayList<>();   // {optimistic ratio, value, candidate index}
            for (Target t : fills) {
                int v = 0;
                for (int c : t.covers) {
                    if (!covered[c]) {
                        ++v;
                    }
                }
                if (v == 0) {
                    continue;
                }
                score.add(new double[]{v / (double) travelCost(t) * noise(), v, cand.size()});
                cand.add(t);
            }
            // Cheapest pair of emitters (different families) crossing on each uncovered cell; keep the cheapest cells.
            List<int[]> singles = new ArrayList<>();   // {cost, cell, e1, e2}
            for (int x : pCells) {
                if (covered[x]) {
                    continue;
                }
                int bestCost = Integer.MAX_VALUE, be1 = -1, be2 = -1;
                for (int e1 = 1; e1 <= 6; ++e1) {
                    for (int e2 = e1 + 1; e2 <= 6; ++e2) {
                        if (FAMILY[e1] == FAMILY[e2]) {
                            continue;
                        }
                        int cost = 1;
                        for (int e = 1; e <= 6; ++e) {
                            if (e == e1 || e == e2) {
                                cost = Math.max(cost, ringDist(pos[e], fire[e][x]) + (on[e] ? 0 : 1));
                            }
                            else if (on[e]) {
                                cost = Math.max(cost, 1);
                            }
                        }
                        if (cost < bestCost) {
                            bestCost = cost;
                            be1 = e1;
                            be2 = e2;
                        }
                    }
                }
                singles.add(new int[]{bestCost, x, be1, be2});
            }
            singles.sort(Comparator.comparingInt(s -> s[0]));
            for (int i = 0; i < Math.min(singleTargets, singles.size()); ++i) {
                int[] s = singles.get(i);
                Target t = new Target();
                t.tpos[s[2]] = fire[s[2]][s[1]];
                t.ton[s[2]] = true;
                t.tpos[s[3]] = fire[s[3]][s[1]];
                t.ton[s[3]] = true;
                t.covers = new int[]{s[1]};
                score.add(new double[]{1.0 / s[0] * noise(), 1, cand.size()});
                cand.add(t);
            }
            score.sort((a, b) -> Double.compare(b[0], a[0]));

            Target best = null;
            double bestRatio = 0;
            int checked = 0;
            for (double[] s : score) {
                if (s[0] <= bestRatio || s[0] * bias <= immediateGain || checked >= checkedTargets) {
                    break;
                }
                Target t = cand.get((int) s[2]);
                double ratio = s[1] / dryRun(t);
                ++checked;
                if (ratio > bestRatio) {
                    bestRatio = ratio;
                    best = t;
                }
            }
            if (best == null && immediateGain == 0 && !score.isEmpty()) {
                best = cand.get((int) score.get(0)[2]);
            }
            ratioOut[0] = bestRatio;
            return best;
        }

        int remaining(int[] p, boolean[] o, Target t) {
            int d = 0;
            for (int e = 1; e <= 6; ++e) {
                if (t.ton[e]) {
                    d += ringDist(p[e], t.tpos[e]) + (o[e] ? 0 : 1);
                }
                else if (t.park[e]) {
                    d += ringDist(p[e], t.tpos[e]) + (o[e] ? 1 : 0);
                }
                else if (o[e]) {
                    ++d;
                }
            }
            return d;
        }

        /**
         * Best safe tick from (p, o) that strictly reduces the distance to t, or null. Emitters of the target
         * step toward it, stay or toggle; the others can only switch off.
         */
        int[] travelStep(int[] p, boolean[] o, Target t, int dist, int[] outD) {
            int[][] choices = new int[7][];
            int total = 1;
            for (int e = 1; e <= 6; ++e) {
                if (t.ton[e] || t.park[e]) {
                    int d = signedDist(p[e], t.tpos[e], S);
                    boolean toggle = t.ton[e] || o[e];   // a parked emitter only toggles to switch off
                    if (d == 0) {
                        choices[e] = toggle ? new int[]{0, 3} : new int[]{0};
                    }
                    else {
                        choices[e] = toggle ? new int[]{0, d > 0 ? 1 : 2, 3} : new int[]{0, d > 0 ? 1 : 2};
                    }
                } else {
                    choices[e] = o[e] ? new int[]{0, 3} : new int[]{0};
                }
                total *= choices[e].length;
            }
            int[] op = new int[7], np = new int[7], bestOp = null;
            boolean[] no = new boolean[7];
            int bestD = Integer.MAX_VALUE, bestCh = 99;
            for (int idx = 0; idx < total; ++idx) {
                int rest = idx, ch = 0;
                for (int e = 1; e <= 6; ++e) {
                    op[e] = choices[e][rest % choices[e].length];
                    rest /= choices[e].length;
                    if (op[e] != 0) {
                        ++ch;
                    }
                    np[e] = op[e] == 1 ? (p[e] + 1) % S : op[e] == 2 ? (p[e] + S - 1) % S : p[e];
                    no[e] = op[e] == 3 ? !o[e] : o[e];
                }
                int d = remaining(np, no, t);
                if (d >= dist || d > bestD || (d == bestD && ch >= bestCh)) {
                    continue;
                }
                if (eval(np, no) < 0) {
                    continue;
                }
                bestD = d;
                bestCh = ch;
                bestOp = op.clone();
            }
            outD[0] = bestD;
            return bestOp;
        }

        /** Exact number of ticks travel(t) would take from the current state. */
        int dryRun(Target t) {
            int[] p = pos.clone(), outD = new int[1];
            boolean[] o = on.clone();
            int dist = remaining(p, o, t), used = 0;
            while (dist > 0) {
                int[] op = travelStep(p, o, t, dist, outD);
                if (op == null) {
                    return used + guaranteedLength(p, o, t);
                }
                for (int e = 1; e <= 6; ++e) {
                    if (op[e] == 1) {
                        p[e] = (p[e] + 1) % S;
                    }
                    else if (op[e] == 2) {
                        p[e] = (p[e] + S - 1) % S;
                    }
                    else if (op[e] == 3) {
                        o[e] = !o[e];
                    }
                }
                dist = outD[0];
                ++used;
            }
            return Math.max(used, 1);
        }

        int guaranteedLength(int[] p, boolean[] o, Target t) {
            boolean anyOn = false;
            for (int e = 1; e <= 6; ++e) {
                anyOn |= o[e];
            }
            int maxMove = 0;
            for (int e = 1; e <= 6; ++e) {
                if (!t.ton[e] && !t.park[e]) {
                    continue;
                }
                int d = ringDist(p[e], t.tpos[e]);
                if (anyOn && !o[e] && d > 0) {
                    --d;   // off emitters already move during the switch-off tick
                }
                maxMove = Math.max(maxMove, d);
            }
            return (anyOn ? 1 : 0) + maxMove + 1;
        }

        /** Moves to the target through safe states only; falls back to "all off, move, all on" when stuck. */
        boolean travel(Target t) {
            int dist = remaining(pos, on, t);
            if (dist == 0) {
                return apply(preposition(NOOP, fixedOf(t)));   // already there: one more tick re-creates its cells
            }
            int[] outD = new int[1];
            while (dist > 0) {
                int[] op = travelStep(pos, on, t, dist, outD);
                if (op == null) {
                    return guaranteed(t);
                }
                if (!apply(preposition(line(op), fixedOf(t)))) {
                    return false;
                }
                dist = outD[0];
            }
            return true;
        }

        boolean guaranteed(Target t) {
            boolean anyOn = false;
            for (int e = 1; e <= 6; ++e) {
                anyOn |= on[e];
            }
            if (anyOn) {
                // Everything off at once is always safe: no beam, no hit.
                int[] op = new int[7];
                for (int e = 1; e <= 6; ++e) {
                    op[e] = on[e] ? 3 : ((t.ton[e] || t.park[e]) ? stepToward(e, t.tpos[e]) : 0);
                }
                if (!apply(preposition(line(op), fixedOf(t)))) {
                    return false;
                }
            }
            while (true) {
                int[] op = new int[7];
                boolean moved = false;
                for (int e = 1; e <= 6; ++e) {
                    if ((t.ton[e] || t.park[e]) && pos[e] != t.tpos[e]) {
                        op[e] = stepToward(e, t.tpos[e]);
                        moved = true;
                    }
                }
                if (!moved) {
                    break;
                }
                if (!apply(preposition(line(op), fixedOf(t)))) {
                    return false;
                }
            }
            int[] op = new int[7];
            for (int e = 1; e <= 6; ++e) {
                if (t.ton[e]) {
                    op[e] = 3;
                }
            }
            return apply(preposition(line(op), fixedOf(t)));   // the target configuration, which is safe
        }

        int stepToward(int e, int target) {
            int d = signedDist(pos[e], target, S);
            return d == 0 ? 0 : d > 0 ? 1 : 2;
        }

        /** Runs the greedy for `passes` submissions; passEnd[i] = number of ticks when pass i+1 is complete. */
        boolean buildPasses(int passes) {
            for (int i = 0; i < passes; ++i) {
                Arrays.fill(covered, false);
                uncovered = pCount;
                if (build() == null) {
                    return false;
                }
                passEnd.add(ticks.size());
            }
            return true;
        }

        final List<Integer> passEnd = new ArrayList<>();

        /** Fresh machine that replays lines with the exact Level 5 rules. */
        final class Sim {
            final int[] p = startPos.clone();
            final boolean[] o = new boolean[7], m = new boolean[n];
            int pm, count;

            boolean step(char[] line) {
                for (int e = 1; e <= 6; ++e) {
                    char op = line[e - 1];
                    if (op == 'F') {
                        p[e] = (p[e] + 1) % S;
                    }
                    else if (op == 'B') {
                        p[e] = (p[e] + S - 1) % S;
                    }
                    else if (op == 'T') {
                        o[e] = !o[e];
                    }
                }
                int tn = spread(p, o);
                for (int k = 0; k < tn; ++k) {
                    int c = touched[k], h = hit[c];
                    hit[c] = 0;
                    if (h == 2 && !m[c]) {
                        m[c] = true;
                        if (inP[c]) {
                            ++pm;
                        }
                    } else if (h >= 3 && m[c]) {
                        m[c] = false;
                        if (inP[c]) {
                            --pm;
                        }
                    }
                }
                if (pm < pCount) {
                    return false;
                }
                for (int c : skirt) {
                    if (m[c]) {
                        return false;   // matter touching the pattern
                    }
                }
                for (int c : pCells) {
                    m[c] = false;
                }
                pm = 0;
                ++count;
                return true;
            }
        }

        static final char[] NOOP = {'-', '-', '-', '-', '-', '-'};

        /**
         * Replays the first `prefix` ticks, then walks into the window [a, b] and bounces inside it (no-op after each
         * submission, optionally reversing), and stops the moment the required count is reached.
         */
        List<char[]> playOut(int prefix, int a, int b, int required, boolean reverseOnSubmit) {
            Sim sim = new Sim();
            List<char[]> prog = new ArrayList<>();
            boolean lastSub = false;
            for (int i = 0; i < prefix; ++i) {
                prog.add(ticks.get(i));
                lastSub = sim.step(ticks.get(i));
                if (sim.count >= required) {
                    return prog;
                }
            }
            int cur = prefix - 1, dir = -1, guard = 0, limit = 4 * required * (b - a + 2) + 2 * prefix + 100;
            while (sim.count < required) {
                if (++guard > limit) {
                    return null;
                }
                char[] line;
                if (lastSub || (a == b && cur == a)) {
                    line = NOOP;
                    if (lastSub && reverseOnSubmit) {
                        dir = -dir;
                    }
                } else {
                    if (cur > b) {
                        dir = -1;
                    }
                    else if (cur < a) {
                        dir = 1;
                    }
                    else if (cur == a && dir < 0) {
                        dir = 1;
                    }
                    else if (cur == b && dir > 0) {
                        dir = -1;
                    }
                    if (dir > 0) {
                        ++cur;
                        line = ticks.get(cur);
                    } else {
                        line = reverse(ticks.get(cur));
                        --cur;
                    }
                }
                prog.add(line);
                lastSub = sim.step(line);
            }
            return prog;
        }

        /** Shortest program over: 1..maxPasses greedy passes, then bouncing in a short covering window. */
        List<char[]> bestProgram(int required) {
            List<char[]> best = null;
            for (int pass = 0; pass < passEnd.size(); ++pass) {
                int prefix = passEnd.get(pass);
                for (int[] w : coveringWindows(prefix)) {
                    for (boolean rev : new boolean[]{false, true}) {
                        List<char[]> prog = playOut(prefix, w[0], w[1], required, rev);
                        if (prog != null && (best == null || prog.size() < best.size())) {
                            best = prog;
                        }
                    }
                }
            }
            return best;
        }

        /** The covering suffix plus the shortest covering windows (latest first) among the first `prefix` ticks. */
        List<int[]> coveringWindows(int prefix) {
            List<int[]> out = new ArrayList<>();
            int[] cnt = new int[n];
            int have = 0, a = 0, bestLen = Integer.MAX_VALUE;
            List<int[]> all = new ArrayList<>();
            for (int b = 0; b < prefix; ++b) {
                for (int c : created.get(b)) {
                    if (cnt[c] == 0) {
                        ++have;
                    }
                    ++cnt[c];
                }
                while (have == pCount && a < b) {
                    boolean removable = true;
                    for (int c : created.get(a)) {
                        if (cnt[c] == 1) {
                            removable = false;
                            break;
                        }
                    }
                    if (!removable) {
                        break;
                    }
                    for (int c : created.get(a)) {
                        --cnt[c];
                    }
                    ++a;
                }
                if (have == pCount) {
                    all.add(new int[]{a, b});
                    bestLen = Math.min(bestLen, b - a);
                }
            }
            if (all.isEmpty()) {
                return out;
            }
            out.add(all.get(all.size() - 1));   // the window ending at the last tick (covering suffix)
            int taken = 0;
            for (int i = all.size() - 1; i >= 0 && taken < 3; --i) {
                int[] w = all.get(i);
                if (w[1] - w[0] == bestLen) {
                    out.add(w);
                    ++taken;
                }
            }
            return out;
        }

        // ------------------------------------------------ short repeating cycles

        static final int KEEP = 150, MAX_USEFUL = 7, MAX_DEPTH = 8;

        boolean evalConf(Conf cf, int[] pIndex) {
            Arrays.fill(cf.cov, 0);
            cf.count = 0;
            int tn = spread(cf.pos, cf.on);
            boolean safe = true;
            for (int k = 0; k < tn; ++k) {
                int c = touched[k], h = hit[c];
                hit[c] = 0;
                if (h == 2) {
                    if (!allow2[c]) {
                        safe = false;
                    } else if (inP[c]) {
                        cf.cov[pIndex[c] >> 6] |= 1L << (pIndex[c] & 63);
                        ++cf.count;
                    }
                } else if (h >= 3 && inP[c]) {
                    safe = false;
                }
            }
            return safe;
        }

        int unionCount(long[] a, long[] b) {
            int cnt = 0;
            for (int w = 0; w < a.length; ++w) {
                cnt += Long.bitCount(a[w] | b[w]);
            }
            return cnt;
        }

        /** Keeps the `keep` chains with the most covered cells (ties: shorter). */
        List<Chain> prune(List<Chain> chains, int keep) {
            chains.sort((x, y) -> x.count != y.count ? y.count - x.count : x.confs.size() - y.confs.size());
            return chains.size() > keep ? new ArrayList<>(chains.subList(0, keep)) : chains;
        }

        /** Extends every chain by one tick (per emitter: stay, step, toggle; an unused emitter may switch on anywhere useful). */
        List<Chain> expand(List<Chain> chains, int[][] useful, int[] pIndex, List<Chain> complete) {
            List<Chain> out = new ArrayList<>();
            int[][] optPos = new int[7][];
            boolean[][] optOn = new boolean[7][];
            Conf b = new Conf((pCount + 63) / 64);
            for (Chain ch : chains) {
                Conf a = ch.confs.get(ch.confs.size() - 1);
                int total = 1;
                for (int e = 1; e <= 6; ++e) {
                    if (a.parked[e] || a.on[e]) {
                        int p = a.pos[e];
                        optPos[e] = new int[]{p, (p + 1) % S, (p + S - 1) % S, p};
                        optOn[e] = new boolean[]{a.on[e], a.on[e], a.on[e], !a.on[e]};
                    } else {
                        int u = Math.min(3, useful[e].length);   // an unused emitter joins on one of its 3 best lines
                        optPos[e] = new int[u + 1];
                        optOn[e] = new boolean[u + 1];
                        optPos[e][0] = -1;
                        for (int i = 0; i < u; ++i) {
                            optPos[e][i + 1] = useful[e][i];
                            optOn[e][i + 1] = true;
                        }
                    }
                    total *= optPos[e].length;
                }
                for (int idx = 0; idx < total; ++idx) {
                    int rest = idx;
                    boolean same = true;
                    for (int e = 1; e <= 6; ++e) {
                        int k = rest % optPos[e].length;
                        rest /= optPos[e].length;
                        b.pos[e] = optPos[e][k];
                        b.on[e] = optOn[e][k];
                        b.parked[e] = b.pos[e] >= 0;
                        if (b.pos[e] != a.pos[e] || b.on[e] != a.on[e]) {
                            same = false;
                        }
                    }
                    if (same || !evalConf(b, pIndex)) {
                        continue;
                    }
                    int uc = unionCount(ch.cov, b.cov);
                    if (uc <= ch.count) {
                        continue;   // must add something new
                    }
                    Chain ext = new Chain(ch, b);
                    if (uc == pCount) {
                        complete.add(ext);
                    } else {
                        out.add(ext);
                    }
                }
                if (out.size() > 20 * KEEP) {
                    out = prune(out, KEEP);
                }
            }
            return prune(out, KEEP);
        }

        Target targetOf(Chain ch) {
            Conf first = ch.confs.get(0);
            Target t = new Target();
            for (int e = 1; e <= 6; ++e) {
                if (first.on[e]) {
                    t.ton[e] = true;
                    t.tpos[e] = first.pos[e];
                } else if (first.parked[e]) {
                    t.park[e] = true;
                    t.tpos[e] = first.pos[e];
                }
            }
            return t;
        }

        char[] stepLine(Conf x, Conf y) {
            char[] l = NOOP.clone();
            for (int e = 1; e <= 6; ++e) {
                if (x.on[e] != y.on[e]) {
                    l[e - 1] = 'T';
                } else if (x.pos[e] >= 0 && y.pos[e] >= 0 && x.pos[e] != y.pos[e]) {
                    l[e - 1] = signedDist(x.pos[e], y.pos[e], S) > 0 ? 'F' : 'B';
                }
            }
            return l;
        }

        /**
         * Program built from a short chain of safe configurations that together create the whole pattern:
         * travel to the first one, walk the chain, then bounce back and forth (a no-op at each turn) once per repetition.
         */
        /** For every emitter, the ring positions whose beam crosses the most pattern cells (at most MAX_USEFUL). */
        int[][] usefulLines() {
            int[][] useful = new int[7][];
            for (int e = 1; e <= 6; ++e) {
                Map<Integer, Integer> cnt = new HashMap<>();
                for (int c : pCells) {
                    cnt.merge(fire[e][c], 1, Integer::sum);
                }
                List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(cnt.entrySet());
                entries.sort((x, y) -> y.getValue() - x.getValue());
                int u = Math.min(MAX_USEFUL, entries.size());
                useful[e] = new int[u];
                for (int i = 0; i < u; ++i) {
                    useful[e][i] = entries.get(i).getKey();
                }
            }
            return useful;
        }

        int[] patternIndex() {
            int[] pIndex = new int[n];
            Arrays.fill(pIndex, -1);
            for (int i = 0; i < pCount; ++i) {
                pIndex[pCells.get(i)] = i;
            }
            return pIndex;
        }

        /** Every single safe configuration over the useful lines, best first; complete ones go to `complete`. */
        List<Chain> levelOne(int[][] useful, int[] pIndex, List<Chain> complete, int[] bestSingleOut) {
            long product = 1;
            for (int e = 1; e <= 6; ++e) {
                product *= useful[e].length + 1;
            }
            List<Chain> chains = new ArrayList<>();
            Conf cf = new Conf((pCount + 63) / 64);
            int bestSingle = 0;
            for (long idx = 0; idx < product; ++idx) {
                long rest = idx;
                for (int e = 1; e <= 6; ++e) {
                    int k = (int) (rest % (useful[e].length + 1));
                    rest /= useful[e].length + 1;
                    cf.on[e] = k > 0;
                    cf.parked[e] = k > 0;
                    cf.pos[e] = k > 0 ? useful[e][k - 1] : -1;
                }
                if (!evalConf(cf, pIndex) || cf.count == 0) {
                    continue;
                }
                bestSingle = Math.max(bestSingle, cf.count);
                Chain ch = new Chain(cf.copy());
                if (cf.count == pCount) {
                    complete.add(ch);
                } else {
                    chains.add(ch);
                }
            }
            bestSingleOut[0] = bestSingle;
            return prune(chains, KEEP);
        }

        /**
         * Best chain to start a build with: the one whose cells beat what the greedy would make in the same
         * number of ticks by the widest margin (about 1.8 cells per tick), or null.
         */
        Chain bestChain() {
            int[][] useful = usefulLines();
            int[] pIndex = patternIndex();
            List<Chain> complete = new ArrayList<>();
            List<Chain> chains = levelOne(useful, pIndex, complete, new int[1]);
            Chain best = !complete.isEmpty() ? complete.get(0) : (chains.isEmpty() ? null : chains.get(0));
            if (best == null) {
                return null;
            }
            for (int depth = 1; depth < MAX_DEPTH && complete.isEmpty() && !chains.isEmpty(); ++depth) {
                chains = expand(chains, useful, pIndex, complete);
                Chain top = !complete.isEmpty() ? complete.get(0) : (chains.isEmpty() ? null : chains.get(0));
                if (top == null) {
                    break;
                }
                if (top.count - 1.8 * top.confs.size() > best.count - 1.8 * best.confs.size()) {
                    best = top;
                }
            }
            return best;
        }

        List<char[]> cycleProgram(int required) {
            int[][] useful = usefulLines();
            int[] pIndex = patternIndex();
            List<Chain> complete = new ArrayList<>();
            int[] bestSingle = new int[1];
            List<Chain> chains = levelOne(useful, pIndex, complete, bestSingle);
            int depth = 1;
            while (complete.isEmpty() && depth < MAX_DEPTH && !chains.isEmpty() && (long) bestSingle[0] * (depth + 1) >= pCount) {
                chains = expand(chains, useful, pIndex, complete);
                ++depth;
            }
            if (complete.isEmpty()) {
                return null;
            }

            complete.sort(Comparator.comparingInt(ch -> travelCost(targetOf(ch))));
            Chain best = null;
            int bestTotal = Integer.MAX_VALUE;
            for (int i = 0; i < Math.min(60, complete.size()); ++i) {
                Chain ch = complete.get(i);
                int k = ch.confs.size();
                int total = dryRun(targetOf(ch)) + (k - 1) + (required - 1) * k;
                if (total < bestTotal) {
                    bestTotal = total;
                    best = ch;
                }
            }
            if (!travel(targetOf(best))) {
                return null;
            }
            List<char[]> program = new ArrayList<>(ticks), steps = new ArrayList<>();
            for (int i = 1; i < best.confs.size(); ++i) {
                steps.add(stepLine(best.confs.get(i - 1), best.confs.get(i)));
            }
            program.addAll(steps);
            for (int rep = 2; rep <= required; ++rep) {
                program.add(NOOP.clone());
                if (rep % 2 == 0) {
                    for (int i = steps.size() - 1; i >= 0; --i) {
                        program.add(reverse(steps.get(i)));
                    }
                } else {
                    program.addAll(steps);
                }
            }
            return program;
        }

        Chain prefix;   // optional chain of configurations to start every pass with

        boolean applyPrefix() {
            Target t = targetOf(prefix);
            int k = prefix.confs.size();
            if (prefix.count - 1.8 * (dryRun(t) + k - 1) <= 0) {
                return true;   // not worth it from here
            }
            if (!travel(t)) {
                return false;
            }
            for (int i = 1; i < k; ++i) {
                if (!apply(stepLine(prefix.confs.get(i - 1), prefix.confs.get(i)))) {
                    return false;
                }
            }
            return true;
        }

        List<char[]> build() {
            int limit = ticks.size() + 60 + 8 * pCount;
            double[] ratio = new double[1];
            if (prefix != null && !applyPrefix()) {
                return null;
            }
            while (uncovered > 0) {
                if (ticks.size() > limit) {
                    return null;
                }
                findImmediate();
                Target t = chooseTarget(bestImmediateGain, ratio);
                if (bestImmediate != null && (t == null || bestImmediateGain >= ratio[0] * bias)) {
                    if (!apply(preposition(bestImmediate, null))) {
                        return null;
                    }
                } else if (t != null) {
                    if (!travel(t)) {
                        return null;
                    }
                } else {
                    return null;
                }
            }
            return ticks;
        }

    }

    static final class Build {
        final List<char[]> ticks = new ArrayList<>();
        int firstCreate = -1;   // index of the first tick that creates a cell
        int[] pos;
        boolean[] on;
    }

    /** Builds the pattern once: line by line (lines of the gate's family), cell by cell along each line. */
    static Build buildOnce(Board b, List<int[]> pattern, int gate, int sweeper, boolean descending) {
        TreeMap<Integer, List<int[]>> lines = new TreeMap<>();
        for (int[] cell : pattern) {
            int key = lineCoord(gate, cell);
            lines.computeIfAbsent(descending ? -key : key, k -> new ArrayList<>()).add(cell);
        }
        int radius = b.radius;
        Build bu = new Build();
        bu.pos = new int[]{0, 5 * radius, 0, radius, 2 * radius, 3 * radius, 4 * radius};
        bu.on = new boolean[7];
        for (List<int[]> cells : lines.values()) {
            cells.sort(Comparator.comparingInt(cell -> alongCoord(gate, cell)));
            int gateTarget = b.firePos[gate][cells.get(0)[0]][cells.get(0)[1]];
            // Sweep the line in whichever direction is cheaper to reach from where the emitters stand now.
            int[] first = cells.get(0), last = cells.get(cells.size() - 1);
            int costFirst = transitionLength(bu, b, gate, gateTarget, sweeper, b.firePos[sweeper][first[0]][first[1]]);
            int costLast = transitionLength(bu, b, gate, gateTarget, sweeper, b.firePos[sweeper][last[0]][last[1]]);
            if (costLast < costFirst) {
                Collections.reverse(cells);
            }
            for (int[] cell : cells) {
                transition(bu, b, gate, gateTarget, sweeper, b.firePos[sweeper][cell[0]][cell[1]]);
            }
        }
        return bu;
    }

    static int lineCoord(int e, int[] cell) {
        int f = FAMILY[e];
        return f == 1 ? cell[0] : f == 2 ? cell[1] - cell[0] : cell[0] + cell[1];
    }

    static int alongCoord(int e, int[] cell) {
        return FAMILY[e] == 1 ? cell[1] : cell[0];
    }

    static int signedDist(int from, int to, int size) {
        int d = Math.floorMod(to - from, size);
        return d > size / 2 ? d - size : d;
    }

    static int transitionLength(Build bu, Board b, int g, int tg, int s, int ts) {
        return transitionLength(bu.on[g], bu.on[s], signedDist(bu.pos[g], tg, b.size), signedDist(bu.pos[s], ts, b.size));
    }

    /** Ticks needed to bring the gate to tg and the sweeper to ts with both on, creating nothing but the target cell. */
    static int transitionLength(boolean gOn, boolean sOn, int dg, int ds) {
        int ag = Math.abs(dg), as = Math.abs(ds);
        if (!gOn && !sOn) {
            return Math.max(ag, as) + 1;
        }
        if (gOn != sOn) {
            int ax = gOn ? as : ag, ay = gOn ? ag : as;   // x is off and toggles on at the end, y stays on
            return Math.max(ax + 1, Math.max(ay, 1));
        }
        if ((ag == 0 && as == 1) || (as == 0 && ag == 1)) {
            return 1;   // one single step straight onto the target
        }
        return Math.min(Math.max(ag + 2, as), Math.max(as + 2, ag));
    }

    /**
     * Appends the ticks that move the gate to tg and the sweeper to ts and leave both switched on.
     * At every tick but the last at least one of the two is off, so the only cell created is the target.
     */
    static void transition(Build bu, Board b, int g, int tg, int s, int ts) {
        int dg = signedDist(bu.pos[g], tg, b.size), ds = signedDist(bu.pos[s], ts, b.size);
        int ag = Math.abs(dg), as = Math.abs(ds);
        boolean gOn = bu.on[g], sOn = bu.on[s];
        int m = transitionLength(gOn, sOn, dg, ds);
        char[][] t = new char[m][6];
        for (char[] line : t) {
            Arrays.fill(line, '-');
        }
        char gMove = dg > 0 ? 'F' : 'B', sMove = ds > 0 ? 'F' : 'B';

        if (!gOn && !sOn) {
            for (int i = 0; i < ag; ++i) {
                t[i][g - 1] = gMove;
            }
            for (int i = 0; i < as; ++i) {
                t[i][s - 1] = sMove;
            }
            t[m - 1][g - 1] = 'T';
            t[m - 1][s - 1] = 'T';
        } else if (gOn != sOn) {
            boolean xIsGate = !gOn;   // the one that is off travels and toggles on at the end; the other travels while it is off
            int x = xIsGate ? g : s, y = xIsGate ? s : g;
            int ax = xIsGate ? ag : as, ay = xIsGate ? as : ag;
            char xMove = xIsGate ? gMove : sMove, yMove = xIsGate ? sMove : gMove;
            for (int i = 0; i < ax; ++i) {
                t[i][x - 1] = xMove;
            }
            for (int i = 0; i < ay; ++i) {
                t[i][y - 1] = yMove;
            }
            t[m - 1][x - 1] = 'T';
        } else if (m == 1) {
            if (ag == 1) {
                t[0][g - 1] = gMove;
            } else {
                t[0][s - 1] = sMove;
            }
        } else {
            // Both on: x switches off first, travels, and switches back on at the arrival tick; y travels meanwhile.
            boolean xIsGate = Math.max(ag + 2, as) <= Math.max(as + 2, ag);
            int x = xIsGate ? g : s, y = xIsGate ? s : g;
            int ax = xIsGate ? ag : as, ay = xIsGate ? as : ag;
            char xMove = xIsGate ? gMove : sMove, yMove = xIsGate ? sMove : gMove;
            t[0][x - 1] = 'T';
            for (int i = 0; i < ax; ++i) {
                t[i + 1][x - 1] = xMove;
            }
            for (int i = 0; i < ay; ++i) {
                t[i][y - 1] = yMove;
            }
            t[m - 1][x - 1] = 'T';
        }

        Collections.addAll(bu.ticks, t);
        bu.pos[g] = tg;
        bu.pos[s] = ts;
        bu.on[g] = true;
        bu.on[s] = true;
        if (bu.firstCreate < 0) {
            bu.firstCreate = bu.ticks.size() - 1;
        }
    }

    /**
     * The build visits states S[j..m-1] (j = first creating tick). Walking them backwards (F<->B) recreates
     * every cell and completes the pattern again at S[j]; walking forwards again completes it at S[m-1].
     * A no-op first re-creates the cell of the state we are standing in, so each repetition costs m-j ticks.
     */
    static List<char[]> repeat(Build bu, int required) {
        List<char[]> program = new ArrayList<>(bu.ticks);
        int m = bu.ticks.size(), j = bu.firstCreate;
        for (int rep = 2; rep <= required; ++rep) {
            char[] noop = new char[6];
            Arrays.fill(noop, '-');
            program.add(noop);
            if (rep % 2 == 0) {
                for (int i = m - 1; i > j; --i) {
                    program.add(reverse(bu.ticks.get(i)));
                }
            } else {
                for (int i = j + 1; i < m; ++i) {
                    program.add(bu.ticks.get(i));
                }
            }
        }
        return program;
    }

    static char[] reverse(char[] t) {
        char[] r = t.clone();
        for (int i = 0; i < 6; ++i) {
            if (r[i] == 'F') {
                r[i] = 'B';
            } else if (r[i] == 'B') {
                r[i] = 'F';
            }
        }
        return r;
    }

    // ---------------------------------------------------------------- Level 5 simulator (the checker)

    static int simulate(Board b, List<int[]> pattern, boolean[][] isPattern, List<char[]> program) {
        int radius = b.radius;
        char[][] board = new char[b.empty.length][];
        for (int r = 0; r < board.length; ++r) {
            board[r] = b.empty[r].clone();
        }
        int[] pos = {0, 5 * radius, 0, radius, 2 * radius, 3 * radius, 4 * radius};
        boolean[] active = new boolean[7];
        int[][] hits = new int[board.length][];
        for (int r = 0; r < board.length; ++r) {
            hits[r] = new int[board[r].length];
        }
        List<int[]> touched = new ArrayList<>();
        int count = 0, patternM = 0;

        for (char[] ins : program) {
            for (int e = 1; e <= 6; ++e) {
                char op = ins[e - 1];
                if (op == 'F') {
                    pos[e] = (pos[e] + 1) % b.size;
                } else if (op == 'B') {
                    pos[e] = (pos[e] + b.size - 1) % b.size;
                } else if (op == 'T') {
                    active[e] = !active[e];
                }
            }
            for (int e = 1; e <= 6; ++e) {
                if (!active[e]) {
                    continue;
                }
                int[] cells = b.beam[e][pos[e]];
                for (int k = 0; k < cells.length; k += 2) {
                    int r = cells[k], c = cells[k + 1];
                    if (hits[r][c] == 0) {
                        touched.add(new int[]{r, c});
                    }
                    ++hits[r][c];
                }
            }
            for (int[] cell : touched) {
                int r = cell[0], c = cell[1];
                if (hits[r][c] == 2 && board[r][c] != 'M') {
                    board[r][c] = 'M';
                    if (isPattern[r][c]) {
                        ++patternM;
                    }
                } else if (hits[r][c] >= 3 && board[r][c] == 'M') {
                    board[r][c] = 'O';
                    if (isPattern[r][c]) {
                        --patternM;
                    }
                }
                hits[r][c] = 0;
            }
            touched.clear();
            if (patternM == pattern.size() && nothingTouching(board, pattern, isPattern)) {
                ++count;
                for (int[] cell : pattern) {
                    board[cell[0]][cell[1]] = 'O';
                }
                patternM = 0;
            }
        }
        return count;
    }

    static boolean nothingTouching(char[][] board, List<int[]> pattern, boolean[][] isPattern) {
        for (int[] cell : pattern) {
            for (int k = 0; k < 6; ++k) {
                int r = cell[0] + NR[k], c = cell[1] + NC[k];
                if (r >= 0 && r < board.length && c >= 0 && c < board[r].length
                        && board[r][c] == 'M' && !isPattern[r][c]) {
                    return false;
                }
            }
        }
        return true;
    }

    // ---------------------------------------------------------------- board geometry

    /** Empty board, border ring and, for every emitter and ring position, the interior cells its beam crosses. */
    static final class Board {
        final int radius, size;
        final char[][] empty;
        final int[][] ring;
        final int[][][] beam;      // [emitter][ring index] -> {r, c, r, c, ...}
        final int[][][] firePos;   // [emitter][r][c] -> the ring index from which the emitter's beam crosses (r, c)

        Board(int radius) {
            this.radius = radius;
            empty = emptyBoard(radius);
            ring = border(radius);
            size = ring.length;
            beam = new int[7][size][];
            firePos = new int[7][empty.length][];
            for (int e = 1; e <= 6; ++e) {
                for (int r = 0; r < empty.length; ++r) {
                    firePos[e][r] = new int[empty[r].length];
                    Arrays.fill(firePos[e][r], -1);
                }
                for (int p = 0; p < size; ++p) {
                    List<Integer> cells = new ArrayList<>();
                    int r = ring[p][0] + DR[e], c = ring[p][1] + DC[e];
                    while (r >= 0 && r < empty.length && c >= 0 && c < empty[r].length) {
                        if (empty[r][c] == 'O') {
                            cells.add(r);
                            cells.add(c);
                            firePos[e][r][c] = p;
                        }
                        r += DR[e];
                        c += DC[e];
                    }
                    beam[e][p] = cells.stream().mapToInt(Integer::intValue).toArray();
                }
            }
        }
    }

    static char[][] emptyBoard(int radius) {
        char[][] board = new char[2 * radius + 1][];
        for (int row = 0; row <= 2 * radius; ++row) {
            int d = Math.abs(row - radius);
            int symbols = 2 * radius + 1 - d;
            char[] line = new char[4 * radius + 1];
            Arrays.fill(line, '-');
            for (int k = 0; k < symbols; ++k) {
                boolean border = d == radius || k == 0 || k == symbols - 1;
                line[d + 2 * k] = border ? '*' : 'O';
            }
            board[row] = line;
        }
        return board;
    }

    // Border cells clockwise from the top-left corner; corners: 0 (emitter 2), R (3), 2R (4), 3R (5), 4R (6), 5R (1).
    static int[][] border(int radius) {
        List<int[]> ring = new ArrayList<>();
        for (int k = 0; k <= radius; ++k) {
            ring.add(new int[]{0, radius + 2 * k});
        }
        for (int r = 1; r <= 2 * radius; ++r) {
            ring.add(new int[]{r, r <= radius ? 3 * radius + r : 5 * radius - r});
        }
        for (int k = 1; k <= radius; ++k) {
            ring.add(new int[]{2 * radius, 3 * radius - 2 * k});
        }
        for (int r = 2 * radius - 1; r >= 1; --r) {
            ring.add(new int[]{r, Math.abs(r - radius)});
        }
        return ring.toArray(new int[0][]);
    }
}
