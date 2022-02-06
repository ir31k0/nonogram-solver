package de.ir31k0.nonogramsolver.api.data;

import de.ir31k0.nonogramsolver.api.exception.IllegalFieldException;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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
    public Nonogram(final int size, final List<List<Integer>> horizontalNumbers, final List<List<Integer>> verticalNumbers) {
        this(size, size, horizontalNumbers, verticalNumbers);
    }

    /**
     * Calls {@link Nonogram(int, int, Field[][], List, List)} and passes a blank board through.
     *
     * @param width the width of the board
     * @param height the height of the board
     * @param horizontalNumbers collection of horizontal numbers
     * @param verticalNumbers collection of vertical numbers
     */
    public Nonogram(final int width, final int height, final List<List<Integer>> horizontalNumbers, final List<List<Integer>> verticalNumbers) {
        this(width, height, createABlankBoard(width, height), horizontalNumbers, verticalNumbers);
    }

    /**
     * Calls {@link Nonogram(int, int, Field[][], List, List)} and passes the size of the board at width and height through.
     *
     * @param board the board
     * @param horizontalNumbers collection of horizontal numbers
     * @param verticalNumbers collection of vertical numbers
     */
    public Nonogram(final Field[][] board, final List<List<Integer>> horizontalNumbers, final List<List<Integer>> verticalNumbers) {
        this(board.length, board[0].length, board, horizontalNumbers, verticalNumbers);
    }

    /**
     * Creates a new nonogram with the given board.
     *
     * @param board the board
     * @param horizontalNumbers collection of horizontal numbers
     * @param verticalNumbers collection of vertical numbers
     */
    public Nonogram(final int width, final int height, final Field[][] board, final List<List<Integer>> horizontalNumbers, final List<List<Integer>> verticalNumbers) {
        this.board = board;
        this.width = width;
        this.height = height;
        this.horizontalNumbers = horizontalNumbers;
        this.verticalNumbers = verticalNumbers;
    }

    /**
     * Creates a blank board where all fields are {@link Field#UNKNOWN}.
     *
     * @param width the width of board
     * @param height the height of board
     * @return created board
     */
    private static Field[][] createABlankBoard(final int width, final int height) {
        final Field[][] board = new Field[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                board[x][y] = Field.UNKNOWN;
            }
        }
        return board;
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
        final Field[] horizontalLine = new Field[width];
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
        final Field[] verticalLine = new Field[height];
        System.arraycopy(board[x], 0, verticalLine, 0, height);
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
    public boolean updateHorizontalLine(final int y, final Field[] horizontalLine) throws IllegalFieldException {
        boolean change = false;
        for (int x = 0; x < width; x++) {
            if (updateField(x, y, horizontalLine[x])) {
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
    public boolean updateVerticalLine(final int x, final Field[] verticalLine) throws IllegalFieldException {
        boolean change = false;
        for (int y = 0; y < height; y++) {
            if (updateField(x, y, verticalLine[y])) {
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
     * @param newField the field to be updated with
     * @return true when the line has been changed, otherwise false
     * @throws IllegalFieldException if a field conflict occurs
     */
    private boolean updateField(final int x, final int y, final Field newField) throws IllegalFieldException {
        final Field boardField = board[x][y];
        if (!boardField.equals(newField)) {
            if (Field.UNKNOWN.equals(boardField)) {
                board[x][y] = newField;
                return true;
            } else {
                throw new IllegalFieldException("Cannot update " + boardField.name() + " field with " + newField.name());
            }
        }
        return false;
    }

    public boolean isSolved() {
        for (final Field[] line : board) {
            if (!isLineComplete(line)) return false;
        }
        return true;
    }

    private static boolean isLineComplete(final Field[] line) {
        for (final Field field : line) {
            if (Field.UNKNOWN == field) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        final AtomicInteger horizontalLength = new AtomicInteger();
        final List<String> horizontalNumbersAsStrings = horizontalNumbers.stream()
                .map(line -> line.stream().map(Object::toString).collect(Collectors.joining(" ")))
                .peek(row -> horizontalLength.set(Math.max(horizontalLength.get(), row.length()))).toList();
        final int maxVerticalNumbers = verticalNumbers.stream().max(Comparator.comparing(List::size)).map(List::size).orElse(0);
        final String template = (horizontalLength.get() > 0) ? ("%" + horizontalLength.get() + "s ") : " ";
        final String initialVerticalNumberSpaces = String.format(template, "");
        final StringBuilder builder = new StringBuilder("Nonogram [")
                .append(width)
                .append(':')
                .append(height)
                .append("]:")
                .append('\n');

        for (int current = maxVerticalNumbers; 0 < current; --current) {
            builder.append(initialVerticalNumberSpaces);
            for (final List<Integer> numbers :verticalNumbers) {
                if (numbers.size() >= current) {
                    builder.append(numbers.get(numbers.size() - current));
                } else {
                    builder.append(' ');
                }
                builder.append(' ');
            }
            builder.append('\n');
        }

        final String[] line = new String[width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                line[x] = getField(x, y).getCharacter();
            }
            builder.append(String.format(template, horizontalNumbersAsStrings.get(y)));
            builder.append(String.join(" ", line));
            builder.append("\n");
        }
        return builder.toString();
    }
}
