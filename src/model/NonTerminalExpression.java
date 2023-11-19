package model;

public abstract class NonTerminalExpression implements Expression {
  private Expression leftNode;
  private Expression rightNode;

  public NonTerminalExpression(Expression l, Expression r) {
    setLeftNode(l);
    setRightNode(r);
  }
  public void setLeftNode(Expression node) {
    leftNode = node;
  }
  public void setRightNode(Expression node) {
    rightNode = node;
  }
  public Expression getLeftNode() {
    return leftNode;
  }
  public Expression getRightNode() {
    return rightNode;
  }
}// model.NonTerminalExpression

class AddExpression extends NonTerminalExpression {
  public Double evaluate(Context c) {
    return getLeftNode().evaluate(c) +
           getRightNode().evaluate(c);
  }
  public AddExpression(Expression l, Expression r) {
    super(l, r);
  }
}// model.AddExpression

class SubtractExpression extends NonTerminalExpression {
  public Double evaluate(Context c) {
    return getLeftNode().evaluate(c) -
           getRightNode().evaluate(c);
  }
  public SubtractExpression(Expression l, Expression r) {
    super(l, r);
  }
}// model.SubtractExpression

class MultiplyExpression extends NonTerminalExpression {
  public Double evaluate(Context c) {
    return getLeftNode().evaluate(c) * getRightNode().evaluate(c);
  }
  public MultiplyExpression(Expression l, Expression r) {
    super(l, r);
  }

}// model.MultiplyExpression

class DivideExpression extends NonTerminalExpression {
  public Double evaluate(Context c) {
    return getLeftNode().evaluate(c) / getRightNode().evaluate(c);
  }
  public DivideExpression(Expression l, Expression r) {
    super(l, r);
  }
}

class ModExpression extends NonTerminalExpression {
  public ModExpression(Expression l, Expression r) {
    super(l, r);
  }
  @Override
  public Double evaluate(Context c) {
    return getLeftNode().evaluate(c) % getRightNode().evaluate(c);
  }
}

class PowExpression extends NonTerminalExpression {
  public PowExpression(Expression l, Expression r) {
    super(l, r);
  }
  @Override
  public Double evaluate(Context c) {
    return Math.pow(getLeftNode().evaluate(c), getRightNode().evaluate(c));
  }
}

class IntDivideExpression extends NonTerminalExpression {
  public IntDivideExpression(Expression l, Expression r) {
    super(l, r);
  }
  @Override
  public Double evaluate(Context c) {
    int a = getLeftNode().evaluate(c).intValue();
    int b = getRightNode().evaluate(c).intValue();
    return (double) (a / b);
  }
}

class PermutationExpression extends NonTerminalExpression {
  @Override
  public Double evaluate(Context c) {
    double n = getLeftNode().evaluate(c);
    double r = getRightNode().evaluate(c);
    if(n%1.0!=0.0 || r%1.0!=0.0 || n<r || n<1 || r<1) {
        return null;
    }
    else {
      return (double) Calculator.factorial(n) / Calculator.factorial(n - r);
    }
  }
  public PermutationExpression(Expression l, Expression r) {
    super(l, r);
  }
}

class CombinationExpression extends NonTerminalExpression {
  @Override
  public Double evaluate(Context c) {
    double n = getLeftNode().evaluate(c);
    double r = getRightNode().evaluate(c);
    if(n%1.0!=0.0 || r%1.0!=0.0 || n<r || n<1 || r<1) {
      return null;
    }
    return (double) Calculator.factorial(n) / (Calculator.factorial(r) * Calculator.factorial(n-r));
  }
  public CombinationExpression(Expression l, Expression r) {
    super(l, r);
  }
}
