package org.saiku.admin.model;


import org.saiku.admin.model.olap.Connection;
import org.saiku.admin.model.olap.Security;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "saiku_olap")
public class Olap {

    @Id
    private String id;
    private String name;
    private Security security;
    private Connection connection;


    public Olap() {
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


	public Security getSecurity() {
		return security;
	}


	public void setSecurity(Security security) {
		this.security = security;
	}


	public Connection getConnection() {
		return connection;
	}


	public void setConnection(Connection connection) {
		this.connection = connection;
	}


//	public String getSaikuDatasources() {
//		return saikuDatasources;
//	}
//
//
//	public void setSaikuDatasources(String saikuDatasources) {
//		this.saikuDatasources = saikuDatasources;
//	}
//
//
//	public String getSaikuLogSql() {
//		return saikuLogSql;
//	}


//	public void setSaikuLogSql(String saikuLogSql) {
//		this.saikuLogSql = saikuLogSql;
//	}
//
//
//	public String getSaikuCube() {
//		return saikuCube;
//	}
//
//
//	public void setSaikuCube(String saikuCube) {
//		this.saikuCube = saikuCube;
//	}
//
//
//	public String getSaikuUsers() {
//		return saikuUsers;
//	}
//
//
//	public void setSaikuUsers(String saikuUsers) {
//		this.saikuUsers = saikuUsers;
//	}
//    
	@Override
    public String toString() {
        return "Olap{" + "id=" + id + ", name=" + name + 
        		", security=" + security.toString() + ", connection=" + connection.toString()+"}";
    }
    
}
