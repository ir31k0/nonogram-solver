package de.ir31k0.nonogramsolver.data;

import de.ir31k0.nonogramsolver.exception.IllegalFieldException;

import java.util.List;

/**
 * Representation of a nonogram.
 */
public class Nonogram {
    /** The width of nonogram */
    private final int width;

    /** The height of nonogram */
    private final int height;

    /** All horizontal numbers of nonogram */
    private final List<List<Integer>> horizontalNumbers;

    /** All vertical numbers of nonogram */
    private final List<List<Integer>> verticalNumbers;

    /** The board of the nonogram */
    private final Field[][] board;

    /**
     * Calls {@link Nonogram(int, int, List, List)} and passes the size at width and height through.
     *
     * @param size the size of the board (width and height)
     * @param horizontalNumbers collection of horizontal numbers
     * @param verticalNumbers collection of vertical numbers
     */
    public Nonogram(int size, List<List<Integer>> horizontalNumbers, List<List<Integer>> verticalNumbers) {
        this(size, size, horizontalNumbers, verticalNumbers);
    }

    /**
     * Creates a new nonogram with a board where all fields are unknown.
     *
     * @param width the width of the board
     * @param height the height of the board
     * @param horizontalNumbers collection of horizontal numbers
     * @param verticalNumbers collection of vertical numbers
     */
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

    /**
     * Creates a new nonogram with the given board.
     *
     * @param board the board
     * @param horizontalNumbers collection of horizontal numbers
     * @param verticalNumbers collection of vertical numbers
     */
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

    /**
     * Gets the field type of specific coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the type of field
     */
    public Field getField(int x, int y) {
        return board[x][y];
    }

    /**
     * Gets the list of horizontal line numbers by given row index.
     *
     * @param y the index of row
     * @return the line numbers of row
     */
    public List<Integer> getHorizontalLineNumbers(int y) {
        return horizontalNumbers.get(y);
    }

    /**
     * Gets the list of horizontal line numbers by given column index.
     *
     * @param x the index of column
     * @return the line numbers of column
     */
    public List<Integer> getVerticalLineNumbers(int x) {
        return verticalNumbers.get(x);
    }

    /**
     * Gets a copy of horizontal line.
     *
     * @param y the index of row
     * @return the line
     */
    public Field[] getHorizontalLine(int y) {
        Field[] horizontalLine = new Field[width];
        for (int i = 0; i < width; i++) {
            horizontalLine[i] = board[i][y];
        }
        return horizontalLine;
    }

    /**
     * Gets a copy of vertical line.
     *
     * @param x the index of column
     * @return the line
     */
    public Field[] getVerticalLine(int x) {
        Field[] verticalLine = new Field[height];
        for (int i = 0; i < height; i++) {
            verticalLine[i] = board[x][i];
        }
        return verticalLine;
    }

    /**
     * Updates a horizontal line.
     *
     * @param y the index of the row
     * @param horizontalLine the horizontal line
     * @return true when the line has been changed, otherwise false
     * @throws IllegalFieldException if a field conflict occurs
     */
    public boolean updateHorizontalLine(int y, Field[] horizontalLine) throws IllegalFieldException {
        boolean change = false;
        for (int x = 0; x < width; x++) {
            if (updateLine(x, y, x, horizontalLine)) {
                change = true;
            }
        }
        return change;
    }

    /**
     * Updates a horizontal line.
     *
     * @param x the index of the row
     * @param verticalLine the vertical line
     * @return true when the line has been changed, otherwise false
     * @throws IllegalFieldException if a field conflict occurs
     */
    public boolean updateVerticalLine(int x, Field[] verticalLine) throws IllegalFieldException {
        boolean change = false;
        for (int y = 0; y < height; y++) {
            if (updateLine(x, y, y, verticalLine)) {
                change = true;
            }
        }
        return change;
    }

    /**
     * Updates the field by given coordinates.
     *
     * @param x the index of row
     * @param y the index of column
     * @param lineIndex the index of the field from the line to be updated with
     * @param line the line with updated fields
     * @return true when the line has been changed, otherwise false
     * @throws IllegalFieldException if a field conflict occurs
     */
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
