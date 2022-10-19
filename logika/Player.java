package logika;
import java.util.Random;

public class Player extends characterBasics {
        private int hp = 100;
    private int maxHp = 110;

    public Vec zbran;

    public Player(String nazev, int hp, int maxDmg, int minDmg) {
        super(nazev, hp, maxDmg, minDmg);
        this.zbran = new Vec("Holé ruce", Vec.Status.ZVEDNUTELNE, "ruce", 0, 20);
    }

    public int getHp() {return hp;}
        public int getDamage() {
         return NumberHelper.nahodneCislo(zbran.getminDmg(), zbran.getmaxDmg());
        }
        public void setHp(int hp) {this.hp = hp;}

    public void upravitHp(int hp){
        setHp(getHp()+hp);
        if(getHp()>maxHp){
            setHp(maxHp);
        }
    }

    public void setZbran(Vec zbran) {
        this.zbran = zbran;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }



}
