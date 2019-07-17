import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 数据源
 * @author: zhegong
 * @create: 2019-07-17 10:37
 **/
public class SentenceSpout extends BaseRichSpout {

    private static final long serialVersionUID = 225243592780939490L;

    private SpoutOutputCollector collector;
    private static final String field = "word";
    private int count = 1;
    private static Random random = new Random();

    private String[] message = {
            "My nickname is luffy",
            "My github is https://github.com/BreakthrougBackEndTech",
            "My weixin blog is BreakthrougBackEndTech"
    };

    //创建一个map用来保存发送数据
    private Map<String, String> msgBuffer = new ConcurrentHashMap<>();

    /**
     * open()方法中是在ISpout接口中定义，在Spout组件初始化时被调用。
     * 有三个参数:
     * 1.Storm配置的Map;
     * 2.topology中组件的信息;
     * 3.发射tuple的方法;
     */
    @Override
    public void open(Map map, TopologyContext arg1, SpoutOutputCollector collector) {
        System.out.println("open:" + map.get("test"));
        this.collector = collector;
    }

    /**
     * nextTuple()方法是Spout实现的核心。
     * 也就是主要执行方法，用于输出信息,通过collector.emit方法发射。
     */
    @Override
    public void nextTuple() {
//        UUID.randomUUID().toString()
        if (count <= 100) {
            System.out.println(count + " time send data...");
            String msg = message[random.nextInt(3)];

            if (msg == null) {
                //模拟从消息队列中取数据为空， sleep
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                msgBuffer.put(String.valueOf(count), msg);

                this.collector.emit(new Values(msg), count);
                count++;

                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * declareOutputFields是在IComponent接口中定义，用于声明数据格式。
     * 即输出的一个Tuple中，包含几个字段。
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        System.out.println("define format...");
        declarer.declare(new Fields(field));
    }

    /**
     * 当一个Tuple处理成功时，会调用这个方法
     */
    @Override
    public void ack(Object msgId) {
        System.out.println("ack:" + msgId);

        msgBuffer.remove(msgId);
    }

    /**
     * 当Topology停止时，会调用这个方法
     */
    @Override
    public void close() {
        System.out.println("close...");
    }

    /**
     * 当一个Tuple处理失败时，会调用这个方法
     */
    @Override
    public void fail(Object msgId) {
        System.out.println("fail:" + msgId);

        String msg = msgBuffer.get(msgId.toString());

        System.out.println("send fail:" + msg);
        this.collector.emit(new Values(msg), msgId);
    }

}
