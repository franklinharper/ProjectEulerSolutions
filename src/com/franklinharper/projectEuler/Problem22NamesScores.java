package com.franklinharper.projectEuler;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;

import com.franklinharper.projectEuler.util.IoUtils;
import com.franklinharper.projectEuler.util.MyArrays;

public class Problem22NamesScores {

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
        String[] names = IoUtils.readStrings( "p022_names.txt", "\"(.*?)\"", 1 );
        System.out.println( "namesScores: " + sumNameScores( names ) );
    }

    private static int sumNameScores( String[] names ) {
        int sumNamesScores = 0;
        MyArrays.quickSort( names );
//        Arrays.sort( names );
        for( int i = 0; i < names.length; i++ ) {
            sumNamesScores += score( names, i );
        }
        return sumNamesScores;
    }

    private static int score( String[] names, int index ) {
        String name = names[ index ];
        int letterSum = 0;
        for( int i = 0; i < name.length(); i++ ) {
            letterSum += name.charAt( i ) - 'A' + 1;
        }
        return letterSum * ( index + 1 );
    }

    // JUnit tests below

    @Test
    public void testSumNameScores() {
        assertEquals( 1, sumNameScores( new String[] { "A" } ) );
        assertEquals( 3, sumNameScores( new String[] { "AB" } ) );
        assertEquals( 14 + 3, sumNameScores( new String[] { "CD", "AB" } ) );
        assertEquals( 159 + 17 , sumNameScores( new String[] { "COLIN", "CD", "AB", } ) );
    }

    @Test
    public void testScore() {
        assertEquals( 1, score( new String[] { "A" }, 0 ) );
        assertEquals( 3, score( new String[] { "AB" }, 0 ) );
        assertEquals( 14, score( new String[] { "AB", "CD" }, 1 ) );
        assertEquals( 159, score( new String[] { "AB", "CD", "COLIN" }, 2 ) );
    }
}