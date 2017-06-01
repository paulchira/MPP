package Model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Chira Paul on 3/8/2017.
 */
public class Bilet implements Serializable {
    private int id;
    private int idAngajat;
    private int idClient;
    private int idZbor;
    private int nrBilet;

    public Bilet(int id, int idAngajat, int idClient, int idZbor, int nrBilet) {
        this.id = id;
        this.idAngajat = idAngajat;
        this.idClient = idClient;
        this.idZbor = idZbor;
        this.nrBilet = nrBilet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAngajat() {
        return idAngajat;
    }

    public void setIdAngajat(int idAngajat) {
        this.idAngajat = idAngajat;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdZbor() {
        return idZbor;
    }

    public void setIdZbor(int idZbor) {
        this.idZbor = idZbor;
    }

    public int getNrBilet() {
        return nrBilet;
    }

    public void setNrBilet(int nrBilet) {
        this.nrBilet = nrBilet;
    }

    @Override
    public String toString() {
        return "Bilet{" +
                "id=" + id +
                ", idAngajat=" + idAngajat +
                ", idClient=" + idClient +
                ", idZbor=" + idZbor +
                ", nrBilet=" + nrBilet +
                '}';
    }
}

