package com.franklinharper.projectEuler;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import com.franklinharper.projectEuler.util.ArrayUtils;
import com.franklinharper.projectEuler.util.IoUtils;

public class Problem18And67MaximumPathSum {

    public static void main( String[] args ) throws Exception {
        System.out.println( "Results for problem 18" );
        maxPathSum( IoUtils.createArray( "p018_triangle.txt" ) );
        System.out.println( "Results for problem 67" );
        int[][] data = IoUtils.createArray( "p067_triangle.txt" );
        maxPathSum( data );
    }

    private static int maxPathSum( int[][] input ) {
        long startMillis = System.currentTimeMillis();
        int[][] resultCache = ArrayUtils.createArray( input );
        int result = recursiveMaxPathSum( 0, 0, input, resultCache );
        double elapsedSeconds = ( System.currentTimeMillis() - startMillis ) / 1000.0;
        System.out.println( "Input data" );
        System.out.println( Arrays.deepToString( input ) );
        System.out.println( "Result cache" );
        System.out.println( Arrays.deepToString( resultCache ) );
        System.out.println( String.format( "maximumPathSum: %d", result ) );
        System.out.println( String.format( "Elapsed time: %.3fs", elapsedSeconds ) );
        return result;
    }

    private static int recursiveMaxPathSum( int row, int col, int[][] input, int[][] resultCache ) {
        if( resultCache[ row ][ col ] != 0 ) {
            return resultCache[ row ][ col ];
        }
        if( row + 1 == input.length ) {
            // We have hit a leaf node at the bottom the triangle.
            return input[ row ][ col ];
        }
        int maxLeft = recursiveMaxPathSum( row + 1, col, input, resultCache );
        int maxRight = recursiveMaxPathSum( row + 1, col + 1, input, resultCache );
        int max = maxLeft > maxRight ? maxLeft : maxRight;
        max += input[ row ][ col ];
        resultCache[ row ][ col ] = max;
        return max;
    }

    // JUnit tests below

    @Test
    public void test() throws Exception {

        assertEquals( 75, maxPathSum( new int[][] { { 75 }, } ) );

        assertEquals( 77, maxPathSum( new int[][] { { 75 }, { 1, 2 } } ) );

        assertEquals( 77, maxPathSum( new int[][] { { 75 }, { 2, 1 } } ) );

        assertEquals( 15, maxPathSum( new int[][] { { 1 }, { 5, 10 }, { 9, 2, 3 }, } ) );

        assertEquals( 20, maxPathSum( new int[][] { { 1 }, { 5, 10 }, { 2, 9, 3 }, } ) );

        assertEquals( 22, maxPathSum( new int[][] { { 1 }, { 5, 10 }, { 2, 3, 11 }, } ) );

        assertEquals( 1074, maxPathSum( IoUtils.createArray( "p018_triangle.txt" ) ) );
    }

}
