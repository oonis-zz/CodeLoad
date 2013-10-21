/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;
import java.io.File;
/**
 *
 * @author Sam
 */
public class RemoteFile extends File {
    private String remotePath;
    public RemoteFile( String path ){
        super( path );
    }
    
    public void setRemotePath( String path ){
        remotePath = path;
    }
    
    public String getRemotePath( ){
        return remotePath;
    }
    
}
