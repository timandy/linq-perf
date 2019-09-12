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

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by 许崇雷 on 2019-09-02.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
@State(Scope.Benchmark)
@Warmup(iterations = 10, time = 400, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 400, timeUnit = TimeUnit.MILLISECONDS)
public class Assertion {
    @Param({"10", "100", "1000", "10000", "100000"})
    private int count;
    private List<Integer> data;

    @Setup
    public void init() {
        this.data = new LinkedList<>();
        for (int i = 0; i < this.count; i++)
            this.data.add(i);
    }

    @Benchmark
    public int common() {
        int sum = 0;
        for (Integer num : this.data) {
            sum += num;
        }
        return sum;
    }

    @Benchmark
    public int withAssert() {
        int sum = 0;
        for (Integer num : this.data) {
            assert num != null;
            sum += num;
        }
        return sum;
    }
}
