package com.franklinharper.projectEuler.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class IoUtils {

//    public static int[][] createArray( String fileName ) throws IOException {
//        File inputFile = new File( fileName );
//        BufferedReader inputReader = new BufferedReader( new FileReader( inputFile ) );
//        int rowCount = 0;
//        int maxColCount = 0;
//        String line;
//        while( ( line = inputReader.readLine() ) != null ) {
//            String[] strings = line.split( " " );
//            if( strings.length > maxColCount ) {
//                maxColCount = strings.length;
//            }
//            rowCount++;
//        }
//        inputReader.close();
//        return createArray( inputFile, rowCount, maxColCount );
//    }

    public static int[][] createArray( String fileName ) throws NumberFormatException, IOException {
        // int[][] data = new int[ numRows ][ numCols ];
        File inputFile = new File( fileName );
        ArrayList< int[] > result = new ArrayList< int[] >();
        BufferedReader inputReader = new BufferedReader( new FileReader( inputFile ) );
        String line;
        while( ( line = inputReader.readLine() ) != null ) {
            String[] strings = line.split( " " );
            int col = 0;
            int[] row = new int[ strings.length ];
            for( String string : strings ) {
                row[ col ] = Integer.parseInt( string );
                col++;
            }
            result.add( row );
        }
        inputReader.close();
        int[][] array = new int[ 0 ][ 0 ];
        return result.toArray( array );
    }
}
