package org.saiku.admin.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
 
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckFiles {

	static final Logger logger = LoggerFactory.getLogger(CheckFiles.class);
	
	@SuppressWarnings("finally")
	public boolean checkFile(String File,String md5File) {
		 FileInputStream fis = null;
		 boolean status = false;
		  try {
		     
		      fis = new FileInputStream(File);
		      String md5String = DigestUtils.md5Hex(fis);
		      if(md5String.equalsIgnoreCase(md5File)){
		    	  status = true;
		      }else{
		    	  logger.error("The File ["+File+"] has been modified");
		    	  status = false;
		      }
		    } catch (FileNotFoundException e) {
		      e.printStackTrace();
		      logger.error("The File["+File+"] don't exists");
		    } catch (IOException e) {
		      logger.error(e.toString());
		      e.printStackTrace();
		    }finally{
		    	return status;
		    }
	}
}
