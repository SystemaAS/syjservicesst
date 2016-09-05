package no.systema.jservices.skat.z.maintenance.main.model.dao.services;
import java.io.Writer;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import no.systema.jservices.skat.z.maintenance.main.model.dao.entities.DkxghDao;
import no.systema.jservices.skat.z.maintenance.main.model.dao.mapper.DkxghMapper;
import no.systema.main.util.DbErrorMessageManager;

/**
 * 
 * @author oscardelatorre
 * @date Sep 2, 2016
 * 
 */
public class DkxghDaoServicesImpl implements DkxghDaoServices {
	private static Logger logger = Logger.getLogger(DkxghDaoServicesImpl.class.getName());
	private DbErrorMessageManager dbErrorMessageMgr = new DbErrorMessageManager();
	
	
	
	public List getList(StringBuffer errorStackTrace){
		List<DkxghDao> retval = new ArrayList<DkxghDao>();
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(this.getSELECT_FROM_CLAUSE());
			//sql.append(" FETCH FIRST 00 ROWS ONLY ");
			
			logger.info(sql.toString());
			retval = this.jdbcTemplate.query( sql.toString(),  new DkxghMapper());
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
		List<DkxghDao> retval = new ArrayList<DkxghDao>();
		String SQL_WILD_CARD = "%";
		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" where tggnr LIKE ? ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id + SQL_WILD_CARD }, new DkxghMapper());
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
	public List findByIdExactMatch (String id, StringBuffer errorStackTrace ){
		List<DkxghDao> retval = new ArrayList<DkxghDao>();
		try{
			StringBuffer sql = new StringBuffer();
			
			sql.append(this.getSELECT_FROM_CLAUSE());
			sql.append(" where tggnr = ? ");
			
			retval = this.jdbcTemplate.query( sql.toString(), new Object[] { id  }, new DkxghMapper());
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
			DkxghDao dao = (DkxghDao)daoObj;
			StringBuffer sql = new StringBuffer();
			//DEBUG --> logger.info("mydebug");
			sql.append(" INSERT INTO dkxgh (tggnr, tgkna, tgtina, tgnaa, tgada1, tgpna, tgpsa, tglka, tgtsd, tggty, ");
			sql.append(" tggfv, tgakny, tgakgm, tggbl, tggvk, tggblb, tgprm ) ");
			
			sql.append(" VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			sql.append(" ?, ?, ?, ?, ?, ?, ? ) ");
			
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
		
		return retval;
	}
	/**
	 * UPDATE
	 */
	public int update(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
		
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
		
		return retval;
	}
	/**
	 * DELETE
	 */
	public int delete(Object daoObj, StringBuffer errorStackTrace){
		int retval = 0;
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

		return retval;
	}
	/**
	 * 
	 * @return
	 */
	private StringBuffer getSELECT_FROM_CLAUSE(){
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * from dkxgh ");
		
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
