package com.franklinharper.projectEuler;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class Problem20FactorialDigitSum {

    // This problem would be trivial if solved using the standard Java BigInteger class,
    //
    // Since the whole point of ProjectEuler is to sharpen one's algorithmic skills, I will develop my own embryonic MyBigInteger class.
    //
    // Problem 20 -Factorial digit sum
    // ===============================
    //
    // n! means n × (n − 1) × ... × 3 × 2 × 1
    //
    // For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800,
    // and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.
    // 
    // Find the sum of the digits in the number 100!

    public static void main( String[] args ) throws Exception {
        System.out.println( "factorialDigitSum( 100 ): " + factorialDigitSum() );
    }

    private static int factorialDigitSum() {
        MyBigInt factorial = new MyBigInt( 1 );

        for( int i = 100; i > 1; i-- ) {
             factorial = factorial.multiply( i );
        }
        int digitSum = 0;
        for( int i = 0; i < factorial.numDigits(); i++ ) {
            digitSum += factorial.digit( i );
        }
        return digitSum;
    }

    // A toy BigInteger implementation.
    // An arbitrary precision number is represented using of digits (ArrayList< Integer >).
    // The least digit is stored at index 0.
    
    private static class MyBigInt {
        private ArrayList< Integer > mDigits = new ArrayList< Integer >();

        private MyBigInt() {
        };

        public MyBigInt( int n ) {
            if( n == 0 ) {
                mDigits.add( 0 );
                return;
            }
            while( n > 0 ) {
                mDigits.add( n % 10 );
                n /= 10;
            }
        }

        public MyBigInt add( MyBigInt otherBigInt ) {
            MyBigInt result = new MyBigInt();

            int maxDigits = Integer.max( this.numDigits(), otherBigInt.numDigits() );
            for( int i = 0; i < maxDigits; i++ ) {
                int sum = this.digit( i ) + otherBigInt.digit( i );
                while( sum > 9 ) {
                    result.mDigits.add( sum % 10 );
                    sum /= 10;
                    i++;
                    sum += this.digit( i ) + otherBigInt.digit( i );
                }
                result.mDigits.add( sum );
            }
            return result;
        }

        public MyBigInt multiply( int multiplicand ) {
            MyBigInt result = new MyBigInt( 0 );

            for( int i = 0; i < mDigits.size(); i++ ) {
                MyBigInt tmp = new MyBigInt();
                for( int j = 0; j < i; j++ ) {
                    tmp.mDigits.add( 0 );
                }
                int multiple = digit( i ) * multiplicand;
                while( multiple > 0 ) {
                    tmp.mDigits.add( multiple % 10 );
                    multiple /= 10;
                }
                result = result.add( tmp );
            }
            return result;
        }

        public int digit( int i ) {
            int a1 = i >= this.mDigits.size() ? 0 : this.mDigits.get( i );
            return a1;
        }

        public int numDigits() {
            return mDigits.size();
        }

        // Java is so verbose! At least this boilerplate code doesn't have to be
        // written by hand.

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ( ( mDigits == null ) ? 0 : mDigits.hashCode() );
            return result;
        }

        @Override
        public boolean equals( Object obj ) {
            if( this == obj )
                return true;
            if( obj == null )
                return false;
            if( getClass() != obj.getClass() )
                return false;
            MyBigInt other = (MyBigInt) obj;
            if( mDigits == null ) {
                if( other.mDigits != null )
                    return false;
            } else if( !mDigits.equals( other.mDigits ) )
                return false;
            return true;
        }

        @Override
        public String toString() {
            return "MyBigInt [mDigits=" + mDigits + "]";
        }

    }

    // JUnit tests below

    @Test
    public void testDigit() {
        assertEquals( 0, new MyBigInt( 0 ).digit( 0 ) );
        assertEquals( 1, new MyBigInt( 1 ).digit( 0 ) );
        assertEquals( 0, new MyBigInt( 1 ).digit( 1 ) );
        MyBigInt test = new MyBigInt( 12345 );
        assertEquals( 5, test.digit( 0 ) );
        assertEquals( 1, test.digit( 4 ) );
        assertEquals( 0, test.digit( 5 ) );
    }

    @Test
    public void testAdd() {
        assertEquals( new MyBigInt( 0 ), new MyBigInt( 0 ).add( new MyBigInt( 0 ) ) );
        assertEquals( new MyBigInt( 1 ), new MyBigInt( 1 ).add( new MyBigInt( 0 ) ) );
        assertEquals( new MyBigInt( 1 ), new MyBigInt( 0 ).add( new MyBigInt( 1 ) ) );
        assertEquals( new MyBigInt( 18 ), new MyBigInt( 17 ).add( new MyBigInt( 1 ) ) );
        assertEquals( new MyBigInt( 110 ), new MyBigInt( 11 ).add( new MyBigInt( 99 ) ) );
        assertEquals( new MyBigInt( 1010 ), new MyBigInt( 1005 ).add( new MyBigInt( 5 ) ) );
    }

    @Test
    public void testMultiply() {
        assertEquals( new MyBigInt( 0 ), new MyBigInt( 0 ).multiply( 0 ) );
        assertEquals( new MyBigInt( 0 ), new MyBigInt( 1 ).multiply( 0 ) );
        assertEquals( new MyBigInt( 0 ), new MyBigInt( 0 ).multiply( 1 ) );
        assertEquals( new MyBigInt( 0 ), new MyBigInt( 10 ).multiply( 0 ) );
        assertEquals( new MyBigInt( 9900 ), new MyBigInt( 100 ).multiply( 99 ) );
        assertEquals( new MyBigInt( 9801 ), new MyBigInt( 99 ).multiply( 99 ) );
        assertEquals( new MyBigInt( 91179 ), new MyBigInt( 99 ).multiply( 921 ) );
    }
}