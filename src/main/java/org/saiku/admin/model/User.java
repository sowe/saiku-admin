package org.saiku.admin.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "saiku_users")
public class User {

    @Id
    private String id;
    private String name;
    private String surName;
    private String email;
    private String user;
    private String userName;
    private String password;
    private Date createDate;
    private List<String> roles;

    public User() {
    }

    public User(String _id, String _name, String _surName, String _email, String _user, String _userName, String _password) {
        this.id 		= _id;
        this.name 		= _name;
        this.surName 	= _surName;
        this.email 		= _email;
        this.user 		= _user;
        this.userName 	= _userName;
        this.password 	= _password;

        roles = new ArrayList<String>();
        roles.add("23423dsf1243");
        roles.add("23423dsf12ghfkjyt43");
        roles.add("23423dsf124bntr4hu3");
    }


    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	public String rolesToString() {
		String retval = "";
		if(roles!=null && roles.size()>0) {
			for (Iterator<String> iterator = roles.iterator(); iterator.hasNext();) {
				retval += iterator.next()+",";
			}
			retval = retval.substring(0, retval.length()-1);
		}
		return retval;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", surName=" + surName + ", email=" + email + ", user=" + user + ", userName=" + userName + "}";
    }

}
