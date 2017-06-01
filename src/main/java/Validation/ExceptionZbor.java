package Validation;


import Model.Zbor;

/**
 * Created by Chira Paul on 10/7/2016.
 */
public class ExceptionZbor extends Exception implements Validator<Zbor> {
    /*
    validare sectie
    formam un string cu errori
    post:aruncam erroare cu stringul cu errori
     */
    public void Validare(Zbor c) throws GeneralException {
        String err = "Eroare:\n";
        if (c.getId() < 0)
            err += "id invalid\n";
        if (c.getDest().length() == 0)
            err += "destinatie vida\n";
        if (c.getAeroport().length() == 0)
            err += "aeroport vid\n";
        if (c.getNrLoc() < 0)
            err += "nrloc invalid\n";
        if (err != "Eroare:\n")
            throw new GeneralException(err);
    }
}
