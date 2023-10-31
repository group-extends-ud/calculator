package model;

public class TerminalExpression implements Expression {
  private final String var;
  public TerminalExpression(String v) {
    var = v;
  }
  public double evaluate(Context c) {
    return c.getValue(var);
  }
}
