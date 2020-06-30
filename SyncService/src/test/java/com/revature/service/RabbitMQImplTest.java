package com.revature.service;

import static org.hamcrest.CoreMatchers.any;
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
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
		rabbitImpl =new RabbitMQImpl(rabbitTemplate, messageConverter, dataFilterService, formService);
		dataFilterService = mock(DataFilterService.class, Mockito.RETURNS_DEEP_STUBS);
//		formService = mock(FormService.class, Mockito.RETURNS_DEEP_STUBS);
		List<FormResponse> fr=new ArrayList<FormResponse>();
		when(dataFilterService.mapFormResponses()).thenReturn(fr);
		Form f= new Form();
		f.setFormId(-1);
		when(formService.getFormById(1)).thenReturn(f);
		rabbitImpl.sendData();
	}
	
	@Test
	public void testSendBatchData()
	{
		
	}
	

}
