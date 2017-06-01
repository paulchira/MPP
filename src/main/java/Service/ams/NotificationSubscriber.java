package Service.ams;

import Model.ams.Notification;

/**
 * Created by Chira Paul on 5/8/2017.
 */
public interface NotificationSubscriber {
    void notificationReceived(Notification notification);
}
