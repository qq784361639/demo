package com.mhc.demo;

import com.camaro.starter.mq.base.MessageBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	@Autowired
	private MessageProducer messageProducer;
	@Test
	public void testMQ(){
		User user = new User();
		user.setName("zhangsan");
		user.setPassword("123456");
		messageProducer.syncSend(MessageBuilder.of(user).topic("xiangzi_test").tag("syn_test").build());
	}

}
