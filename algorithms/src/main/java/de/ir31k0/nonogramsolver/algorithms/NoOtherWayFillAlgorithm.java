package de.ir31k0.nonogramsolver.algorithms;

import de.ir31k0.nonogramsolver.api.algorithm.Ignore;
import de.ir31k0.nonogramsolver.api.algorithm.MultipleTimeAlgorithm;
import de.ir31k0.nonogramsolver.api.data.Field;

import java.util.Iterator;
import java.util.List;

/**
 * Has bugs see from horse test.
 */
@Ignore
public class NoOtherWayFillAlgorithm implements MultipleTimeAlgorithm {
    @Override
    public Field[] runOverLine(Field[] line, List<Integer> numbers) {
        if (numbers.isEmpty()) return line;
        Iterator<Integer> numberIterator = numbers.iterator();
        int number = numberIterator.next();
        int numberPosition = 0;
        int size = line.length;
        for (int i = 0; i < size; i++) {
            Field field = line[i];
            if (field == Field.FILLED) {
                numberPosition++;
                if (numberPosition == number) {
                    if (numberIterator.hasNext()) {
                        number = numberIterator.next();
                        i++;
                        numberPosition = 0;
                    } else {
                        break;
                    }
                }
            } else {
                if (numberPosition > 0) {
                    int leftFields = number - numberPosition;
                    // left direction
                    int leftMaxIndex = i - numberPosition - leftFields;
                    int fieldsCanBeFilledToTheLeft = 0;
                    if (leftMaxIndex < 0) {
                        fieldsCanBeFilledToTheLeft = leftFields + leftMaxIndex;
                    } else {
                        boolean gotoNextField = false;
                        for (int bw = i - numberPosition - 1; bw >= leftMaxIndex; bw--) {
                            Field bwField = line[bw];
                            if (bwField == Field.UNKNOWN) {
                                fieldsCanBeFilledToTheLeft++;
                            } else if (bwField == Field.FILLED) {
                                gotoNextField = true;
                            } else {
                                break;
                            }
                        }
                        if (gotoNextField) {
                            if (numberIterator.hasNext()) {
                                number = numberIterator.next();
                                numberPosition = 0;
                                continue;
                            } else {
                                break;
                            }
                        }
                    }

                    //right direction
                    int rightMaxIndex = i + leftFields - 1;
                    int fieldsCanBeFilledToTheRight = 0;
                    if (rightMaxIndex >= size) {
                        fieldsCanBeFilledToTheRight = size - i;
                    } else {
                        boolean gotoNextField = false;
                        for (int fw = i; fw <= rightMaxIndex; fw++) {
                            Field fwField = line[fw];
                            if (fwField == Field.UNKNOWN) {
                                fieldsCanBeFilledToTheRight++;
                            } else if (fwField == Field.FILLED){
                                gotoNextField = true;
                                break;
                            } else {
                                break;
                            }
                        }
                        if (gotoNextField) {
                            if (numberIterator.hasNext()) {
                                number = numberIterator.next();
                                numberPosition = 0;
                                continue;
                            } else {
                                break;
                            }
                        }
                    }

                    // fill to the left
                    int mustFillToTheLeft = leftFields - fieldsCanBeFilledToTheRight;
                    while (mustFillToTheLeft > 0) {
                        line[i - numberPosition - mustFillToTheLeft] = Field.FILLED;
                        mustFillToTheLeft--;
                    }

                    // fill to the right
                    int mustFillToTheRight = leftFields - fieldsCanBeFilledToTheLeft;
                    while (mustFillToTheRight > 0) {
                        line[i + mustFillToTheRight - 1] = Field.FILLED;
                        mustFillToTheRight--;
                    }

                    if (numberIterator.hasNext()) {
                        number = numberIterator.next();
                        i += leftFields - fieldsCanBeFilledToTheLeft;
                        numberPosition = 0;
                    } else {
                        break;
                    }
                }
            }
        }
        return line;
    }
}
