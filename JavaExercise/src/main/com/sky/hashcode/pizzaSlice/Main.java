package com.sky.hashcode.pizzaSlice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private int L, H;
    private Solution solution;
    private boolean bestSolutionFound;

    public static void main(String[] args) throws FileNotFoundException {
        new Main().start(new File("JavaExercise/src/resources/medium.in"));
    }

    private void start(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int rowCount = scanner.nextInt();
        int colCount = scanner.nextInt();
        L = scanner.nextInt();
        H = scanner.nextInt();
        scanner.nextLine();
        Pizza pizza = new Pizza(rowCount, colCount);
        for (int i = 0; i < rowCount; i++) {
            String data = scanner.nextLine();
            for(int j = 0; j < colCount; j++) {
                pizza.setData(i, j, data.charAt(j) == 'T');
            }
        }
        scanner.close();
        solution = null;
        bestSolutionFound = false;
        process(pizza, new LinkedList<>(), 0, null, null);
        System.out.println("Best Solution  (" + solution.point + ") : \n" + solution);
    }

    private void process(Pizza pizza, List<Slice> cutSlices, int from, Slice growingSlice, List<Direction> directions) {
        if (bestSolutionFound) return;
        if (growingSlice != null) {
            if (growingSlice.isValid(L, H)) {
                cutSlices.add(growingSlice);
                process(pizza, cutSlices, from + 1, null, null);
                cutSlices.remove(cutSlices.size() - 1);
            }
            if (growingSlice.size() < H) {
                Direction[] dirs = (directions.isEmpty() || directions.get(directions.size() - 1) == Direction.RIGHT) ? Direction.values() : new Direction[] {Direction.DOWN};
                for (Direction dir : dirs) {
                    if (canExtend(dir, growingSlice, pizza, cutSlices)) {
                        growingSlice.extend(dir, pizza);
                        directions.add(dir);

                        process(pizza, cutSlices, from, growingSlice, directions);

                        growingSlice.undoExtend(directions.remove(directions.size() - 1), pizza);
                    }
                }
            }
        } else {
            int index = pizza.findFirstAvailableCellIndex(cutSlices, from);

            if (index < 0 || from >= pizza.size() - 2 * L) {
                Solution newSolution = new Solution(cutSlices);
                solution = newSolution.compareTo(solution) > 0 ? newSolution : solution;
                //System.out.println("Solution Found (" + newSolution.point + ") : \n" + newSolution);
                bestSolutionFound = solution.point == pizza.size();
                return;
            }
            int row = index / pizza.getColumnCount();
            int col = index % pizza.getColumnCount();
            Slice newSlice = new Slice(row, col, row, col);
            newSlice.tomatoCount = pizza.isTomato(row, col) ? 1 : 0;

            process(pizza, cutSlices, index, newSlice, new LinkedList<>());
            process(pizza, cutSlices, index + 1, null, null);
        }
    }

    private boolean canExtend(Direction dir, Slice slice, Pizza pizza, List<Slice> cuttedSlices) {
        if (slice.size() >= H) {
            return false;
        }
        switch (dir) {
            case DOWN:
                return slice.row2 + 1 < pizza.getRowCount() &&
                        pizza.areCellsAllAvailable(slice.row1, slice.col1, slice.row2 + 1, slice.col2, cuttedSlices);
            default:
                return slice.col2 + 1 < pizza.getColumnCount() &&
                        pizza.areCellsAllAvailable(slice.row1, slice.col1, slice.row2, slice.col2 + 1, cuttedSlices);
        }
    }
}