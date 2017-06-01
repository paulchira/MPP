package Service.ams;

/**
 * Created by Chira Paul on 5/8/2017.
 */
public interface NotificationReceiver {
    void start(NotificationSubscriber subscriber);
    void stop();
}
