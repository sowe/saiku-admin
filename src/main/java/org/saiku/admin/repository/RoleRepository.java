package org.saiku.admin.repository;

import java.util.List;

import javax.inject.Inject;


import org.saiku.admin.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepository {

    @Inject
    private MongoTemplate mongoTemplate;

    static final Logger logger = LoggerFactory.getLogger(RoleRepository.class);

    public Role create(Role role) {
         mongoTemplate.insert(role);
         return role;
    }

    public Role update(Role role) {
         mongoTemplate.save(role);
         return role;
    }

    public Role findById(String id) {
        return mongoTemplate.findById(id, Role.class);
    }

    public List<Role> findAll() {
        return mongoTemplate.findAll(Role.class);
    }

    public void remove(String id) {
         mongoTemplate.remove(new Query(Criteria.where("id").is(id)), Role.class);
    }

    public void removeAll() {

        if (mongoTemplate.collectionExists(Role.class)) {
            mongoTemplate.dropCollection(Role.class);
            mongoTemplate.createCollection(Role.class);
        }

    }

}
