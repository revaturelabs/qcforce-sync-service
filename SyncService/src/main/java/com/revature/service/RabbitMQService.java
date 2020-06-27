package com.revature.service;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revature.config.RabbitMQConfig;
import com.revature.models.FormResponse;

@Service
public class RabbitMQService implements MessageService{

	private RabbitTemplate rabbitTemplate;
	
	private MessageConverter messageConverter;
	
	private DataFilterService dataFilterService;
	
	/**
	 * @param rabbitTemplate
	 */
	@Autowired
	public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	/**
	 * @param dataRetrievalService
	 */
	@Autowired
	public void setDataFilterService(DataFilterService dataFilterService) {
		this.dataFilterService = dataFilterService;
	}

	/**
	 * @param messageConverter
	 */
	@Autowired
	public void setMessageConverter(MessageConverter messageConverter) {
		this.messageConverter = messageConverter;
	}
	
	@Override
	public void sendData()
	{
		rabbitTemplate.setMessageConverter(messageConverter);
		List<FormResponse> data = dataFilterService.mapFormResponses();
		System.out.println("Data:\n"+data);
		//for (FormResponse row : data) {
			rabbitTemplate.convertAndSend(RabbitMQConfig.exchange,RabbitMQConfig.routingKey,"Hello");
		//}
	}
}
