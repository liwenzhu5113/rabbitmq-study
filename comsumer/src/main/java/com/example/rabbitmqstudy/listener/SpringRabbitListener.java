package com.example.rabbitmqstudy.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author liwenzhu
 */
@Component
public class SpringRabbitListener {
    //指定监听的队列名称
    @RabbitListener(queues = "simple.queue")
    public void listenSimpleQueue1(String msg) throws InterruptedException {
        System.out.println("消费者收到simple.queue的消息:["+msg+"]");
        Thread.sleep(20);
    }

    @RabbitListener(queues = "simple.queue")
    public void listenSimpleQueue2(String msg) throws InterruptedException {
        System.err.println("消费者收到simple.queue的消息:["+msg+"]");
        Thread.sleep(200);
    }

    //指定监听的队列名称
    @RabbitListener(queues = "fanout.queue1")
    public void listenFanoutQueue1(String msg) throws InterruptedException {
        System.out.println("消费者收到fanout.queue1的消息:["+msg+"]");
    }

    @RabbitListener(queues = "fanout.queue2")
    public void listenFanoutQueue2(String msg) throws InterruptedException {
        System.err.println("消费者收到fanout.queue2的消息:["+msg+"]");
    }


    //创建一个direct类型的Queue
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue1"),
            exchange = @Exchange(name ="liwenzhu.direct",type = ExchangeTypes.DIRECT),
            key = {"red","blue"}
    ))
    public void listenDirectQueue1(String msg){
        System.err.println("消费者收到direct.queue1的消息:["+msg+"]");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue2"),
            exchange = @Exchange(name ="liwenzhu.direct",type = ExchangeTypes.DIRECT),
            key = {"red","yellow"}
    ))
    public void listenDirectQueue2(String msg){
        System.err.println("消费者收到direct.queue2的消息:["+msg+"]");
    }
}
