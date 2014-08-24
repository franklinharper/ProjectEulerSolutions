package com.franklinharper.projectEuler.util;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class IoUtils {

    public static int[][] createIntArray2d( String fileName, String regex ) throws NumberFormatException, IOException {
        Pattern pattern = Pattern.compile( regex );
        File inputFile = new File( fileName );
        ArrayList< int[] > result = new ArrayList< int[] >();
        BufferedReader inputReader = new BufferedReader( new FileReader( inputFile ) );
        String line;
        while( ( line = inputReader.readLine() ) != null ) {
            String[] strings = pattern.split( line );
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

    public static String readFile( String fileName ) throws IOException {
        File inputFile = new File( fileName );
        BufferedReader inputReader = new BufferedReader( new FileReader( inputFile ) );
        StringBuffer stringBuffer = new StringBuffer();
        char[] cbuf = new char[ 8192 ];
        int charsRead;
        while( ( charsRead = inputReader.read( cbuf ) ) != -1 ) {
            stringBuffer.append( cbuf, 0, charsRead );
        }
        inputReader.close();
        return stringBuffer.toString();
    }

    public static String[] readStrings( String fileName, String regex ) throws IOException {
        return readStrings( fileName, regex, 0 );
    }
    
    public static String[] readStrings( String fileName, String regex, int groupNumber) throws IOException {
        String input = readFile( fileName );
        Pattern pattern = Pattern.compile( regex );
        Matcher matcher = pattern.matcher( input );
        List< String > result = new ArrayList< String >();
        while( matcher.find() ) {
            result.add( matcher.group( groupNumber ) );
        }
        return result.toArray( new String[] {} );
    }

    @Test
    public void testReadStrings() throws IOException {
        String[] names = IoUtils.readStrings( "p022_names.txt", "\"(.*?)\"", 1 );
        assertEquals( "MARY", names[ 0 ] );
        assertEquals( "ALONSO", names[ names.length - 1 ] );
    }
}
