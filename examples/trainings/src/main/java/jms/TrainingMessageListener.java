package jms;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;

public class TrainingMessageListener implements MessageListener {

  public void onMessage(Message message) {
    TextMessage textMessage = (TextMessage) message;
    try {
      System.out.println("Message received: " + textMessage.getText());
    } catch (JMSException e) {
      System.out.println(
          "Error while trying to consume messages: " + e.getMessage());
    }
  }
}
