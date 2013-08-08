/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import connection.ConnectionInfo;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Oliver
 */
public class Preferences {
    private static final String savePath = "prefs.ini";
    
    public static ConnectionInfo getLastConnectionInfo() throws Exception{
        List< String > prefsContent = getFileContent( savePath );
        String username = "", password = "", ip = "";
        for( String s : prefsContent ){
            if( s.startsWith( "u:" ) )
                username = s.substring( 2 );
            else if( s.startsWith( "p:" ) )
                password = s.substring( 2 );
            else if( s.startsWith( "i:" ) )
                ip = s.substring( 2 );
        }
        return new ConnectionInfo( username, password, ip );
    }
    
    public static void saveConnectionInfo( ConnectionInfo info ) throws Exception{
        String prefsContent = "u:" + info.userName + "\np:" + info.password + "\ni:" + info.connectionIP;
        File f = new File( savePath );
        FileOutputStream fos = new FileOutputStream( f );
        OutputStreamWriter osw = new OutputStreamWriter( fos );
        BufferedWriter bw = new BufferedWriter( osw );
        bw.write( prefsContent );
        bw.close();
    }
    
    private static List<String> getFileContent( String path ) throws FileNotFoundException, IOException{
        File f = new File( path );
        FileInputStream fis = new FileInputStream( f );
        InputStreamReader isr = new InputStreamReader( fis );
        BufferedReader br = new BufferedReader( isr );
        List< String > resultList = new ArrayList();
        while( br.ready() )
            resultList.add( br.readLine() );
        return resultList;
    }
}
