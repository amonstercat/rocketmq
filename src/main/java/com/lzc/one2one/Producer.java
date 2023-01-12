package com.lzc.one2one;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class Producer {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        //谁来发？
        DefaultMQProducer  producer=new DefaultMQProducer("group1");
        //生产者只需要发给NameServer
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        //发送消息
        String msg="hello rocketmq-----------";   //String byte[] char[] 相互转换
        Message message = new Message("topic1", "tag1", msg.getBytes());
        SendResult sendResult = producer.send(message);
        //输出发送结果
        System.out.println(sendResult);
        //关闭生产者(shutdown)
        producer.shutdown();

    }
}
