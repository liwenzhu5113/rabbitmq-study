package com.example.rabbitmqstudy;

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
public class liwenzhuTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;



    @Test
    public void testSendMessage(){
        String queueName = "simple.queue";
        String message = "hello rabbitmq";
        rabbitTemplate.convertAndSend(queueName,message);
    }

    @Test
    public void testSendMessageFor() {
        String queueName = "simple.queue";
        String message = "hello rabbitmq__";
        for (int i = 1; i <=50 ; i++) {
            rabbitTemplate.convertAndSend(queueName,message +i);
        }
    }

    @Test
    public void testSendMessageForExchange() {
        //交换机名称
        String exchangeName = "liwenzhu.direct";
        //消息
        String message = "hello yellow";
        //发送消息
        rabbitTemplate.convertAndSend(exchangeName,"yellow",message);
    }

    @Test
    public void testSendMessageForTopicExchange() {
        //交换机名称
        String exchangeName = "liwenzhu.topic";
        //消息
        String message = "hello topic";
        //发送消息
        rabbitTemplate.convertAndSend(exchangeName,"china.#",message);
    }

    @Test
    public void testSendMessageForMessageForObjectType() {
        String queueName = "object.queue";
        Map<String,Object> map = new HashMap<>();
        map.put("baozi","aaaa");
        map.put("age","die");
        rabbitTemplate.convertAndSend(queueName,map);
    }
}
