package logika;

public class Vec {
    private String nazev;
    private int minDmg;
    private int maxDmg;
    private int hp;
    private final Status STATUS;
    private final String TYP;

    public enum Status{
        POUZITELNE,
        ZVEDNUTELNE
    }

    public Vec(String nazev, Status status, String typ, int minDmg, int maxDmg) {
        this.nazev = nazev;
        this.STATUS = status;
        this.TYP = typ;
        this.minDmg = minDmg;
        this.maxDmg = maxDmg;
    }
    public String getNazev () { return nazev;}

    public String getTyp() {return TYP;}

    public Status getStatus() {
        return STATUS;
    }

    /**
     * Metoda vrátí zda je možné věci zvednout nebo ne.
     *
     * @return zvednutelnost věci
     */
    public boolean lzeVzit (){
        if(getStatus() == Status.ZVEDNUTELNE){
            return true;
        }
        return false;
    }

    /**
     * Metoda vrátí zda je možné danou věc použít.
     *
     * @return použitelnost věci
     */
    public boolean lzePouzit (){
        if(getStatus() == Status.POUZITELNE){
            return true;
        }
        return false;
    }

    public int getminDmg() {return minDmg;}
    public void setminDmg(int minDmg) {this.minDmg = minDmg;}

    public int getmaxDmg() {return maxDmg;}
    public void setmaxDmg(int maxDmg) {this.maxDmg = maxDmg;}

    public int getHp() {return hp;}

}
