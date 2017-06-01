package Service.ams;

import Model.Angajat;
import Model.Bilet;
import Model.Zbor;
import Service.IObserverService;

/**
 * Created by Chira Paul on 4/1/2017.
 */
public interface IServiceServerAMS {
    void login(Angajat angj) throws Exception;

    void logout(Angajat angj) throws Exception;

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
