package Client.gui;

import Model.Angajat;
import Model.Bilet;
import Model.Zbor;
import Service.IObserverService;
import Service.IServiceServer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Chira Paul on 4/1/2017.
 */
public class ControllerObserver extends UnicastRemoteObject implements IObserverService,Serializable {

    private IServiceServer serviceServer;
    private Angajat angajat;
    public BooleanProperty sold;
    public ControllerObserver(IServiceServer serviceServer) throws RemoteException{
        this.serviceServer = serviceServer;
        sold = new SimpleBooleanProperty(false);

    }


    public void login(String username, String password) throws Exception {
        Angajat userL = new Angajat(-1, username, password);
        serviceServer.login(userL, this);
        angajat = userL;
    }

    @Override
    public void newTicketSold(int refreshTabel) {
       if(refreshTabel == 1){
           sold.setValue(true);
       }
    }

    @Override
    public Zbor[] receiveAllZbor() {
        try {
            return serviceServer.getAllZbor();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
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

    boolean foundClient(String nume, String prenume, String adresa) {
        try {
            return serviceServer.foundClient(nume, prenume, adresa);
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    void insertClient(String nume, String prenume, String adresa) {
        try {
            serviceServer.insertClient(nume, prenume, adresa);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void insertBilet(Bilet entity) {
        try {
            serviceServer.insertBilet(entity);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    int getIdClient(String nume, String prenume, String adresa) {
        try {
            return serviceServer.getIdClient(nume, prenume,adresa);
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }

    void update(Zbor newEntity, int id) {
        try {
            serviceServer.update(newEntity, id);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
