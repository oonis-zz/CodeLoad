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
      String userName="macalus6";
      String password="buquwr67";
      String connectionIP = "adriatic.cse.msu.edu";
      SSHManager instance = new SSHManager(userName, password, connectionIP, "");
      String errorMsg = instance.connect();
      if(errorMsg!=null){
          System.out.println(errorMsg);
      }else{
          System.out.println("Connected!");
      }
      
      
      ArrayList<FileInfo> firstLevel = instance.getLS();
      System.out.println("Printing directory:: " + firstLevel.get(3).getName());
      ArrayList<FileInfo> secondLevel = instance.changeDirectory(firstLevel.get(3));
      //System.out.println(instance.sendCommand("pwd"));
      //instance.downloadFile(null);
      //System.out.println(instance.sendCommand("pwd"));
      
      
      /*for(FileInfo temp : firstLevel){
          if((temp.getType().equals("dir"))){
              secondLevel = instance.changeDirectory(temp);
              System.out.println("Printing directory:: " + temp.getName());
              System.out.println("---------------------------------");
              printDirectory(secondLevel);
          }
      }*/
      //printDirectory(secondLevel);
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