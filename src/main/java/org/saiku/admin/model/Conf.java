package org.saiku.admin.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "saiku_conf")
public class Conf {

    @Id
    private String id;
    @Field("saiku_datasources")
    private String saikuDatasources;
    @Field("saiku_log_sql")
    private String saikuLogSql;
    @Field("saiku_cube")
    private String saikuCube;
    @Field("saiku_users")
    private String saikuUsers;


    public Conf() {
    }


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getSaikuDatasources() {
		return saikuDatasources;
	}


	public void setSaikuDatasources(String saikuDatasources) {
		this.saikuDatasources = saikuDatasources;
	}


	public String getSaikuLogSql() {
		return saikuLogSql;
	}


	public void setSaikuLogSql(String saikuLogSql) {
		this.saikuLogSql = saikuLogSql;
	}


	public String getSaikuCube() {
		return saikuCube;
	}


	public void setSaikuCube(String saikuCube) {
		this.saikuCube = saikuCube;
	}


	public String getSaikuUsers() {
		return saikuUsers;
	}


	public void setSaikuUsers(String saikuUsers) {
		this.saikuUsers = saikuUsers;
	}
    
	@Override
    public String toString() {
        return "Config{" + "id=" + id + ", saiku_datasources=" + saikuDatasources + 
        		", saiku_log_sql=" + saikuLogSql + ", saiku_cube=" + saikuCube +
        		", saiku_users=" + saikuUsers +'}';
    }
    
}
