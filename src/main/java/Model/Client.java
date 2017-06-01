package Model;

import java.io.Serializable;

/**
 * Created by Chira Paul on 3/18/2017.
 */
public class Client implements Serializable {
    private int id;
    private String nume;
    private String prenume;
    private String adresa;

    public Client(int id,String nume,String prenume,String adresa){
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.adresa = adresa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", adresa='" + adresa + '\'' +
                '}';
    }
}
