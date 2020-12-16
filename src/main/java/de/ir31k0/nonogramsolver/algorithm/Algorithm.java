package de.ir31k0.nonogramsolver.algorithm;

import de.ir31k0.nonogramsolver.data.Field;

import java.util.*;

public interface Algorithm {
    Field[] runOverLine(Field[] line, List<Integer> numbers);

    default Field[] runOverLine(Field[] line, Integer... numbers) {
        return runOverLine(line, Arrays.asList(numbers));
    }

    default int sum(Collection<Integer> numbers) {
        return numbers.stream().mapToInt(Integer::intValue).sum();
    }

    default int sum(Field[] line, Field searchFor) {
        int sum = 0;
        for (Field field : line) {
            if (field.equals(searchFor)) ++sum;
        }
        return sum;
    }

    default int count(Field[] line, Field toCount) {
        int count = 0;
        for (Field field : line) {
            if (field.equals(toCount)) ++count;
        }
        return count;
    }

    default void fillAllUnknownFields(Field[] line, Field fillWith) {
        int size = line.length;
        for (int i = 0; i < size; i++) {
            if (Field.UNKNOWN.equals(line[i])) {
                line[i] = fillWith;
            }
        }
    }

    default Map<Integer, Integer> detectMinStartIndex(Field[] line, List<Integer> numbers) {
        Map<Integer, Integer> minStartIndices = new HashMap<>();
        int numberCount = numbers.size();
        int numberIndex = 0;
        int number = numbers.get(numberIndex);
        int possibleNumberFieldCount = 0;
        int numberStartIndex = -1;
        int size = line.length;
        for (int i = 0; i < size; i++) {
            Field field = line[i];
            if (field != Field.EMPTY) {
                if (numberStartIndex < 0) {
                    numberStartIndex = i;
                }
                possibleNumberFieldCount++;
                if (possibleNumberFieldCount == number) {
                    minStartIndices.put(numberIndex, numberStartIndex);
                    numberIndex++;
                    if (numberIndex < numberCount) {
                        number = numbers.get(numberIndex);
                        i++;
                        possibleNumberFieldCount = 0;
                        numberStartIndex = -1;
                    } else {
                        break;
                    }
                }
            } else {
                possibleNumberFieldCount = 0;
                numberStartIndex = -1;
            }
        }
        return minStartIndices;
    }

    default Map<Integer, Integer> detectMaxStartIndex(Field[] line, List<Integer> numbers) {
        Map<Integer, Integer> maxStartIndices = new HashMap<>();

        int numberCount = numbers.size();
        int numberIndex = numberCount - 1;
        int number = numbers.get(numberIndex);
        int possibleNumberFieldCount = 0;
        int size = line.length;
        for (int i = size - 1; i >= 0; i--) {
            Field field = line[i];
            if (field != Field.EMPTY) {
                possibleNumberFieldCount++;
                if (possibleNumberFieldCount == number) {
                    maxStartIndices.put(numberIndex, i);
                    numberIndex--;
                    if (numberIndex > -1) {
                        number = numbers.get(numberIndex);
                        i--;
                        possibleNumberFieldCount = 0;
                    } else {
                        break;
                    }
                }
            } else {
                possibleNumberFieldCount = 0;
            }
        }
        return maxStartIndices;
    }
}
