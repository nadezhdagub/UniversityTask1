package polynomial;

public class Poly {
    private final int[] variables;

    public Poly(int[] variables) {
        this.variables = variables;
    }

    @Override
    public final String toString() {
        if (getLength() == 0 || getVariables() == null) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        result.append(getVariables()[0] != 0 ? getVariables()[0] + " + " : "");
        if (getLength() > 1) {
            result.append(getVariables()[1] == 1 ? "x" : getVariables()[1] + "*x");
            for (int i = 2; i < getLength(); i++)
                if (getVariables()[i] != 0) {
                    result.append(" + ");
                    result.append(getVariables()[i] == 1 ? "x^" + i : getVariables()[i] + "*x^" + i);
                }
        }
        return result.toString();
    }

    @Override
    public int hashCode() {
        int result = 0;
        for (int element : variables)
            result += element;
        return 31 + result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass() || !Poly.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        Poly objCopy = (Poly) obj;
        int[] objVariables = objCopy.getVariables();
        if (getLength() == objCopy.getLength()) {
            for (int i = 0; i < getLength(); i++)
                if (variables[i] != objVariables[i])
                    return false;
            return true;
        }
        return false;
    }

    public long value(int x) {
        int power = 0;
        long polyValue = 0;
        for (int element : variables) {
            polyValue += Math.pow(x, power) * element;
            power++;
        }
        return polyValue;
    }

    public Poly sum(Poly obj) {
        int larger;
        int smaller;
        int[] largerVariables;
        largerVariables = getLength() > obj.getLength() ? variables : obj.getVariables();
        larger = largerVariables.length;
        smaller = obj.getLength() == larger ? getLength() : obj.getLength();
        int[] newVariables = new int[larger];
        for (int i = 0; i < smaller; i++)
            newVariables[i] = variables[i] + obj.getVariables()[i];
        if (larger != smaller)
            System.arraycopy(largerVariables, smaller, newVariables, smaller, larger - smaller);
        return removeZeros(newVariables);
    }

    public Poly subtraction(Poly obj) {
        int larger;
        int smaller;
        int[] largerVariables;
        largerVariables = getLength() > obj.getLength() ? variables : obj.getVariables();
        larger = largerVariables.length;
        smaller = obj.getLength() == larger ? getLength() : obj.getLength();
        int[] resultVariables = new int[larger];
        for (int i = 0; i < smaller; i++)
            resultVariables[i] = variables[i] - obj.getVariables()[i];
        if (larger != smaller)
            System.arraycopy(largerVariables, smaller, resultVariables, smaller, larger - smaller);
        return removeZeros(resultVariables);
    }

    private Poly div(Poly obj, boolean which) { // Auxiliary method used to return a division or rem from dividing one
                                                // polynomial to another
        int[] divider = getLength() > obj.getLength() ? variables : obj.getVariables();
        int[] divisor = getLength() > obj.getLength() ? obj.getVariables() : variables;
        int[] quotient = new int[divider.length - divisor.length + 1];
        for (int i = 0; i < quotient.length; i++) {
            int coefficient = divider[divider.length - i - 1] / divisor[divisor.length - 1];
            quotient[quotient.length - i - 1] = coefficient;
            for (int j = 0; j < divisor.length; j++)
                divider[divider.length - i - j - 1] -= coefficient * divisor[divisor.length - j - 1];
        }
        return which ? removeZeros(quotient) : removeZeros(divider);
    }

    public Poly division(Poly obj) {
        return div(obj, true);
    }

    public Poly remainder(Poly obj) {
        return div(obj, false);
    }

    public Poly multiplication(Poly obj) {
        int objLength = obj.getLength();
        int[] objVariables = obj.getVariables();
        int[] resultVariables = new int[getLength() + objLength - 1];
        int larger;
        int smaller;
        int[] largerVariables;
        int[] smallerVariables;
        largerVariables = getLength() > objLength ? variables : objVariables;
        smallerVariables = getLength() > objLength ? objVariables : variables;
        larger = largerVariables.length;
        smaller = objLength == larger ? getLength() : objLength;
        for (int i = 1; i < larger + 1; i++)
            for (int j = 1; j < smaller + 1; j++)
                resultVariables[i + j - 2] += largerVariables[i - 1] * smallerVariables[j - 1];

        return new Poly(resultVariables);
    }

    private Poly removeZeros(int[] variables) {
        if (variables[variables.length - 1] == 0) {
            for (int i = variables.length - 1; i >= 0; i--) { // Cycle removes a number of zeros from an array of values
                if (variables[i] != 0) {
                    int[] result = new int[i + 1];
                    System.arraycopy(variables, 0, result, 0, i + 1);
                    return new Poly(result);
                }
            }
        } else {
            return new Poly(variables);
        }
        return new Poly(new int[] {});
    }

    public int[] getVariables() {
        return this.variables;
    }

    public int getLength() {
        return this.variables.length;
    }
}
