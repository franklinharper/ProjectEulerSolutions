package com.franklinharper.projectEuler;

import static org.junit.Assert.*;

import org.junit.Test;

public class Problem14LongestcollatzSequence {

    public static void main( String[] args ) {
        long startMillis = System.currentTimeMillis();
        long maxLength = 1;
        long collatzMax = 1;
        for( int i = 1; i < 1000000; i++ ) {
            long length = collatzLength( i );
            if( length > maxLength ) {
                maxLength = length;
                collatzMax = i;
            }
        }
        double elapsedSeconds = ( System.currentTimeMillis() - startMillis ) / 1000d;
        System.out.println( String.format( "Elapsed time: %.3fs", elapsedSeconds ) );
        System.out.println( String.format( "maximum collatzLength( %d ): %d", collatzMax, maxLength ) );
    }

    private static long collatzLength( long n ) {
        long current = n;
        long length = 1;
        while( current != 1 ) {
            length++;
            if( current % 2 == 0 ) {
                current /= 2;
            } else {
                current = 3 * current + 1;
            }            
        }
        return length;
    }

    @Test
    public void test() {
        assertEquals( 10, collatzLength( 13 ) );
    }
}
