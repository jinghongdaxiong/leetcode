package com.jiyu.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <b>功能名：LoopHelperTest</b><br>
 * <b>说明：</b><br>
 * <b>著作权：</b> Copyright (C) 2022 HUIFANEDU  CORPORATION<br>
 * <b>修改履历：
 *
 * @author 2022-04-02 xuxiongzi
 */
@BenchmarkMode(Mode.Throughput) // 吞吐量
@OutputTimeUnit(TimeUnit.MILLISECONDS) // 结果所使用的时间单位
@State(Scope.Thread) // 每个测试线程分配一个实例
@Fork(2) // Fork进行的数目
@Warmup(iterations = 4) // 先预热4轮
@Measurement(iterations = 10) // 进行10轮测试
public class LoopHelperTest {
    // 定义2个list大小 分别对不同大小list进行测试
    @Param({"1000",  "100000", })
    private int n;

    private List<Integer> list;

    /**
     * 初始化方法，在全部Benchmark运行之前进行
     */
    @Setup(Level.Trial)
    public void init() {
        list = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
    }
    @Benchmark
    public void testIteratorMaxInteger() {
        LoopHelper.iteratorMaxInteger(list);
    }

    @Benchmark
    public void testForEachLoopMaxInteger() {
        LoopHelper.forEachLoopMaxInteger(list);
    }

    @Benchmark
    public void testForMaxInteger() {
        LoopHelper.forMaxInteger(list);
    }

    @Benchmark
    public void testParallelStreamMaxInteger() {
        LoopHelper.parallelStreamMaxInteger(list);
    }

    @Benchmark
    public void testLambdaMaxInteger() {
        LoopHelper.lambdaMaxInteger(list);
    }

    /**
     *  结束方法，在全部Benchmark运行之后进行
     */
    @TearDown(Level.Trial)
    public void arrayRemove() {
        for (int i = 0; i < n; i++) {
            list.remove(0);
        }
    }
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(LoopHelperTest.class.getSimpleName()).build();
        new Runner(options).run();
    }
}