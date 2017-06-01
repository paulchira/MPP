package Model.ams;

/**
 * Created by Chira Paul on 5/9/2017.
 */
public class Notification {
    private NotificationType type;
    private int sold;

    public Notification() {
    }

    public Notification(int sold) {
        this.sold = sold;
    }

    public Notification(NotificationType type) {

        this.type = type;
    }

    public Notification(NotificationType type, int sold) {
        this.type = type;
        this.sold = sold;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "type=" + type +
                ", sold=" + sold +
                '}';
    }
}
