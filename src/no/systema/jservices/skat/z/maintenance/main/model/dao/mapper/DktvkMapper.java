package no.systema.jservices.skat.z.maintenance.main.model.dao.mapper;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import no.systema.jservices.skat.z.maintenance.main.model.dao.entities.DktvkDao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author oscardelatorre
 * @date  Maj 31, 2016
 * 
 */
public class DktvkMapper implements RowMapper {
	private static Logger logger = Logger.getLogger(DktvkMapper.class.getName());
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	DktvkDao dao = new DktvkDao();
    	
    	dao.setDkvk_kd(rs.getString("dkvk_kd"));
    	dao.setDkvk_dts(rs.getString("dkvk_dts"));
    	dao.setDkvk_dte(rs.getString("dkvk_dte"));
    	dao.setDkvk_omr(rs.getString("dkvk_omr"));
    	dao.setDkvk_krs(rs.getString("dkvk_krs"));
    	
    	
        return dao;
    }

}


