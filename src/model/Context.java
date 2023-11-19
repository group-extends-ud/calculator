package model;

import javax.swing.*;

public class Context {
    public double getValue(Object obj) {
        if(isDouble(obj)) {
            return (Double) obj;
        }
        String symbol = (String) obj;
        switch (symbol) {
            case "π" -> {
                return Math.PI;
            }
            case "e" -> {
                return Math.E;
            }
            case "τ" -> {
                return Math.PI * 2.0;
            }
            default -> {
                JOptionPane.showMessageDialog(null, "Ingrese una expresión válida");
                throw new NumberFormatException("Símbolo desconocido");
            }
        }
    }

    public static Boolean isInt(String string) {
        try {
            Integer.parseInt(string);
            return true;
        }
        catch (NumberFormatException numberFormat){
            return false;
        }
    }

    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        }
        catch (NumberFormatException numberFormat){
            return false;
        }
    }

    public static boolean isDouble(Object obj) {
        try {
            Double d = (Double) obj;
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public static boolean isConstant(String symbol) {
        return symbol.equals("π") || symbol.equals("e") || symbol.equals("τ");
    }
}
