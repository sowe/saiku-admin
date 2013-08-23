package org.saiku.admin.config;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.mongodb.Mongo;

@Configuration 
@EnableWebMvc 
@ComponentScan(basePackages = "org.saiku.admin")
@PropertySource({ "classpath:/application.properties" })
public class MongoConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private Environment env;


	@Bean
    public Mongo mongo() throws UnknownHostException {
        return new Mongo(env.getProperty("mongo.host"), 
        				 new Integer(env.getProperty("mongo.port")).intValue());
    }

    @Bean
    public MongoTemplate mongoTemplate() throws UnknownHostException {
        return new MongoTemplate(mongo(), 
        						 env.getProperty("mongo.database"), 
        						 new UserCredentials(env.getProperty("mongo.user"), env.getProperty("mongo.password")));
    }
}
