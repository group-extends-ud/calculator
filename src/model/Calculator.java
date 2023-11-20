package model;

import java.util.HashMap;

public class Calculator {
    private String expression;
    private final HashMap<String, String> operators;
    private Context ctx;

    public Calculator() {
        operators = new HashMap<>(10);
        operators.put("+", "1");
        operators.put("-", "1");
        operators.put("/", "2");
        operators.put("*", "2");
        operators.put("\\", "2"); // división entera
        operators.put("%", "3");
        operators.put("P", "4");
        operators.put("C", "4");
        operators.put("^", "5");
        operators.put("(", "0");
    }

    public static int factorial(double n) {
        int fact = 1;
        for (int i = 2; i <= n; i++) {
            fact = fact * i;
        }
        return fact;
    }

    public void setContext(Context c) {
        ctx = c;
    }

    public void setExpression(String expr) {
        expression = expr;
    }

    public Double evaluate() {
        // infix to Postfix
        Stack pfExpr = infixToPostFix(expression);

        // build the Binary Tree
        Expression rootNode = buildTree(pfExpr);

        // Evaluate the tree
        if (rootNode == null) {
            return null;
        }
        return rootNode.evaluate(ctx);
    }

    private NonTerminalExpression getNonTerminalExpression(String operation, Expression l, Expression r) {
        return switch (operation.trim()) {
            case "+" -> new AddExpression(l, r);
            case "-" -> new SubtractExpression(l, r);
            case "*" -> new MultiplyExpression(l, r);
            case "/" -> new DivideExpression(l, r);
            case "\\" -> new IntDivideExpression(l, r);
            case "%" -> new ModExpression(l, r);
            case "^" -> new PowExpression(l, r);
            case "P" -> new PermutationExpression(l, r);
            case "C" -> new CombinationExpression(l, r);
            default -> null;
        };

    }

    private Expression buildTree(Stack expr) {
        Stack s = new Stack();

        while (!expr.isEmpty()) {
            Object currObj = expr.pop();

            if (!ctx.isOperator(currObj)) {
                Expression e = new TerminalExpression(currObj);
                s.push(e);
            } else {
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

        String tempStr;
        String expr = str.trim();

        // recorre cada char en str
        for (int i = 0; i < expr.length(); i++) {
            String currChar = expr.substring(i, i + 1);

            // if the current character is an operand
            if (ctx.isInt(currChar)) {
                double valor = 0.0;
                int maxSize = 0;
                // toma el double más largo desde esa posición
                for (int j = i; j < expr.length() + 1; j++) {
                    if (ctx.isDouble(expr.substring(i, j))) {
                        valor = Double.parseDouble(expr.substring(i, j));
                        maxSize = j - i;
                    }
                }
                pfExpr.push(valor);
                i += maxSize - 1;
            } else if (ctx.isConstant(currChar)) {
                pfExpr.push(ctx.getValue(currChar));
            } else if (currChar.equals("(")) {
                stack.push(currChar);
            } else if (currChar.equals(")")) {
                Object element;
                while (true) {
                    element = stack.pop();
                    if (element != null) {
                        if (element.equals("(")) {
                            break;
                        } else {
                            pfExpr.push(element);
                        }
                    } else {
                        break;
                    }
                }
            } else if (ctx.isOperator(currChar)) {
                // 8a
                if (stack.isEmpty()) {
                    stack.push(currChar);
                    // Por si el número inicial es negativo
                    if (currChar.equals("-") && i == 0) {
                        pfExpr.push(0.0);
                    }
                }
                // 8b
                else {
                    // 8bi


                    tempStr = (String) stack.pop();

                    // 8biA
                    String strVal1 = operators.get(tempStr);
                    int val1 = Integer.parseInt(strVal1);

                    String strVal2 = operators.get(currChar);
                    int val2 = Integer.parseInt(strVal2);

                    // 8biB
                    while (val1 >= val2) {
                        pfExpr.push(tempStr);
                        val1 = -100;
                        if (!stack.isEmpty()) {
                            tempStr = (String) stack.pop();
                            strVal1 = operators.get(tempStr);
                            val1 = Integer.parseInt(strVal1);
                        }
                    }
                    if (val1 != -100) {
                        stack.push(tempStr);
                    }

                    stack.push(currChar);

                }
            }
        }
        while (!stack.isEmpty()) {
            pfExpr.push(stack.pop());
        }

        // se invierte el orden para facilitar el recorrido
        Stack postFix = new Stack();
        while (!pfExpr.isEmpty()) {
            postFix.push(pfExpr.pop());
        }
        return postFix;
    }

}

