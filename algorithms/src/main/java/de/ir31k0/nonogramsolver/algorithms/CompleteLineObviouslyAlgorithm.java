package de.ir31k0.nonogramsolver.algorithms;

import de.ir31k0.nonogramsolver.api.algorithm.OneTimeAlgorithm;
import de.ir31k0.nonogramsolver.api.data.Field;

import java.util.Iterator;
import java.util.List;

/**
 * Completes a line obviously. Solves a line if it can be solved directly.
 *
 * Example:
 * Size 10 / Number 10
 * Size 10 / Number 6,3 (6+3+1 blank separator)
 */
public class CompleteLineObviouslyAlgorithm implements OneTimeAlgorithm {

    @Override
    public Field[] runOverLine(Field[] line, List<Integer> numbers) {
        int size = line.length;
        int sum = sum(numbers);
        if (sum + numbers.size() - 1 == size) {
            Iterator<Integer> numberIterator = numbers.iterator();
            int current = 0;
            while (numberIterator.hasNext()) {
                int number = numberIterator.next();
                int end = current + number;
                for (int i = current; i < end; ++i) {
                    line[i] = Field.FILLED;
                }
                if (end < size) {
                    line[end] = Field.EMPTY;
                    current = end + 1;
                }
            }
        }
        return line;
    }
}
