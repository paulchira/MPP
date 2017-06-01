package Server.ams;

import Model.ams.Notification;
import Model.ams.NotificationType;
import Service.ams.IServiceNotification;
import org.springframework.jms.core.JmsOperations;

/**
 * Created by Chira Paul on 5/11/2017.
 */
public class NotificationServiceImpl implements IServiceNotification {
    private JmsOperations jmsOperations;

    public NotificationServiceImpl(JmsOperations jmsOperations) {
        this.jmsOperations = jmsOperations;
    }

    @Override
    public void newTicketSold(int refreshTabel) throws Exception {
        System.out.println("new ticket buyed");
        Notification notification = new Notification(NotificationType.NEW_TICKET_BUY, 1);
        jmsOperations.convertAndSend(notification);
        System.out.println("Sent message to ActiveMQ... " + notification);
    }
}
