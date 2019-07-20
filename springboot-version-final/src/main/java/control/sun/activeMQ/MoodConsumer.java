package control.sun.activeMQ;

import control.sun.domain.Mood;
import control.sun.service.MoodService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 消费者
 */
@Component
public class MoodConsumer {

    @Resource
    private MoodService moodService;

    @JmsListener(destination = "sun.queue")     // 配置消费者监听的队列sun.queue, 其中text为接收到的消息
    public void receiveQueue(String text) {

        System.out.println("用户发表说说【" + text + "】成功！");

    }


    @JmsListener(destination = "sun.queue.asyn.save")
    public void receiveQueue(Mood mood) {   // 异步消息

        moodService.save(mood);

    }

}
