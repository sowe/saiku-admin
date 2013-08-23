package org.saiku.admin.repository;

import javax.inject.Inject;


import org.saiku.admin.model.Olap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OlapRepository {

    @Inject
    private MongoTemplate mongoTemplate;

    static final Logger logger = LoggerFactory.getLogger(OlapRepository.class);

    public Olap create(Olap olap) {
         mongoTemplate.insert(olap);
         return olap;
    }

//    public User update(User user) {
//         mongoTemplate.save(user);
//         return user;
//    }
//    
//    public User findById(String id) {
//        return mongoTemplate.findById(id, User.class);
//    }
//
//    public List<User> findAll() {
//        return mongoTemplate.findAll(User.class);
//    }
//    
//    public void remove(String id) {
//         mongoTemplate.remove(new Query(Criteria.where("id").is(id)), User.class);
//    }
//    
//    public void removeAll() {
//        
//        if (mongoTemplate.collectionExists(User.class)) {
//            mongoTemplate.dropCollection(User.class);
//            mongoTemplate.createCollection(User.class);
//        }
//         
//    }
    
}
