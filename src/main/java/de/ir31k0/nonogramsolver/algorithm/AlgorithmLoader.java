package de.ir31k0.nonogramsolver.algorithm;

import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AlgorithmLoader {
    private static final AlgorithmLoader holder = new AlgorithmLoader();

    private final List<OneTimeAlgorithm> oneTimeAlgorithms;
    private final List<MultipleTimeAlgorithm> multipleTimeAlgorithms;

    private AlgorithmLoader() {
        Reflections reflections = new Reflections(getClass().getPackageName() + ".impl");
        oneTimeAlgorithms = createAlgorithms(reflections.getSubTypesOf(OneTimeAlgorithm.class));
        multipleTimeAlgorithms = createAlgorithms(reflections.getSubTypesOf(MultipleTimeAlgorithm.class));
    }

    private <T extends Algorithm> List<T> createAlgorithms(Collection<Class<? extends T>> algorithmClasses) {
        List<T> algorithms = new ArrayList<>();
        for (Class<? extends T> algorithm : algorithmClasses) {
            Algorithm instance = createAlgorithm(algorithm);
            if (instance instanceof DisabledAlgorithm) {
                System.out.println(instance.getClass().getSimpleName() + " is disabled. To enable the algorithm, remove the " + DisabledAlgorithm.class.getSimpleName());
            } else {
                algorithms.add((T) instance);
            }
        }
        return algorithms;
    }

    private <T extends Algorithm> T createAlgorithm(Class<T> algorithmClass) {
        try {
            return algorithmClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("Cannot instantiate algorithm class " + algorithmClass.getName());
        }
    }

    public static AlgorithmLoader getInstance() {
        return holder;
    }

    public List<OneTimeAlgorithm> getOneTimeAlgorithms() {
        return oneTimeAlgorithms;
    }

    public List<MultipleTimeAlgorithm> getMultipleTimeAlgorithms() {
        return multipleTimeAlgorithms;
    }
}
