package com.mhc.demo;

import com.alibaba.fastjson.JSONObject;
import com.camaro.starter.mq.base.MessageBuilder;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p><p>
 *
 * @Auther: dsf （weizhen@maihaoche.com）
 * @Date: 2018/12/24 10:32
 * @since V1.0.0
 */
public class TestMQ {
    @Autowired
    private MessageProducer messageProducer;

    public static void main(String[] args) {
        User user = new User();
        user.setName("zhangsan");
        user.setPassword("123456");
        MessageProducer messageProducer = new MessageProducer();
        DefaultMQProducer producer = new DefaultMQProducer("local_pufang_producer");
        // Specify name server addresses.
        producer.setNamesrvAddr("172.21.10.116:9876");
        //Launch the instance.
        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        producer.setRetryTimesWhenSendAsyncFailed(0);
        Message build = MessageBuilder.of(user).topic("xiangzi_test").tag("syn_test").build();
        try {
            producer.send(build);
            producer.send(build);
            producer.send(build);
            producer.send(build);
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        producer.shutdown();


        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("CID_test");

        // Specify name server addresses.
        consumer.setNamesrvAddr("172.21.10.116:9876");

        // Subscribe one more more topics to consume.
        try {
            consumer.subscribe("xiangzi_test", "*");
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        // Register callback to execute on arrival of messages fetched from brokers.
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                MessageExt xiangzi_test = null;

                try {
                    xiangzi_test = consumer.viewMessage("xiangzi_test",msgs.get(0).getMsgId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                byte[] body = xiangzi_test.getBody();
                    String str= new String (body);
                    User user1 = JSONObject.parseObject(str, User.class);
                    System.out.println(user1);

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        //Launch the consumer instance.
        try {
            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        System.out.printf("Consumer Started.%n");







    }

}
