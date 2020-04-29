import java.math.BigInteger;
public class Karatsuba {
    private final static BigInteger ZERO = new BigInteger("0");
    public static BigInteger karatsuba(BigInteger x, BigInteger y) {

        int N = Math.max(x.bitLength(), y.bitLength());
        if (N <= 2000){
            return x.multiply(y); //bigInteger multiplication since value is not Int/double and cant be used for x*y
        }
        //round up Bits div/2
        N = (N/2) + (N % 2);

        // x = a + 2^N(b), y = c + 2^N(d)
        BigInteger b = x.shiftRight(N);
        BigInteger a = x.subtract(b.shiftLeft(N));
        BigInteger d = y.shiftRight(N);
        BigInteger c = y.subtract(d.shiftLeft(N));


        //Sub-expression computation
        BigInteger ac = karatsuba(a, c);
        BigInteger bd = karatsuba(b, d);
        BigInteger abcd = karatsuba(a.add(b), c.add(d));

        return ac.add(abcd.subtract(ac).subtract(bd).shiftLeft(N)).add(bd.shiftLeft(N*2));

    }
    static long multiply(long x, long y) {

        // 0 multiplied with anything gives 0
        if (y == 0)
            return 0;

        // Add x one by one
        if (y > 0)
            return (x + multiply(x, y - 1));

        // the case where y is negative
        if (y < 0)
            return -multiply(x, -y);

        return -1;
    }

}
