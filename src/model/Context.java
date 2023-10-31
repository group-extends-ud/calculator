package model;

import java.util.HashMap;

public class Context {
    private final HashMap varList = new HashMap();

    public void assign(String var, int value) {
        varList.put(var, Integer.valueOf(value));
    }

}
