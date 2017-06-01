package Client.ams;

import Model.Angajat;
import Model.Bilet;
import Model.Zbor;
import Model.ams.Notification;
import Service.IObserverService;
import Service.IServiceServer;
import Service.ams.IServiceServerAMS;
import Service.ams.NotificationReceiver;
import Service.ams.NotificationSubscriber;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Chira Paul on 4/1/2017.
 */
@Service
public class ControllerObserverAMS extends UnicastRemoteObject implements NotificationSubscriber, Serializable {

    private IServiceServerAMS serviceServer;
    private Angajat angajat;
    public BooleanProperty sold;

    public void setReceiver(NotificationReceiver receiver) {
        this.receiver = receiver;
    }

    private NotificationReceiver receiver;

    public ControllerObserverAMS(IServiceServerAMS serviceServer) throws Exception {
        this.serviceServer = serviceServer;
        sold = new SimpleBooleanProperty(false);
        System.out.println(serviceServer.getAllZbor().length);

    }

    @Override
    public void notificationReceived(Notification notification) {
        try {
            System.out.println("Ctrl notificationReceived ... " + notification.getType());
            SwingUtilities.invokeLater(() -> {
                switch (notification.getType()) {
                    case NEW_TICKET_BUY: {
                        if(notification.getSold() == 1){
                            sold.setValue(true);
                        }
                        break;
                    }

                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void login(String username, String password) throws Exception {
        Angajat userL = new Angajat(-1, username, password);
        serviceServer.login(userL);
        angajat = userL;
        receiver.start(this);
    }


    public void newTicketSold(int refreshTabel) {
        if (refreshTabel == 1) {
            sold.setValue(true);
        }
    }

    public Zbor[] receiveAllZbor() {
        try {
            return serviceServer.getAllZbor();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Zbor[] receiveSearchZbor(String destinatia, String data) {
        try {
            return serviceServer.getSearchZbor(destinatia, data);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public int getIdAngajat(String username, String password) {
        try {
            return serviceServer.getIdAngajat(username, password);
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }

    public boolean foundClient(String nume, String prenume, String adresa) {
        try {
            return serviceServer.foundClient(nume, prenume, adresa);
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public void insertClient(String nume, String prenume, String adresa) {
        try {
            serviceServer.insertClient(nume, prenume, adresa);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insertBilet(Bilet entity) {
        try {
            serviceServer.insertBilet(entity);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int getIdClient(String nume, String prenume, String adresa) {
        try {
            return serviceServer.getIdClient(nume, prenume, adresa);
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }

    public void update(Zbor newEntity, int id) {
        try {
            serviceServer.update(newEntity, id);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void logout() {
        try {
            serviceServer.logout(angajat);
        } catch (Exception e) {
            System.out.println("Logout error "+e);
        }finally {
            receiver.stop();
        }
    }

}
