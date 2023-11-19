package model;

public interface Expression {
  Double evaluate(Context c) throws CalculatorException;
}
