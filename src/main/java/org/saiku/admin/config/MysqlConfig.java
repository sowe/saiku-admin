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
        bean.setUrl("jdbc:mysql://"+env.getProperty("mysql.host")+":"+env.getProperty("mysql.port")+"/"+env.getProperty("mysql.database"));
        bean.setUsername(env.getProperty("mysql.user"));
        bean.setPassword(env.getProperty("mysql.password"));

        return bean;
    }
}
