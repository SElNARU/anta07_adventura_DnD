package logika;

public class PrikazVem implements IPrikaz {
    private static final String NAZEV = "vezmi";
    private Inventory inventory;
    private Hra hra;
    private Player player;

    public PrikazVem(Inventory inventory, Hra hra, Player player) {
        this.inventory = inventory;
        this.hra = hra;
        this.player = player;
    }

    /**
     *  Pokud ano přidá ji do inventáře a odebere z prostoru.
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if(parametry.length == 0){
            return "Nevíš, co vzít";
        }
        String nazevVeci = parametry[0];
        Prostor prostor = hra.getHerniPlan().getAktualniProstor();
        Vec vec = prostor.getVec(nazevVeci);
        if(Inventory.getInventory().size()>=2){
            return "Zbraně jsou těžké, uneseš jen jednu!";
        }
        if(vec == null){
            return "Tuto věc vzít nemůžeš" + "\n" + prostor.dlouhyPopis();
        } else if(vec.lzeVzit()){
            if(vec.getTyp()=="zbran"){
                zmenaRNG(vec);
            }
            else{
                Inventory.getInventory().put(vec.getNazev(), vec);
            }
            prostor.odstranPredmet(vec);
            return "Vzal jsi zbraň: " + vec.getNazev() + "\n";
        }
        return null;
    }

    private void zmenaRNG(Vec vec) {

    }


    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     * @return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }


}

