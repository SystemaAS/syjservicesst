package no.systema.jservices.model.dao.mapper;

import org.apache.logging.log4j.*;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.model.dao.entities.DbConnectionTesterDao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  Jun 13, 2016
 * 
 */
public class DbConnectionTesterMapper implements RowMapper {
	private static Logger logger = LogManager.getLogger(DbConnectionTesterMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	DbConnectionTesterDao dao = new DbConnectionTesterDao();
    	dao.setText(rs.getString("text"));
    	dao.setDbserver(rs.getString("dbserver"));
    	//DEBUG-->
    	logger.info(dao.getText());
    	
        return dao;
    }

}


