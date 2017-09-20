# 消息中间件 (activeMQ) 实例
## queues 队列模式
*  创建工厂 ``ConnectionFactory factory = new ActiveMQConnectionFactory(URL);``
*  创建连接 ``Connection connection = factory.createConnection();``
*  开启连接 ``connection.start();``
*  创建Session  ``Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);``
    * false 为关闭事物
    * Session.AUTO_ACKNOWLEDGE 自动模式
*  创建目标（队列模式,名字） ``Destination dest = session.createQueue(name);``
*  创建消息生产者 ``MessageProducer producer = session.createProducer(dest);``
*  创建消息(通过session) ``TextMessage message = session.createTextMessage("队列消息");``
*  发送消息(通过生产者) ``producer.send(message);``
*  关闭连接 ``connection.close();``


## topics 主题模式
