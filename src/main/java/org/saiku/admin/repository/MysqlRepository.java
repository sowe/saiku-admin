package org.saiku.admin.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.saiku.admin.model.mysql.ExplainData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class MysqlRepository {

    @Inject
    private DataSource mysqlTemplate;

    static final Logger logger = LoggerFactory.getLogger(ConfRepository.class);

    public List<ExplainData> getExplainData(String query) {

    	List<ExplainData> explainList = new ArrayList<ExplainData>();
		try {
			PreparedStatement ps = mysqlTemplate.getConnection().prepareStatement("EXPLAIN "+query+";");
			ResultSet rs = ps.executeQuery();

	        while(rs.next()) {
	        	explainList.add( new ExplainData(
					        			rs.getString("id"), 
					        			rs.getString("select_type"), 
					        			rs.getString("table"), 
					        			rs.getString("type"), 
					        			rs.getString("possible_keys"), 
					        			rs.getString("key"), 
					        			rs.getString("key_len"), 
					        			rs.getString("ref"), 
					        			rs.getString("rows"), 
					        			rs.getString("Extra")));
	        }

		} catch (SQLException e) {
			logger.error("", e);
		}

    	return explainList;
    }    
}
