package logika;

/**
 *
 *  Vrací jednotlivé údaje o postavě a upravuje její hp během souboje.
 */

public abstract class Character extends characterBasics {

    private final String NAZEV;
    private int hp;
    private int damage;

    public Character(String nazev, int hp, int damage) {
        super("nazev", 50,10,0);
        this.NAZEV = nazev;
        this.hp = hp;
        this.damage = damage;
    }
    public String getNazev() {return NAZEV;}

    public int getHp() {return hp;}

    public void setHp(int hp) {this.hp = hp;}

    public void upravitHp(int hp){
        setHp(getHp()+hp);
    }

    public int getDamage() {return damage;}

    public abstract void superschopnost(Character postava);

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
