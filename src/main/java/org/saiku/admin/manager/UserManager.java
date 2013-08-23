package org.saiku.admin.manager;

import java.util.List;

import org.saiku.admin.model.User;



public interface UserManager {
	List<User> findAll();
	User create(User user);
}
