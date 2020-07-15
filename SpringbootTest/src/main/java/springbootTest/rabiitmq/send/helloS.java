package springbootTest.rabiitmq.send;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import springbootTest.entity.User;

@Component
public class helloS {
	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send(User user1, User user2, User user3) {
		// String msg = "hello " + new Date();
		// System.out.println("Sender : " + msg);
		// this.rabbitTemplate.convertAndSend("hello", msg);
		// for (int i = 0; i < 100; i++) {
		// this.rabbitTemplate.convertAndSend("hello", user);
		// }

		this.rabbitTemplate.convertAndSend("exchange", "topic.query1", user1);
		this.rabbitTemplate.convertAndSend("exchange", "topic.query2", user2);
		this.rabbitTemplate.convertAndSend("fanoutExchange", "", user3);
	}
}
