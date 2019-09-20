package com.bestvike.linq.linked_list;

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

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by 许崇雷 on 2019-07-31.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
@State(Scope.Benchmark)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
public class SelectPerf {
    @Param({"1", "10", "100"})
    private int count;
    private List<Integer> data;

    @Setup
    public void init() {
        this.data = new LinkedList<>();
        for (int i = 0; i < this.count; i++)
            this.data.add(i);
    }

    @Benchmark
    public int stream() {
        return this.data.stream().map(x -> x * 2).reduce(0, Integer::sum);
    }

    @Benchmark
    public int linq() {
        return Linq.of(this.data).select(x -> x * 2).aggregate(0, Integer::sum);
    }
}
