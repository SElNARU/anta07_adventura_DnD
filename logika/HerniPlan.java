package logika;


/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Adam Antoš, Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2016/2017
 */
public class HerniPlan {
    
    private Prostor aktualniProstor;
    
     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        zalozProstoryHry();

    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví dungeon.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor dungeon = new Prostor("dungeon","dungeon, úvod pro hráče");
        Prostor zombieLand = new Prostor("zombieLand", "zombie se už blíží");
        Prostor boss = new Prostor("boss","místnost s finální příšerou");
        Prostor ghoul = new Prostor("ghoul","ghoulova nechutná jeskyně");
        Prostor skeletonHut = new Prostor("skeletonHut","nechutný kostlivec");
        
        // přiřazují se průchody mezi prostory (sousedící prostory)
        dungeon.setVychod(ghoul);
        ghoul.setVychod(dungeon);
        ghoul.setVychod(skeletonHut);
        skeletonHut.setVychod(ghoul);
        skeletonHut.setVychod(boss);
        skeletonHut.setVychod(zombieLand);
        boss.setVychod(skeletonHut);
        zombieLand.setVychod(skeletonHut);

        aktualniProstor = dungeon;  // hra začíná v domečku

        /**
         *  Seznam zbraní
         *
         *@return     aktuální prostor
         */

        Vec DivineSunderer = new Vec("DivineSunderer", Vec.Status.ZVEDNUTELNE,"zbran", 10, 20);
        DivineSunderer.setminDmg(10);
        DivineSunderer.setmaxDmg(20);

        Vec ZbytecneDlouhaHul = new Vec("ZbytecneDlouhaHul", Vec.Status.ZVEDNUTELNE,"zbran", 20, 30);
        ZbytecneDlouhaHul.setminDmg(15);
        ZbytecneDlouhaHul.setmaxDmg(30);

        Vec ZombiePrst = new Vec("ZombiePrst", Vec.Status.ZVEDNUTELNE,"zbran", 30, 40);
        ZbytecneDlouhaHul.setminDmg(40);
        ZbytecneDlouhaHul.setmaxDmg(50);

        ghoul.pridejVec(DivineSunderer);
        skeletonHut.pridejVec(ZbytecneDlouhaHul);
        zombieLand.pridejVec(ZombiePrst);

        Prisery ghoul_char = new Prisery("Ghoul",40,5,0);
        ghoul_char.setZbranVInv(DivineSunderer);
        Prisery skeleton = new Prisery("Skeleton",50,10,0);
        Prisery zombie = new Prisery("Zombie",50,15,0);
        Prisery drak_boss = new Prisery("Drak",80,20,0);



        ghoul.addChar(ghoul_char);
        skeletonHut.addChar(skeleton);
        zombieLand.addChar(zombie);
        boss.addChar(drak_boss);

    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }

    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
    }

}
