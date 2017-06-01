package Model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Chira Paul on 3/8/2017.
 */
public class AngajatTest {
    private Angajat a;
    @Before
    public void setUp() throws Exception {
        a = new Angajat(1,"chira","secretar");
    }

    @Test
    public void getId() throws Exception {
        assertEquals(a.getId(),1);
    }

    @Test
    public void setId() throws Exception {
        a.setId(2);
        assertEquals(a.getId(),2);
    }

}