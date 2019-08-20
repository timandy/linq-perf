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
 * Created by 许崇雷 on 2019-08-20.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
@State(Scope.Benchmark)
@Warmup(iterations = 10, time = 400, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 400, timeUnit = TimeUnit.MILLISECONDS)
public class StringBuilderPerf {
    @Param({"10", "100", "1000", "10000", "100000"})
    private int count;

    @Setup
    public void init() {
    }

    @Benchmark
    public StringBuilder appendChar() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.count; i++) {
            sb.append('(');
        }
        return sb;
    }

    @Benchmark
    public StringBuilder appendString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.count; i++) {
            sb.append("(");
        }
        return sb;
    }
}
