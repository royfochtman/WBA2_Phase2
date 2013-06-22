package main.java.com.photobay.util;

import java.io.File;
import java.io.IOException;

public class DeleteFolder {

	
	public static Boolean delete(File file)
	    	throws IOException{
	 
	    	if(file.isDirectory()){
	 
	    		//directory is empty, then delete it
	    		if(file.list().length==0){
	 
	    		   file.delete();
	    		   return true;
	 
	    		}else{
	 
	    		   //list all the directory contents
	        	   String files[] = file.list();
	 
	        	   for (String temp : files) {
	        	      //construct the file structure
	        	      File fileDelete = new File(file, temp);
	 
	        	      //recursive delete
	        	     delete(fileDelete);
	        	   }
	 
	        	   //check the directory again, if empty then delete it
	        	   if(file.list().length==0){
	           	     file.delete();
	        	     return true;
	        	   }
	    		}
	 
	    	}else{
	    		//if file, then delete it
	    		file.delete();
	    		return true;
	    	}
	    	return false;
	    }
}
