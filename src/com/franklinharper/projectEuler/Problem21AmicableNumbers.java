package com.franklinharper.projectEuler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Problem21AmicableNumbers {

    // Problem 21 - Amicable numbers
    // =============================
    //
    // Let d(n) be defined as the sum of proper divisors of n (numbers less than
    // n which divide evenly into n).
    // If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair
    // and each of a and b are called amicable numbers.
    //
    // For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22,
    // 44, 55 and 110;
    // therefore d(220) = 284. The proper divisors of 284 are 1, 2, 4, 71 and
    // 142; so d(284) = 220.
    //
    // Evaluate the sum of all the amicable numbers under 10000.

    public static void main( String[] args ) throws Exception {
        System.out.println( "sumAmicableNumbers( 10000 ): " + sumAmicableNumbers( 10000 ) );
    }

    private static int sumAmicableNumbers( int n ) {
        int sum = 0;

        int d[] = new int[ n ];
        for( int i = 2; i < n; i++ ) {
            d[ i ] = sumOfProperDivisors( i );
            if( d[ i ] < i && d[ d[ i ] ] == i ) {
                sum += i + d[ i ];
                System.out.format("Found amicable numbers a1:%d, a2:%d\n", d[ i ], i );
            }
        }
        return sum;
    }

    private static int sumOfProperDivisors( int n ) {
        int sum = 1;
        int sqrtN = (int) Math.sqrt( n );
        if( sqrtN * sqrtN == n  ) {
            sum += sqrtN;
        }
        for( int i = 2; i < sqrtN; i++ ) {
            if( n % i == 0 ) {
                sum += i + n / i;
            }
        }
        return sum;
    }

    // JUnit tests below

    @Test
    public void testSumOfProperDivisors() {
        assertEquals( 1, sumOfProperDivisors( 2 ) );
        assertEquals( 15, sumOfProperDivisors( 16 ) );
        assertEquals( 284, sumOfProperDivisors( 220 ) );
        assertEquals( 220, sumOfProperDivisors( 284 ) );
    }
    
    @Test
    public void testSumAmicableNumbers() {
        assertEquals( 0, sumAmicableNumbers( 7 ) );
        assertEquals( 504, sumAmicableNumbers( 285 ) );
    }
}