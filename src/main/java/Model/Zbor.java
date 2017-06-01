package Model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Chira Paul on 3/8/2017.
 */
public class Zbor implements Serializable {
    private int id;
    private String dest;
    private Date data;
    private String aeroport;
    private int nrLoc;

    public Zbor(){
        this.id = -1;
        this.dest = "";
        this.data = null;
        this.aeroport = "'";
        this.nrLoc = -1;
    }
    public Zbor(int id,String dest,Date data,String aeroport,int nrLoc){
        this.id = id;
        this.dest = dest;
        this.data = data;
        this.aeroport = aeroport;
        this.nrLoc = nrLoc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getAeroport() {
        return aeroport;
    }

    public void setAeroport(String aeroport) {
        this.aeroport = aeroport;
    }

    public int getNrLoc() {
        return nrLoc;
    }

    public void setNrLoc(int nrLoc) {
        this.nrLoc = nrLoc;
    }

    @Override
    public String toString() {
        return "Zbor{" +
                "id=" + id +
                ", dest='" + dest + '\'' +
                ", data=" + data +
                ", aeroport='" + aeroport + '\'' +
                ", nrLoc=" + nrLoc +
                '}';
    }
}
