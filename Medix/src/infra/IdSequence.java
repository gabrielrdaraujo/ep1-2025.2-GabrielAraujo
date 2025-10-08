package infra;

public class IdSequence {
    private static int contador = 1;

    public static String nextId(String prefix) {
        return prefix + contador++;
    }
}
