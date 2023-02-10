package cn.itcast.mq.listenner;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Map;

@Component
public class SpringRabbitListener {

//    @RabbitListener(queues = "simple.queue")
//    public void listenerSimpleQueue(String msg){
//        System.out.println("成功");
//    }

    @RabbitListener(queues = "simple.queue")
    public void listenerWorkQueue_1(String msg) throws InterruptedException {
        System.out.println("成功 1  " + LocalTime.now());
        Thread.sleep(20);
    }

    @RabbitListener(queues = "simple.queue")
    public void listenerWorkQueue_2(String msg) throws InterruptedException {
        System.err.println("成功 2  " + LocalTime.now());
        Thread.sleep(200);
    }

    @RabbitListener(queues = "fanout.queue1")
    public void listenerFanoutQueue1(String msg){
        System.out.println("fanout.queue1 成功 ");
    }

    @RabbitListener(queues = "fanout.queue2")
    public void listenerFanoutQueue2(String msg){
        System.out.println("fanout.queue2 成功 ");
    }




    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue1"),
            exchange = @Exchange(name = "yutian.direct",type = ExchangeTypes.DIRECT),
            key = {"red","blue"}
    ))
    public void listenerDirectQueue1(String msg){
        System.out.println("direct.queue1 成功 ");
    }
;


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue2"),
            exchange = @Exchange(name = "yutian.direct",type = ExchangeTypes.DIRECT),
            key = {"red","yellow"}
    ))
    public void listenerDirectQueue2(String msg){
        System.out.println("direct.queue2 成功 ");
    }





    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue1"),
            exchange = @Exchange(name = "yutian.topic",type = ExchangeTypes.TOPIC),
            key = "china.#"
    ))
    public void listenerTopicQueue1(String msg){
        System.out.println("topic.queue1 成功 ");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue2"),
            exchange = @Exchange(name = "yutian.topic",type = ExchangeTypes.TOPIC),
            key = "#.news"
    ))
    public void listenerTopicQueue2(String msg){
        System.out.println("topic.queue2 成功 ");
    }

    @RabbitListener(queues = "object.queue")
    public void ListenObjectQueue(Map<String,Object> msg){
        System.out.println("成功 + " + msg);
    }



}
