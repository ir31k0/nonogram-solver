package de.ir31k0.nonogramsolver.util;

import com.google.gson.Gson;
import de.ir31k0.nonogramsolver.data.Field;
import de.ir31k0.nonogramsolver.data.Nonogram;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Some util functionalities.
 */
public class SolverUtil {
    public static boolean isLineComplete(Field[] line) {
        for (Field field : line) {
            if (field == Field.EMPTY) {
                return false;
            }
        }
        return true;
    }

    /**
     * Creates a nonogram instance by the given .nono file.
     *
     * @param file .nono file
     * @return the nonogram instance
     * @throws IOException on IO errors when trying to read the file
     */
    public static Nonogram fromFile(File file) throws IOException {
        List<String> lines = Files.lines(file.toPath()).collect(Collectors.toCollection(ArrayList::new));

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
            } else if (s[0].equals("horizontal")) {
                mode = "horizontal";
            } else if (s[0].equals("vertical")) {
                mode = "vertical";
            } else if (mode.equals("horizontal")) {
                if(horizontalNumbers.size() >= height) continue;
                horizontalNumbers.add(parseRowDesc(line));
            } else if(mode.equals("vertical")) {
                if(verticalNumbers.size() >= width) continue;
                verticalNumbers.add(parseRowDesc(line));
            }
        }
        return new Nonogram(width, height, horizontalNumbers, verticalNumbers);
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

    /**
     * Generates a json string of board.
     *
     * @param board the board
     * @return board as json
     */
    public static String makeJson(Field[][] board) {
        return new Gson().toJson(board);
    }
}
