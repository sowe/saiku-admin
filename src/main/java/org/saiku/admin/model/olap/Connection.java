package org.saiku.admin.model.olap;

import org.springframework.data.mongodb.core.mapping.Field;



public class Connection {

	private String type;
	private String driver;
	@Field("database_jdbc_driver")
	private String databaseJdbcDriver;
	@Field("database_jdbc")
	private String databaseJdbc;
	@Field("database_username")
	private String databaseUsername;
	@Field("database_password")
	private String databasePassword;
	private String catalog;

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getDatabaseJdbcDriver() {
		return databaseJdbcDriver;
	}
	public void setDatabaseJdbcDriver(String databaseJdbcDriver) {
		this.databaseJdbcDriver = databaseJdbcDriver;
	}
	public String getDatabaseJdbc() {
		return databaseJdbc;
	}
	public void setDatabaseJdbc(String databaseJdbc) {
		this.databaseJdbc = databaseJdbc;
	}
	public String getDatabaseUsername() {
		return databaseUsername;
	}
	public void setDatabaseUsername(String databaseUsername) {
		this.databaseUsername = databaseUsername;
	}
	public String getDatabasePassword() {
		return databasePassword;
	}
	public void setDatabasePassword(String databasePassword) {
		this.databasePassword = databasePassword;
	}
	public String getCatalog() {
		return catalog;
	}
	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}


	@Override
    public String toString() {
        return "Connection{type=" + type + 
        		", driver=" + driver + ", databaseJdbcDriver=" + databaseJdbcDriver+", " +
        		"databaseJdbc=" + databaseJdbc+", databaseUsername=" + databaseUsername+", " +
        		"databasePassword=" + databasePassword+", catalog=" + catalog+"}";
    }
}
