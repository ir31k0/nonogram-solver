package de.ir31k0.nonogramsolver.api.algorithm;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Flag annotation for algorithms which are not executed.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Ignore { }
