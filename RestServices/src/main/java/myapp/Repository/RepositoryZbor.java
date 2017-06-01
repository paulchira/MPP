package myapp.Repository;

import myapp.Utils.JdbcUtils;
import myapp.Model.Zbor;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Chira Paul on 3/7/2017.
 */

public class RepositoryZbor implements IRepositoryZbor {
    private JdbcUtils dbUtils;
    private Connection con;

    public RepositoryZbor(Properties props) {
        this.dbUtils = new JdbcUtils(props);
        con = dbUtils.getConnection();
    }


    @Override
    public List<Zbor> getAllZbor() {
        List<Zbor> curse = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM Zbor WHERE nrLoc > 0");
            while (result.next()) {
                Zbor z = new Zbor(result.getInt(1), result.getString(2), result.getTimestamp(3), result.getString(4), result.getInt(5));
                curse.add(z);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return curse;
    }

    @Override
    public List<Zbor> getSearchZbor(String destinatia, String data) {
        List<Zbor> curse = new ArrayList<>();
        try (PreparedStatement preStmt = con.prepareStatement("select * from Zbor where dest = ? and date(data) = ? and nrLoc > 0")) {
            preStmt.setString(1, destinatia);
            preStmt.setString(2, data);
            try (ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    //curse.add(new String(result.getString(0) + " " +result.getString(1) + " " + result.getString(2)));
                    Zbor z = new Zbor(result.getInt(1), result.getString(2), result.getTimestamp(3), result.getString(4), result.getInt(5));
                    curse.add(z);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
        return curse;
    }

    @Override
    public Zbor getZbor(String destinatia, String data) {
        try (PreparedStatement preStmt = con.prepareStatement("select * from Zbor where dest = ? and time(data) = ?")) {
            preStmt.setString(1, destinatia);
            preStmt.setString(2, data);
            try (ResultSet result = preStmt.executeQuery()) {
                if (result.next()) {
                    Zbor z = new Zbor(result.getInt(1), result.getString(2), result.getTimestamp(3), result.getString(4), result.getInt(5));
                    return z;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
        return null;
    }

    @Override
    public Zbor findByID(int id) {
        try (PreparedStatement preStmt = con.prepareStatement("select * from Zbor where idZbor = ?")) {
            preStmt.setInt(1, id);
            try (ResultSet result = preStmt.executeQuery()) {
                if (result.next()) {
                    Zbor z = new Zbor(result.getInt(1), result.getString(2), result.getTimestamp(3), result.getString(4), result.getInt(5));
                    return z;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
        return null;
    }

    @Override
    public void save(Zbor zbor) {
        try (PreparedStatement preStmt = con.prepareStatement("insert into Zbor(dest, data, aeroport, nrLoc) values (?,?,?,?)")) {
            preStmt.setString(1, zbor.getDest());
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String format = formatter.format(zbor.getData());
            try {
                Timestamp d = new Timestamp(formatter.parse(format).getTime());
                preStmt.setTimestamp(2, d);
                preStmt.setString(3, zbor.getAeroport());
                preStmt.setInt(4, zbor.getNrLoc());
                int result = preStmt.executeUpdate();
            }catch (Exception e){
                System.out.println("Error DB " + e);
            }
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement preStmt = con.prepareStatement("delete from Zbor where idZbor=?")) {
            preStmt.setInt(1, id);
            int result = preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
    }

    @Override
    public void update(Zbor newEntity, int id) {
        try (PreparedStatement preStmt = con.prepareStatement("update Zbor set idZbor = ?,dest = ?,data = ?, aeroport = ?, nrLoc = ? where idZbor = ?")) {
            preStmt.setInt(1, newEntity.getId());

            preStmt.setString(2, newEntity.getDest());
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String format = formatter.format(newEntity.getData());
            try {
                Timestamp d = new Timestamp(formatter.parse(format).getTime());
                preStmt.setTimestamp(3, d);
                preStmt.setString(4, newEntity.getAeroport());
                preStmt.setInt(5, newEntity.getNrLoc());
                preStmt.setInt(6, id);
                int result = preStmt.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (SQLException ex) {
            System.out.println("Error DB " + ex);
        }
    }

}
