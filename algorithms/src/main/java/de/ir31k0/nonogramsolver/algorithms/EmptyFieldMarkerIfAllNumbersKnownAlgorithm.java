package de.ir31k0.nonogramsolver.algorithms;

import de.ir31k0.nonogramsolver.api.algorithm.MultipleTimeAlgorithm;
import de.ir31k0.nonogramsolver.api.data.Field;

import java.util.List;

/**
 * If all filled fields are known, all other fields could be marked as empty.
 */
public class EmptyFieldMarkerIfAllNumbersKnownAlgorithm implements MultipleTimeAlgorithm {

    @Override
    public Field[] runOverLine(final Field[] line, final List<Integer> numbers) {
        final int sumNumbers = sum(numbers);
        final int countFilledFields = count(line, Field.FILLED);
        if (sumNumbers == countFilledFields) {
            fillAllUnknownFields(line, Field.EMPTY);
        }
        return line;
    }
}
