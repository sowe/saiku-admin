package org.saiku.admin.repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


import org.saiku.admin.model.OlapConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OlapConfRepository {

    @Inject
    private MongoTemplate mongoTemplate;

    static final Logger logger = LoggerFactory.getLogger(OlapConfRepository.class);

    public OlapConf findFirst() {

    	OlapConf conf = new OlapConf();
    	List<String> x = new ArrayList<String>();
    	x.add("uno");

    	conf.setConnectionType(x);

    	mongoTemplate.insert(conf);
    	
    	
    	List<OlapConf> list = mongoTemplate.findAll(OlapConf.class);

    	if(list!=null) {
    		return list.get(0);
    	}

    	return null;
    }    
}
