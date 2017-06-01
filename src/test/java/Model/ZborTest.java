package Model;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

/**
 * Created by Chira Paul on 3/9/2017.
 */
public class ZborTest {
    private Zbor z;
    @Before
    public void setUp() throws Exception {
        z = new Zbor(1,"Barcelona",null,"Cluj",12);
        z.setData(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("12/05/2017 20:12"));
    }

    @Test
    public void getId() throws Exception {
        assertEquals(z.getId(),1);
    }

    @Test
    public void setId() throws Exception {
        z.setId(2);
        assertEquals(z.getId(),2);
    }

    @Test
    public void getDest() throws Exception {
        assertEquals(z.getDest(),"Barcelona");
    }

    @Test
    public void setDest() throws Exception {
        z.setDest("Viena");
        assertEquals(z.getDest(),"Viena");

    }

    @Test
    public void getData() throws Exception {
        assertEquals(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(z.getData()),"12/05/2017 20:12");

    }

    @Test
    public void setData() throws Exception {
        z.setData(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("01/01/2017 19:12"));
        assertEquals(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(z.getData()),"01/01/2017 19:12");
    }

    @Test
    public void getAeroport() throws Exception {
        assertEquals(z.getAeroport(),"Cluj");
    }

    @Test
    public void setAeroport() throws Exception {
        z.setAeroport("Clujana");
        assertEquals(z.getAeroport(),"Clujana");
    }

    @Test
    public void getNrLoc() throws Exception {
        assertEquals(z.getNrLoc(),12);
    }

    @Test
    public void setNrLoc() throws Exception {
        z.setNrLoc(1);
        assertEquals(z.getNrLoc(),1);
    }

}