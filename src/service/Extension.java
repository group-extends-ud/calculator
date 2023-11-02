package service;

public class Extension {

    public static Boolean isInt(String string) {
        try {
            Integer.parseInt(string);
            return true;
        }
        catch (NumberFormatException numberFormat){
            return false;
        }
    }

    public static Boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        }
        catch (NumberFormatException numberFormat){
            return false;
        }
    }

}
