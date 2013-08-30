package org.saiku.admin.utils;

import java.util.List;

import org.saiku.admin.model.Conf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.Scheduled;



public class AsynchronousService {
	private final int schedulerTime = 3000;
	private CheckFiles cheksum = new CheckFiles();
	private AsyncResult<String> result = new AsyncResult<String>("0 Files check");
	private String url = null;
	private String[] cubes = null;
	static final Logger logger = LoggerFactory.getLogger(AsynchronousService.class);
	
    @Async
    public List<String> configFileChek(String path, String md5) {
    
    for (String cubes_ : cubes) {
	
    	if(cheksum.checkFile(path+cubes_, md5) == true ){
    		logger.error("Son iguales"+"["+path+"]");
    	}else{
    		logger.error("Son distintos" +"["+path+"]");
    	}

     }
    return null;
    }
    
    
    @Scheduled (fixedDelay = schedulerTime)
     public void logSendMailsState() {
        System.out.println();
    }
    
    
    public AsynchronousService() {
    	Conf saiku_conf = new Conf();
    	url = saiku_conf.getSaikuCube();
    	cubes = saiku_conf.getCubes();
	}
}
