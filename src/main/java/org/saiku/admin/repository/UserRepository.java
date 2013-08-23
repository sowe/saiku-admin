package org.saiku.admin.repository;

import java.util.List;

import javax.inject.Inject;


import org.saiku.admin.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Inject
    private MongoTemplate mongoTemplate;

    static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    public User create(User user) {
         mongoTemplate.insert(user);
         return user;
    }

    public User update(User user) {
         mongoTemplate.save(user);
         return user;
    }
    
    public User findById(String id) {
        return mongoTemplate.findById(id, User.class);
    }

    public List<User> findAll() {
        return mongoTemplate.findAll(User.class);
    }
    
    public void remove(String id) {
         mongoTemplate.remove(new Query(Criteria.where("id").is(id)), User.class);
    }
    
    public void removeAll() {
        
        if (mongoTemplate.collectionExists(User.class)) {
            mongoTemplate.dropCollection(User.class);
            mongoTemplate.createCollection(User.class);
        }
         
    }
    
}
