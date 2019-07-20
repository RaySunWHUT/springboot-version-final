package control.sun.activeMQ;

import control.sun.domain.Mood;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * 生产者
 */
@Service
public class MoodProducer {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;  // 发消息的工具类

    public void sendMessage(Destination destination, final String message) {    // 参数destination为：发送到的消息队列
        // 参数message为：待发送的消息
        jmsMessagingTemplate.convertAndSend(destination, message);

    }


    public void sendMessage(Destination destination, final Mood mood) {

        jmsMessagingTemplate.convertAndSend(destination, mood);

    }

}
