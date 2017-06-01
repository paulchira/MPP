package Validation;

/**
 * Created by Chira Paul on 10/24/2016.
 */
public interface Validator<T> {
    void Validare(T entity) throws GeneralException;
}
