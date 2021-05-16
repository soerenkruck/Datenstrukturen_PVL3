package LoesungG33;

import PVL.PVL3;

public class Loesung_G33 implements PVL3 {

    @Override
    public boolean is_valid_expression(String expression) {

        String cleanString = expression.replace(" ", "");

        if (Utility.isStringOkayFromAvailableCharacters(cleanString, MengeAllerZeichen.getAllCharacters())) {
            return Utility.isStringMathematiclyOkay(cleanString);
        }

        return false;
    }

    @Override
    public int calculate(String expression) {

        expression = expression.replace("+-", "-");

        /*
         Laut Aufgabenstellung ist die expression schon valid,
         aber wir haben das einfach mal noch mit eingebaut, das die Expression
         überprüft wird.
        */
        if (!is_valid_expression(expression))
            return -1;

        return Utility.calculate(expression);
    }

}


