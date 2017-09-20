package com.yx.activeMQ.topics;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MQCustomer {
	private static final String URL = "tcp://localhost:61616";

	private static final String name = "topics-test";

	public static void main(String[] args) throws JMSException {
		ConnectionFactory factory = new ActiveMQConnectionFactory(URL);
		Connection connection = factory.createConnection();
		connection.start();

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination dest = session.createTopic(name);
		MessageConsumer consumer = session.createConsumer(dest);

		consumer.setMessageListener(new MessageListener() {

			public void onMessage(Message message) {
				TextMessage msg = (TextMessage) message;
				try {
					System.out.println("消费者一:" + msg.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
		System.out.println("消费者一启动");

		// connection.close();

	}
}
