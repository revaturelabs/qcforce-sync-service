package com.revature.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.client.AMQP.Exchange;

/**
 * RabbitMQ main configurations used throught the application.
 * @author Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 */
@Configuration
public class RabbitMQConfig {

	/**
	 * This {@link String} represents the name of the queue for form messages. 
	 */
	public final static String queueFormResponse = "FormResponse-Queue";
	/**
	 * This {@link String} represents the name of the queue for batch messages. 
	 */
	public final static String batchFormResponse = "BatchData-Queue";
	/**
	 * This {@link String} represents the key needed to access the form queue. 
	 */
	public final static String routingKeyFormResponse = "FormResponse-Queue-Key";
	/**
	 * This {@link String} represents the key needed to access the batch queue. 
	 */
	public final static String routingKeyBatchData = "BatchData-Key";
	/**
	 * This {@link String} represents the name of the exchange for forms. 
	 */
	public final static String exchangeFormResponse = "FormResponse-Exchange";
	/**
	 * This {@link String} represents the name of the exchange for batches. 
	 */
	public final static String exchangeBatchData = "BatchData-Exchange";

	/**
	 * Creates a {@link Queue} form Forms.
	 * @return a {@link Queue} element and makes a bean for forms.
	 */
	@Bean
	public Queue queueForms() {
		return new Queue(queueFormResponse, true);
	}

	/**
	 * Creates a {@link Queue} for Batches.
	 * @return a {@link Queue} element and makes a bean for batches.
	 */
	@Bean
	public Queue queueBatches() {
		return new Queue(batchFormResponse, true);
	}

	/**
	 * Creates an {@link DirectExchange} for Forms.
	 * @return a {@link DirectExchange} element and makes a bean for forms.
	 */
	@Bean
	public DirectExchange exchangeForms() {
		return new DirectExchange(exchangeFormResponse);
	}

	/**
	 * Creates an {@link DirectExchange} for Batches.
	 * @return a {@link DirectExchange} element and makes a bean for batches.
	 */
	@Bean
	public DirectExchange exchangeBatches() {
		return new DirectExchange(exchangeBatchData);
	}

	/**
	 * Binds a {@link Queue} to an {@link DirectExchange} with the routing key.
	 * @param queueForms represents a {@link Queue} element which will be bound to an exchange.
	 * @param exchangeForms represents the {@link Exchange} a queue will be bound to.
	 * @return a {@link Binding} between a {@link Queue} and an {@link DirectExchange}
	 */
	@Bean
	public Binding bindingForms(Queue queueForms, DirectExchange exchangeForms) {
		return BindingBuilder.bind(queueForms).to(exchangeForms).with(routingKeyFormResponse);
	}

	/**
	 * Binds a {@link Queue} to an {@link DirectExchange} with the routing key.
	 * @param queueBatches represents a {@link Queue} element which will be bound to an exchange.
	 * @param exchangeBatches represents the {@link Exchange} a queue will be bound to.
	 * @return a {@link Binding} between a {@link Queue} and an {@link DirectExchange}
	 */
	@Bean
	public Binding bindingBatches(Queue queueBatches, DirectExchange exchangeBatches) {
		return BindingBuilder.bind(queueBatches).to(exchangeBatches).with(routingKeyBatchData);
	}

	/**
	 * Creates a {@link MessageConverter} for converting JSON data.
	 * @return a {@link MessageConverter} used to convert object before passing them to a {@link DirectExchange}
	 */
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

}
