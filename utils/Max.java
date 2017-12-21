package utils;

public final class Max {
    /**
     *  Clasa utilitara pentru determinarea valorii maxime dintre
     *  cele doua din parametrii.
     */

    private Max() { }

    public static int max(final int a, final int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }
}
