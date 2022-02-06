package org.example.algorithm;

import de.ir31k0.nonogramsolver.api.algorithm.OneTimeAlgorithm;
import de.ir31k0.nonogramsolver.api.data.Field;

import java.util.List;

public class ExampleAlgorithm implements OneTimeAlgorithm {
    @Override
    public Field[] runOverLine(Field[] line, List<Integer> numbers) {
        System.out.println("Example algorithm called");
        return line;
    }
}
