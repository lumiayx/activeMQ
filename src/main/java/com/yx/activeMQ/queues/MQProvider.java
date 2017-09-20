package com.yx.activeMQ.queues;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class MQProvider {
	private static final String URL = "tcp://localhost:61616";

	private static final String name = "queues-test";

	public static void main(String[] args) throws JMSException {
		ConnectionFactory factory = new ActiveMQConnectionFactory(URL);
		Connection connection = factory.createConnection();
		connection.start();

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination dest = session.createQueue(name);
		MessageProducer producer = session.createProducer(dest);

		for (int i = 0; i < 200; i++) {
			TextMessage message = session.createTextMessage("消息：" + i);
			producer.send(message);
		}

		connection.close();

	}
}
