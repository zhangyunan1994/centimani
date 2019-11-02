package com.github.zyndev.centimani;

import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 *
 * @param <T> 参数
 * @author zhangyunan
 */
public class Experiment<T> {

    private String name;
    private BiFunction<T, T, Boolean> comparator;

    public Experiment(String name, BiFunction<T, T, Boolean> comparator) {
        this.name = name;
        this.comparator = comparator;
    }

    public Result run(Supplier<T> control, Supplier<T> candidate) {

        Timer controlTimer = new Timer();
        Timer candidateTimer = new Timer();
        T controlVal = execute(control, controlTimer);
        T candidateVal = execute(candidate, candidateTimer);

        return Result
                .builder()
                .match(comparator.apply(controlVal, candidateVal))
                .controlTime(controlTimer.timeCost())
                .candidateTime(candidateTimer.timeCost())
                .build();
    }

    private T execute(Supplier<T> supplier, Timer timer) {
        timer.start();
        T t = supplier.get();
        timer.end();
        return t;
    }

}
