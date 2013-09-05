/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;
//import connection.*;
import javax.swing.filechooser.FileSystemView;
import com.jcraft.jsch.*;
import java.io.File;
import java.util.Random;
import java.io.IOException;
/**
 *
 * @author Sam
 */
public class RemoteFileSystemView extends FileSystemView{
    
    // NOTE: I still need to figure out what variables i need/don't need
    private static final char[] symbols = new char[36];{ // We want symbols to have every alphanumeric value 0-9:a-z
        for (int idx = 0; idx < 10; ++idx)
            symbols[idx] = (char) ('0' + idx);
        for (int idx = 10; idx < 36; ++idx)
            symbols[idx] = (char) ('a' + idx - 10);
  }
    private char[] buff;
    private Random random = new Random();
    
    public static final String FILE_SEPERATOR = "/";
    private final Session sesConnection;
    private File currLocalFile;
    private static ChannelSftp sftpChannel;
    private String homeDirectory;
    private String sessionName; // What we'll call the root folder in the tempdir
    
    public RemoteFileSystemView(Session sesConnection){
        this.sesConnection = sesConnection;
        Channel channel;
        try{
            channel = sesConnection.openChannel("sftp");
            channel.connect();
            sftpChannel = (ChannelSftp)channel;
        } catch( JSchException ioX){
            System.out.println(ioX.getMessage());
        }
        
        sessionName = generateSessionName();
        File localRoot = new File( System.getProperty("java.io.tmpdir") + sessionName );
        if( !localRoot.mkdir() )
            System.out.println("Error creating temporary ");
        currLocalFile = localRoot;
        
        
    }
    
    private String generateSessionName(){
        buff = new char[10];
        for( int i = 0; i < buff.length;i++)
            buff[i] = symbols[ random.nextInt( symbols.length ) ];
        return new String( buff );
    }
    
    
    // 
    // Overridden methods from FileSystemView
    // 
    @Override
    public File createNewFolder ( File containingDir ) throws IOException{
        throw new IOException( "Method not completed" );
    }
    
    
    
    
    
    
    
    
}
