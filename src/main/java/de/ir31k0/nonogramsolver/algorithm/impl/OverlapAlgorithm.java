package de.ir31k0.nonogramsolver.algorithm.impl;

import de.ir31k0.nonogramsolver.algorithm.MultipleTimeAlgorithm;
import de.ir31k0.nonogramsolver.data.Field;

import java.util.List;
import java.util.Map;

/**
 * Detects the farthest left and the farthest right option. If they overlap in the middle, they must be filled.
 *
 * Example:
 * 2,3 | | | |X| | | | |X| -> | |F| |X| |F|F| |X|
 */
public class OverlapAlgorithm implements MultipleTimeAlgorithm {

    @Override
    public Field[] runOverLine(Field[] line, List<Integer> numbers) {
        if (numbers.isEmpty()) return line;
        Map<Integer, Integer> minStartIndices = detectMinStartIndex(line, numbers);
        Map<Integer, Integer> maxStartIndices = detectMaxStartIndex(line, numbers);

        int numberCount = numbers.size();
        for (int n = 0; n < numberCount; n++) {
            int minStartIndex = minStartIndices.get(n);
            int maxStartIndex = maxStartIndices.get(n);
            int number = numbers.get(n);
            int end = minStartIndex + number;
            if (end > maxStartIndex) {
                for (int i = maxStartIndex; i < end; i++) {
                    line[i] = Field.FILLED;
                }
            }
        }
        return line;
    }
}
