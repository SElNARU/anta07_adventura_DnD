package logika.testy;
import logika.Hra;
import org.junit.*;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KonecTest {
    private Hra hra;

    @Before
    public void setUp() {
        hra = new Hra();
    }

    @Test
    public void konecHryPrikazem() {
        assertEquals("hra ukončena příkazem konec",hra.zpracujPrikaz("konec"));
    }
    @Test
    public void neplatnyPrikazKonec() {
        assertEquals("Konec neprošel",hra.zpracujPrikaz("Test neprošel"));
    }

}
