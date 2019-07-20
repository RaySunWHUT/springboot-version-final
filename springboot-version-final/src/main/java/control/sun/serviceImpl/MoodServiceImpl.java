package control.sun.serviceImpl;

import control.sun.activeMQ.MoodProducer;
import control.sun.domain.Mood;
import control.sun.repository.MoodRepository;
import control.sun.service.MoodService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

@Service
public class MoodServiceImpl implements MoodService {

    @Resource
    private MoodRepository moodRepository;

    // 队列
    private static Destination destination = new ActiveMQQueue("sun.queue.asyn.save");

    @Resource
    private MoodProducer moodProducer;

    @Override
    public Mood save(Mood mood) {

        return moodRepository.save(mood);

    }

    @Override
    public String asynSave(Mood mood) {

        // 向队列：sun.queue.asyn.save推送消息, 消息内容为：说说实体
        moodProducer.sendMessage(destination, mood);

        return "success";

    }

}
