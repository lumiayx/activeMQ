# 消息中间件 (activeMQ) 实例
> 队列模式和主题模式的区别：
>>  1.创建目标方式不同：
>>>     队列模式 ``session.createQueue(name);``
>>>     主题模式 ``session.createTopic(name);``
>>  2.消费者消费方式不同(连接关闭后不消费)：
>>>     队列：通过连接，均匀分配（生产者先后提供都可用）
>>>     主题模式：通过订阅，消费所有消息(每个连接消费相同), 订阅之前的消息消费不了
## queues 队列模式
### 生产者
      *  创建工厂: ``ConnectionFactory factory = new ActiveMQConnectionFactory(URL);``
      *  创建连接: ``Connection connection = factory.createConnection();``
      *  开启连接: ``connection.start();``
      *  创建Session:  ``Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);``
        * false: 为关闭事物
        * Session.AUTO_ACKNOWLEDGE: 自动模式
      *  创建目标（队列模式,名字）: ``Destination dest = session.createQueue(name);``
      *  创建消息生产者: ``MessageProducer producer = session.createProducer(dest);``
      *  创建消息(通过session): ``TextMessage message = session.createTextMessage("队列消息");``
      *  发送消息(通过生产者): ``producer.send(message);``
      *  关闭连接: ``connection.close();``

### 消费者
      * 创建消费者   ``MessageConsumer consumer = session.createConsumer(dest);``
      * 消费者创建消息监听：  ``consumer.setMessageListener(new MessageListener() {onMessage();})``


## topics 主题模式
*  创建目标（模式,名字） ``Destination dest = session.createQueue(name);``
