package com.revature.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 *
 */
@Configuration
public class RabbitMQConfig {

	/** * */
	public final static String queueFormResponse = "FormResponse-Queue";
	/** * */
	public final static String batchFormResponse = "BatchData-Queue";
	/** * */
	public final static String routingKeyFormResponse = "FormResponse-Queue-Key";
	/** * */
	public final static String routingKeyBatchData = "BatchData-Key";
	/** * */
	public final static String exchangeFormResponse = "FormResponse-Exchange";
	/** * */
	public final static String exchangeBatchData = "BatchData-Exchange";

	/**
	 * @return
	 */
	@Bean
	public Queue queue() {
		return new Queue(queueFormResponse, false);
	}

	/**
	 * @return
	 */
	@Bean
	public Queue queue2() {
		return new Queue(batchFormResponse, false);
	}

	/**
	 * @return
	 */
	@Bean
	public DirectExchange exchange() {
		return new DirectExchange(exchangeFormResponse);
	}

	/**
	 * @return
	 */
	@Bean
	public DirectExchange exchange2() {
		return new DirectExchange(exchangeBatchData);
	}

	/**
	 * @param queue
	 * @param exchange
	 * @return
	 */
	@Bean
	public Binding binding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(routingKeyFormResponse);
	}

	/**
	 * @param queue2
	 * @param exchange2
	 * @return
	 */
	@Bean
	public Binding binding2(Queue queue2, DirectExchange exchange2) {
		return BindingBuilder.bind(queue2).to(exchange2).with(routingKeyBatchData);
	}

	/**
	 * @return
	 */
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

}
