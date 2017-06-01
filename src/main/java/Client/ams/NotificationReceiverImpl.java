package Client.ams;

import Model.ams.Notification;
import Service.ams.NotificationReceiver;
import Service.ams.NotificationSubscriber;
import org.springframework.jms.core.JmsOperations;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * Created by Chira Paul on 5/11/2017.
 */
public class NotificationReceiverImpl implements NotificationReceiver {
    private JmsOperations jmsOperations;
    private boolean running;
    ExecutorService service;
    NotificationSubscriber subscriber;

    public NotificationReceiverImpl(JmsOperations jmsOperations) {
        this.jmsOperations = jmsOperations;
    }

    @Override
    public void start(NotificationSubscriber subscriber) {
        System.out.println("Starting notification receiver ...");
        running=true;
        this.subscriber=subscriber;
        service = Executors.newSingleThreadExecutor();
        service.submit(()->{run();});
    }

    @Override
    public void stop() {
        running=false;
        try {
            service.awaitTermination(100, TimeUnit.MILLISECONDS);
            service.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Stopped notification receiver");
    }

    private void run(){
        while(running){
            Notification notif=(Notification)jmsOperations.receiveAndConvert();
            System.out.println("Received Notification... "+notif);
            subscriber.notificationReceived(notif);

        }
    }
}
