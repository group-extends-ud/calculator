package model;

import java.util.HashMap;

import static service.Extension.isDouble;
import static service.Extension.isInt;

public class Calculator {
  private String expression;
  private final HashMap operators;
  private Context ctx;

  public Calculator() {
    operators = new HashMap();
    operators.put("+", "1");
    operators.put("-", "1");
    operators.put("/", "2");
    operators.put("*", "2");
    operators.put("(", "0");
  }

  public void setContext(Context c) {
    ctx = c;
  }

  public void setExpression(String expr) {
    expression = expr;
  }

  public double evaluate() {
    // infix to Postfix
    Stack pfExpr = infixToPostFix(expression);

    // build the Binary Tree
    Expression rootNode = buildTree(pfExpr);

    // Evaluate the tree
    return rootNode.evaluate(ctx);
  }

  private NonTerminalExpression getNonTerminalExpression(String operation, Expression l, Expression r) {
    if (operation.trim().equals("+")) {
      return new AddExpression(l, r);
    }
    if (operation.trim().equals("-")) {
      return new SubtractExpression(l, r);
    }
    if (operation.trim().equals("*")) {
      return new MultiplyExpression(l, r);
    }

    return null;
  }

  private Expression buildTree(Stack expr) {
    Stack s = new Stack();

    while (!expr.isEmpty()) {
      Object currObj = expr.pop();

      if (!isOperator(currObj)) {
        Expression e = new TerminalExpression(currObj);
        s.push(e);
      }
      else {
        Expression r = (Expression) s.pop();
        Expression l = (Expression) s.pop();
        Expression n = getNonTerminalExpression((String) currObj, l, r);
        s.push(n);
      }
    } // for
    return (Expression) s.pop();
  }

  private Stack infixToPostFix(String str) {
    Stack stack = new Stack();
    Stack pfExpr = new Stack();

    String tempStr = "";
    String expr = str.trim();

    // recorre cada char en str
    for (int i = 0; i < expr.length(); i++) {
      String currChar = expr.substring(i, i + 1);

      // if the current character is an operand
      if (isInt(currChar)) {
        double valor = 0.0;
        int maxSize = 0;
        // toma el double más largo desde esa posición
        for (int j = i; j <expr.length(); j++) {
          if(isDouble(expr.substring(i,j))) {
            valor = Double.parseDouble(expr.substring(i,j));
            maxSize = j-i;
          }
        }
        pfExpr.push(valor);
        i+=maxSize-1;
      }
      else if(currChar.equals("(")) {
        stack.push(currChar);
      }
      else if(currChar.equals(")")) {
        Object element;
        while (true) {
          element = stack.pop();
          if(element!=null) {
            if(element.equals("(")) {
              break;
            }
            else {
              pfExpr.push(element);
            }
          }
          else {
            break;
          }
        }
      }
      else if(isOperator(currChar)) {
        // 8a
        if(stack.isEmpty()) {
          stack.push(currChar);
        }
        // 8b
        else {
          // 8bi
          if(isOperator(currChar)) {
            if(!stack.isEmpty()) {
              tempStr = (String) stack.pop();

              // 8biA
              String strVal1 = (String) operators.get(tempStr);
              int val1 = Integer.parseInt(strVal1);

              String strVal2 = (String) operators.get(currChar);
              int val2 = Integer.parseInt(strVal2);

              // 8biB
              while (val1 >= val2) {
                pfExpr.push(tempStr);
                val1 = -100;
                if (!stack.isEmpty()) {
                  tempStr = (String) stack.pop();
                  strVal1 = (String) operators.get(tempStr);
                  val1 = Integer.parseInt(strVal1);
                }
              }
              if (val1 < val2 && val1 != -100) {
                stack.push(tempStr);
              }
            }
            stack.push(currChar);
          }
        }
      }
    }
    while (!stack.isEmpty()) {
      pfExpr.push(stack.pop());
    }

    // se invierte el orden para facilitar el recorrido
    Stack postFix = new Stack();
    while(!pfExpr.isEmpty()) {
      postFix.push(pfExpr.pop());
    }
    return postFix;
  }

  private boolean isOperator(String str) {
      return str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/");
  }

  private boolean isOperator(Object obj) {
    try {
      return isOperator((String) obj);
    }
    catch (Exception e) {
      return false;
    }
  }

}

