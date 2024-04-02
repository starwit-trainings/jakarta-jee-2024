package org.eclipse.jakarta.jms;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.Schedule;
import jakarta.ejb.Stateless;
import jakarta.ejb.Timer;
import jakarta.jms.Connection;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSException;
import jakarta.jms.MessageProducer;
import jakarta.jms.Queue;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;

@Stateless
public class SenderBean {

    @Resource
	private ConnectionFactory cf;

    @Resource(lookup = "java:/jms/queue/mySampleQueue")
	private Queue queue;

	@PostConstruct
	private void setup() {
		System.out.println("preparing JMS example....");
	}    

    @Schedule(hour = "*", minute = "*", second = "*/5", info = "Every 5 seconds timer")
    public void automaticallyScheduled(Timer timer) {
        try {
            Connection connection = cf.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer producer = session.createProducer(queue);
			TextMessage message = session.createTextMessage("text");			
			producer.send(message);
			
			producer.close();
			connection.close();
		} catch (JMSException e) {
			System.out.println("couldn't send " + e.getMessage());
		} 
    }
    
}
