/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeTest;
import connection.FileInfo;
import connection.SSHManager;
import java.util.ArrayList;
//import java.awt.*;
//import javax.swing.*;
/**
 *
 * @author Sam
 */
public class JSCHTest {
  public static void main(String[] arg){
      String userName="macalus6";
      String password="";
      String connectionIP = "adriatic.cse.msu.edu";
      SSHManager instance = new SSHManager(userName, password, connectionIP, "");
      String errorMsg = instance.connect();
      if(errorMsg!=null){
          System.out.println(errorMsg);
      }else{
          System.out.println("Connected!");
      }


      instance.disconnect();
  }
  
  public static void printDirectory(ArrayList<FileInfo> input){
      System.out.println("Directory size:: " + input.size());
      for(FileInfo temp : input){
          System.out.println("Name:: " + temp.getName());
          System.out.println("Location:: "+temp.getLocation());
          System.out.println("MIME type:: " + temp.getType());
          System.out.println("Size:: " + temp.getSize());
          
          if(temp.canRead())
              System.out.println("Can read");
          else
              System.out.println("Can't read");
          if(temp.canWrite())
              System.out.println("Can write");
          else
              System.out.println("Can't write");

      }
  }
  
}