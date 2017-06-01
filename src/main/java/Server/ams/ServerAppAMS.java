package Server.ams;

import Model.Angajat;
import Model.Bilet;
import Model.Zbor;
import com.Repository.IRepositoryAngajat;
import com.Repository.IRepositoryBilet;
import com.Repository.IRepositoryClient;
import com.Repository.IRepositoryZbor;
import Service.ams.IServiceNotification;
import Service.ams.IServiceServerAMS;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Chira Paul on 4/1/2017.
 */
public class ServerAppAMS implements IServiceServerAMS{
    private IRepositoryAngajat repAn;
    private IRepositoryZbor repZbor;
    private IRepositoryClient repClient;
    private IRepositoryBilet repBilet;
    private Map<String, Angajat> loggedClients;
    private IServiceNotification serviceNotification;

    public ServerAppAMS(IRepositoryAngajat repAn, IRepositoryZbor repZbor, IRepositoryClient repClient, IRepositoryBilet repBilet, IServiceNotification serviceNotification) {
        this.repAn = repAn;
        this.repClient = repClient;
        this.repBilet = repBilet;
        this.repZbor = repZbor;
        this.serviceNotification = serviceNotification;
        loggedClients = new ConcurrentHashMap<>();
    }

    @Override
    public synchronized void login(Angajat angj) throws Exception {
        if (repAn.validateAccount(angj.getUsername(), angj.getPassword())) {
            if (loggedClients.get(angj.getUsername()) != null) {
                throw new Exception("angajat logat");
            }
            loggedClients.put(angj.getUsername(), new Angajat(-1,angj.getUsername(), angj.getPassword()));
            //notifyAllClients();
        } else {
            throw new Exception("login failed");
        }
    }

    @Override
    public synchronized void logout(Angajat angj) throws Exception {
        Angajat localClient = loggedClients.remove(angj.getId());
        if (localClient == null) {
            throw new Exception("user not logged in");
        }
    }


    @Override
    public synchronized int getIdAngajat(String username, String password) throws Exception {
        return repAn.getIdAngajat(username, password);
    }

    @Override
    public synchronized Zbor[] getAllZbor() throws Exception {
        return repZbor.getAllZbor().toArray(new Zbor[repZbor.getAllZbor().size()]);
    }

    @Override
    public synchronized Zbor[] getSearchZbor(String destinatia, String data) throws Exception {
        return repZbor.getSearchZbor(destinatia, data).toArray(new Zbor[repZbor.getSearchZbor(destinatia, data).size()]);
    }

    @Override
    public synchronized boolean foundClient(String nume, String prenume, String adresa) throws Exception {
        return repClient.foundClient(nume, prenume, adresa);
    }

    @Override
    public synchronized void insertClient(String nume, String prenume, String adresa) throws Exception {
        repClient.insertClient(nume, prenume, adresa);
    }

    @Override
    public synchronized void insertBilet(Bilet entity) throws Exception {
        repBilet.insertBilet(entity);
    }

    @Override
    public synchronized int getIdClient(String nume, String prenume, String adresa) throws Exception {
        return repClient.getIdClient(nume, prenume, adresa);
    }

    @Override
    public synchronized void update(Zbor newEntity, int id) throws Exception {
        repZbor.update(newEntity, id);
        serviceNotification.newTicketSold(1);

    }

    @Override
    public synchronized Zbor getZbor(String destinatia, String data) throws Exception {
        return repZbor.getZbor(destinatia, data);
    }

}
