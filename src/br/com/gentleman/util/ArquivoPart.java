package br.com.gentleman.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.http.Part;

public class ArquivoPart {
	
	public String getFileName(Part part){
	    String header = part.getHeader( "content-disposition" );
	    for( String tmp : header.split(";") ){
	        if( tmp.trim().startsWith("filename") ){
	            return tmp.substring( tmp.indexOf("=")+2 , tmp.length()-1 );
	        }
	    }
	    return null;
	}
	
	public boolean verificaIMG(String path) throws IOException{
        File f = new File(path);
        //String mimetype= new MimetypesFileTypeMap().getContentType(f);
        String mit = Files.probeContentType(f.toPath());
        String type = mit.split("/")[0];
        if(type.equals("image"))
            return true;
        else 
            return false;
	}
	
	public boolean verificaExists(String path){
		File file = new File(path);
		if(file.exists()){
			return true;
		}
		return false;
	}
}
