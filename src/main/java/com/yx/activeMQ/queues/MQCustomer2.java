package com.yx.activeMQ.queues;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class MQCustomer2 {
	private static final String URL = "tcp://localhost:61616";

	private static final String name = "queues-test";

	public static void main(String[] args) throws JMSException {
		ConnectionFactory factory = new ActiveMQConnectionFactory(URL);
		Connection connection = factory.createConnection();
		connection.start();

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination dest = session.createQueue(name);
		MessageConsumer consumer = session.createConsumer(dest);

		consumer.setMessageListener(new MessageListener() {

			public void onMessage(Message message) {
				TextMessage msg = (TextMessage) message;
				try {
					System.out.println("消费者二:" + msg.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
		System.out.println("消费者二启动");
		// connection.close();

	}
}
