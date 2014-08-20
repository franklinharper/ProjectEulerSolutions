package com.franklinharper.projectEuler;

public class ArrayUtils {

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
}
