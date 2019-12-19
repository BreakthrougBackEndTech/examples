package cluster;

/*import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.annotation.Scheduled;*/
/**
 * @description:
 * @author: zhegong
 * @create: 2019-12-19 15:10
 **/
//@Slf4j
//@EnableBinding(Channels.OutputChannel.class)
public class Producer {

   /* @Autowired
    private Channels.OutputChannel outputChannel;

    public MessageChannel getMessageChannel() {
        return outputChannel.output();
    }

    public void produce(String playload) {
        log.info("produce: {}", playload);
        getMessageChannel().send(MessageBuilder.withPayload(playload).build());
    }

    @Scheduled(initialDelay = 5000, fixedRate = 5000)
    public void scheduledProuce() {
        log.info("=== produce ===");
        getMessageChannel().send(MessageBuilder.withPayload("=== scheduled ===").build());
    }*/
}

