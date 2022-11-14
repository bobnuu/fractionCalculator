import java.lang.String;
import java.util.ArrayList;
import java.util.Collections;

public class Calculator {
    BasicOperations operations = new BasicOperations();

    public Fraction calculate(String expr) {
        ExprContainer parsedExpr = parse(expr);
        // Iteratively execute operations one by one according to their priorities.
        while (parsedExpr.operators.size() > 0) {
            parsedExpr = executeHighestPriorityOperation(parsedExpr);
        }
        return parsedExpr.values.get(0);
    }

    /*
    Here we parse a mathematical expression string to values, operations and priorities of operations.
    As a result, we get n values and n-1 operations.
    Higher priority operations should be executed first.
    + and - operators have 0 priority;
    * and / operators have 1 priority;
    % operator has 2 priority;
    Priority increases by 4 inside each pair of parentheses;

    % operator is used to define in-expression fraction rather than just division.
    1 / 2 / 3 = 1 / 6 but 1 / 2 % 3 = 3 / 2
     */
    public ExprContainer parse(String expr) {
        ArrayList<Fraction> values = new ArrayList<>();
        ArrayList<Character> operators = new ArrayList<>();
        ArrayList<Integer> opPriorities = new ArrayList<>();
        String stringDigits = "0123456789";
        String stringOps = "+-*/%";
        StringBuilder stringNum = new StringBuilder();
        int basePriority = 0;

        for (int i=0; i<expr.length(); i++) {
            char ch = expr.charAt(i);
            boolean isNumber = stringDigits.indexOf(ch) != -1;
            boolean isOperation = stringOps.indexOf(ch) != -1;
            boolean increasePriority = ch == '(';
            boolean decreasePriority = ch == ')';

            if (isNumber) {
                stringNum.append(ch);
            } else {
                if (stringNum.length() > 0) {
                    int value = Integer.parseInt(stringNum.toString());
                    values.add(new Fraction(value, 1));
                    stringNum = new StringBuilder();
                }
            }

            if (isOperation) {
                operators.add(ch);
                int priority = basePriority;
                if (ch == '*' || ch == '/') {
                    priority += 1;
                } else if (ch == '%') {
                    priority += 2;
                }
                opPriorities.add(priority);
            }

            if (increasePriority) {
                basePriority += 4;
            } else if (decreasePriority) {
                basePriority -= 4;
            }
        }
        if (stringNum.length() > 0) {
            int value = Integer.parseInt(stringNum.toString());
            values.add(new Fraction(value, 1));
        }
        return new ExprContainer(values, operators, opPriorities);
    }

    public ExprContainer executeHighestPriorityOperation(ExprContainer expr) {
        int index = expr.opPriorities.indexOf(Collections.max(expr.opPriorities));
        Fraction a = expr.values.get(index);
        Fraction b = expr.values.get(index + 1);
        char op = expr.operators.get(index);
        Fraction newValue = this.operations.calculate(a, b, op);
        expr.values.remove(index);
        expr.operators.remove(index);
        expr.opPriorities.remove(index);
        expr.values.set(index, newValue);
        return expr;
    }

}
