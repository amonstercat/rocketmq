package com.lzc.one2one;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import java.util.List;

public class Consumer {
    public static void main(String[] args) throws MQClientException {
        //new一个push模式下的consumer
        DefaultMQPushConsumer consumer=new DefaultMQPushConsumer("GROUP1");
        consumer.setNamesrvAddr("localhost:9876");// 设置nameserver地址(从哪里收消息)
        //设置监听哪一个消息队列
        consumer.subscribe("topic1","*"); //*表示都监听
        //业务流程处理-注册监听器
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
            //写业务逻辑
                for (MessageExt messageExt : list) {
                    System.out.println(messageExt);
                    byte[] body = messageExt.getBody();
                    System.out.println(new String(body));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS ;
            }
        });  //接口不能被new(例如集合)  用匿名内部类
        consumer.start();

        //消费者不能关，因为要监听
    }

}
