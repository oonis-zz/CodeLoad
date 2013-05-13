/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SSH;

import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Sam
 */
public class FileInfo {
    //maybe these should be public
    private boolean canRead;
    private boolean canWrite;
    private int size;
    private String name;
    private String type = "";
    private Map locations;
    
    //need to do constructors and methods
    
    
    
    public FileInfo(String fileString){
        //TODO. analyze the string and set all of the private variables
        String[] arr = fileString.split("\\s+");
        //System.out.println(fileString);
        //for(String s : arr){
        //    System.out.println(s);
        //}
        analyzePermissions(arr[0]);
        size = Integer.parseInt(arr[4]);
        
        name = arr[8];  //Question: should this include the MIME type?
        System.out.println("DEBUG " + arr[8]);
        String[] splitName=arr[8].split("\\.");
        //System.out.println("DEBUG"+splitName.length);
        if(splitName.length > 0 && !(type.equals("dir")))
            type = splitName[splitName.length-1];
        
    }
    
    private void analyzePermissions(String arr){
        
        if(arr.charAt(0)=='d')
            type = "dir";
        
        //I'm pretty sure we only care about User permissions
        if(arr.charAt(1)=='r')
            canRead = true;
        else
            canRead = false;
        
        if(arr.charAt(2)=='w')
            canWrite = true;
        else
            canWrite = false;
    }
    
    //if (FileInfo).getRead()==1, then we can read the file
    public boolean getRead(){
        return canRead;
    }
    
    //if (FileInfo).getWrite()==1, then we can write to the file
    public boolean getWrite(){
        return canWrite;
    }
    
    public int getSize(){
        return size;
    }
    
    
    public String getName(){
        return name;
    }
    
    public String getType(){
        return type;
    }
}
