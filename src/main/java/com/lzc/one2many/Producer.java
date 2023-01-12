package com.lzc.one2many;

import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class Producer {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        //谁来发？
        DefaultMQProducer  producer=new DefaultMQProducer("group1");
        DefaultMQProducer  producer2=new DefaultMQProducer("group1");
        //生产者只需要发给NameServer
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        //测试sql过滤
        String msg1="--------------hello rocketmq 1-----------";
        Message message1 = new Message("topic5", "player",msg1.getBytes());
        message1.putUserProperty("age","20");
        message1.putUserProperty("name","haland");

        String msg2="--------------hello rocketmq 2-----------";
        Message message2 = new Message("topic5", "player",msg2.getBytes());
        message2.putUserProperty("age","24");
        message2.putUserProperty("name","mbappe");
        //发送消息
//        for (int  i= 0;  i< 20; i++) {
//            String msg="--------------hello rocketmq-----------"+i;   //String byte[] char[] 相互转换
//            Message message = new Message("topic2", "tag1", msg.getBytes());
//            SendResult sendResult = producer.send(message);
//            //输出发送结果
//            System.out.println(sendResult);
//        }

        //测试异步消息
//        for (int  i= 0;  i< 20; i++) {
//            String msg="--------------hello rocketmq-----------"+i;   //String byte[] char[] 相互转换
//            Message message = new Message("topic2", "tag1", msg.getBytes());
//            producer.send(message, new SendCallback() {
//                @Override
//                public void onSuccess(SendResult sendResult) {
//                    System.out.println(sendResult);
//                }
//                @Override
//                public void onException(Throwable throwable) {
//                    System.out.println(throwable);
//                }
//            });
//
//
//        }
        //关闭生产者(shutdown)
         // producer.shutdown();

    }
}
