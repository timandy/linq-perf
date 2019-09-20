package com.bestvike.linq.array;

import com.bestvike.linq.Linq;
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
import java.util.stream.Stream;

/**
 * Created by 许崇雷 on 2019-07-31.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
@State(Scope.Benchmark)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
public class WhereSelectPerf {
    @Param({"1", "10", "100"})
    private int count;
    private Integer[] data;

    @Setup
    public void init() {
        this.data = new Integer[this.count];
        for (int i = 0; i < this.data.length; i++)
            this.data[i] = i;
    }

    @Benchmark
    public int stream() {
        return Stream.of(this.data).filter(x -> x % 2 == 0).map(x -> x * 2).reduce(0, Integer::sum);
    }

    @Benchmark
    public int linq() {
        return Linq.of(this.data).where(x -> x % 2 == 0).select(x -> x * 2).aggregate(0, Integer::sum);
    }
}
