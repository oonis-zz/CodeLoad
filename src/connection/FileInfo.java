/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.io.File;
import java.util.ArrayList;
/**
 *
 * @author Sam
 */
public class FileInfo extends File{

    private boolean canRead;
    private boolean canWrite;
    private int size;
    private String name;
    private ArrayList<FileInfo> elements;
    private ArrayList<FileInfo> rootElements; // For now do this. fix it later
    private String type = "";
    private String location;
    
    // TODO: make it so this is the only thing needed maybe?
    public FileInfo(String fileString){
        super(fileString);

        String[] arr = fileString.split("\\s+");

        analyzePermissions(arr[0]);
        size = Integer.parseInt(arr[4]);
        
        name = arr[8];  //Question: should this include the MIME type?
        String[] splitName=arr[8].split("\\.");

        if(splitName.length > 0 && !(type.equals("dir")))
            type = splitName[splitName.length-1];
        
        //setPath(name);
        
    }
    public FileInfo( String dirName,ArrayList<FileInfo> files ){
        super(dirName);
        name = dirName;
        if(!addElements(files))
            System.out.println("Why are you calling on me!");
    }
    public void setPath(String pwd){
        location = pwd.replaceAll("(\\r|\\n)", "") + "/" + name;
    }
    
    /**
     * Interpretes the permissions section of the ls return statement
     * @param arr The linux permissions string to be interpreted
     */
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
    
    // TODO
    public boolean addElements(ArrayList<FileInfo> toAdd){
        if(!type.equals("dir"))
            return false;
        
        elements = toAdd;
        return true;
    }
    
    @Override
    public File[] listFiles(){
        if(!type.equals("dir"))
            return null;

        return elements.toArray(new FileInfo[elements.size()]);
    }
    
    /*@Override
    public static FileInfo[] listRoots(){
        
    }*/
    
    // TODO. Just return false for now
    @Override
    public boolean isHidden(){
        return false;
    }
    
    @Override
    public boolean canRead(){
        return canRead;
    }
    
    @Override
    public boolean isDirectory(){
        return type.equals("dir");
    }
    
    @Override
    public boolean isFile(){
        return !(type.equals("dir"));
    }
    
    // If (FileInfo).getWrite()==1, then we can write to the file
    @Override
    public boolean canWrite(){
        return canWrite;
    }
    
    @Override
    public boolean canExecute(){ // For now, just assume you can
        return true;
    }
    
    public int getSize(){
        return size;
    }
    
    @Override
    public String getName(){
        return name;
    }
    
    public String getType(){
        return type;
    }
    
    public String getLocation(){
        return location;
    }
}
