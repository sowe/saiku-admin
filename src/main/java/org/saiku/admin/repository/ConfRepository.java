package org.saiku.admin.repository;

import java.util.List;

import javax.inject.Inject;


import org.saiku.admin.model.Conf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ConfRepository {

    @Inject
    private MongoTemplate mongoTemplate;

    static final Logger logger = LoggerFactory.getLogger(ConfRepository.class);

    public Conf findFirst() {

    	List<Conf> list = mongoTemplate.findAll(Conf.class);

    	if(list!=null) {
    		return list.get(0);
    	}

    	return null;
    }    
}
