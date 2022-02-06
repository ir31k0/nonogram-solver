package de.ir31k0.nonogramsolver.api.util;

import de.ir31k0.nonogramsolver.api.data.Field;
import de.ir31k0.nonogramsolver.api.data.Nonogram;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Some util functionalities.
 */
public class SolverUtil {
    private static final String PATTERN_DELIMITER = " ";

    /**
     * Creates a nonogram instance by the given .nono file.
     *
     * @param path .nono file path
     * @return the nonogram instance
     * @throws IOException on IO errors when trying to read the file
     */
    public static Nonogram fromPath(Path path) throws IOException {
        List<String> lines = Files.lines(path).collect(Collectors.toCollection(ArrayList::new));

        int width = 0;
        int height = 0;

        List<List<Integer>> horizontalNumbers = new ArrayList<>();
        List<List<Integer>> verticalNumbers = new ArrayList<>();

        String mode = "";
        for (String line : lines) {
            String[] s = line.split(" ");
            if (s[0].equals("width")) {
                width = Integer.parseInt(s[1]);
            } else if (s[0].equals("height")) {
                height = Integer.parseInt(s[1]);
            } else if (s[0].equals("rows")) {
                mode = "rows";
            } else if (s[0].equals("columns")) {
                mode = "columns";
            } else if (mode.equals("rows")) {
                if(horizontalNumbers.size() >= height) continue;
                horizontalNumbers.add(parseRowDesc(line));
            } else if(mode.equals("columns")) {
                if(verticalNumbers.size() >= width) continue;
                verticalNumbers.add(parseRowDesc(line));
            }
        }
        return new Nonogram(width, height, horizontalNumbers, verticalNumbers);
    }

    /**
     * Creates a nonogram instance by the given .nono file.
     *
     * @param file .nono file
     * @return the nonogram instance
     * @throws IOException on IO errors when trying to read the file
     */
    @Deprecated
    public static Nonogram fromFile(File file) throws IOException {
        return fromPath(file.toPath());
    }

    /**
     * Parses the given description of numbers of a row.
     *
     * @param s row description
     * @return numbers as list
     */
    private static List<Integer> parseRowDesc(String s) {
        return Arrays.stream(s.split(",")).map(Integer::parseInt).filter(i -> i > 0).collect(Collectors.toList());
    }

    public static Field[] createLineByPattern(String... fields) {
        int length = fields.length;
        Field[] line = new Field[length];
        for (int i = 0; i < length; i++) {
            String field = fields[i];
            if ("_".equals(field)) {
                line[i] = Field.UNKNOWN;
            } else if ("■".equals(field)) {
                line[i] = Field.FILLED;
            } else if ("X".equals(field)) {
                line[i] = Field.EMPTY;
            } else {
                throw new RuntimeException(field + " is an unknown pattern character.");
            }
        }
        return line;
    }

    public static String createPattern(Field[] line) {
        int length = line.length;
        String[] pattern = new String[line.length];
        for (int i = 0; i < length; i++) {
            Field field = line[i];
            if (Field.UNKNOWN.equals(field)) {
                pattern[i] = "_";
            } else if (Field.FILLED.equals(field)) {
                pattern[i] = "■";
            } else if (Field.EMPTY.equals(field)) {
                pattern[i] = "X";
            } else {
                throw new RuntimeException(field + " is an unknown pattern character.");
            }
        }
        return String.join(PATTERN_DELIMITER, pattern);
    }

    /*
     * Generates a json string of board.
     *
     * @param board the board
     * @return board as json
     *
    public static String generateJson(Field[][] board) {
        return new Gson().toJson(board);
    }
    */
}
