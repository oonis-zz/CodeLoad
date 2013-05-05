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
    private String type;
    private Map locations;
    
    //need to do constructors and methods
    
    
    
    public FileInfo(String fileString){
        //TODO. analyze the string and set all of the private variables
        String[] arr = fileString.split("\\s+");
        //System.out.println(fileString);
        for(String s : arr){
            System.out.println(s);
        }
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
    /*public boolean getRead(){
        return canRead;
    }
    public boolean getWrite(){
        return canWrite;
    }*/
}
