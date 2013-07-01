/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SSH;

import com.jcraft.jsch.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
//import java.util.Arrays;
import javax.swing.JOptionPane;
//import java.util.Map;
//import java.util.HashMap;
import java.util.List;


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
    //private ChannelSftp sftpConnection;
    private String tempDir = System.getProperty("java.io.tmpdir");
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

    public ArrayList getLS(){
        String lsString = sendCommand("ls -l");
        String pwd = sendCommand("pwd");
        
        //TODO: break the return up and then return possibly a vector
        ArrayList<FileInfo> out = new ArrayList<FileInfo>(); 
        String[] arr = lsString.split("\n");
        //FileInfo[] out = new FileInfo[arr.length-1];
        for(int x=1;x<arr.length;x++){
            out.add(new FileInfo(arr[x],pwd));
        }
        return out;
    }
    
    // I would put this in FileInfo.java, but it would require a lot of redundancies
    public void downloadFile(FileInfo input){
        /*if(input.getType().equals("dir")){
            System.out.println("Is a directory");
            //run the cd command
            sendCommand("cd" + input.getLocation());
            String junk = sendCommand("pwd");
            System.out.println(junk);
            return;
        }*/
        
        String dirTemp = tempDir + "" + input.getName();
        System.out.println("DEBUG:: " + dirTemp);
        try{
        ChannelSftp sftpChannel = (ChannelSftp)sesConnection.openChannel("sftp");
        sftpChannel.connect();
        
        //System.out.println("begin downloading file");
        if(input.getType().equals("dir")){
            sftpChannel.cd(input.getName());
            String junk = sendCommand("pwd");
            System.out.println(junk);
        } else{
            sftpChannel.get(input.getLocation(),dirTemp);
        }
        sftpChannel.exit();
        } catch( JSchException | SftpException ioX){
            System.out.println(ioX.getMessage());
        }
    }
    
    //using a generic command sending method may make things a hell of a lot
    //easier in the futre
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
    
    public void disconnect(){
        sesConnection.disconnect();
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
