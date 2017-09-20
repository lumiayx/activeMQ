package com.yx.activeMQ.topics;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MQProvider {
	private static final String URL = "tcp://localhost:61616";

	private static final String name = "topics-test";

	public static void main(String[] args) throws JMSException {
		ConnectionFactory factory = new ActiveMQConnectionFactory(URL);
		Connection connection = factory.createConnection();
		connection.start();

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination dest = session.createTopic(name);
		MessageProducer producer = session.createProducer(dest);

		for (int i = 0; i < 200; i++) {
			TextMessage message = session.createTextMessage("消息：" + i);
			producer.send(message);
		}

		connection.close();

	}
}
