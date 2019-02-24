package com.zxx.senior.basics.j8.java8;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @author xuxiao zhang
 * @create 2018/09/20
 **/
public class MyCollector implements Collector<String, CollectionCombiner, String> {

    @Override
    public Supplier<CollectionCombiner> supplier() {
        return null;
    }

    @Override
    public BiConsumer<CollectionCombiner, String> accumulator() {
        return null;
    }

    @Override
    public BinaryOperator<CollectionCombiner> combiner() {
        return null;
    }

    @Override
    public Function<CollectionCombiner, String> finisher() {
        return null;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return null;
    }
}

