package org.saiku.admin.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "saiku_roles")
public class Role {

    @Id
    private String id;
    private String name;

    public Role() {
    } 

    public Role(String _name) {
        this.name = _name;
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

	@Override
    public String toString() {
        return "Role{" + "id=" + id + ", name=" + name + '}';
    }
}
