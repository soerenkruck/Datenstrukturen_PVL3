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
        return 0;
    }

}


