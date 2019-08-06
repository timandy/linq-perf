package com.bestvike.linq;

import com.bestvike.linq.common.ArrayListPerf;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * Created by 许崇雷 on 2019-08-01.
 */
public class Program {
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ArrayListPerf.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
