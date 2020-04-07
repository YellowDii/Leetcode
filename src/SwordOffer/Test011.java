package SwordOffer;

public class Test011 {
    public static double power(double base, int exponent) {
        // 指数和底数不能同时为0
        if (base == 0 && exponent == 0) {
            throw new RuntimeException("invalid input. base and exponent both are zero");
        }

        if (exponent == 0 || base == 1) {
            return 1;
        }
        long exp = exponent;
        if (exponent < 0) {
            exp = -exp;
        }

        // 求幂次方
        double result = powerWithUnsignedExponent(base, exp);

        // 指数是负数，要进行求倒数
        if (exponent < 0) {
            result = 1 / result;
        }

        return result;
    }

    private static double powerWithUnsignedExponent(double base, long exp) {
        if (exp == 0 || base == 1) {
            return 1;
        }
        if (exp == 1){
            return base;
        }
        double result=powerWithUnsignedExponent(base,exp>>1);

        result*=result;
        if (exp%2==1){
            result*=base;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(power(2d,11));
    }
}
