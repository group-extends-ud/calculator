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

    public boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        }
        catch (NumberFormatException numberFormat){
            return false;
        }
    }

    public boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        }
        catch (NumberFormatException numberFormat){
            return false;
        }
    }

    public boolean isDouble(Object obj) {
        try {
            Double d = (Double) obj;
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean isOperator(String str) {
        return str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/") || str.equals("%")
                || str.equals("^") || str.equals("\\") || str.equals("P") || str.equals("C");
    }

    public boolean isOperator(Object obj) {
        try {
            return isOperator((String) obj);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isConstant(String str) {
        return str.equals("π") || str.equals("e") || str.equals("τ");
    }

}
