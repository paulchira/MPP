package Service;

import Model.Zbor;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Chira Paul on 4/1/2017.
 */
public interface IObserverService extends Remote{
    void newTicketSold(int refreshTabel) throws RemoteException;
    Zbor[] receiveAllZbor() throws RemoteException;
    Zbor[] receiveSearchZbor(String destinatia, String data) throws RemoteException;
}
