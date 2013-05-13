/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeTest;
import SSH.*;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author Sam
 */
public class JSCHTest {
  public static void main(String[] arg){
      String userName="";
      String password="";
      String connectionIP = "adriatic.cse.msu.edu";
      SSHManager instance = new SSHManager(userName, password, connectionIP, "");
      String errorMsg = instance.connect();
      if(errorMsg!=null){
          System.out.println(errorMsg);
      }else{
          System.out.println("Connected!");
      }
      ArrayList<FileInfo> testArray = instance.getLS();
      
      for(FileInfo temp : testArray){
          System.out.println("Name:: " + temp.getName());
          System.out.println("MIME type:: " + temp.getType());
          System.out.println("Size:: " + temp.getSize());
          if(temp.getRead())
              System.out.println("Can read");
          else
              System.out.println("Can't read");
          if(temp.getWrite())
              System.out.println("Can write");
          else
              System.out.println("Can't write");
          System.out.println();
      }
      
      instance.disconnect();
      //System.out.println(lsCommand);
  }
}