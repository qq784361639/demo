package com.mhc.demo;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;


/**
 * <p><p>
 *
 * @Auther: dsf （weizhen@maihaoche.com）
 * @Date: 2018/12/21 14:17
 * @since V1.0.0
 */
//# 简单作业任务
public class TestTask implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println("测试"+shardingContext.getShardingParameter());
    }
}
