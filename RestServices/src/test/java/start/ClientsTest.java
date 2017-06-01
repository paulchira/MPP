package start;

import myapp.Model.Zbor;
import myapp.rest.ServiceException;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Chira Paul on 5/19/2017.
 */
public class ClientsTest {
    private  Clients clients;
    private Zbor zbor;
    private static void show(Runnable task) {
        try {
            task.run();
        } catch (ServiceException e) {
            //  LOG.error("Service exception", e);
            System.out.println("Service exception"+ e);
        }
    }
    @Before
    public void setUp() throws Exception {
        clients = new Clients();
        zbor = new Zbor(1000,"Barcelona",null,"Cluj",12);
        zbor.setData(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("12/05/2017 20:12"));
    }

    @Test
    public void create() throws Exception {

        show(()-> System.out.println("Create: " + clients.create(zbor)));
    }

    @Test
    public void update() throws Exception {
        zbor.setDest("Madrid");
        show(()-> System.out.println("Update(set new destination=Madrid: " + clients.update(zbor, 1000)));
    }

    @Test
    public void delete() throws Exception {
        show(()-> {
            System.out.println("Delete zbor with id 1000:");
            clients.delete(1000);
        });
    }

    @Test
    public void getByID() throws Exception {
        show(()-> System.out.println("Get zbor with id 1" + clients.getByID(1)));
    }

    @Test
    public void getAll() throws Exception {
        show(()->{
            Zbor[] res=clients.getAll();
            for(Zbor u:res){
                System.out.println(zbor);
            }
        });
    }

}