package de.ir31k0.nonogramsolver.api.algorithm;

import de.ir31k0.nonogramsolver.api.data.Field;

import java.util.*;

/**
 * The algorithm super interface.
 *
 * This interface marks a class as an algorithm and provides standard functionalities.
 */
public interface Algorithm {

    /**
     * This method is executed from the nonogram solver and must be implemented by the algorithm with its logic.
     *
     * @param line the line
     * @param numbers the numbers of line
     * @return amended line
     */
    Field[] runOverLine(Field[] line, List<Integer> numbers);

    /**
     * @see #runOverLine(Field[], List)
     */
    default Field[] runOverLine(Field[] line, Integer... numbers) {
        return runOverLine(line, Arrays.asList(numbers));
    }

    /**
     * Sums all integer from the given collection.
     *
     * @param numbers collection of integers to sum
     * @return the result
     */
    default int sum(Collection<Integer> numbers) {
        return numbers.stream().mapToInt(Integer::intValue).sum();
    }

    /**
     * Counts a specific field type in line.
     *
     * @param line the line
     * @param searchFor the field type to count
     * @return the number of fields searched
     */
    default int count(Field[] line, Field searchFor) {
        int count = 0;
        for (Field field : line) {
            if (field.equals(searchFor)) ++count;
        }
        return count;
    }

    /**
     * Changes all unknown fields to the given field type.
     *
     * @param line the line
     * @param fillWith the field type to be filled with
     */
    default void fillAllUnknownFields(Field[] line, Field fillWith) {
        int size = line.length;
        for (int i = 0; i < size; i++) {
            if (Field.UNKNOWN.equals(line[i])) {
                line[i] = fillWith;
            }
        }
    }

    /**
     * Detects the minimum possible start index of each number of the line.
     *
     * @param line the line
     * @param numbers the numbers of the line
     * @return a map with the index of the number list as key and the minimum possible start index of the line as value
     */
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

    /**
     * Detects the maximum possible start index of each number of the line.
     *
     * @param line the line
     * @param numbers the numbers of the line
     * @return a map with the index of the number list as key and the maximum possible start index of the line as value
     */
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
