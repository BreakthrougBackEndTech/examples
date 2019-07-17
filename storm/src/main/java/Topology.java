import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-07-17 10:24
 **/
public class Topology {
    private static final String sentence_spout = "sentence_spout";
    private static final String splitSentence_bolt = "splitSentence_bolt";
    private static final String countWord_bolt = "countWord_bolt";

    public static void main(String[] args) {
        //定义一个拓扑
        TopologyBuilder builder = new TopologyBuilder();
        //设置一个Executeor(线程)，默认一个
        builder.setSpout(sentence_spout, new SentenceSpout(), 1);
        //shuffleGrouping:表示是随机分组
        //设置两个Executeor(线程)，和两个task
        builder.setBolt(splitSentence_bolt, new SplitSentenceBolt(), 2).setNumTasks(2).shuffleGrouping(sentence_spout);
        //fieldsGrouping:表示是按字段分组
        //设置两个个Executeor(线程)，和两个task
        builder.setBolt(countWord_bolt, new CountWordBolt(), 2).setNumTasks(2).fieldsGrouping(splitSentence_bolt, new Fields("count"));
        Config conf = new Config();

        conf.put("test", "test");
        //超时时间， 默认30s
        conf.put(Config.TOPOLOGY_MESSAGE_TIMEOUT_SECS, 2);
        try {
            //运行拓扑
            if (args != null && args.length > 0) { //有参数时，表示向集群提交作业，并把第一个参数当做topology名称
                System.out.println("remote mode");
                StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
            } else {//没有参数时，本地提交
                //启动本地模式
                System.out.println("local mote");
                LocalCluster cluster = new LocalCluster();
                cluster.submitTopology("Word-counts", conf, builder.createTopology());
                Thread.sleep(60000);
                //  //关闭本地集群
                cluster.shutdown();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

