package com.ashketshup.Landmark.Interfaces;

/**
 * The interface Foo return.
 *
 * @param <T> the type parameter
 */
@FunctionalInterface
public interface FooReturn<T> {
    /**
     * Apply t.
     *
     * @return the t
     */
    T apply();
}
