package no.systema.jservices.skat.z.maintenance.skatncts.model.dao.services;
import java.io.Writer;
import java.util.*;

import org.slf4j.*;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.skat.z.maintenance.skatncts.model.dao.mapper.DkxkodfMapper;
import no.systema.jservices.skat.z.maintenance.skatncts.model.dao.entities.DkxkodfDao;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Apr 10, 2016
 * 
 * 
 */
public class DkxkodfDaoServicesImpl implements DkxkodfDaoServices {
	private static Logger logger = LoggerFactory.getLogger(DkxkodfDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	
	/**
	 * 
	 * @param code
	 * @param errorStackTrace
	 * @return
	 */
	public List getList(String code, StringBuffer errorStackTrace){
		List<DkxkodfDao> retval = new ArrayList<DkxkodfDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" where tkunik = ? ");
			sql.append(" order by tkunik ");
			
			retval = this.jdbcTemplate.query( sql.toString() , new Object[] { code }, new DkxkodfMapper());
			
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
	 */
	public List findById (String code, String id, StringBuffer errorStackTrace ){
		List<DkxkodfDao> retval = new ArrayList<DkxkodfDao>();
		String WILDCARD = "%";
		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(this.getSELECT_FROM_CLAUSE());
			//WHERE
			sql.append(" where tkunik = ?  ");
			sql.append(" and tkkode LIKE ?  ");
			sql.append(" order by tkunik ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { code, id + WILDCARD}, new DkxkodfMapper());
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
		}
		return retval;
	}
	
	/**
	 * 
	 */
	public List getList(StringBuffer errorStackTrace){
		List retval = new ArrayList();
		// N/A
		return retval;
	}
	/**
	 * 
	 */
	public List findById (String id, StringBuffer errorStackTrace ){
		List retval = new ArrayList();
		// N/A
		return  retval;
	}
	/**
	 * 
	 */
	public int insert(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			DkxkodfDao dao = (DkxkodfDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO dkxkodf (tkunik, tkkode, tktxtn, tktxte) ");
			sql.append(" VALUES( ?, ?, ?, ? ) ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getTkunik(), dao.getTkkode(), dao.getTktxtn(), dao.getTktxte()  } );
			
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
	 */
	public int update(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;

		try{
			DkxkodfDao dao = (DkxkodfDao)daoObj;
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE dkxkodf SET tktxtn = ?, tktxte = ?" );
			//id's
			sql.append(" WHERE tkunik = ? ");
			sql.append(" AND tkkode = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
						dao.getTktxtn(), dao.getTktxte(),
						//id's
						dao.getTkunik(),
						dao.getTkkode(),
						
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
	 * 
	 */
	public int delete(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
		try{
			DkxkodfDao dao = (DkxkodfDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from dkxkodf ");
			sql.append(" WHERE tkunik = ? ");		
			sql.append(" AND tkkode = ? ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getTkunik(), dao.getTkkode() } );
			
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
	private String getSELECT_FROM_CLAUSE(){
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select * ");
		sql.append(" from dkxkodf  ");
	
		return sql.toString();
	}
	
	/**                                                                                                  
	 * Wires jdbcTemplate                                                                                
	 *                                                                                                   
	 */                                                                                                  
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}                                    

}
