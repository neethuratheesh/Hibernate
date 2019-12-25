package com.h2kInfosys.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TopicSender {
         private static final String brokenUrl="tcp://localhost:61616";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		//Step 1 Connection Factory
		ConnectionFactory conf = new ActiveMQConnectionFactory(brokenUrl);
		//Step 2 Create Connection
		Connection connection = conf.createConnection();
		connection.start();
		//Step 3 Create Session
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//Step 4 Create Topic
		Topic topic = session.createTopic("H2k.Topic2019");
		//Step 5 Create Producer
		MessageProducer producer = session.createProducer(topic);
		//Step 6 Create Message
		TextMessage textmessage=session.createTextMessage("My First Topic Message");
		//Step 7
		producer.send(textmessage);
		System.out.println("Message send");
		session.close();
		connection.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
