package LoesungG33;

public class MengeAllerZeichen {

    private static final char[] A = {'+', '-', '*'};
    private static final char[] B = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
    private static final char[] C = {'(', ')'};

    private static final char[][] allCharacters = {A, B, C};

    public enum Mengen {
        A_MENGE, B_MENGE, C_MENGE
    }

    /**
     * @return Gibt alle Verfügbaren Zeichen wieder.
     */
    public static char[][] getAllCharacters() {
        return allCharacters;
    }

    /**
     * Funktion, die alle Zeichen einer spezifischen Menge zurückgibt.
     * @param mengen Die Menge, aus der alle Zeichen zurückgegeben werden sollen.
     * @return Alle Zeichen, die sich in der Menge befinden.
     */
    public static char[] getCharsInMenge(Mengen mengen) {

        switch (mengen) {
            case A_MENGE:
                return A;
            case B_MENGE:
                return B;
            case C_MENGE:
                return C;
        }

        return null;
    }

    /**
     * Gibt zurück, ob ein bestimmtes Zeichen in einer Menge existent ist.
     * @param c Der zu überprüfende char
     * @param mengen Die Menge, welche geprüft wird, ob sich der char c in dieser befindet.
     * @return Gibt boolean zurück, ob der char sich in der spezifischen Menge befindet.
     */
    public static boolean isCharInMenge(char c, Mengen mengen) {
        int m = 0;

        for (char c1: getCharsInMenge(mengen)) {
            if (c == c1)
                m++;
        }

        return (m > 0);
    }

    public static char[] getA() {
        return A;
    }

    public static char[] getB() {
        return B;
    }

    public static char[] getC() {
        return C;
    }
}
