package com.franklinharper.projectEuler;

import static org.junit.Assert.*;

import org.junit.Test;

public class Problem17NumberLetterCounts {

    private static final String[] LOW_NUMBERS = {
        // "zero" is a special case, because it isn't used when writing numbers.
        // E.g. 20 is not written as "twenty zero", while 21 is written "twenty one"
        "",
        "one",
        "two",
        "three",
        "four",
        "five",
        "six",
        "seven",
        "eight",
        "nine",
        "ten",
        "eleven",
        "twelve",
        "thirteen",
        "fourteen",
        "fifteen",
        "sixteen",
        "seventeen",
        "eighteen",
        "nineteen", };

    private static final String[] TENS = {
        // "zero" tens is a special case, because it isn't used when writing numbers.
        // E.g. 100 is not written as "one hundred zero zero", while 110 is written "one hundred and 10"
        "",
        "ten",
        "twenty",
        "thirty",
        "forty",
        "fifty",
        "sixty",
        "seventy",
        "eighty",
        "ninety",
    };
    
    public static void main( String[] args ) {
        System.out.println( String.format( "sum letterLengths: %d", sumLetterLengths( 1000 ) ) );
    }

    private static int sumLetterLengths( int n ) {
        long startMillis = System.currentTimeMillis();
        int sumLetterLengths = 0;
        for( int i = 1; i <= n; i++ ) {
            String numberInWords = number2Words( i );
            sumLetterLengths += numberInWords.length();
            System.out.println( String.format("%d: %s, letterCount: %d, totalLetterCount: %d", i, numberInWords, numberInWords.length(), sumLetterLengths ) );
        }
        double elapsedSeconds = ( System.currentTimeMillis() - startMillis ) / 1000d;
        System.out.println( String.format( "Elapsed time: %.3fs", elapsedSeconds ) );
        return sumLetterLengths;
    }

    // Spaces are not inserted.
    private static String number2Words( int n ) {
        if( n < 0 || n > 1000 ) {
            throw new IllegalArgumentException("n is " + n + ". The value of n must be >= 0 and <= 1000");
        }
        if( n == 1000 ) {
            return "onethousand";
        }
        if( n >= 100 ) {
            if( n % 100 == 0 ) {
                return LOW_NUMBERS[ n / 100 ] + "hundred";
            } else {
                return LOW_NUMBERS[ n / 100 ] + "hundredand" + number2Words( n % 100 );
            }
        }
        if( n >= 20 ) {
            return TENS[ n / 10 ] + number2Words( n % 10 );
        }
        return LOW_NUMBERS[ n ];
    }

    // JUnit tests below
    
    @Test
    public void testSumLetterLengths() {
        assertEquals( 19, sumLetterLengths( 5 ) );
    }
    
    @Test
    public void testNumber2Words() {
        assertEquals( "", number2Words( 0 ) );
        assertEquals( "one", number2Words( 1 ) );
        assertEquals( "nineteen", number2Words( 19 ) );
        assertEquals( "twenty", number2Words( 20 ) );
        assertEquals( "twentyone", number2Words( 21 ) );
        assertEquals( "fortytwo", number2Words( 42 ) );
        assertEquals( "ninetynine", number2Words( 99 ) );
        assertEquals( "onehundred", number2Words( 100 ) );
        assertEquals( "onehundredandfifteen", number2Words( 115 ) );
        assertEquals( "threehundredandfortytwo", number2Words( 342 ) );
        assertEquals( "ninehundredandninetynine", number2Words( 999 ) );
        assertEquals( "onethousand", number2Words( 1000 ) );
    }
}
