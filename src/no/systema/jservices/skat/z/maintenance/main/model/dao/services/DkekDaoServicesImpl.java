package no.systema.jservices.skat.z.maintenance.main.model.dao.services;
import java.io.Writer;
import java.util.*;

import org.slf4j.*;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.skat.z.maintenance.main.model.dao.entities.DkekDao;
import no.systema.jservices.skat.z.maintenance.main.model.dao.mapper.DkekMapper;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Jan, 2021
 * 
 */
public class DkekDaoServicesImpl implements DkekDaoServices {
	private static Logger logger = LoggerFactory.getLogger(DkekDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	
	public List getList(StringBuffer errorStackTrace){
		//N/A
		return null;
	}
	
	public List getList(String kundnr, StringBuffer errorStackTrace){
		List<DkekDao> retval = new ArrayList<DkekDao>();
		
		try{
				
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" where dkek_knr = ? ");
			sql.append(" FETCH FIRST 1000 ROWS ONLY ");
			
			logger.warn(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { kundnr  },  new DkekMapper());
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		return retval;
	}
	
	 
	public List findById (String id, StringBuffer errorStackTrace ){
		// N/A
		return null;
	}
	/**
	 * 
	 */
	public List findById (String kundnr, String varenr, StringBuffer errorStackTrace ){
		List<DkekDao> retval = new ArrayList<DkekDao>();
		//String SQL_WILD_CARD = "%";
		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" where dkek_knr = ? ");
			sql.append(" and dkek_vnr = ? ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { kundnr, varenr }, new DkekMapper());
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = null;
		}
		return retval;
	}
	
	/**
	 * 
	 * @param id
	 * @param errorStackTrace
	 * @return
	 */
	
	
	
	
	/**
	 * 
	 * @param dao
	 * @param errorStackTrace
	 * @return
	 */
	public int insert(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			DkekDao dao = (DkekDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO dkek (dkek_knr, dkek_vnr, dkek_311, dkek_314, dkek_315, dkek_331, dkek_332, dkek_sikk, dkek_34a, dkek_37, ");
			sql.append(" dkek_401a, dkek_402a, dkek_403a, dkek_411, dkek_441, dkek_4421, dkek_442a, ");
			sql.append(" dkek_443, dkek_444, dkek_446a, dkek_447, dkek_449a, dkek_49, dkek_bem1 ) ");
			
			sql.append(" VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			sql.append(" ?, ?, ?, ?, ?, ?, ?, ");
			sql.append(" ?, ?, ?, ?, ?, ?, ? ) ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
					dao.getDkek_knr(), dao.getDkek_vnr(), dao.getDkek_311(), dao.getDkek_314(), dao.getDkek_315(), dao.getDkek_331(), dao.getDkek_332(), dao.getDkek_sikk(), dao.getDkek_34a(), dao.getDkek_37(), 
					dao.getDkek_401a(), dao.getDkek_402a(), dao.getDkek_403a(), dao.getDkek_411(), dao.getDkek_441(), dao.getDkek_4421(), dao.getDkek_442a(),  
					dao.getDkek_443(), dao.getDkek_444(), dao.getDkek_446a(), dao.getDkek_447(), dao.getDkek_449a(), dao.getDkek_49(), dao.getDkek_bem1()  } );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		
		return retval;
	}
	/**
	 * UPDATE
	 */
	public int update(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			DkekDao dao = (DkekDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//dkek_311, dkek_314, dkek_315, dkek_331, dkek_332, dkek_sikk, dkek_34a, dkek_37, ");
			//sql.append(" dkek_401a, dkek_402a, dkek_403a, dkek_411, dkek_441, dkek_4421, dkek_442a, ");
			//sql.append(" dkek_443, dkek_444, dkek_446a, dkek_447, dkek_449a, dkek_49, dkek_bem1 ) ");
			
			sql.append(" UPDATE dkek SET  dkek_311 = ?, dkek_314 = ?, dkek_315 = ?, dkek_331 = ?, dkek_332 = ?, dkek_sikk = ?, dkek_34a = ?, dkek_37 = ?, ");
			sql.append(" dkek_401a = ?, dkek_402a = ?, dkek_403a = ?, dkek_411 = ?, dkek_441 = ?, dkek_4421 = ?, dkek_442a = ?, ");
			sql.append(" dkek_443 = ?, dkek_444 = ?, dkek_446a = ?, dkek_447 = ?, dkek_449a = ?, dkek_49 = ?, dkek_bem1 = ?  ");
			//id's
			sql.append(" WHERE dkek_knr = ? ");
			sql.append(" AND dkek_vnr = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
						dao.getDkek_311(), dao.getDkek_314(), dao.getDkek_315(), dao.getDkek_331(), dao.getDkek_332(), dao.getDkek_sikk(), dao.getDkek_34a(), dao.getDkek_37(),
						dao.getDkek_401a(), dao.getDkek_402a(), dao.getDkek_403a(), dao.getDkek_411(), dao.getDkek_441(), dao.getDkek_4421(), dao.getDkek_442a(),
						dao.getDkek_443(), dao.getDkek_444(), dao.getDkek_446a(), dao.getDkek_447(), dao.getDkek_449a(), dao.getDkek_49(), dao.getDkek_bem1(),
						//id's
						dao.getDkek_knr(),
						dao.getDkek_vnr()
						} );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		
		return retval;
	}
	/**
	 * DELETE
	 */
	public int delete(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		try{
			DkekDao dao = (DkekDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from dkek ");
			sql.append(" WHERE dkek_knr = ? ");
			sql.append(" AND dkek_vnr = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getDkek_knr(), dao.getDkek_vnr() } );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}

		return retval;
	}
	/**
	 * 
	 * @return
	 */
	private StringBuffer getSELECT_FROM_CLAUSE(){
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * from dkek ");
		
		return sql;
	}
	
	/**                                                                                                  
	 * Wires jdbcTemplate                                                                                
	 *                                                                                                   
	 */                                                                                                  
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}                                    

}
