package Model;

import java.io.Serializable;

/**
 * Created by Chira Paul on 3/8/2017.
 */
public class Angajat implements Serializable{
    private int id;
    private String username;
    private String password;

    public Angajat() {
        this(-1);
    }

    public Angajat(int id) {
        this(id,"","");
    }

    public Angajat(int id, String username) {
        this(id,username,"");
    }
    public Angajat(int id,String username,String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
