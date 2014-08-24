package com.franklinharper.projectEuler.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Collections2;

public class MyArrays {

    /**
     * Create an int[][] array of the same dimensions as srcArray.
     * 
     */
    public static int[][] createArray( int[][] srcArray ) {
        int rows = srcArray.length;
        int[][] newArray = new int[ rows ][];
        for( int row = 0; row < rows; row++ ) {
            newArray[ row ] = new int[ srcArray[ row ].length ];
        }
        return newArray;
    }

    public static void quickSort( Object[] input ) {
        recursiveQuickSort( input, 0, input.length - 1 );
    }

    private static void recursiveQuickSort( Object[] array, int left, int right ) {
        if( left < right ) {
            int pivotIndex = partition( array, left, right );
            recursiveQuickSort( array, left, pivotIndex - 1 );
            recursiveQuickSort( array, pivotIndex + 1, right );
        }
    }

    //
    // private static void partition( int pivotIndex, String[] array, int left,
    // int right ) {
    //
    // if( array.length < 2 ) {
    // // Empty arrays or arrays with 1 element are already sorted.
    // return;
    // }
    // String[] tmp = array.clone();
    //
    // String pivotValue = array[ pivotIndex ];
    //
    // // All elements to the left of lesserIndex are <= pivotValue
    // int lesserIndex = left;
    //
    // // All elements to the right of greaterIndex are > pivotValue
    // int greaterIndex = right;
    //
    // // The following loop will be executed right - left + 1 times.
    // //
    // // In the case where all values are <= pivotValue, after the loop is done
    // // executing the values will be
    // //
    // // lesserIndex: left + right - left + 1 = right + 1
    // //
    // // In the case where all values are > pivotValue, after the loop is done
    // // executing the values will be
    // //
    // // greaterIndex: right - (right - left + 1) = right - right + left -1 =
    // // left - 1
    //
    // for( int i = left; i <= right; i++ ) {
    // if( tmp[ i ].compareTo( pivotValue ) <= 0 ) {
    // array[ lesserIndex ] = tmp[ i ];
    // lesserIndex++;
    // } else {
    // array[ greaterIndex ] = tmp[ i ];
    // greaterIndex--;
    // }
    // }
    // }

    /**
     * Partition a subarray around a given partitionIndex.
     * 
     * The values to the left of the partitionIndex will be < the partitionValue
     * I.e. array[ start ], array[ start + 1 ], ..., array[ partitionIndex - 1]
     * will be < array[ partitionIndex ]
     * 
     * The values to the right of the partitionIndex will be >= the
     * partitionValue I.e. array[ partitionIndex + 1 ], array[ partitionIndex +
     * 2 ], ..., array[ end ] will be < array[ end ]
     * 
     * @param partitionIndex
     *            index around which to partition the array
     * @param array
     *            the array containing values
     * @param start
     *            index of the first value in the subarray
     * @param end
     *            index of the last value in the subarray
     */
    private static final int partition( Object[] array, int start, int end ) {
        int pivotIndex = start + ( end - start ) / 2;
        Object pivotValue = array[ pivotIndex ];
        swap( array, pivotIndex, end );
        // lt: lt - 1 is the index for sequence of elements LESSER THAN the
        // pivotValue
        // lt is the index the first value >= pivotValue
        int lt = start;
        // ge: index for sequence of elements GREATER than the pivotValue
        // Both sequence le and g are initially empty.
        for( int ge = start; ge < end; ge++ ) {
            if( ( (Comparable) array[ ge ] ).compareTo( pivotValue ) < 0 ) {
                swap( array, ge, lt );
                lt++;
            }
        }
        swap( array, end, lt );
        return lt;
    }

    public static final < T > void swap( T[] a, int i, int j ) {
        T t = a[ i ];
        a[ i ] = a[ j ];
        a[ j ] = t;
    }

    // JUnit tests below

    @Test
    public void testPartition() {

        String[][] testData = new String[][] { { "1" }, { "1", "2" }, { "1", "2", "3" }, { "1", "1", "2" }, };
        for( String[] originalArray : testData ) {
            List< String > originalList = Arrays.asList( originalArray );
            for( List< String > testList : Collections2.permutations( originalList ) ) {
                String[] testInput = testList.toArray( new String[] {} );
                String[] testOutput = testInput.clone();
                int pivotIndex = partition( testOutput, 0, testOutput.length - 1 );
                assertTrue( 
                    String.format( "testInput: %s, pivotIndex: %d testOutput: %s",
                        Arrays.toString( testInput ),
                        pivotIndex,
                        Arrays.toString( testOutput ) ),
                    isPartition( testOutput, pivotIndex ) );
            }            
        }
    }

    @Test
    public void testSort() {

        String[][] testData = new String[][] { { "1" }, { "1", "2" }, { "1", "2", "3" }, { "1", "1", "2" }, };
        for( String[] originalArray : testData ) {
            List< String > originalList = Arrays.asList( originalArray );
            for( List< String > testList : Collections2.permutations( originalList ) ) {
                String[] testArray = testList.toArray( new String[] {} );
                quickSort( testArray );
                assertArrayEquals(
                    String.format(
                        "testArray: %s expected: %s actual %s\n",
                        Arrays.toString( testArray ),
                        Arrays.toString( originalArray ),
                        Arrays.toString( testArray ) ),
                    originalArray,
                    testArray
                );
            }            
        }
    }
    
    private boolean isPartition( Object[] array, int pivotIndex ) {
        Object pivotValue = array[ pivotIndex ];
        for( int i = 0; i < array.length; i++ ) {
            if( i < pivotIndex ) {
                if( ( (Comparable) array[ i ] ).compareTo( pivotValue ) > 0 ) {
                    return false;
                }
            } else if( i > pivotIndex ) {
                if( ( (Comparable) array[ i ] ).compareTo( pivotValue ) < 0 ) {
                    return false;
                }
            }
        }
        return true;
    }

}
