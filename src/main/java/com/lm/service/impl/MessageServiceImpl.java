package com.lm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.lm.model.MesInfo;
import com.lm.service.MessageService;

@Service("msi")
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	private JmsTemplate jmsTemplate;

	public void send(MesInfo mi) {
		
		jmsTemplate.convertAndSend(mi);
	}

	@Override
	public void send2() {
		
		System.out.println("send2...................... @ msi");
		
		int i = 1/0;
	}
}
