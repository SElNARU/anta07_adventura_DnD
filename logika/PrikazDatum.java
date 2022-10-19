package logika;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PrikazDatum implements IPrikaz {

    private static final String NAZEV = "datum";

    private Hra hra;

    private String datum;

    public String getNazev() {return NAZEV;}

    public PrikazDatum(){
        this.datum = datum;
    }


    @Override
    public String provedPrikaz(String... parametry) {
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Calendar calobj = Calendar.getInstance();
        return (df.format(calobj.getTime()));
    }
}
