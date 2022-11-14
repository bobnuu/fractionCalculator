

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Fraction result = calculator.calculate("1 / 3 % 4 + 2 * (2 % 3 - 1 % 6");
        System.out.println(result);
    }
}