package com.revature.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 *
 */
@Configuration
public class RabbitMQConfig {
	
	/** * */
	@Value("FormResponse-Queue")
	public String queueName;
	
	/** * */
	@Value("FormResponse-Queue-Key")
	public static String routingKey;
	
	//TODO: This value should be changed - perhaps to "FormResponse-Exchange"
	/** * */
	@Value("FormResponse-Exchange")
	public static String exchange;
	
	/**
	 * @return
	 */
	@Bean
	public Queue queue() {
		return new Queue(queueName, false);
	}
	
	/**
	 * @return
	 */
	@Bean
	public DirectExchange exchange() {
		return new DirectExchange(exchange);
	}

	/**
	 * @param queue
	 * @param exchange
	 * @return
	 */
	@Bean
	public Binding binding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(routingKey);
	}

	/**
	 * @return
	 */
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	/**
	 * @param rabbitTemplate
	 * @param messageConverter
	 * @return
	 */
	public RabbitTemplate rabbitTemplate(RabbitTemplate rabbitTemplate, MessageConverter messageConverter) {
		rabbitTemplate.setMessageConverter(messageConverter);
		return rabbitTemplate;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public String getRoutingKey() {
		return routingKey;
	}

	public String getExchange() {
		return exchange;
	}

	
	

}
