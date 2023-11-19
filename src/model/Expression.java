package model;

public interface Expression {
  double evaluate(Context c) throws CalculatorException;
}
