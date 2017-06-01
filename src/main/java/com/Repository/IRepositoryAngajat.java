package com.Repository;

/**
 * Created by Chira Paul on 3/26/2017.
 */
public interface IRepositoryAngajat {
    boolean validateAccount(String username, String password);
    int getIdAngajat(String username, String password);
}
