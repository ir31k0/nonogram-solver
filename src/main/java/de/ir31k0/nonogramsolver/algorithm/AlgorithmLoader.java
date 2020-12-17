package de.ir31k0.nonogramsolver.algorithm;

import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The algorithm loader loads all algorithms over reflection and holds them.
 * This is structured according to the singleton pattern. To get an instance, use the {@link #getInstance()} method.
 */
public class AlgorithmLoader {
    private static final AlgorithmLoader holder = new AlgorithmLoader();

    /** Holds all one time algorithms */
    private final List<OneTimeAlgorithm> oneTimeAlgorithms;

    /** Holds all multiple time algorithms */
    private final List<MultipleTimeAlgorithm> multipleTimeAlgorithms;

    private AlgorithmLoader() {
        Reflections reflections = new Reflections(getClass().getPackageName() + ".impl");
        oneTimeAlgorithms = createAlgorithms(reflections.getSubTypesOf(OneTimeAlgorithm.class));
        multipleTimeAlgorithms = createAlgorithms(reflections.getSubTypesOf(MultipleTimeAlgorithm.class));
    }

    /**
     * Creates an instance of the given algorithm classes.
     *
     * Ignores class that flagged with {@link DisabledAlgorithm}.
     *
     * @param algorithmClasses collection of algorithm classes
     * @param <T> type of algorithm e.g. {@link OneTimeAlgorithm}, {@link MultipleTimeAlgorithm}
     * @return list with instances
     */
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

    /**
     * Creates an instance of given algorithm class.
     *
     * @param algorithmClass the algorithm class
     * @param <T> type of algorithm
     * @return new instance of algorithm
     */
    private <T extends Algorithm> T createAlgorithm(Class<T> algorithmClass) {
        try {
            return algorithmClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("Cannot instantiate algorithm class " + algorithmClass.getName());
        }
    }

    /**
     * Gets the singleton instance of the algorithm loader.
     *
     * @return the instance
     */
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
