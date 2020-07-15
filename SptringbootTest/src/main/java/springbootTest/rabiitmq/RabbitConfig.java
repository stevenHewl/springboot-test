package springbootTest.rabiitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

	@Bean(name = "Queue1")
	public Queue Queue1() {
		return new Queue("topic.query1");
	}
	
	@Bean(name = "Queue2")
	public Queue Queue2() {
		return new Queue("topic.query2");
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange("exchange");
	}

	@Bean
	Binding bindingExchangeQuery1(@Qualifier("Queue1") Queue Queue, TopicExchange exchange) {
		return BindingBuilder.bind(Queue).to(exchange).with("topic.query1");
	}

	// topic.# 可以匹配topic开头的队列
	@Bean
	Binding bindingExchangeQuery2(@Qualifier("Queue2") Queue Queue, TopicExchange exchange) {
		return BindingBuilder.bind(Queue).to(exchange).with("topic.#");
	}

	@Bean
	FanoutExchange fanoutExchange() {
		return new FanoutExchange("fanoutExchange");
	}

	// fanout 广播式给队列推送消息，只要消息生产着发的消息匹配上Exchange就推给相应的queue
	// 每个queue 都要绑定Exchange，虽然代码是一样的，区别在于Queue参数值，默认和queeu方法名相同，
	// 或者 @Qualifier("Queue1") 区分
	// @Qualifier 此注释可在字段或参数上用作自动装配时候选bean的限定符。它还可以用于注释其他自定义注释，然后这些注释可以作为限定符使用。
	/*
	 * @frameworkMy.Bean Binding bindingExchange(Queue Queue1, FanoutExchange fanoutExchange) {
	 * return BindingBuilder.bind(Queue1).to(fanoutExchange); }
	 * 
	 * @frameworkMy.Bean Binding bindingExchange2(Queue Queue2, FanoutExchange fanoutExchange) {
	 * return BindingBuilder.bind(Queue2).to(fanoutExchange); }
	 */

	@Bean
	Binding bindingExchange(@Qualifier("Queue1") Queue Queue, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(Queue).to(fanoutExchange);
	}

	@Bean
	Binding bindingExchange2(@Qualifier("Queue2") Queue Queue, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(Queue).to(fanoutExchange);
	}
}
