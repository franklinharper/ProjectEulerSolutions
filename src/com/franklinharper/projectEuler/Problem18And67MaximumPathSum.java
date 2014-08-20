package com.franklinharper.projectEuler;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import com.franklinharper.projectEuler.util.IoUtils;

public class Problem18And67MaximumPathSum {

    public static void main( String[] args ) throws Exception {
        System.out.println( "Results for problem 18" );
        maxPathSum( IoUtils.createArray( "p018_triangle.txt" ) );
        System.out.println( "Results for problem 67" );
        int[][] data = IoUtils.createArray( "p067_triangle.txt" );
        maxPathSum( data );
    }

    private static int maxPathSum( int[][] values ) {
        System.out.println( "Input array" );
        System.out.println( Arrays.deepToString( values ) );
        long startMillis = System.currentTimeMillis();

        // The trick is to work up from the bottom of the triangular array, rather down from the top.
        //
        // On the bottom row, the paths are all of length 1, so the maximum paths are all trivially equal to the input.
        //
        //      maximumPath( r, c ) = triangle(r, c)
        //
        // For all the preceding rows, the maximumPath(r, c) = value(r, c) + the maximum of the left and right sub trees.
        //
        for( int r = values.length - 2; r >= 0; r-- ) {
            for( int c = 0; c < values[ r ].length; c++ ) {
                int max = Integer.max( values[ r + 1 ][ c ], values[ r + 1 ][ c + 1 ] );
                values[ r ][ c ] += max;
            }
        }

        double elapsedSeconds = ( System.currentTimeMillis() - startMillis ) / 1000.0;
        System.out.println( "Result array" );
        System.out.println( Arrays.deepToString( values ) );
        int result = values[ 0 ][ 0 ];
        System.out.println( String.format( "maximumPathSum: %d", result ) );
        System.out.println( String.format( "Elapsed time: %.3fs", elapsedSeconds ) );
        return result;
    }

    // JUnit tests below

    @Test
    public void test() throws Exception {

        assertEquals( 75, maxPathSum( new int[][] { 
            { 75 },
        } ) );

        assertEquals( 77, maxPathSum( new int[][] {
            { 75 }, 
            { 1, 2 },
        } ) );

        assertEquals( 77, maxPathSum( new int[][] {
            { 75 },
            { 2, 1 },
        } ) );

        assertEquals( 15, maxPathSum( new int[][] {
            { 1 },
            { 5, 10 },
            { 9, 2, 3 },
        } ) );

        assertEquals( 20, maxPathSum( new int[][] {
            { 1 },
            { 5, 10 },
            { 2, 9, 3 },
        } ) );

        assertEquals( 22, maxPathSum( new int[][] {
            { 1 },
            { 5, 10 },
            { 2, 3, 11 },
        } ) );

        assertEquals( 1074, maxPathSum( IoUtils.createArray( "p018_triangle.txt" ) ) );
        assertEquals( 7273, maxPathSum( IoUtils.createArray( "p067_triangle.txt" ) ) );
    }

}
