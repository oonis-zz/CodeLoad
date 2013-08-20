/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Sam
 */
public class RemoteFileSystemView extends FileSystemView{
    
    
    // 
    // Overridden methods from FileSystemView
    // 
    @Override
    public File createNewFolder ( File containingDir ) throws IOException{
        throw new IOException( "Method not completed" );
    }
}
