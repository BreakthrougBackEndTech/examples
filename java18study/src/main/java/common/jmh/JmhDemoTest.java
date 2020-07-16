package common.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: zhegong
 * @create: 2020-07-16 14:45
 **/
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 3)
@Measurement(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class JmhDemoTest {

    private JmhDemo jmhDemo = new JmhDemo();

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().include(JmhDemoTest.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }

    @Benchmark
    @Timeout(time = 10, timeUnit = TimeUnit.MINUTES)
    public void bentch(SharePara sharePara) {
        sharePara.jmhDemo.append();
    }

    @Benchmark
    public void bentch1(SharePara sharePara) {
        sharePara.jmhDemo.append1();
    }

    @Benchmark
    public void bentch2(SharePara sharePara) {
        sharePara.jmhDemo.append2();
    }

    @Benchmark
    public void bentch3(SharePara sharePara) {
        sharePara.jmhDemo.append3();
    }

    @Benchmark
    public void bentch4(SharePara sharePara) {
        sharePara.jmhDemo.append4();
    }

    @State(Scope.Thread)
    public static class SharePara {
        JmhDemo jmhDemo = new JmhDemo();
    }
}

