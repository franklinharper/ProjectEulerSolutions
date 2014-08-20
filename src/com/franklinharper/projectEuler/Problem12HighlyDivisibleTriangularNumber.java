package com.franklinharper.projectEuler;

import static org.junit.Assert.*;

import org.junit.Test;

public class Problem12HighlyDivisibleTriangularNumber {

    public static void main( String[] args ) {
        long startMillis = System.currentTimeMillis();
        long triangular = 1;
        int k = 2;
        long factorCount;
        while( ( factorCount = factorCount( triangular ) ) <= 500 ) {
            triangular += k;
            k++;
        }
        double elapsedSeconds = ( System.currentTimeMillis() - startMillis ) / 1000d;
        System.out.println( String.format( "Elapsed time: %.3fs", elapsedSeconds ) );
        System.out.println( String.format( "factorCount( %d ): %d", triangular, factorCount ) );
    }

    private static int factorCount( long n ) {
        int factors = 0;
        // 1. Factors occur in pairs where n = small * big, and small <= big
        // 2. The small factor can never be greater than sqrt(n), otherwise small > big
        long smallUpperLimit = (long) Math.sqrt( n );
        if( smallUpperLimit * smallUpperLimit == n ) {
            // When n is a square don't count sqrt(n) twice.
            factors++;
            smallUpperLimit--;
        }
        for( int i = 1; i <= smallUpperLimit; i++ ) {
            if( n % i == 0 ) {
                factors += 2;
            }
        }
        return factors;
    }

    @Test
    public void testFactorCount() {
        assertEquals( 6, factorCount( 28 ) );
        // Special case: squares are the only numbers with an odd number of
        // factors.
        assertEquals( 5, factorCount( 16 ) );
    }
}
