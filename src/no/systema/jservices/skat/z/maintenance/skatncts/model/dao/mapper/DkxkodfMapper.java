package no.systema.jservices.skat.z.maintenance.skatncts.model.dao.mapper;

import org.slf4j.*;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.skat.z.maintenance.skatncts.model.dao.entities.DkxkodfDao;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author oscardelatorre
 * @date  Apr 10, 2016
 * 
 */
public class DkxkodfMapper implements RowMapper {
	private static Logger logger = LoggerFactory.getLogger(DkxkodfMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	
    	DkxkodfDao dao = new DkxkodfDao();
    	try{
	    	Class cl = Class.forName(dao.getClass().getCanonicalName());
			Field[] fields = cl.getDeclaredFields();
			List<Field> list = Arrays.asList(fields);
			for(Field field : list){
				String name = (String)field.getName();
				if(name!=null && !"".equals(name)){
					//DEBUG --> logger.info(field.getName() + " Name:" + name + " value:" + rs.getString(name));
				}
				try{
					//here we put the value
					field.setAccessible(true);
					field.set(dao, rs.getString(name));
				}catch(Exception e){
					//Usually when no column matches the JavaBean property...
					//logger.info(e.getMessage() + e.toString());
					continue;
				}
			}
    	}catch(Exception e){
    		e.toString();
    		logger.info(e.getMessage() + e.toString());
    	}
        
    	
        return dao;
    }

}


