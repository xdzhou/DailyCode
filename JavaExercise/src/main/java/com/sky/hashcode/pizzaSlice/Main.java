package com.sky.hashcode.pizzaSlice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Comparator<Solution> SOLUTION_COMPARATOR = (s1, s2) -> (s1.point - s2.point);
    private int L, H;
    private List<Pair> dimens;
    private Solution solution;
    private boolean bestSolutionFound;
    private int callCount;
    private int potentialMaxPoint;

    public static void main(String[] args) throws FileNotFoundException {
        Main main = new Main();
        //main.start(new File("src/resources/pizza/medium.in"));
        //main.start(new File("src/resources/pizza/example.in"));
        main.start(new File("src/main/resources/hashcode/pizzaSlice/small.in"));
    }

    private void start(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file, "UTF-8");
        int rowCount = scanner.nextInt();
        int colCount = scanner.nextInt();
        L = scanner.nextInt();
        H = scanner.nextInt();
        scanner.nextLine();
        Pizza pizza = new Pizza(rowCount, colCount);
        int tomatoCount = 0;
        for (int i = 0; i < rowCount; i++) {
            String data = scanner.nextLine();
            for (int j = 0; j < colCount; j++) {
                boolean isTomato = data.charAt(j) == 'T';
                pizza.setData(i, j, isTomato);
                if (isTomato) {
                    tomatoCount++;
                }
            }
        }
        potentialMaxPoint = Math.min(pizza.size(), Math.min(tomatoCount / L, (pizza.size() - tomatoCount) / L) * H);
        scanner.close();
        solution = null;
        bestSolutionFound = false;
        dimens = getDimenParis(2 * L);
        callCount = 0;
        process(pizza, new LinkedList<>(), 0, null, null);
        System.out.println("Call count : " + callCount);
        System.out.println("Best Solution  (" + solution.point + ") : \n" + solution);
    }

    private void process(Pizza pizza, List<Slice> cutSlices, int from, Slice changingSlice, List<Direction> directions) {
        callCount++;
        if (bestSolutionFound) {
            return;
        }
        if (changingSlice != null) {
            if (changingSlice.isValid(L, H)) {
                cutSlices.add(changingSlice);
                process(pizza, cutSlices, from + 1, null, null);
                cutSlices.remove(cutSlices.size() - 1);
            }
            if (changingSlice.size() < H && !changingSlice.neverCut(L, H)) {
                Direction[] dirs = (directions.isEmpty() || directions.get(directions.size() - 1) == Direction.RIGHT) ? Direction.values() : new Direction[]{Direction.DOWN};
                for (Direction dir : dirs) {
                    if (canExtend(dir, changingSlice, pizza, cutSlices)) {
                        changingSlice.extend(dir, pizza);
                        directions.add(dir);

                        process(pizza, cutSlices, from, changingSlice, directions);

                        changingSlice.undoExtend(directions.remove(directions.size() - 1), pizza);
                    }
                }
            }
        } else {
            int index = pizza.findFirstAvailableCellIndex(cutSlices, from);

            if (index < 0 || from >= pizza.size() - 2 * L) {
                Solution newSolution = new Solution(cutSlices);
                solution = SOLUTION_COMPARATOR.compare(newSolution, solution) > 0 ? newSolution : solution;
                //System.out.println("Solution Found (" + newSolution.point + ") : \n" + newSolution);
                bestSolutionFound = solution.point >= potentialMaxPoint;
                return;
            }

            boolean neverBeBest = solution != null && cutSlices.size() > 1 && neverBeBest(index, pizza, cutSlices);
            if (!neverBeBest) {
                int row = index / pizza.getColumnCount();
                int col = index % pizza.getColumnCount();

                for (int i = 0; i < dimens.size(); i++) {
                    Pair pair = dimens.get(i);
                    Slice slice = generateSlice(row, col, pair.x, pair.y, pizza, cutSlices);
                    if (slice != null) {
                        process(pizza, cutSlices, index, slice, new LinkedList<>());
                    }
                    if (pair.x != pair.y) {
                        slice = generateSlice(row, col, pair.y, pair.x, pizza, cutSlices);
                        if (slice != null) {
                            process(pizza, cutSlices, index, slice, new LinkedList<>());
                        }
                    }
                }
                //process(pizza, cutSlices, index + 1, null, null);
            }
        }
    }

    private boolean neverBeBest(int index, Pizza pizza, List<Slice> cutSlices) {
        int sliceSize = 0;
        for (Slice slice : cutSlices) {
            sliceSize += slice.size();
        }
        boolean b = pizza.size() - index + sliceSize < solution.point;
        if (!b) {
            b = pizza.getRestAvailableCellCount(index, cutSlices) + sliceSize < solution.point;
        }
        return b;
    }

    private Slice generateSlice(int fromRow, int fromCol, int rowCount, int colCount, Pizza pizza, List<Slice> cutSlice) {
        if (fromRow + rowCount - 1 >= pizza.getRowCount() || fromCol + colCount - 1 >= pizza.getColumnCount()) {
            return null;
        }
        boolean b = pizza.areCellsAllAvailable(fromRow, fromCol, fromRow + rowCount - 1, fromCol + colCount - 1, cutSlice);
        if (b) {
            Slice s = new Slice(fromRow, fromCol, fromRow + rowCount - 1, fromCol + colCount - 1);
            s.tomatoCount = pizza.getTomatoCount(s);
            return s;
        } else {
            return null;
        }
    }

    private List<Pair> getDimenParis(int size) {
        List<Pair> list = new ArrayList<>();
        list.add(new Pair(1, size));
        for (int i = 2; i * i <= size; i++) {
            if (size % i == 0) {
                int y = size / i;
                list.add(new Pair(i, y));
            }
        }
        return list;
    }

    private boolean canExtend(Direction dir, Slice slice, Pizza pizza, List<Slice> cutSlices) {
        if (slice.size() >= H) {
            return false;
        }
        switch (dir) {
            case DOWN:
                return slice.row2 + 1 < pizza.getRowCount() &&
                    slice.size() + (slice.col2 - slice.col1 + 1) <= H &&
                    pizza.areCellsAllAvailable(slice.row1, slice.col1, slice.row2 + 1, slice.col2, cutSlices);
            default:
                return slice.col2 + 1 < pizza.getColumnCount() &&
                    slice.size() + (slice.row2 - slice.row1 + 1) <= H &&
                    pizza.areCellsAllAvailable(slice.row1, slice.col1, slice.row2, slice.col2 + 1, cutSlices);
        }
    }

    private static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}