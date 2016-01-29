package com.lm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.lm.model.MesInfo;
import com.lm.service.MessageService;

@Service("msi2")
public class MessageServiceImpl2 implements MessageService{
	
	@Autowired
	private JmsTemplate jmsTemplate;

	public void send(MesInfo mi) {
		
		jmsTemplate.convertAndSend(mi);
	}

	@Override
	public void send2() {
		
		System.out.println("send2...................... @ msi2");
		
		//int i = 1/0;
	}
}
