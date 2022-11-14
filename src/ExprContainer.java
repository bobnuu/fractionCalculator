import java.util.ArrayList;

public class ExprContainer {
    public ArrayList<Fraction> values;
    public ArrayList<Character> operators;
    public ArrayList<Integer> opPriorities;

    public ExprContainer(ArrayList<Fraction> values, ArrayList<Character> operators, ArrayList<Integer> opPriorities) {
        this.values = values;
        this.operators = operators;
        this.opPriorities = opPriorities;
    }
}
