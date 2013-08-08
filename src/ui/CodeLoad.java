/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import connection.ConnectionInfo;
import connection.SSHManager;
import javax.swing.JFrame;

/**
 *
 * @author Oliver
 */
public class CodeLoad extends JFrame{
    public static void main( String[] args ) throws Exception{
        new CodeLoad().setVisible( true );
    }
    
    public CodeLoad(){
        SSHManager sshm = new SSHManager( LoginDialog.promptForLogin() );
        System.err.println( sshm.connect() );
        setSize( 600, 600 );
        setLocationRelativeTo( null );
    }
}
