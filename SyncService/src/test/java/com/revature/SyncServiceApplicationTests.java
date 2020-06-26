package com.revature;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.config.RabbitMQConfig;
import com.revature.models.FormResponse;
import com.revature.service.GoogleSheets;


/**
 * @author Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 *
 */
@SpringBootTest
class SyncServiceApplicationTests {
	//TODO: Determine if we should leave these @AutoWired fields the way we see them.
	
	/** * */
	@Autowired
	GoogleSheets gSheet;
	
	/** * */
	@Autowired
	RabbitTemplate rabbit;
	
	/** * */
	@Autowired
	MessageConverter mc;
	
	/** * */
	@Value("FormResponse-Queue-Key")
	String routingKey;

	
	/**
	 * 
	 */
	@Test
	public void testConnectToSpreadSheet() {
		rabbit.setMessageConverter(mc);
		List<FormResponse> data = gSheet.getFormResponses();
		for (FormResponse row : data) {
			System.out.println(row.toString());
			rabbit.convertAndSend("WeisDirect",routingKey,row);
		}
	}
	
	/**
	 * @param values
	 */
	public void testMono(List<List<Object>> values) {
		
	}

}
