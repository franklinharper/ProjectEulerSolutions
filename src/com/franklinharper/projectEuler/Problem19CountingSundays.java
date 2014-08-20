package com.franklinharper.projectEuler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Problem19CountingSundays {

    // DAYS_IN_MONTH[ 0 ] is a dummy value inserted so that
    // Jan. = 1,
    // Feb. = 2, etc.
    private static final int[] DAYS_IN_MONTH = new int[] { -1, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31, };

    public static void main( String[] args ) throws Exception {
        int result = sundaysOnFirstOfMonth( new YearMonthDay( 1901, 1 ), new YearMonthDay( 2000, 12 ) );
        System.out.println( "Sundays on the first of the month: " + result );
    }

    private static class YearMonthDay {

        int mYear;
        int mMonth;

        public YearMonthDay( int year, int month ) {
            mYear = year;
            mMonth = month;
        }

    }

    private static int sundaysOnFirstOfMonth( YearMonthDay start, YearMonthDay end ) {
        int year = 1900;
        int month = 1;
        int day = 1;
        int sundays = 0;
        while( year < end.mYear || ( year == end.mYear && month <= end.mMonth ) ) {
            if( day % 7 == 0 && (year > start.mYear || ( year == start.mYear && month >= start.mMonth ) ) ) {
                sundays++;
            }
            day += daysInMonth( year, month );
            month++;
            if( month == 13 ) {
                year++;
                month = 1;
            }
        }
        return sundays;
    }

    private static int daysInMonth( int year, int month ) {
        if( month == 2 ) {
            return isLeapYear( year ) ? 29 : 28;
        } else {
            return DAYS_IN_MONTH[ month ];
        }
    }

    private static boolean isLeapYear( int year ) {
        return year % 400 == 0 || ( year % 4 == 0 && year % 100 != 0 );
    }

    // JUnit tests below

    @Test
    public void testSundaysOnFirstOfMonth() throws Exception {
        assertEquals( 0, sundaysOnFirstOfMonth( new YearMonthDay( 1900, 1 ), new YearMonthDay( 1900, 1 ) ) );
        assertEquals( 0, sundaysOnFirstOfMonth( new YearMonthDay( 1900, 1 ), new YearMonthDay( 1900, 3 ) ) );
        assertEquals( 1, sundaysOnFirstOfMonth( new YearMonthDay( 1900, 4 ), new YearMonthDay( 1900, 4 ) ) );
        assertEquals( 1, sundaysOnFirstOfMonth( new YearMonthDay( 1900, 3 ), new YearMonthDay( 1900, 4 ) ) );
        assertEquals( 1, sundaysOnFirstOfMonth( new YearMonthDay( 1900, 4 ), new YearMonthDay( 1900, 5 ) ) );
        
        assertEquals( 2, sundaysOnFirstOfMonth( new YearMonthDay( 1998, 2 ), new YearMonthDay( 1998, 3 ) ) );
        assertEquals( 3, sundaysOnFirstOfMonth( new YearMonthDay( 1998, 2 ), new YearMonthDay( 1998, 11 ) ) );
        
        assertEquals( 1, sundaysOnFirstOfMonth( new YearMonthDay( 2000, 10 ), new YearMonthDay( 2000, 10 ) ) );        
        assertEquals( 0, sundaysOnFirstOfMonth( new YearMonthDay( 2000, 11 ), new YearMonthDay( 2000, 12 ) ) );
    }

    @Test
    public void testDaysInmonth() throws Exception {

        assertEquals( 31, daysInMonth( 1900, 1 ) );

        assertEquals( 28, daysInMonth( 1900, 2 ) );
        assertEquals( 28, daysInMonth( 1901, 2 ) );
        assertEquals( 29, daysInMonth( 1904, 2 ) );
        assertEquals( 29, daysInMonth( 2000, 2 ) );

        assertEquals( 31, daysInMonth( 1900, 3 ) );

        assertEquals( 30, daysInMonth( 1900, 4 ) );

        assertEquals( 31, daysInMonth( 1900, 5 ) );

        assertEquals( 30, daysInMonth( 1900, 6 ) );

        assertEquals( 31, daysInMonth( 1900, 7 ) );

        assertEquals( 31, daysInMonth( 1900, 8 ) );

        assertEquals( 30, daysInMonth( 1900, 9 ) );

        assertEquals( 31, daysInMonth( 1900, 10 ) );

        assertEquals( 30, daysInMonth( 1900, 11 ) );

        assertEquals( 31, daysInMonth( 1900, 12 ) );
    }

    @Test
    public void testIsLeapYear() throws Exception {
        assertEquals( false, isLeapYear( 1900 ) );
        assertEquals( false, isLeapYear( 1901 ) );
        assertEquals( false, isLeapYear( 1902 ) );
        assertEquals( false, isLeapYear( 1903 ) );
        assertEquals( true, isLeapYear( 1904 ) );
        assertEquals( false, isLeapYear( 1999 ) );
        assertEquals( true, isLeapYear( 2000 ) );
        assertEquals( false, isLeapYear( 2001 ) );
        assertEquals( true, isLeapYear( 2004 ) );
    }

}
