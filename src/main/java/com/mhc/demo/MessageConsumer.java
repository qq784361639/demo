package com.mhc.demo;

import com.camaro.starter.mq.annotation.MQConsumer;
import com.camaro.starter.mq.base.AbstractMQPushConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p><p>
 *
 * @Auther: dsf （weizhen@maihaoche.com）
 * @Date: 2018/12/24 11:17
 * @since V1.0.0
 */
@Component
@Slf4j
@MQConsumer(topic = "xiangzi_test", consumerGroup = "CID_test", tag="syn_test1")
public class MessageConsumer extends AbstractMQPushConsumer<User> {
    @Override
    public boolean process(User user, Map<String, Object> map) {
        System.out.println(user.toString());
        return true;
    }
}
