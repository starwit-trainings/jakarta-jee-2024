package services;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSProducer;
import jakarta.jms.Message;
import jakarta.jms.Queue;

@Stateless
public class TrainingSenderService {
 
    @Resource
	private ConnectionFactory connectionFactory;

    @Resource(lookup = "java:/jms/queue/TrainingQueue")
	private Queue queue;

	@PostConstruct
	private void setup() {
		System.out.println("preparing JMS example....");
	}    

    public void sendTrainingTitleMessage(String title) {
		try (JMSContext context = connectionFactory.createContext();) {
		    JMSProducer producer = context.createProducer();
			Message message = context.createTextMessage(title);
			producer.send(queue, message);
		} 
    }
    
}
