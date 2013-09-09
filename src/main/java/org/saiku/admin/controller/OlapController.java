package org.saiku.admin.controller;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.inject.Inject;


import org.saiku.admin.model.Olap;
import org.saiku.admin.model.OlapConf;
import org.saiku.admin.model.olap.RoleMapping;
import org.saiku.admin.repository.ConfRepository;
import org.saiku.admin.repository.OlapConfRepository;
import org.saiku.admin.repository.OlapRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller 
@RequestMapping("/api/olap")
@Service
public class OlapController {

    @Inject
    private OlapConfRepository olapConfRepository;

    @Inject
    private OlapRepository olapRepository;
    
    @Inject
    private ConfRepository confRepository;

    static final Logger logger = LoggerFactory.getLogger(OlapController.class);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json") 
    @ResponseBody
    public OlapConf findAll() {
        return olapConfRepository.findFirst();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json") 
    @ResponseStatus(HttpStatus.CREATED) 
    @ResponseBody
    public Olap create(@RequestBody Olap olap) throws FileNotFoundException, UnsupportedEncodingException {

    	logger.info(olap.toString());

    	olapRepository.create(olap);

    	this.createFileOlap(olap);

    	return null;
    }


    private void createFileOlap(Olap olap) throws FileNotFoundException, UnsupportedEncodingException {

    	PrintWriter writer = new PrintWriter(confRepository.findFirst().getSaikuCube()+olap.getName(), "UTF-8");

    	writer.println("type="+olap.getConnection().getType());
		writer.println("name="+olap.getName());
		writer.println("driver=mondrian.olap4j.MondrianOlap4jDriver");
		writer.println("location=jdbc:mondrian:" +
							"Jdbc="+olap.getConnection().getDatabaseJdbc()+";" +
							"Catalog="+olap.getConnection().getCatalog()+";" +
							"JdbcDrivers="+olap.getConnection().getDatabaseJdbcDriver());

		// # location=jdbc:mondrian:Jdbc=jdbc:mysql://localhost/sampledata;Catalog=../webapps/saiku/steelwheels/steelwheels.mondrian.xml;JdbcDrivers=com.mysql.jdbc.Driver;

		//location=jdbc:mondrian:Jdbc=jdbc:hsqldb:res:foodmart/foodmart;Catalog=res:foodmart/FoodMart.xml;

		writer.println("username="+olap.getConnection().getDatabaseUsername());
		writer.println("password="+olap.getConnection().getDatabasePassword());
		writer.println("# some security configuration for roles, first enable it");
		writer.println("security.enabled="+olap.getSecurity().getEnable());
		writer.println("");
		writer.println("# there are 3 different types of security:");
		writer.println("# \"one2one\" (try and map spring user roles to mondrian roles), ");
		writer.println("# \"lookup\" (define the mapping of spring and mondrian roles manually) ");
		writer.println("# \"passthrough\" (will pass username + password of logged in user to connection, e.g. jdbc user + password)");
		writer.println("");
		writer.println("security.type="+olap.getSecurity().getType());

		// Add mapping if security type is one2one or lookup
		if (olap.getSecurity().getType().equals("one2one") || olap.getSecurity().getType().equals("lookup")) {

			String lineMapping = "";
			RoleMapping[] aMapping =  olap.getSecurity().getMapping();

			for (RoleMapping mapping : aMapping) {

				if (mapping!=null && !mapping.getRoleSpring().isEmpty() && mapping.getRolesSaiku()!=null) {

					for (String roleSaiku : mapping.getRolesSaiku()) {
						lineMapping += mapping.getRoleSpring()+"="+roleSaiku+";";
					}
				}
			}

			if (!lineMapping.isEmpty()) {
				writer.println("security.mapping="+lineMapping);
			}
		}

    	writer.close();
    }
}
