package myapp.rest;

/**
 * Created by Chira Paul on 5/18/2017.
 */
public class ServiceException extends RuntimeException {
    public ServiceException(Exception e){
        super(e);
    }
    public ServiceException(String msg){
        super(msg);
    }
}
