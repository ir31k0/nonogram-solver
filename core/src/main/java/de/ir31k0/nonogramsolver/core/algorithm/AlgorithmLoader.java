package de.ir31k0.nonogramsolver.core.algorithm;

import de.ir31k0.nonogramsolver.api.algorithm.*;
import org.reflections.*;
import org.reflections.scanners.*;
import org.reflections.util.*;
import org.tinylog.*;

import java.lang.reflect.*;
import java.util.*;

/**
 * The algorithm loader loads all algorithms over reflection and holds them.
 * This is structured according to the singleton pattern. To get an instance, use the {@link #getInstance()} method.
 */
public final class AlgorithmLoader {
    /** The singleton instance of the algorithm loader */
    private static final AlgorithmLoader instance = new AlgorithmLoader();

    /** Holds all one time algorithms */
    private final List<OneTimeAlgorithm> oneTimeAlgorithms;

    /** Holds all multiple time algorithms */
    private final List<MultipleTimeAlgorithm> multipleTimeAlgorithms;

    private AlgorithmLoader() {
        Logger.info("Load algorithms..");
        final Reflections reflections = new Reflections(new ConfigurationBuilder().setUrls(ClasspathHelper.forJavaClassPath()).setScanners(Scanners.SubTypes));
        oneTimeAlgorithms = createAlgorithms(reflections.getSubTypesOf(OneTimeAlgorithm.class));
        Logger.info("Loaded {} one time algorithms", oneTimeAlgorithms.size());
        multipleTimeAlgorithms = createAlgorithms(reflections.getSubTypesOf(MultipleTimeAlgorithm.class));
        Logger.info("Loaded {} multiple time algorithms", multipleTimeAlgorithms.size());
    }

    /**
     * Gets the singleton instance of the algorithm loader.
     *
     * @return the instance
     */
    public static AlgorithmLoader getInstance() {
        return instance;
    }

    /**
     * Creates an instance of the given algorithm classes.
     *
     * Ignores class that flagged with {@link Ignore}.
     *
     * @param algorithmClasses collection of algorithm classes
     * @param <T> type of algorithm e.g. {@link OneTimeAlgorithm}, {@link MultipleTimeAlgorithm}
     * @return list with instances
     */
    private <T extends Algorithm> List<T> createAlgorithms(final Iterable<Class<? extends T>> algorithmClasses) {
        final List<T> algorithms = new ArrayList<>(100);
        for (final Class<? extends T> algorithmClass : algorithmClasses) {
            final T algorithm = createAlgorithm(algorithmClass);
            if (algorithm.getClass().isAnnotationPresent(Ignore.class)) {
                Logger.info("{} is disabled. To enable the algorithm, remove the {}", algorithm.getClass().getSimpleName(), Ignore.class.getSimpleName());
            } else {
                algorithms.add(algorithm);
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
    private static <T extends Algorithm> T createAlgorithm(final Class<T> algorithmClass) {
        try {
            return algorithmClass.getConstructor().newInstance();
        } catch (final InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("Cannot instantiate algorithm class " + algorithmClass.getSimpleName());
        }
    }

    public List<OneTimeAlgorithm> getOneTimeAlgorithms() {
        return oneTimeAlgorithms;
    }

    public List<MultipleTimeAlgorithm> getMultipleTimeAlgorithms() {
        return multipleTimeAlgorithms;
    }
}
