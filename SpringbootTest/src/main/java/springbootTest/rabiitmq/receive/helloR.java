package springbootTest.rabiitmq.receive;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import springbootTest.entity.User;

@Component
@RabbitListener(queues = "topic.query1")
public class helloR {
	@RabbitHandler
	public void process(User usr) {
		System.out.println("Receiver 1 : " + usr.username);
	}
}
