package com.Repository;

import com.Utils.JdbcUtils;

import java.sql.*;
import java.util.Properties;

/**
 * Created by Chira Paul on 3/7/2017.
 */
public class RepositoryAngajat implements IRepositoryAngajat {
    private JdbcUtils dbUtils;
    private Connection con;

    public RepositoryAngajat(Properties props) {
        this.dbUtils = new JdbcUtils(props);
        con = dbUtils.getConnection();
    }

    @Override
    public boolean validateAccount(String username, String password) {
        try (PreparedStatement preStmt = con.prepareStatement("select count(*) as SIZE from Angajat where username = ? and password = ?")) {
            preStmt.setString(1, username);
            preStmt.setString(2, password);
            try (ResultSet result = preStmt.executeQuery()) {
                if (result.next()) {
                    int nr = result.getInt("SIZE");
                    if (nr != 0) {
                        return true;
                    }
                    System.out.println("error");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
        return false;
    }

    @Override
    public int getIdAngajat(String username, String password) {
        try (PreparedStatement preStmt = con.prepareStatement("select idAngajat from Angajat where username = ? and password = ?")) {
            preStmt.setString(1, username);
            preStmt.setString(2, password);
            try (ResultSet result = preStmt.executeQuery()) {
                if (result.next()) {
                    int nr = result.getInt("idAngajat");
                    return nr;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
        return -1;
    }
}
