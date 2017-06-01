package Model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Chira Paul on 3/18/2017.
 */
public class ClientTest {
    private Client c;
    @Before
    public void setUp() throws Exception {
        c = new Client(1,"paul","chira","p1");
    }

    @Test
    public void getId() throws Exception {
        assertEquals(c.getId(),1);
    }

    @Test
    public void setId() throws Exception {
        c.setId(2);
        assertEquals(c.getId(),2);
    }

    @Test
    public void getNume() throws Exception {
        assertEquals(c.getNume(),"paul");
    }

    @Test
    public void setNume() throws Exception {
        c.setNume("vlad");
        assertEquals(c.getNume(),"vlad");
    }

    @Test
    public void getPrenume() throws Exception {
        assertEquals(c.getPrenume(),"chira");
    }

}