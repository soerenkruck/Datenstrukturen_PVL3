package LoesungG33;

public class Utility {

    /**  Prüft, ob ein String nur Zeichen beinhaltet, welche in einer Menge gegeben sind.
     * @param string Der zu überprüfende String
     * @param chars Die Menge der möglichen Zeichen.
     * @return Ob der String nur mögliche Zeichen beinhaltet.
     **/
    public static boolean isStringOkayFromAvailableCharacters(String string, char[][] chars) {

        for (int i = 0; i < string.length(); i++) {
            if (!isCharInGesamtMenge(string.charAt(i), chars))
                return false;
        }

        return true;
    }

    /**  Prüft, ob ein Zeichen in einer gegben Menge existiert.
     * @param c Der zu überprüfende Char
     * @param chars Die Menge der möglichen Zeichen.
     * @return Ist der Char in der Menge
     **/
    public static boolean isCharInGesamtMenge(char c, char[][] chars) {

        int m = 0;

        for (char[] c1: chars) {
            for (char c2: c1) {
                if (c == c2)
                    m++;
            }
        }

        return (m > 0);
    }

    /**  Prüft, ob ein Zeichen in einer gegben Menge existiert.
     * @deprecated
     * @param c Der zu überprüfende Char
     * @param chars Die Menge der möglichen Zeichen.
     * @return Ist der Char in der Menge
     **/
    public static boolean isCharInMenge(char c, char[] chars) {

        int m = 0;

        for (char c1: chars) {
            if (c == c1)
                m++;
        }

        return (m > 0);
    }

    /**  Prüft, ob eine Formel (String) korrekt formuliert ist.
     * @param formel Die zu überprüfende Formel.
     * @return Ob die Formel korrekt ist.
     **/
    public static boolean isStringMathematiclyOkay(String formel) {

        int anzahlVonC = 0;

        for (int i = 0; i < formel.length(); i++) {

            char currentChar = formel.charAt(i);

            char previousChar = 0;
            char nextChar = 0;

            if (i != 0) // Prüft, dass currentChar nicht das erste Zeichen ist.
                previousChar = formel.charAt(i - 1);

            if (i != formel.length() - 1) // Prüft, dass currentChar nicht das letzte Zeichen ist.
                nextChar = formel.charAt(i + 1);

            // Regeln:
            if (MengeAllerZeichen.isCharInMenge(currentChar, MengeAllerZeichen.Mengen.A_MENGE)) {

                // Regeln für die A Menge
                if (nextChar != 0) {
                    if (!(MengeAllerZeichen.isCharInMenge(nextChar, MengeAllerZeichen.Mengen.B_MENGE) || nextChar == '(')) {
                        return false;
                    }
                } else {
                    // Wenn der nächste char nicht existiert, dann ist der Letzte char einer Formel ein +, - oder *; Das Darf nicht sein.
                    return false;
                }

                if (previousChar != 0) {
                    if (!(MengeAllerZeichen.isCharInMenge(previousChar, MengeAllerZeichen.Mengen.B_MENGE) || previousChar == ')')) {
                        return false;
                    }
                }

            } else if (MengeAllerZeichen.isCharInMenge(currentChar, MengeAllerZeichen.Mengen.B_MENGE)) {

                // Regeln für die B-Menge

                // Das Folgende muss aus A oder ) sein
                if (!(MengeAllerZeichen.isCharInMenge(nextChar, MengeAllerZeichen.Mengen.A_MENGE) || nextChar == ')'))
                    return false;

                // Das element davor muss A oder ( sein, oder keins
                if (!(MengeAllerZeichen.isCharInMenge(previousChar, MengeAllerZeichen.Mengen.A_MENGE) || previousChar == '(' || previousChar == 0))
                    return false;

            } else if (MengeAllerZeichen.isCharInMenge(currentChar, MengeAllerZeichen.Mengen.C_MENGE)) {
                if (currentChar == '(') {
                    if (!(MengeAllerZeichen.isCharInMenge(nextChar, MengeAllerZeichen.Mengen.B_MENGE) || nextChar == '('))
                        return false;

                    if (!(MengeAllerZeichen.isCharInMenge(previousChar, MengeAllerZeichen.Mengen.A_MENGE) || previousChar == '(' || previousChar == 0))
                        return false;

                    anzahlVonC++;
                } else if (currentChar == ')') {
                    if (!(MengeAllerZeichen.isCharInMenge(nextChar, MengeAllerZeichen.Mengen.A_MENGE) || nextChar == ')' || nextChar == 0))
                        return false;

                    if (!(MengeAllerZeichen.isCharInMenge(previousChar, MengeAllerZeichen.Mengen.B_MENGE) || previousChar == ')'))
                        return false;

                    anzahlVonC--;
                }
            } else {
                return false;
            }
        }

        // Prüft ob Anzahl der Elemente aus C (nur '(' und ')') gleich häufig vertreten sind.
        return anzahlVonC == 0;
    }

    /**
     *Rechnet einen einfachen Term (ohne Klammersetzung) aus.
     * @param term Der Term, der ausgerechnet werden soll. (Nur Terme ohne Klammersetzung.)
     * @return Gibt den ausgerechneten Wert zurück.
     */
    public static int calculateSimple(String term) {
        String[] t = term.replace(")", "").replace("(", "").replace("-", "+-").split("\\+");

        int s = 0;
        for (String p: t) {

            int val;

            if (p.contains("*")) {
                String[] u = p.split("\\*");

                val = 1;

                for (String i: u) {
                    val *= Integer.parseInt(i);
                }
            } else {
                val = Integer.parseInt(p);
            }
            s += val;
        }

        return s;
    }

    /**
     * Berechnet einen Term aus Ganzzahlen und ohne Division.
     * @param expression Term in String-Form
     * @return Gibt berechneten Int zurück.
     */
    public static int calculate(String expression) {
        return calculateSimple(interprete(expression));
    }

    /**
     * Rekursive Methode zum untergliedern eines Terms anhand der Klammersetzung
     * @param expression Term in String-Form
     * @return  Gibt Schrittweise untergliederten und aufgelösten Term zurück.
     */
    public static String interprete(String expression) {
        String c = expression;

        if (c.contains("(") && c.contains(")")) {
            for (int i = 0; i < expression.length(); i++) {
                char currentChar = expression.charAt(i);
                if (currentChar == '(') {
                    i++;

                    int cc = 1;

                    StringBuilder toInterprete = new StringBuilder();
                    while (expression.charAt(i) != ')' || cc != 0) {
                        toInterprete.append(expression.charAt(i++));
                        if (expression.charAt(i) == '(')
                            cc++;

                        if (expression.charAt(i) == ')')
                            cc--;
                    }

                    System.out.println(toInterprete);

                    String g = interprete(toInterprete.toString());

                    if (g.contains("("))
                        c = expression.replace("(" + toInterprete + ")", g);
                    else
                        c = expression.replace("(" + toInterprete + ")", String.valueOf(calculateSimple(g)));

                }
            }
        } else {
            c = String.valueOf(calculateSimple(expression));
        }

        return c;
    }
}
