/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import connection.ConnectionInfo;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.*;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Oliver
 */
public class Preferences {
    private static Cipher cipher = null;
    
    private static final SecretKey secretKey = new SecretKey(){
        private final byte[] encoded = DatatypeConverter.parseHexBinary( "0D58FBA7A2C7C7310E07C4CE07E5A1F2DCABEC4F54A85820" );
        public String getAlgorithm() { return "DESede"; }
        public String getFormat() { return "RAW"; }
        public byte[] getEncoded() { return encoded; }
    };
    
    private static final String savePath = "prefs";
    
    public static void main( String[] args ) throws Exception{
        KeyGenerator kg = KeyGenerator.getInstance( "DESede" );
        kg.init( 168 );
        SecretKey sk = kg.generateKey();
        System.out.println( "encoded: " + DatatypeConverter.printHexBinary( sk.getEncoded() ) );
        System.out.println( "algorithm: " + sk.getAlgorithm() );
        System.out.println( "format: " + sk.getFormat() );
    }
    
    public static ConnectionInfo getLastConnectionInfo() throws Exception{
        String prefsContent = decryptFile( savePath );
        String[] parts = prefsContent.split( "\t" );
        return new ConnectionInfo( parts[0], parts[1], parts[2] );
    }
    
    public static void saveConnectionInfo( ConnectionInfo info ) throws Exception{
        String prefContent = info.userName + "\t" + info.password + "\t" + info.connectionIP;
        encryptToFile( prefContent, savePath );
    }
    
    private static void encryptToFile( String content, String path ) throws Exception{
        Path p = Files.write( Paths.get( path ), encrypt( content ) );
        Files.setAttribute( p, "dos:hidden", true );
    }
    
    private static String decryptFile( String path ) throws Exception{
        File f = new File( path ); // Why is this here?
        byte[] data = Files.readAllBytes( Paths.get( path ) );
        return decrypt( data );
    }
    
    private static Cipher getCipher() throws Exception{
        if( cipher == null )
            cipher = Cipher.getInstance( "DESede" );
        return cipher;
    }
    
    private static byte[] encrypt( String s ) throws Exception{
        while( s.length()%8 != 0 )
            s += "\t";
        Cipher c = getCipher();
        c.init( Cipher.ENCRYPT_MODE, secretKey );
        byte[] inBytes = s.getBytes( "UTF8" );
        byte[] resultBytes = c.doFinal( inBytes );
        return resultBytes;
        //return new String( resultBytes, "UTF8" );
    }
    
    private static String decrypt( byte[] inBytes ) throws Exception{
        Cipher c = getCipher();
        c.init( Cipher.DECRYPT_MODE, secretKey );
        //byte[] inBytes = s.getBytes( "UTF8" );
        byte[] outBytes = c.doFinal( inBytes );
        String result = new String( outBytes, "UTF8" );
        while( result.endsWith( "\t" ) )
            result = result.substring( 0, result.length()-1 );
        return result;
    }
}
