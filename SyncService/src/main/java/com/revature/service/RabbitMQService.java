package com.revature.service;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.config.RabbitMQConfig;
import com.revature.domain.Batch;
import com.revature.domain.Form;
import com.revature.models.FormResponse;

@Service
public class RabbitMQService implements MessageService {

	private RabbitTemplate rabbitTemplate;

	private MessageConverter messageConverter;

	private DataFilterService dataFilterService;

	/** * */
	private FormService formService;

	/**
	 * @param formService
	 */
	@Autowired
	public void setFormService(FormService formService) {
		this.formService = formService;
	}

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
	public void sendData() {
		rabbitTemplate.setMessageConverter(messageConverter);
		List<FormResponse> data = dataFilterService.mapFormResponses();
		System.out.println("Data:\n" + data);
		for (FormResponse row : data) {
			System.out.println("Current Row:" + GoogleRetrievalService.currentRow);
			System.out.println("Database Row:" + (formService.getFormById(1).getFormId() + 1));
			if ((formService.getFormById(1).getFormId() + 1) == GoogleRetrievalService.currentRow) {
				try {
					rabbitTemplate.convertAndSend(RabbitMQConfig.exchangeFormResponse,
							RabbitMQConfig.routingKeyFormResponse, row);
					// Updates
					Form f = new Form();
					f.setId(1);
					f.setFormId(GoogleRetrievalService.currentRow);
					formService.updateForm(f);
					GoogleRetrievalService.currentRow += 1;
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Insertion Issue check connection or cue configuration");
				}
			} else {
				System.out.println("Exited due to inconsistent sync parameters");
				break;
			}
		}
	}

	@Override
	public void sendBatchData(List<Batch> data) {
		rabbitTemplate.setMessageConverter(messageConverter);
		for (Batch batchData : data) {
			System.out.println(batchData.toString());
			rabbitTemplate.convertAndSend(RabbitMQConfig.exchangeBatchData, RabbitMQConfig.routingKeyBatchData,
					batchData);
		}
	}

}
