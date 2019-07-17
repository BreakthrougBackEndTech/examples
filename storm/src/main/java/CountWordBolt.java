import java.util.HashMap;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

/**
 * @description: 统计单词出现的次数
 * @author: zhegong
 * @create: 2019-07-17 10:37
 **/
public class CountWordBolt extends BaseRichBolt {

    private static final long serialVersionUID = 4743224635827696343L;

    private OutputCollector collector;

    /**
     * 保存单词和对应的计数
     */
    private HashMap<String, Integer> counts = null;

    private long count = 1;

    /**
     * 在Bolt启动前执行，提供Bolt启动环境配置的入口
     * 一般对于不可序列化的对象进行实例化。
     * 注:如果是可以序列化的对象，那么最好是使用构造函数。
     */
    @Override
    public void prepare(Map map, TopologyContext arg1, OutputCollector collector) {
        System.out.println("prepare:" + map.get("test"));
        this.counts = new HashMap<String, Integer>();
        this.collector = collector;
    }


    /**
     * execute()方法是Bolt实现的核心。
     * 也就是执行方法，每次Bolt从流接收一个订阅的tuple，都会调用这个方法。
     */
    @Override
    public void execute(Tuple tuple) {
        String msg = tuple.getStringByField("count");
//        System.out.println("第"+count+"次统计单词出现的次数");
        System.out.println(count + " time of word");
        /**
         * 如果不包含该单词，说明在该map是第一次出现
         * 否则进行加1
         */
        if (!counts.containsKey(msg)) {
            counts.put(msg, 1);
        } else {
            counts.put(msg, counts.get(msg) + 1);
        }
        count++;

        if (count == 100) {
            //模拟失败
            System.err.println("response fail");
            collector.fail(tuple);
        } else {
            //bolt对数据完成处理后发出信号
            collector.ack(tuple);
        }
    }


    /**
     * cleanup是IBolt接口中定义,用于释放bolt占用的资源。
     * Storm在终止一个bolt之前会调用这个方法。
     */
    @Override
    public void cleanup() {
        System.out.println("===========start show word number============");
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("===========end============");
        System.out.println("CountWordBolt release resource");
    }

    /**
     * 声明数据格式
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer arg0) {

    }
}
