package no.systema.jservices.skat.z.maintenance.skatexport.model.dao.services;
import java.io.Writer;
import java.util.*;

import org.slf4j.*;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.skat.z.maintenance.skatexport.model.dao.entities.DktkdDao;
import no.systema.jservices.skat.z.maintenance.skatexport.model.dao.mapper.DktkdMapper;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Feb 28, 2017
 * 
 */
public class DktkdDaoServicesImpl implements DktkdDaoServices {
	private static Logger logger = LoggerFactory.getLogger(DktkdDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	
	/**
	 * Usually used for read-only scenarios (drop-down in GUI or other)
	 */
	public List getList(String code, StringBuffer errorStackTrace){
		List<DktkdDao> retval = new ArrayList<DktkdDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT * from dktkd where dkkd_typ = ?");
			sql.append("  ");
			//sql.append(" FETCH FIRST 00 ROWS ONLY ");
			
			//logger.info(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { code  }, new DktkdMapper());
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
	 * N/A
	 */
	public List getList(StringBuffer errorStackTrace){
		List<DktkdDao> retval = new ArrayList<DktkdDao>();
		return retval;
	}
	
	/**
	 * when searching for an update
	 * 
	 */
	public List findForUpdate (String code, String id, StringBuffer errorStackTrace ){
		List<DktkdDao> retval = new ArrayList<DktkdDao>();
		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(" SELECT * from dktkd where dkkd_typ = ?");
			sql.append(" and dkkd_kd = ? ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { code, id }, new DktkdMapper());
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
	 * N/A
	 */
	public List findById (String id, StringBuffer errorStackTrace ){
		List<DktkdDao> retval = new ArrayList<DktkdDao>();
		
		return retval;
	}
	
	/**
	 * 
	 * @param code
	 * @param id
	 * @param errorStackTrace
	 * @return
	 */
	public List findById (String code, String id, StringBuffer errorStackTrace ){
		List<DktkdDao> retval = new ArrayList<DktkdDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(" SELECT * from dktkd where dkkd_typ = ?");
			sql.append(" and dkkd_kd = ? ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { code, id }, new DktkdMapper());
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
	 * @param dao
	 * @param errorStackTrace
	 * @return
	 */
	public int insert(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		try{
			DktkdDao dao = (DktkdDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO dktkd (dkkd_typ, dkkd_kd, dkkd_kd2, dkkd_kd3, dkkd_txt )");
			sql.append(" VALUES( ?, ?, ?, ?, ? ) ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getDkkd_typ(), dao.getDkkd_kd(), dao.getDkkd_kd2(), 
				dao.getDkkd_kd3(), dao.getDkkd_txt() } );
			
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
	 * @param daoObj
	 * @param errorStackTrace
	 * @return
	 */
	public int update(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		try{
			DktkdDao dao = (DktkdDao)daoObj;
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE dktkd SET dkkd_kd2 = ?, dkkd_kd3 = ?, dkkd_txt = ? ");
			//id's
			sql.append(" WHERE dkkd_typ = ? ");
			sql.append(" AND dkkd_kd = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] {dao.getDkkd_kd2(), dao.getDkkd_kd3(), dao.getDkkd_txt(), 
						//id's
						dao.getDkkd_typ(),
						dao.getDkkd_kd()
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
	 * @param code
	 * @param daoObj
	 * @param errorStackTrace
	 * @return
	 */
	public int delete(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		try{
			DktkdDao dao = (DktkdDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from dktkd ");
			//id's
			sql.append(" WHERE dkkd_typ = ? ");
			sql.append(" AND dkkd_kd = ? ");
			
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getDkkd_typ(), dao.getDkkd_kd() } );
			
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
	 * Wires jdbcTemplate                                                                                
	 *                                                                                                   
	 */                                                                                                  
	private JdbcTemplate jdbcTemplate = null;                                                            
	public void setJdbcTemplate( JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}          
	public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}                                    

}
