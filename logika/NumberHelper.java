package logika;

public class NumberHelper {
    public static int nahodneCislo(int odCislo, int doCislo) {
        return (int)Math.floor(Math.random()*(doCislo-odCislo+1)+odCislo);
    }
}
