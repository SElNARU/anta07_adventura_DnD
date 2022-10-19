package logika;
import java.util.HashMap;
import java.util.Map;

/**
 *  Inicializuje inventář jako HashMapu.
 *  Umí vložit a odebrat objekt z inventáře.
 *  Umí vypsat celý inventář, či z něj získat konkrétní věc.
 */
public class Inventory {
    private static Map<String,Vec > inventor;

    public Inventory() {
        inventor = new HashMap<>();
    }
    public static Map<String, Vec> getInventory() {
        return inventor;
    }

    public void removeItem(String nazev){
        inventor.remove(nazev);
    }

    /**
     * Vrací inventář s aktuální equipnutou zbraní
     *
     * @return String výpis inventáře
     */
    public String showInventory(){
        String celyInventory = "";
        for(String s: Inventory.inventor.keySet()) {
            if (inventor.get(s).getTyp().equals("zbran")) {
                celyInventory += inventor.get(s).getNazev() + "(" + inventor.get(s).getmaxDmg() + " maxDmg)" + inventor.get(s).getminDmg() + " minDmg)";
            } else{
                celyInventory += inventor.get(s).getNazev() + " ";
            }
        }
        return "Tvoje zbraň: " + celyInventory;
    }

    /**
     * Vrátí věc z inventáře, pokud v inventáří není vrátí null
     *
     * @param nazev
     * @return null nebo věc
     */
    public Vec getVec(String nazev) {
        if (Inventory.getInventory().containsKey(nazev)) {
            return inventor.get(nazev);
        }
        return null;
    }


}
