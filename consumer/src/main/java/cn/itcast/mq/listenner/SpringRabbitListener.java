package cn.itcast.mq.listenner;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class SpringRabbitListener {

//    @RabbitListener(queues = "simple.queue")
//    public void listenerSimpleQueue(String msg){
//        System.out.println("成功");
//    }

    @RabbitListener(queues = "simple.queue")
    public void listenerWorkQueue_1(String msg) throws InterruptedException {
        System.out.println("成功" + LocalTime.now());
        Thread.sleep(20);
    }

    @RabbitListener(queues = "simple.queue")
    public void listenerWorkQueue_2(String msg) throws InterruptedException {
        System.err.println("成功" + LocalTime.now());
        Thread.sleep(200);
    }
}
