package com.franklinharper.projectEuler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Test;

import com.franklinharper.projectEuler.util.IoUtils;

public class Problem23NonAbundantSums {

    // Problem 23
    // ==========
    //
    // A perfect number is a number for which the sum of its proper divisors is
    // exactly equal to the number.
    // For example, the sum of the proper divisors of 28 would be 1 + 2 + 4 + 7
    // + 14 = 28, which means that 28 is a perfect number.
    //
    // A number n is called deficient if the sum of its proper divisors is less
    // than n
    // and it is called abundant if this sum exceeds n.
    //
    // As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the
    // smallest number that can be written
    // as the sum of two abundant numbers is 24.
    //
    // By mathematical analysis, it can be shown that all integers greater than
    // 28123 can be written
    // as the sum of two abundant numbers. However, this upper limit cannot be
    // reduced any further by
    // analysis even though it is known that the greatest number that cannot be
    // expressed as the sum
    // of two abundant numbers is less than this limit.
    //
    // Find the sum of all the positive integers which cannot be written as the
    // sum of two abundant numbers.

    public static void main( String[] args ) throws Exception {
        String[] names = IoUtils.readStrings( "p022_names.txt", "\"(.*?)\"", 1 );
         System.out.println( "sumNotSumOf2Abundants( 28123 ): " + sumNotSumOf2Abundants( 28123 ) );
    }

    private static int sumNotSumOf2Abundants( int n ) {
        int sumNotSumOf2Abundants = 0;
        SortedSet< Integer > abundants = abundants( n );
        for( int candidate = 1; candidate <= n; candidate++ ) {
            if( !isSumOf2Integers( candidate, abundants ) ) {
                sumNotSumOf2Abundants += candidate;
            }
        }
        return sumNotSumOf2Abundants;
    }

    private static boolean isSumOf2Integers( int k, SortedSet< Integer > ints ) {
        for( int i : ints ) {
            if( i >= k ) {
                return false;
            }
            if( ints.contains( k - i ) ) {
                return true;
            }
        }
        return false;
    }

    private static SortedSet< Integer > abundants( int n ) {
        TreeSet< Integer > abundants = new TreeSet< Integer >();
        for( int candidate = 1; candidate <= n; candidate++ ) {
            if( isAbundant( candidate ) ) {
                abundants.add( candidate );
            }
        }
        return abundants;
    }

    private static boolean isAbundant( int n ) {
        // 1 is always a proper divisor except for itself.
        int sumProperDivisors = n == 1 ? 0 : 1;
        int upperLimit = (int) Math.sqrt( n );
        if( upperLimit * upperLimit == n ) {
            // All proper divisors d1,d2 occur in pairs where n = d1 * d2
            // The exception is when n is a square: n = d1 * d1, so in that case
            // don't add d1 twice to sumProperDivisors.
            sumProperDivisors += (int) Math.sqrt( n );
            upperLimit--;
        }
        for( int i = 2; i <= upperLimit; i++ ) {
            if( n % i == 0 ) {
                sumProperDivisors += i + n / i;
            }
        }
        return sumProperDivisors > n;
    }

    // JUnit tests below

    @Test
    public void testIsSumOf2Integers() {
        assertFalse( isSumOf2Integers( 1, new TreeSet< Integer >( Arrays.asList( 1 ) ) ) );
        assertFalse( isSumOf2Integers( 0, new TreeSet< Integer >( Arrays.asList( 1, 2 ) ) ) );
        assertFalse( isSumOf2Integers( 1, new TreeSet< Integer >( Arrays.asList( 1, 2 ) ) ) );
        assertTrue( isSumOf2Integers( 2, new TreeSet< Integer >( Arrays.asList( 1, 2 ) ) ) );
        assertTrue( isSumOf2Integers( 3, new TreeSet< Integer >( Arrays.asList( 1, 2 ) ) ) );
        assertTrue( isSumOf2Integers( 4, new TreeSet< Integer >( Arrays.asList( 1, 2 ) ) ) );
        assertFalse( isSumOf2Integers( 5, new TreeSet< Integer >( Arrays.asList( 1, 2 ) ) ) );
        assertTrue( isSumOf2Integers( 5, new TreeSet< Integer >( Arrays.asList( 1, 2, 3 ) ) ) );
    }

    @Test
    public void testisAbundant() {
        for( int n = 1; n < 12; n++ ) {
            assertFalse( isAbundant( n ) );
        }
        assertTrue( isAbundant( 12 ) );
        assertFalse( isAbundant( 13 ) );
    }

    @Test
    public void testSumNonAbundants() {
        assertEquals( 1, sumNotSumOf2Abundants( 1 ) );
        assertEquals( 3, sumNotSumOf2Abundants( 2 ) );
        {
            // 12 is the smallest abundant, so 24 is the first number that can be written as the sum of 2 abundants ( 12 + 12 ).
            int expected = 0;
            for( int i = 1; i < 24; i++ ) {
                expected += i;
            }
            // expected = 1 + 2 + 3 + ... + 23
            assertEquals( expected, sumNotSumOf2Abundants( 24 ) );            
        }
    }
}