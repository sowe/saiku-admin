package org.saiku.admin.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "saiku_olap_conf")
public class OlapConf {

    @Id
    private String id;
    @Field("security_type")
    private List<String> securityType;
    @Field("connection_type")
    private List<String> connectionType;


    public OlapConf() {
    }


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

    
	public List<String> getSecurityType() {
		return securityType;
	}


	public void setSecurityType(List<String> securityType) {
		this.securityType = securityType;
	}


	public List<String> getConnectionType() {
		return connectionType;
	}


	public void setConnectionType(List<String> connectionType) {
		this.connectionType = connectionType;
	}


	@Override
    public String toString() {
        return "OlapConfig{" + "id=" + id + ", security_type=" + securityType.toString() + 
        		", connection_type=" + connectionType.toString() + "}";
    }

}
