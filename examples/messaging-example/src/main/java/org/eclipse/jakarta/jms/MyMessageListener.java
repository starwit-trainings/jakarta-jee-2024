package org.eclipse.jakarta.jms;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/mySampleQueue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue")
})
public class MyMessageListener implements MessageListener {

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
