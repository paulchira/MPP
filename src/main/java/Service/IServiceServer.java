package Service;

import Model.Angajat;
import Model.Bilet;
import Model.Zbor;

/**
 * Created by Chira Paul on 4/1/2017.
 */
public interface IServiceServer{
    void login(Angajat angj, IObserverService client) throws Exception;

    void logout(Angajat angj, IObserverService client) throws Exception;

    int getIdAngajat(String username, String password) throws Exception;

    Zbor[] getAllZbor() throws Exception;

    Zbor[] getSearchZbor(String destinatia, String data) throws Exception;

    boolean foundClient(String nume, String prenume, String adresa) throws Exception;

    void insertClient(String nume, String prenume, String adresa) throws Exception;

    void insertBilet(Bilet entity) throws Exception;

    int getIdClient(String nume, String prenume, String adresa) throws Exception;

    void update(Zbor newEntity, int id) throws Exception;

    Zbor getZbor(String destinatia, String data) throws Exception;
}
