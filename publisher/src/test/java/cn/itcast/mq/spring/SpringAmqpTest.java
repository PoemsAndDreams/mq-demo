package cn.itcast.mq.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAmqpTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendMessage2SimpleQueue(){
        String queueName = "simple.queue";
        String message = "hello,spring amqp!";
        rabbitTemplate.convertAndSend(queueName,message);

    }

    @Test
    public void testSendMessage2WorkQueue() throws InterruptedException {
        String queueName = "simple.queue";
        String message = "hello,spring amqp!--";
        for (int i = 1; i <= 50; i++) {
            rabbitTemplate.convertAndSend(queueName,message + i);
            Thread.sleep(20);
        }

    }

    @Test
    public void testSendFanoutExchange(){
        String exchangeName = "yutian.fanout";

        String message = "hello,every one";

        rabbitTemplate.convertAndSend(exchangeName,"",message);
    }

    @Test
    public void testSendDirectExchange(){
        String exchangeName = "yutian.direct";

        String message = "hello,every one";

        rabbitTemplate.convertAndSend(exchangeName,"yellow",message);
    }

    @Test
    public void testSendTopicExchange(){
        String exchangeName = "yutian.topic";

        String message = "hello,every one";

        rabbitTemplate.convertAndSend(exchangeName,"china.news",message);
    }

    @Test
    public void testSendObjectQueue(){
        Map<String,Object> msg = new HashMap<>();
        msg.put("name","雨天");
        msg.put("age",21);
        rabbitTemplate.convertAndSend("object.queue",msg);
    }
}
