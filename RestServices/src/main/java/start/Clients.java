package start;

import myapp.Model.Zbor;
import myapp.rest.ServiceException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;

/**
 * Created by Chira Paul on 5/19/2017.
 */
public class Clients {
    public static final String URL = "http://localhost:8080/myapp/zbor";

    private RestTemplate restTemplate = new RestTemplate();

    private <T> T execute(Callable<T> callable) {
        try {
            return callable.call();
        } catch (ResourceAccessException | HttpClientErrorException e) {
            throw new ServiceException(e);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public Zbor create(Zbor zbor) {
        return execute(() -> restTemplate.postForObject(URL, zbor, Zbor.class));
    }

    public Zbor update(Zbor zbor, int id) {
        return execute(() -> {
            restTemplate.put(String.format("%s/%s", URL, id), zbor);
            return null;
        });
    }

    public void delete(int id) {
        execute(() -> {
            restTemplate.delete(String.format("%s/%s", URL, id));
            return null;
        });
    }

    public Zbor getByID(int id) {
        return execute(() -> restTemplate.getForObject(String.format("%s/%s", URL, id), Zbor.class));
    }

    public Zbor[] getAll() {
        return execute(() -> restTemplate.getForObject(URL, Zbor[].class));
    }
}
