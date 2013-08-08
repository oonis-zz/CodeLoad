/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

/**
 *
 * @author Oliver
 */
public class ConnectionInfo {
    public final String userName, password, connectionIP, knownHostsFileName;
    
    public ConnectionInfo( 
            String userName, 
            String password, 
            String connectionIP ){
        
        this( userName, password, connectionIP, "" );
    }
    
    public ConnectionInfo(
            String userName,
            String password,
            String connectionIP,
            String knownHostsFileName ){
        
        this.userName = userName;
        this.password = password;
        this.connectionIP = connectionIP;
        this.knownHostsFileName = knownHostsFileName;
    }
}
