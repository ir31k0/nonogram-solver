package de.ir31k0.nonogramsolver.algorithms;

import de.ir31k0.nonogramsolver.api.algorithm.Ignore;
import de.ir31k0.nonogramsolver.api.algorithm.MultipleTimeAlgorithm;
import de.ir31k0.nonogramsolver.api.data.Field;

import java.util.Iterator;
import java.util.List;

/**
 * I don't know. Should have bugs.
 */
@Ignore
public class EmptyFieldSeparateMarkerAlgorithm implements MultipleTimeAlgorithm {

    @Override
    public Field[] runOverLine(final Field[] line, final List<Integer> numbers) {
        if (numbers.isEmpty()) return line;
        final Iterator<Integer> numberIterator = numbers.iterator();
        int number = numberIterator.next();
        int numberPosition = 0;
        final int size = line.length;
        for (int i = 0; i < size; i++) {
            final Field field = line[i];
            if (Field.FILLED == field) {
                ++numberPosition;
                if (numberPosition == number) {
                    final int beforeNumber = i - number;
                    if (0 <= beforeNumber) {
                        line[beforeNumber] = Field.EMPTY;
                    }
                    final int afterNumber = ++i;
                    if (afterNumber < size) {
                        line[afterNumber] = Field.EMPTY;
                    }
                    if (numberIterator.hasNext()) {
                        number = numberIterator.next();
                        numberPosition = 0;
                    } else {
                        return line;
                    }
                }
            } else {
                numberPosition = 0;
            }
        }
        return line;
    }
}
