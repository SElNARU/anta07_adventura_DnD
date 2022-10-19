package logika;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *  Třída Hra - třída představující logiku adventury.
 * 
 *  Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan, která inicializuje mistnosti hry
 *  a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 *  Vypisuje uvítací a ukončovací text hry.
 *  Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 *@author     Adam Antoš,Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2016/2017
 */

public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;
    private boolean konecHry = false;
    private Inventory Inventory;
    private Player Player;

    /**
     *  Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra() {
        this.Player = new Player("Hrac",50,10,0);
        herniPlan = new HerniPlan();
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz((new PrikazVem(Inventory, this, Player)));
        platnePrikazy.vlozPrikaz(new PrikazHodKostkou(this,Player, herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazDatum());

    }

    /**
     *  Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani() {
        return "──────▄▀▀▀▀▀▀▀▄───────\n" +
                "─────▐─▄█▀▀▀█▄─▌──────\n" +
                "─────▐─▀█▄▄▄█▀─▌──────\n" +
                "──────▀▄▄▄▄▄▄▄▀───────\n" +
                "─────▐▀▄▄▐█▌▄▄▀▌──────\n" +
                "──────▀▄▄███▄▄▀───────\n" +
                "█▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█\n" +
                "█░░╦─╦╔╗╦─╔╗╔╗╔╦╗╔╗░░█\n" +
                "█░░║║║╠─║─║─║║║║║╠─░░█\n" +
                "█░░╚╩╝╚╝╚╝╚╝╚╝╩─╩╚╝░░█\n" +
                "█▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄█\n"

                + "Vítej dobrodruhu!\n" +
               "Nacházíš se v dungeonu, který je plný monster.\n" +
               "Tvým cílem je zabít monstra, získat jejich itemy a s jejich pomocí porazit BOSSE.\n" +
                "Napište 'napoveda', pokud si nevíte rady, jak hrát dál.\n" +
               "\n" +
               herniPlan.getAktualniProstor().dlouhyPopis();
    }
    
    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog() {
        return "Děkuji za hru!";
    }
    
    /** 
     * Vrací true, pokud hra skončila.
     */
     public boolean konecHry() {
        return konecHry;
    }

    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */
     public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
           	parametry[i]= slova[i+1];  	
        }
        String textKVypsani=" .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.provedPrikaz(parametry);
        }
        else {
            textKVypsani="Nevím co tím myslíš? Tento příkaz neznám. ";
        }
        return textKVypsani;
    }
    
    
     /**
     *  Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     *  mohou ji použít i další implementace rozhraní Prikaz.
     *  
     *  @param  konecHry  hodnota false= konec hry, true = hra pokračuje
     */
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }
    
     /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     *  @return     odkaz na herní plán
     */
     public HerniPlan getHerniPlan(){
        return herniPlan;
     }
    public Inventory getInventory(){
        return Inventory;
    }

    public SeznamPrikazu getPlatnePrikazy() {
        return platnePrikazy;
    }

    public Player getPlayer() {
        return Player;
    }

    /*******************************************************************************
     * Testovací třída SeznamPrikazuTest slouží ke komplexnímu otestování třídy
     * SeznamPrikazu
     *
     * @author    Luboš Pavlíček
     * @version   pro školní rok 2016/2017
     */
    public static class SeznamPrikazuTest
    {
        private Hra hra;
        private PrikazKonec prKonec;
        private PrikazJdi prJdi;

        @Before
        public void setUp() {
            hra = new Hra();
            prKonec = new PrikazKonec(hra);
            prJdi = new PrikazJdi(hra.getHerniPlan());
        }

        @Test
        public void testVlozeniVybrani() {
            SeznamPrikazu seznPrikazu = new SeznamPrikazu();
            seznPrikazu.vlozPrikaz(prKonec);
            seznPrikazu.vlozPrikaz(prJdi);
            assertEquals(prKonec, seznPrikazu.vratPrikaz("konec"));
            assertEquals(prJdi, seznPrikazu.vratPrikaz("jdi"));
            assertEquals(null, seznPrikazu.vratPrikaz("nápověda"));
        }
        @Test
        public void testJePlatnyPrikaz() {
            SeznamPrikazu seznPrikazu = new SeznamPrikazu();
            seznPrikazu.vlozPrikaz(prKonec);
            seznPrikazu.vlozPrikaz(prJdi);
            assertEquals(true, seznPrikazu.jePlatnyPrikaz("konec"));
            assertEquals(true, seznPrikazu.jePlatnyPrikaz("jdi"));
            assertEquals(false, seznPrikazu.jePlatnyPrikaz("nápověda"));
            assertEquals(false, seznPrikazu.jePlatnyPrikaz("Konec"));
        }

        @Test
        public void testNazvyPrikazu() {
            SeznamPrikazu seznPrikazu = new SeznamPrikazu();
            seznPrikazu.vlozPrikaz(prKonec);
            seznPrikazu.vlozPrikaz(prJdi);
            String nazvy = seznPrikazu.vratNazvyPrikazu();
            assertEquals(true, nazvy.contains("konec"));
            assertEquals(true, nazvy.contains("jdi"));
            assertEquals(false, nazvy.contains("nápověda"));
            assertEquals(false, nazvy.contains("Konec"));
        }

    }
}

