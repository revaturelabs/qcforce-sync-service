package com.revature.service;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.mapping.Collection;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.revature.domain.Batch;
import com.revature.domain.Form;
import com.revature.models.FormResponse;

@ExtendWith(SpringExtension.class)
class RabbitMQImplTest {

	@Mock
	ArrayList<FormResponse> mockArrayList;
	
	@Mock
	RabbitTemplate rabbitTemplate;
	
	@Mock
	DataFilterService dataFilterService;
	
	@Mock
	MessageConverter messageConverter;

	@Mock
	FormService formService;
	
	@InjectMocks
	RabbitMQImpl rabbitImpl;

	@Test
	public void testSendFormData()
	{
		List<FormResponse> fr=new ArrayList<FormResponse>();
		fr.add(new FormResponse());
		when(dataFilterService.mapFormResponses()).thenReturn(fr);
		Form f= new Form();
		f.setFormId(-1);
		when(formService.getFormById(1)).thenReturn(f);
		rabbitImpl.sendData();
	}
	
	@Test
	
	public void testSendFormDataInconsistentParameters()
	{
		List<FormResponse> fr=new ArrayList<FormResponse>();
		fr.add(new FormResponse());
		when(dataFilterService.mapFormResponses()).thenReturn(fr);
		Form f= new Form();
		f.setFormId(1);
		when(formService.getFormById(1)).thenReturn(f);
		rabbitImpl.sendData();
	}
	
	@Test
	public void testSendFormDataConvertAndSend()
	{
		when(dataFilterService.mapFormResponses()).thenReturn(null);
		Form f= new Form();
		f.setFormId(-1);
		rabbitImpl.sendData();
	}
	
	@Test
	public void testBatchFormData()
	{
		List<Batch> bd=new ArrayList<Batch>();
		bd.add(new Batch());
		rabbitImpl.sendBatchData(bd);
	}


}
