package com.mhc.demo;


import com.camaro.starter.mq.base.MessageBuilder;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.mhc.job.annotation.MHCJobConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p><p>
 *
 * @Auther: dsf （weizhen@maihaoche.com）
 * @Date: 2018/12/21 14:17
 * @since V1.0.0
 */
//# 简单作业任务
@Component
@MHCJobConfig(cron = "0/5 * * * * ?",name="TestTask", streamingProcess = false, overwrite = true, shardingTotalCount = 2, shardingItemParameters = "0=A,1=B")
public class TestTask implements SimpleJob {
    @Autowired
    MessageProducer messageProducer;
    @Override
    public void execute(ShardingContext shardingContext) {
        User user = new User();
        user.setPassword("111");
        user.setName("222");
        messageProducer.syncSend(MessageBuilder.of(user).topic("xiangzi_test").tag("syn_test").build());
    }
}
