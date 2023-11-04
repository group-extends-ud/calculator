package model;

public class TerminalExpression implements Expression {
  private final Object var;
  public TerminalExpression(Object v) {
    var = v;
  }
  public double evaluate(Context c) {
    return c.getValue(var);
  }
}
