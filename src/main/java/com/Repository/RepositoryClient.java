package com.Repository;

import com.Utils.JdbcUtils;

import java.sql.*;
import java.util.Properties;

/**
 * Created by Chira Paul on 3/7/2017.
 */
public class RepositoryClient implements IRepositoryClient {
    private JdbcUtils dbUtils;
    private Connection con;

    public RepositoryClient(Properties props) {
        this.dbUtils = new JdbcUtils(props);
        con = dbUtils.getConnection();
    }

    @Override
    public boolean foundClient(String nume, String prenume, String adresa) {
        try (PreparedStatement preStmt = con.prepareStatement("select count(*) as SIZE from Client where nume = ? and prenume = ? and adresa = ?")) {
            preStmt.setString(1, nume);
            preStmt.setString(2, prenume);
            preStmt.setString(3, adresa);
            try (ResultSet result = preStmt.executeQuery()) {
                if (result.next()) {
                    int nr = result.getInt("SIZE");
                    if (nr != 0) {
                        return true;
                    }
                    System.out.println("errorr");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
        return false;
    }

    @Override
    public void insertClient(String nume, String prenume, String adresa) {
        try (PreparedStatement preStmt = con.prepareStatement("insert into Client(nume,prenume,adresa) values (?,?,?)")) {
            preStmt.setString(1, nume);
            preStmt.setString(2, prenume);
            preStmt.setString(3, adresa);
            int result = preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
    }

    @Override
    public int getIdClient(String nume, String prenume, String adresa) {
        try (PreparedStatement preStmt = con.prepareStatement("select idClient from Client where nume = ? and prenume = ? and adresa = ?")) {
            preStmt.setString(1, nume);
            preStmt.setString(2, prenume);
            preStmt.setString(3, adresa);
            try (ResultSet result = preStmt.executeQuery()) {
                if (result.next()) {
                    int nr = result.getInt("idClient");
                    return nr;
                }
                System.out.println("errorr");
            }
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
        return -1;
    }
}
