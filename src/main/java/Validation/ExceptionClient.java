package Validation;


import Model.Client;
import Model.Zbor;

/**
 * Created by Chira Paul on 10/7/2016.
 */
public class ExceptionClient extends Exception implements Validator<Client> {
    /*
    validare sectie
    formam un string cu errori
    post:aruncam erroare cu stringul cu errori
     */
    public void Validare(Client c) throws GeneralException {
        String err = "Eroare:\n";
        if (c.getId() < 0)
            err += "id invalid\n";
        if (c.getNume().length() == 0)
            err += "nume vid\n";
        if (c.getPrenume().length() == 0)
            err += "prenume vid\n";
        if (c.getAdresa().length() == 0)
            err += "adresa vid\n";
        if (err != "Eroare:\n")
            throw new GeneralException(err);
    }
}
