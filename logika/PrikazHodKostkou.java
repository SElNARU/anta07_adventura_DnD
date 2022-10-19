package logika;

import java.util.*;

public class PrikazHodKostkou implements IPrikaz {
    private static final String NAZEV = "utok";
    private Hra hra;
    private Player player;
    private HerniPlan plan;

    int maxDmg;
    int minDmg;

    public int getDamage() {
        return  (int)Math.floor(Math.random()*(maxDmg-minDmg+1)+minDmg);
    }

    public PrikazHodKostkou(Hra hra, Player player, HerniPlan plan) {
        this.hra = hra;
        this.player = player;
        this.plan = plan;

        if (player == null){
            throw new RuntimeException("Hrac je null");
        }
        maxDmg = player.getMaxDmg();
        minDmg = player.getMinDmg();
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Vyber soupeře";
        }
        String nazevPostavy = parametry[0];
        Prostor prostor = hra.getHerniPlan().getAktualniProstor();
        Map<String, characterBasics> seznamPostav = prostor.getSeznamPostav();

        if(seznamPostav.containsKey(nazevPostavy)){
            if(round(seznamPostav,nazevPostavy, prostor)){
                return "";
            }
        }else{
            return "Tato postava v této místnosti není." + prostor.vypisSeznamuPostav();
        }
        return "Konec kola.";
    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     * @return nazev prikazu
     */
    @Override
    public String getNazev() {return NAZEV;}

    /**
     * Metoda odečte od postavy hráčův damage, vrátí výší jejího hp, v případě dosažení nuly postavu odebere.
     * Dále odebírá damage všech postav v místnosti od hráčova hp. A kontroluje zda je stále na živu.
     *
     * @param seznamPostav
     * @param nazevPostavy
     * @param prostor
     * @return true pokud hra soubojem skončila
     */
    public boolean round(Map<String, characterBasics> seznamPostav, String nazevPostavy, Prostor prostor) {
        Scanner sc = new Scanner(System.in);
        seznamPostav.get(nazevPostavy).upravitHp(-player.getDamage());
        System.out.println("Útok na " + nazevPostavy + ", ubral si " + player.getDamage());

        // pokud má postava menší hp než 1, odebere ji
        if(seznamPostav.get(nazevPostavy).getHp()<1){
            System.out.println(seznamPostav.get(nazevPostavy));
            Vec zbran = seznamPostav.get(nazevPostavy).getZbranVInv();
            if (zbran != null) {
                System.out.println("Gratuluji. Z příšery vypadla zbraň: " + zbran.getNazev() + "\n" + "maxDmg: " + zbran.getmaxDmg());
                System.out.println("          A\n" +
                        "         /!\\\n" +
                        "        / ! \\\n" +
                        " /\\     )___(\n" +
                        "(  `.____(_)_________\n" +
                        "|           __..--\"\"\n" +
                        "(       _.-|\n" +
                        " \\    ,' | |\n" +
                        "  \\  /   | |\n" +
                        "   \\(    | |\n" +
                        "    `    | |\n" +
                        "         | |");

                player.setZbran(zbran);
            }

            seznamPostav.remove(nazevPostavy);
            if(seznamPostav.isEmpty() && prostor.getNazev().equals("skeletonHut")){
                Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor("boss");
                sousedniProstor.getneedKey(false);
                System.out.println("Můžeš na bosse");
            }
            if(nazevPostavy.equals("Drak")){
                System.out.println("            88                                                       \n" +
                        "            \"\"              ,d                                       \n" +
                        "                            88                                       \n" +
                        "8b       d8 88  ,adPPYba, MM88MMM ,adPPYba,  8b,dPPYba, 8b       d8  \n" +
                        "`8b     d8' 88 a8\"     \"\"   88   a8\"     \"8a 88P'   \"Y8 `8b     d8'  \n" +
                        " `8b   d8'  88 8b           88   8b       d8 88          `8b   d8'   \n" +
                        "  `8b,d8'   88 \"8a,   ,aa   88,  \"8a,   ,a8\" 88           `8b,d8'    \n" +
                        "    \"8\"     88  `\"Ybbd8\"'   \"Y888 `\"YbbdP\"'  88             Y88'     \n" +
                        "                                                            d8'      \n" +
                        "                                                           d8'       ");
                hra.setKonecHry(true);
                return true;
            }
        }
        for(String s: seznamPostav.keySet()){   // odebere damage každé postavy v seznamu od hp hráče
            player.upravitHp(-seznamPostav.get(s).getDamage());
            System.out.println("Útok " + seznamPostav.get(s).getNazev() + ",ubral ti " +
                    seznamPostav.get(s).getDamage() + "\n" + "Tvoje hp: " + player.getHp() + "   (zmáčkni enter)");
            sc.nextLine();


            if(player.getHp()<1){
                System.out.println("Byl jsi zabit. Konec hry");
                System.out.println("         ...\n" +
                        "             ;::::;\n" +
                        "           ;::::; :;\n" +
                        "         ;:::::'   :;\n" +
                        "        ;:::::;     ;.\n" +
                        "       ,:::::'       ;           OOO\\\n" +
                        "       ::::::;       ;          OOOOO\\\n" +
                        "       ;:::::;       ;         OOOOOOOO\n" +
                        "      ,;::::::;     ;'         / OOOOOOO\n" +
                        "    ;:::::::::`. ,,,;.        /  / DOOOOOO\n" +
                        "  .';:::::::::::::::::;,     /  /     DOOOO\n" +
                        " ,::::::;::::::;;;;::::;,   /  /        DOOO\n" +
                        ";`::::::`'::::::;;;::::: ,#/  /          DOOO\n" +
                        ":`:::::::`;::::::;;::: ;::#  /            DOOO\n" +
                        "::`:::::::`;:::::::: ;::::# /              DOO\n" +
                        "`:`:::::::`;:::::: ;::::::#/               DOO\n" +
                        " :::`:::::::`;; ;:::::::::##                OO\n" +
                        " ::::`:::::::`;::::::::;:::#                OO\n" +
                        " `:::::`::::::::::::;'`:;::#                O\n" +
                        "  `:::::`::::::::;' /  / `:#\n" +
                        "   ::::::`:::::;'  /  /   `#");
                hra.setKonecHry(true);
                return true;
            }

        }
        for(String s: seznamPostav.keySet()){
            System.out.println(seznamPostav.get(s).getNazev() +
                    " damage: " + seznamPostav.get(s).getDamage() + " hp: " + seznamPostav.get(s).getHp());
        }
        return false;


    }


}
