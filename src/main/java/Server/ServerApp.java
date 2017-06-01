package Server;

import Model.Angajat;
import Model.Bilet;
import Model.Zbor;
import com.Repository.IRepositoryAngajat;
import com.Repository.IRepositoryBilet;
import com.Repository.IRepositoryClient;
import com.Repository.IRepositoryZbor;
import Service.IObserverService;
import Service.IServiceServer;

import java.rmi.RemoteException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Chira Paul on 4/1/2017.
 */
public class ServerApp implements IServiceServer{
    private IRepositoryAngajat repAn;
    private IRepositoryZbor repZbor;
    private IRepositoryClient repClient;
    private IRepositoryBilet repBilet;
    private Map<String, IObserverService> loggedClients;
    private final int defaultThreadsNo = 5;


    public ServerApp(IRepositoryAngajat repAn, IRepositoryZbor repZbor, IRepositoryClient repClient, IRepositoryBilet repBilet) {
        this.repAn = repAn;
        this.repClient = repClient;
        this.repBilet = repBilet;
        this.repZbor = repZbor;
        loggedClients = new ConcurrentHashMap<>();
    }

    @Override
    public synchronized void login(Angajat angj, IObserverService client) throws Exception {
        if (repAn.validateAccount(angj.getUsername(), angj.getPassword())) {
            if (loggedClients.get(angj.getUsername()) != null) {
                throw new Exception("angajat logat");
            }
            loggedClients.put(angj.getUsername(), client);
            //notifyAllClients();
        } else {
            throw new Exception("login failed");
        }
    }


    private void notifyAllClients() {
        ExecutorService executorService = Executors.newFixedThreadPool(defaultThreadsNo);
        for (int i = 0; i < loggedClients.size(); i++) {
            executorService.execute(() -> {
                try {
                    System.out.println("angajat nou conectat");
                } catch (Exception e) {
                    System.out.println(e);
                }
            });
        }
        executorService.shutdown();
    }
    private void notifyBuyTicket() {
        ExecutorService executor= Executors.newFixedThreadPool(defaultThreadsNo);

        for (Map.Entry<String, IObserverService> entry : loggedClients.entrySet())
        {
            //System.out.println(entry.getKey() + "/" + entry.getValue());
            IObserverService serviceClient=entry.getValue();
            if (serviceClient!=null)
                executor.execute(() -> {
                    try {
                        System.out.println("Notifying angajat refresh tabel");
                        serviceClient.newTicketSold(1);
                    } catch (RemoteException e) {
                        System.err.println("Error notifying refresh tabel " + e);
                    }
                });
        }
        executor.shutdown();
    }
    @Override
    public synchronized void logout(Angajat angj, IObserverService client) throws Exception {
        IObserverService localClient = loggedClients.remove(angj.getId());
        if (localClient == null) {
            throw new Exception("user not logged in");
        }
        notifyAllClients();
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
        notifyBuyTicket();

    }

    @Override
    public synchronized Zbor getZbor(String destinatia, String data) throws Exception {
        return repZbor.getZbor(destinatia, data);
    }

}
