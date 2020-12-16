package de.ir31k0.nonogramsolver.data;

import de.ir31k0.nonogramsolver.exception.IllegalFieldException;

import java.util.List;

public class Nonogram {
    private final int width;
    private final int height;

    private final List<List<Integer>> horizontalNumbers;
    private final List<List<Integer>> verticalNumbers;

    private final Field[][] board;

    public Nonogram(int size, List<List<Integer>> horizontalNumbers, List<List<Integer>> verticalNumbers) {
        this(size, size, horizontalNumbers, verticalNumbers);
    }

    public Nonogram(int width, int height, List<List<Integer>> horizontalNumbers, List<List<Integer>> verticalNumbers) {
        this.width = width;
        this.height = height;
        board = new Field[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                board[x][y] = Field.UNKNOWN;
            }
        }
        this.horizontalNumbers = horizontalNumbers;
        this.verticalNumbers = verticalNumbers;
    }

    public Nonogram(Field[][] board, List<List<Integer>> horizontalNumbers, List<List<Integer>> verticalNumbers) {
        this.board = board;
        width = board.length;
        height = board[0].length;
        this.horizontalNumbers = horizontalNumbers;
        this.verticalNumbers = verticalNumbers;
    }

    public Field[][] getBoard() {
        return board;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Field getField(int x, int y) {
        return board[x][y];
    }

    public List<Integer> getHorizontalLineNumbers(int y) {
        return horizontalNumbers.get(y);
    }

    public List<Integer> getVerticalLineNumbers(int x) {
        return verticalNumbers.get(x);
    }

    public Field[] getHorizontalLine(int y) {
        Field[] horizontalLine = new Field[width];
        for (int i = 0; i < width; i++) {
            horizontalLine[i] = board[i][y];
        }
        return horizontalLine;
    }

    public Field[] getVerticalLine(int x) {
        Field[] verticalLine = new Field[height];
        for (int i = 0; i < height; i++) {
            verticalLine[i] = board[x][i];
        }
        return verticalLine;
    }

    public boolean updateHorizontalLine(int y, Field[] horizontalLine) throws IllegalFieldException {
        boolean change = false;
        for (int x = 0; x < width; x++) {
            if (updateLine(x, y, x, horizontalLine)) {
                change = true;
            }
        }
        return change;
    }

    public boolean updateVerticalLine(int x, Field[] verticalLine) throws IllegalFieldException {
        boolean change = false;
        for (int y = 0; y < height; y++) {
            if (updateLine(x, y, y, verticalLine)) {
                change = true;
            }
        }
        return change;
    }

    private boolean updateLine(int x, int y, int lineIndex,Field[] line) throws IllegalFieldException {
        boolean change = false;
        Field boardField = board[x][y];
        Field newField = line[lineIndex];
        if (!boardField.equals(newField)) {
            if (Field.UNKNOWN.equals(boardField)) {
                board[x][y] = newField;
                change = true;
            } else {
                throw new IllegalFieldException("Can not update");
            }
        }
        return change;
    }
}
