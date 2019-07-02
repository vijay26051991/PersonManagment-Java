package com.abc.core.transformer;

@FunctionalInterface
public interface RequestResponseTransformer<T, R> {

    <R> R transform(final T t);
}
