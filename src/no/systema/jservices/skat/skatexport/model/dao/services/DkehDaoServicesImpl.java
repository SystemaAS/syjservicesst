package no.systema.jservices.skat.skatexport.model.dao.services;
import java.io.Writer;
import java.util.*;

import org.slf4j.*;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.skat.skatexport.model.dao.entities.DkehDao;
import no.systema.jservices.skat.skatexport.model.dao.mapper.DkehMapper;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Feb, 2021
 * 
 */
public class DkehDaoServicesImpl implements DkehDaoServices {
	private static Logger logger = LoggerFactory.getLogger(DkehDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	
	public List getList(StringBuffer errorStackTrace){
		//N/A
		return null;
	}
	
	
	 
	public List findById (String id, StringBuffer errorStackTrace ){
		// N/A
		return null;
	}
	/**
	 * 
	 */
	public List findByLrn (String lrn, StringBuffer errorStackTrace ){
		List<DkehDao> retval = new ArrayList<DkehDao>();

		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(" select * from dkeh " );
			sql.append(" where dkeh_07 = ? "); //tuillid = Lrn
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { lrn }, new DkehMapper());
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
		
		//N/A
		return retval;
	}
	/**
	 * UPDATE
	 */
	public int update(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		//N/A
		return retval;
	}
	/**
	 * DELETE
	 */
	public int delete(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		

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
