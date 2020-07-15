package springbootTest.rabiitmq.receive;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import springbootTest.entity.User;

@Component
@RabbitListener(queues = "topic.query2")
public class msgReceive {
	@RabbitHandler
	public void process(User usr) {
		System.out.println("Receiver 2 : " + usr.username);
	}
}