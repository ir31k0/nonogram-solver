package de.ir31k0.nonogramsolver.algorithm.impl;

import de.ir31k0.nonogramsolver.algorithm.MultipleTimeAlgorithm;
import de.ir31k0.nonogramsolver.data.Field;

import java.util.List;

/**
 * If all filled fields are known, all other fields could be marked as empty.
 */
public class EmptyFieldMarkerIfAllNumbersKnownAlgorithm implements MultipleTimeAlgorithm {

    @Override
    public Field[] runOverLine(Field[] line, List<Integer> numbers) {
        int sumNumbers = sum(numbers);
        int countFilledFields = count(line, Field.FILLED);
        if (sumNumbers == countFilledFields) {
            fillAllUnknownFields(line, Field.EMPTY);
        }
        return line;
    }
}
