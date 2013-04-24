/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SSH;

import com.jcraft.jsch.*;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JOptionPane;


/**
 *
 * @author Sam
 */
public class SSHManager{
    private JSch jschSSHChannel;
    private String strUserName;
    private String strConnectionIP;
    private String strPassword;
    private Session sesConnection;
    private int intTimeOut;

    private void constructorActions(String userName, String password, String host, String knownHostsFileName){
        jschSSHChannel = new JSch();

        try{
            jschSSHChannel.setKnownHosts(knownHostsFileName);
        }
        catch(JSchException jschX){
            System.out.println(jschX.getMessage());
        }

        strUserName = userName;
        strPassword = password;
        strConnectionIP = host;
  }

    public SSHManager(String userName, String password, String host, String knownHostsFileName){
        constructorActions(userName, password, host, knownHostsFileName);
        intTimeOut = 30000;
    }

    public String connect(){
        String errorMessage = null;

        try{
            sesConnection = jschSSHChannel.getSession(strUserName, strConnectionIP);
            sesConnection.setPassword(strPassword);
            UserInfo ui = new MyUserInfo(){
                public void showMessage(String message){
                    JOptionPane.showMessageDialog(null,message);
                }
                public boolean promptYesNo(String message){
                    Object[] options={"yes","no"};
                    int foo=JOptionPane.showOptionDialog(null,
                                                         message,
                                                         "warning",
                                                         JOptionPane.DEFAULT_OPTION,
                                                         JOptionPane.WARNING_MESSAGE,
                                                         null,options,options[0]);
                    return foo==0;
                }
            };
            
            sesConnection.setUserInfo(ui);
            sesConnection.connect(intTimeOut);
        }
        catch(JSchException jschX){
            errorMessage=jschX.getMessage();
        }

        return errorMessage;    //return a string for debugging
    }

    public String getLS(){
        String lsString = sendCommand("ls -l");
        
        //TODO: break the return up and then return possibly a vector
        System.out.println(lsString);
        String[] arr = lsString.split("\n");
        for(String s : arr){
            System.out.println(s);
        }
        return lsString;
    }
    private String sendCommand(String command){
        StringBuilder outputBuffer = new StringBuilder();

        try{
            Channel channel = sesConnection.openChannel("exec");
            ((ChannelExec)channel).setCommand(command);
            channel.connect();
            InputStream commandOutput = channel.getInputStream();
            int readByte = commandOutput.read();

            while(readByte != 0xffffffff){
                outputBuffer.append((char)readByte);
                readByte = commandOutput.read();
            }

        channel.disconnect();
        }
     catch(IOException | JSchException ioX){
        System.out.println(ioX.getMessage());
        return null;
     }

     return outputBuffer.toString();
  }

public static abstract class MyUserInfo
                          implements UserInfo, UIKeyboardInteractive{
    public String getPassword(){ return null; }
    public boolean promptYesNo(String str){ return false; }
    public String getPassphrase(){ return null; }
    public boolean promptPassphrase(String message){ return false; }
    public boolean promptPassword(String message){ return false; }
    public void showMessage(String message){ }
    public String[] promptKeyboardInteractive(String destination,
                                              String name,
                                              String instruction,
                                              String[] prompt,
                                              boolean[] echo){
      return null;
    }
    }

  }
