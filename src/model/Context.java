package model;

import java.util.HashMap;

public class Context {
    private final HashMap varList = new HashMap();

    public void assign(String var, int value) {
        varList.put(var, Integer.valueOf(value));
    }

    public int getValue(String var) {
        Integer objInt = (Integer) varList.get(var);
        return objInt.intValue();
    }

    public Context() {
        tokenize();
    }

    // Values are hardcoded to keep the example simple
    private void tokenize() {
        assign("a", 20);
        assign("b", 40);
        assign("c", 30);
        assign("d", 10);
    }

}
