package com.bestvike.linq.common;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import java.util.concurrent.TimeUnit;

/**
 * Created by 许崇雷 on 2019-08-13.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
@State(Scope.Benchmark)
@Warmup(iterations = 10, time = 400, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 400, timeUnit = TimeUnit.MILLISECONDS)
public class ArrayPerf {
    @Param({"10", "100", "1000", "10000", "100000"})
    private int count;
    private int[] data;

    @Setup
    public void init() {
        this.data = new int[this.count];
        for (int i = 0; i < this.count; i++)
            this.data[i] = i;
    }

    @Benchmark
    public int iterate() {
        int sum = 0;
        for (int num : this.data) {
            sum += num;
        }
        return sum;
    }

    @Benchmark
    public int index() {
        int sum = 0;
        for (int i = 0; i < this.data.length; i++) {
            sum += this.data[i];
        }
        return sum;
    }
}
