package no.systema.jservices.skat.z.maintenance.felles.model.dao.services;
import java.io.Writer;
import java.util.*;

import org.apache.logging.log4j.*;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.skat.z.maintenance.felles.model.dao.entities.DktvkDao;
import no.systema.jservices.skat.z.maintenance.felles.model.dao.mapper.DktvkMapper;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Jun 13, 2016
 * 
 */
public class DktvkDaoServicesImpl implements DktvkDaoServices {
	private static Logger logger = LogManager.getLogger(DktvkDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	
	/**
	 * Usually used for read-only scenarios (drop-down in GUI or other)
	 */
	public List getListDistinct(StringBuffer errorStackTrace){
		List<DktvkDao> retval = new ArrayList<DktvkDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT distinct dkvk_kd ");
			sql.append(" from dktvk ");
			//sql.append(" FETCH FIRST 00 ROWS ONLY ");
			
			logger.info(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(),  new DktvkMapper());
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
	public List getList(StringBuffer errorStackTrace){
		List<DktvkDao> retval = new ArrayList<DktvkDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			//WE must specify all the columns since there are numeric formats. All numeric formats are incompatible with JDBC template (at least in DB2)
			//when issuing select * from ...
			//The numeric formats MUST ALWAYS be converted to CHARs (IBM string equivalent to Oracle VARCHAR)
			sql.append(this.getSELECT_CLAUSE());
			//sql.append(" FETCH FIRST 00 ROWS ONLY ");
			
			logger.info(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(),  new DktvkMapper());
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
	 * when searching for an update
	 * 
	 */
	public List findForUpdate (String id, String alfa, StringBuffer errorStackTrace ){
		List<DktvkDao> retval = new ArrayList<DktvkDao>();
		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(this.getSELECT_CLAUSE());
			sql.append(" where dkvk_kd = ? ");
			sql.append(" and dkvk_dts = ? ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id , alfa }, new DktvkMapper());
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
	public List findById (String id, StringBuffer errorStackTrace ){
		List<DktvkDao> retval = new ArrayList<DktvkDao>();
		String SQL_WILD_CARD = "%";
		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(this.getSELECT_CLAUSE());
			sql.append(" where dkvk_kd LIKE ? ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id + SQL_WILD_CARD }, new DktvkMapper());
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
			DktvkDao dao = (DktvkDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO dktvk (dkvk_kd, dkvk_dts, dkvk_dte, dkvk_omr, dkvk_krs )");
			sql.append(" VALUES(?, ?, ?, ?, ? ) ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getDkvk_kd(), dao.getDkvk_dts(), dao.getDkvk_dte(),
				dao.getDkvk_omr(), dao.getDkvk_krs() } );
			
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
			DktvkDao dao = (DktvkDao)daoObj;
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE dktvk SET dkvk_dts = ?, dkvk_dte = ?, dkvk_omr = ?, dkvk_krs = ? ");
			//id's
			sql.append(" WHERE dkvk_kd = ? ");
			sql.append(" AND dkvk_dts = ? ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
						dao.getDkvk_dts(), dao.getDkvk_dte(), dao.getDkvk_omr(), dao.getDkvk_krs(), 
						//id's
						dao.getDkvk_kd(),
						dao.getDkvk_dts(),
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
			DktvkDao dao = (DktvkDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from dktvk ");
			//id's
			sql.append(" WHERE dkvk_kd = ? ");
			sql.append(" AND dkvk_dts = ? ");
			
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getDkvk_kd(), dao.getDkvk_dts() } );
			
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
	private StringBuffer getSELECT_CLAUSE(){
		//Compatibility issue on special characters (ø,æ, etc)
		//All columns with special characters (NO,SE,DK) such as ö,ä,ø, etc MUST be defined with CAPITAL LETTERS, otherwise the selection in SQL will be invalid
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT dkvk_kd, CHAR(dkvk_dts) dkvk_dts, CHAR(dkvk_dte) dkvk_dte, CHAR(dkvk_omr) dkvk_omr, CHAR(dkvk_krs) dkvk_krs ");
		sql.append(" from dktvk ");
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
