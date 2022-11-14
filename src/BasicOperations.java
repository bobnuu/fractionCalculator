public class BasicOperations {
    public Fraction add(Fraction a, Fraction b) {
        int cdm = lcm(a.dm, b.dm);
        int nm = a.nm * cdm / a.dm + b.nm * cdm / b.dm;
        return normalize(new Fraction(nm, cdm));
    }

    public Fraction subtract(Fraction a, Fraction b) {
        // a - b = a + (-b)
        Fraction nb = new Fraction(-b.nm, b.dm); // nb = -b
        return normalize(add(a, nb));
    }

    public Fraction multiply(Fraction a, Fraction b) {
        Fraction res = new Fraction(a.nm * b.nm, a.dm * b.dm);
        return normalize(res);
    }

    public Fraction divide(Fraction a, Fraction b) throws ArithmeticException {
        // a / b = a * (1 / b)
        if (b.nm == 0) {
            throw new ArithmeticException("Division by zero");
        }
        Fraction ob = new Fraction(b.dm, b.nm);  // ob = 1 / b
        return normalize(multiply(a, ob));
    }

    public int gcd(int a, int b) {
        while (a != 0) {
            if (b > a) {
                int c = a;
                a = b;
                b = c;
            }
            a %= b;
        }
        return b;
    }

    public int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    /*
    Cancels both numerator and denominator by greatest common divisor.
     */
    public Fraction normalize(Fraction a) {
        int cd = gcd(a.nm, a.dm);
        return new Fraction(a.nm / cd, a.dm / cd);
    }

    public Fraction calculate(Fraction a, Fraction b, char op) throws ArithmeticException {
        return switch (op) {
            case '+' -> add(a, b);
            case '-' -> subtract(a, b);
            case '*' -> multiply(a, b);
            case '/', '%' -> divide(a, b);
            default -> throw new ArithmeticException("Could not find the operation");
        };
    }
}
