/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeTest;
import connection.FileInfo;
import connection.SSHManager;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import java.io.File;
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
      File currentDirectory = instance.getRoot();
      File test = new File("C:\\Users\\Sam\\Desktop\\Phi2.txt");
      //final JFileChooser fc = new JFileChooser(currentDirectory);
      //fc.showOpenDialog(null);

      instance.disconnect();
      /*String tempDir = System.getProperty("java.io.tmpdir")+"herro";
      File f = new File(tempDir);
      boolean mkdir = f.mkdir();*/
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