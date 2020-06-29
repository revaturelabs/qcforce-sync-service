package com.revature.service;

import java.util.List;

import org.mortbay.log.Log;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

import com.revature.AppLogger;
import com.revature.config.RabbitMQConfig;
import com.revature.domain.Batch;
import com.revature.domain.Form;
import com.revature.models.FormResponse;

/**
 * Use to send Google Sheets form response data to a RabbitMQ service.
 * @author Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 */
@Service
public class RabbitMQImpl implements MessageService {

	
	/**
	 * Instance of a Rabbit Template.
	 */
	private RabbitTemplate rabbitTemplate;

	
	/**
	 * Instance of a Message Converter.
	 */
	private MessageConverter messageConverter;

	/**
	 * Instance of a Data Filter Service.
	 */
	private DataFilterService dataFilterService;

	/**
	 * Instance of a Form Service.
	 */
	private FormService formService;

	
	/**
	 * Initializes all services.
	 * @param rabbitTemplate Rabbit Template bean.
	 * @param messageConverter Message Converter bean.
	 * @param dataFilterService Data Filter Service bean.
	 * @param formService Form Service bean.
	 */
	public RabbitMQImpl(RabbitTemplate rabbitTemplate, MessageConverter messageConverter,
			DataFilterService dataFilterService, FormService formService) {
		super();
		this.rabbitTemplate = rabbitTemplate;
		this.messageConverter = messageConverter;
		this.dataFilterService = dataFilterService;
		this.formService = formService;
	}


	@Override
	public void sendData() {
		rabbitTemplate.setMessageConverter(messageConverter);
		List<FormResponse> data = dataFilterService.mapFormResponses();
		for (FormResponse row : data) {
			if ((formService.getFormById(1).getFormId() + 1) == GoogleRetrievalImpl.currentRow) {
				try {
					rabbitTemplate.convertAndSend(RabbitMQConfig.exchangeFormResponse,
							RabbitMQConfig.routingKeyFormResponse, row);
					// Updates
					Form f = new Form();
					f.setId(1);
					f.setFormId(GoogleRetrievalImpl.currentRow);
					formService.updateForm(f);
					GoogleRetrievalImpl.currentRow+=1;
				} catch (Exception e) {
					AppLogger.log.error("Insertion Issue check connection or cue configuration");
				}
			} else {
				AppLogger.log.error("Exited due to inconsistent sync parameters");
				break;
			}
		}
		AppLogger.log.info("Sent "+data.size()+" forms successfully");
	}


	@Override
	public void sendBatchData(List<Batch> data) {
		rabbitTemplate.setMessageConverter(messageConverter);
		for (Batch batchData : data) {
			rabbitTemplate.convertAndSend(RabbitMQConfig.exchangeBatchData, RabbitMQConfig.routingKeyBatchData,
					batchData);
		}
		Log.debug("in send batch data");
	}

}
