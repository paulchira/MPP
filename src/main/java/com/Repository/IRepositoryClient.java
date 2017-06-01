package com.Repository;

/**
 * Created by Chira Paul on 3/26/2017.
 */
public interface IRepositoryClient {
    boolean foundClient(String nume, String prenume, String adresa);

    void insertClient(String nume, String prenume, String adresa);

    int getIdClient(String nume, String prenume, String adresa);
}
