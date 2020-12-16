package de.ir31k0.nonogramsolver.algorithm.impl;

import de.ir31k0.nonogramsolver.algorithm.MultipleTimeAlgorithm;
import de.ir31k0.nonogramsolver.data.Field;

import java.util.List;

public class FillFromTheEdgeAlgorithm implements MultipleTimeAlgorithm {
    @Override
    public Field[] runOverLine(Field[] line, List<Integer> numbers) {
        int size = line.length;
        Field firstField = line[0];
        if (Field.FILLED.equals(firstField)) {
            int firstNumber = numbers.get(0);
            for (int i = 1; i < firstNumber; i++) {
                line[i] = Field.FILLED;
            }
            if (firstNumber < size) {
                line[firstNumber] = Field.EMPTY;
            }
        }

        Field lastField = line[size - 1];
        if (Field.FILLED.equals(lastField)) {
            int lastNumber = numbers.get(numbers.size() - 1);
            int start = size - lastNumber - 1;
            if (start >= 0){
                line[start] = Field.EMPTY;
            }
            start++;
            for (int i = start; i < size; i++) {
                line[i] = Field.FILLED;
            }
        }
        return line;
    }
}
