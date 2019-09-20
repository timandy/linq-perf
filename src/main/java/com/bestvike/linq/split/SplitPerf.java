package com.bestvike.linq.split;

import com.bestvike.linq.Linq;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import java.util.concurrent.TimeUnit;

/**
 * Created by 许崇雷 on 2019-08-28.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
@State(Scope.Benchmark)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
public class SplitPerf {
    private static final String source = "张三;李四,拿破仑，王五；曹操张三;李四,拿破仑，王五；曹操张三;李四,拿破仑，王五；曹操张三;李四,拿破仑，王五；曹操";

    @Benchmark
    public String stream() {
        return source.split("李四")[0];
    }

    @Benchmark
    public String linq() {
        return Linq.split(source, new String[]{"李四"}).first();
    }
}
