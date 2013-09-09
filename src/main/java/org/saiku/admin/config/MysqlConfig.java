package org.saiku.admin.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration 
@EnableWebMvc 
@ComponentScan(basePackages = "org.saiku.admin")
@PropertySource({ "classpath:/application.properties" })
public class MysqlConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private Environment env;

    @Bean
    public DataSource mysqlTemplate()  {

    	DriverManagerDataSource bean = new DriverManagerDataSource();

        bean.setDriverClassName("com.mysql.jdbc.Driver");
        bean.setUrl("jdbc:mysql://"+this.mysqlData().getHost()+":"+this.mysqlData().getPort()+"/"+this.mysqlData().getDatabase());
        bean.setUsername(this.mysqlData().getUser());
        bean.setPassword(this.mysqlData().getPassword());

        return bean;
    }


    @Bean
    public MysqlConnectionData mysqlData() {

    	MysqlConnectionData data = new MysqlConnectionData(	
    			env.getProperty("mysql.host"),
				env.getProperty("mysql.port"),
				env.getProperty("mysql.database"),
				env.getProperty("mysql.user"),
				env.getProperty("mysql.password"));
    	
		return data;
	}


    public class MysqlConnectionData {
    	private String host;
    	private String port;
    	private String database;
    	private String user;
    	private String password;
    	
    	public MysqlConnectionData(String host, String port, String database, String user, String password) {
    		this.host = host;
    		this.port = port;
    		this.database = database;
    		this.user = user;
    		this.password = password;
    	}

		public String getHost() {
			return host;
		}

		public void setHost(String host) {
			this.host = host;
		}

		public String getPort() {
			return port;
		}

		public void setPort(String port) {
			this.port = port;
		}

		public String getDatabase() {
			return database;
		}

		public void setDatabase(String database) {
			this.database = database;
		}

		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
    	
    	
    }
}
