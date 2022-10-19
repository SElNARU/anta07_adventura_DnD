package logika;

public class characterBasics {
    private final String NAZEV;
    private int hp;
    private int maxDmg;
    private int minDmg;
    int damage;

    Vec zbranVInv;

    public characterBasics(String nazev, int hp, int maxDmg, int minDmg) {
        this.NAZEV = nazev;
        this.hp = hp;
        this.maxDmg = maxDmg;
        this.minDmg = minDmg;
        this.damage = (int)Math.floor(Math.random()*(maxDmg-minDmg+1)+minDmg);
    }
    public String getNazev() {return NAZEV;}

    public int getHp() {return hp;}

    public void setHp(int hp) {this.hp = hp;}

    public void upravitHp(int hp){
        setHp(getHp()+hp);
    }
    public int getMaxDmg() {return maxDmg;}
    public int getMinDmg() {return minDmg;}
    public int getDamage() {return damage;}

    public void setZbranVInv(Vec zbranVInv) {
        this.zbranVInv = zbranVInv;
    }

    public Vec getZbranVInv() {
        return zbranVInv;
    }
}
