package com.yeyeee.seckill.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MQSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

//    public void send(Object msg){
//        log.info("发送消息:"+ msg);
//        rabbitTemplate.convertAndSend("fanoutExchange","",msg);
//    }
//
//    public void send01(Object msg){
//        log.info("发送red消息:"+ msg);
//        rabbitTemplate.convertAndSend("directExchange","queue.red",msg);
//    }
//
//    public void send02(Object msg){
//        log.info("发送green消息:"+ msg);
//        rabbitTemplate.convertAndSend("directExchange","queue.green",msg);
//    }
//    public void send03(Object msg){
//        log.info("发送消息(QUEUE01接受):"+ msg);
//        rabbitTemplate.convertAndSend("topicExchange","queue.red.message",msg);
//    }
//
//    public void send04(Object msg){
//        log.info("发送消息(被两个queue接受):"+ msg);
//        rabbitTemplate.convertAndSend("topicExchange","message.queue.green.abc",msg);
//    }


        public void sendSeckillMessage(String message){
            log.info("发送消息:"+ message);
            rabbitTemplate.convertAndSend("seckillExchange","seckill.message",message);
        }
}
