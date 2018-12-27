package com.mhc.demo;

import com.alibaba.fastjson.JSON;
import com.camaro.starter.mq.annotation.MQConsumer;
import com.camaro.starter.mq.base.AbstractMQPushConsumer;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * <p><p>
 *
 * @Auther: dsf （weizhen@maihaoche.com）
 * @Date: 2018/12/27 10:39
 * @since V1.0.0
 */
@Slf4j
@MQConsumer(topic = "xiangzi_test",consumerGroup = "CID_test1",tag = "syn_test")
public class PasaatConsumer extends AbstractMQPushConsumer<Object> {
    @Override
    public boolean process(Object o, Map<String, Object> map) {
        Object o1 = JSON.toJSON(o);
        System.out.println(o1.toString());
        return true;
    }
}
