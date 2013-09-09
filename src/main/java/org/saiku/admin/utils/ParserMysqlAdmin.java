package org.saiku.admin.utils;

import javax.inject.Inject;

import org.saiku.admin.config.MysqlConfig.MysqlConnectionData;
import org.saiku.admin.model.mysql.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ParserMysqlAdmin {

    @Inject
    private MysqlConnectionData mysqlData;


	static final Logger logger = LoggerFactory.getLogger(ParserMysqlAdmin.class);

	public Status status() {

		try {
			String status = JavaRunCommand.execute("mysqladmin -h "+mysqlData.getHost()+" --port "+mysqlData.getPort()+" " +
					"-u "+mysqlData.getUser()+" status");

			String system[] = status.split(" ");
			return new Status(
					 "MySql",
					 mysqlData.getHost(),
					 new Integer(system[1]), 
					 new Integer(system[4]), 
					 new Integer(system[7]), 
					 new Integer(system[11]), 
					 new Integer(system[14]), 
					 new Integer(system[18]), 
					 new Integer(system[22]), 
					 new Double(system[28]));

		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}
}
