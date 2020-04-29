import java.math.BigInteger;
import java.util.Random;
import java.time.Instant;
import java.time.Duration;

public class Main {
    public static void main(String [] args){
        Random rd = new Random();

        //Number of digits (max 13 due to amount of recursive calls for slow multiplication method causing stack overflow)
        int N = 13;
        //initialize numbers - random numbers with 13 digits length
        Karatsuba k = new Karatsuba();
        BigInteger a = new BigInteger(N, rd);
        BigInteger b = new BigInteger(N, rd);
        long a1 = a.longValueExact();
        long a2 = b.longValueExact();



        //Initialize algorithms for print statements (isEqual)
        BigInteger c = k.karatsuba(a, b);
        long a0 = k.multiply(a1,a2);

        int z = 100000;

        //Time karatsuba z times the same number to lengthen time
        Instant start = Instant.now();
        for(int i = 0; i<z; i++) {
             c = (k.karatsuba(a, b));
        }
        Instant end = Instant.now();
        System.out.println("Karatsuba Algorithm Time: " + Duration.between(start,end));




        //Time naive multiplication z times
        start = Instant.now();
        for(int i = 0; i <z; i++){
             a0 = k.multiply(a1,a2);
        }
        end = Instant.now();
        System.out.println("Naive Multiply Algorithm Time: " +Duration.between(start,end));

        /*
        *Naive method must be hand coded, due to BigInteger.multiply()
        * using a combination of normal, Karatsuba, and Toom-Cook
        * methods always resulting in fastest time.
        * long * operator also uses karatsuba/toom-cook method as well
         */

        //Print true if numbers are equal
       System.out.println("Numbers multiplied is same: " + (c.longValueExact() == (a0))+ "\n" + a0 + "\n" + c);
       System.out.println("Numbers are true values: " + (a0 == a1*a2));
    }

}
