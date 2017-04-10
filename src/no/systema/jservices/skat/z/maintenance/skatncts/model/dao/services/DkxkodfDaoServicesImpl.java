package no.systema.jservices.skat.z.maintenance.skatncts.model.dao.services;
import java.io.Writer;
import java.util.*;

import org.apache.log4j.Logger;
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
	private static Logger logger = Logger.getLogger(DkxkodfDaoServicesImpl.class.getName());
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
			sql.append(" and tkkode like ?  ");
			sql.append(" order by tkunik ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { code, WILDCARD + id + WILDCARD }, new DkxkodfMapper());
			
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
	public int insert(Object dao, StringBuffer errorStackTrace){
		int retval = 0;
		/*try{
			DkxghDao dao = (DkxghDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			//sql.append(" INSERT INTO dkxgh (tggnr, tgkna, tgtina, tgnaa, tgada1, tgpna, tgpsa, tglka, tgtsd, tggty, ");
			//sql.append(" tggfv, tgakny, tgakgm, tggbl, tggvk, tggblb, tgprm ) ");
			
			//sql.append(" VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			//sql.append(" ?, ?, ?, ?, ?, ?, ? ) ");
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getTggnr(), dao.getTgkna(), dao.getTgtina(), dao.getTgnaa(), dao.getTgada1(),
				dao.getTgpna(), dao.getTgpsa(), dao.getTglka(), dao.getTgtsd(), dao.getTggty(), dao.getTggfv(), dao.getTgakny(), dao.getTgakgm(), dao.getTggbl(),  
				dao.getTggvk(), dao.getTggblb(), dao.getTgprm()  } );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		*/
		return retval;
	}
	
	/**
	 * 
	 */
	public int update(Object dao, StringBuffer errorStackTrace){
		int retval = 0;
		/*
		try{
			DkxghDao dao = (DkxghDao)daoObj;
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE dkxgh SET  tgkna = ?, tgtina = ?, tgnaa = ?, tgada1 = ?, tgpna = ?, tgpsa = ?, tglka = ?, ");
			sql.append(" tggty = ?, tggvk = ?, tggbl = ?, tggblb = ?, tgtsd = ?, tggfv = ?, tgakny = ?, tgakgm = ?, ");
			sql.append(" tgprm = ?  ");
			
			//id's
			sql.append(" WHERE tggnr = ? ");
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { 
						dao.getTgkna(), dao.getTgtina(), dao.getTgnaa(), dao.getTgada1(), dao.getTgpna(), dao.getTgpsa(), dao.getTglka(),
						dao.getTggty(), dao.getTggvk(), dao.getTggbl(), dao.getTggblb(), dao.getTgtsd(), dao.getTggfv(), dao.getTgakny(), dao.getTgakgm(),
						dao.getTgprm(),
						//id's
						dao.getTggnr(),
						} );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		*/
		return retval;
	}
	/**
	 * 
	 */
	public int delete(Object dao, StringBuffer errorStackTrace){
		int retval = 0;
		/*
		try{
			DkxghDao dao = (DkxghDao)daoObj;
				
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" DELETE from dkxgh ");
			sql.append(" WHERE tggnr = ? ");		
			
			//params
			retval = this.jdbcTemplate.update( sql.toString(), new Object[] { dao.getTggnr() } );
			
		}catch(Exception e){
			Writer writer = this.dbErrorMessageMgr.getPrintWriter(e);
			logger.info(writer.toString());
			//Chop the message to comply to JSON-validation
			errorStackTrace.append(this.dbErrorMessageMgr.getJsonValidDbException(writer));
			retval = -1;
		}
		*/
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
