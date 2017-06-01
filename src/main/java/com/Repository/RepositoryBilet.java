package com.Repository;

import Model.Bilet;
import com.Utils.JdbcUtils;

import java.sql.*;
import java.util.Properties;

/**
 * Created by Chira Paul on 3/7/2017.
 */
public class RepositoryBilet implements IRepositoryBilet {
    private JdbcUtils dbUtils;
    private Connection con;

    public RepositoryBilet(Properties props) {
        this.dbUtils = new JdbcUtils(props);
        con = dbUtils.getConnection();
    }

    @Override
    public void insertBilet(Bilet entity) {
        try (PreparedStatement preStmt = con.prepareStatement("insert into Bilet(idAngajat,idClient,idZbor,nrLoc) values (?,?,?,?)")) {
            preStmt.setInt(1, entity.getIdAngajat());
            preStmt.setInt(2, entity.getIdClient());
            preStmt.setInt(3, entity.getIdZbor());
            preStmt.setInt(4, entity.getNrBilet());
            int result = preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
    }
}
