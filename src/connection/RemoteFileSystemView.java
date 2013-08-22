/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;
//import connection.*;
import javax.swing.filechooser.FileSystemView;
import com.jcraft.jsch.*;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Sam
 */
public class RemoteFileSystemView extends FileSystemView{
    
    public static final String FILE_SEPERATOR = "/";
    private final Session sesConnection;
    private static ChannelSftp sftpChannel;
    
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
    }
    
    /*public RemoteFile downloadFile( RemoteFile toGet ){
     * 
     * }*/
    
    
    // 
    // Overridden methods from FileSystemView
    // 
    @Override
    public File createNewFolder ( File containingDir ) throws IOException{
        throw new IOException( "Method not completed" );
    }
}
