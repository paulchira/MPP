package Model;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

/**
 * Created by Chira Paul on 3/9/2017.
 */
public class BiletTest {
    private Bilet b;
    @Before
    public void setUp() throws Exception {
        b = new Bilet(1,2,2,2,12);

    }

    @Test
    public void getId() throws Exception {
        assertEquals(b.getId(),1);
    }

    @Test
    public void setId() throws Exception {
        b.setId(2);
        assertEquals(b.getId(),2);
    }


    @Test
    public void getNrBilet() throws Exception {
        assertEquals(b.getNrBilet(),12);
    }

    @Test
    public void setNrBilet() throws Exception {
        b.setNrBilet(1);
        assertEquals(b.getNrBilet(),1);
    }

}