package myapp.Model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Chira Paul on 5/18/2017.
 */
public class Zbor implements Serializable, Comparable<Zbor>{
    private int id;
    private String dest;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date data;
    private String aeroport;
    private int nrLoc;

    public Zbor(String dest, String aeroport, int nrLoc) {
        this.id = -1;
        this.dest = dest;
        this.data = null;
        this.aeroport = aeroport;
        this.nrLoc = nrLoc;
    }

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

    @Override
    public int compareTo(Zbor o) {
        return dest.compareTo(o.getDest());
    }
}
