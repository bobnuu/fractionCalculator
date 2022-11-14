public class Fraction {
    public int nm;  // numerator
    public int dm;  // denominator

    public Fraction(int nm, int dm) throws ArithmeticException {
        if (dm == 0) {
            throw new ArithmeticException("Division by zero");
        }
        this.nm = nm;
        this.dm = dm;
    }

    @Override
    public String toString() {
        return this.nm + "/" + this.dm;
    }
}
