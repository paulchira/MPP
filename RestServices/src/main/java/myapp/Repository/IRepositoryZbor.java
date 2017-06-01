package myapp.Repository;


import myapp.Model.Zbor;

import java.util.List;

/**
 * Created by Chira Paul on 3/26/2017.
 */
public interface IRepositoryZbor {
    List<Zbor> getAllZbor();
    List<Zbor> getSearchZbor(String destinatia, String data);
    void update(Zbor newEntity, int id);
    void delete(int id);
    Zbor getZbor(String destinatia, String data);
    Zbor findByID(int id);
    void save(Zbor zbor);
}
