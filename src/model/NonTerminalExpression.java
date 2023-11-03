package model;

public abstract class NonTerminalExpression
  implements Expression {
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
  public double evaluate(Context c) {
    return getLeftNode().evaluate(c) +
           getRightNode().evaluate(c);
  }
  public AddExpression(Expression l, Expression r) {
    super(l, r);
  }
}// model.AddExpression

class SubtractExpression extends NonTerminalExpression {
  public double evaluate(Context c) {
    return getLeftNode().evaluate(c) -
           getRightNode().evaluate(c);
  }
  public SubtractExpression(Expression l, Expression r) {
    super(l, r);
  }
}// model.SubtractExpression

class MultiplyExpression extends NonTerminalExpression {
  public double evaluate(Context c) {
    return getLeftNode().evaluate(c) * getRightNode().evaluate(c);
  }
  public MultiplyExpression(Expression l, Expression r) {
    super(l, r);
  }

}// model.MultiplyExpression

class DivideExpression extends NonTerminalExpression {
  public double evaluate(Context c) {
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
  public double evaluate(Context c) {
    return getLeftNode().evaluate(c) % getRightNode().evaluate(c);
  }
}

class PowExpression extends NonTerminalExpression {
  public PowExpression(Expression l, Expression r) {
    super(l, r);
  }
  @Override
  public double evaluate(Context c) {
    return Math.pow(getLeftNode().evaluate(c), getRightNode().evaluate(c));
  }
}
