package no.systema.jservices.skat.z.maintenance.main.controller.rules;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.*;

import no.systema.jservices.skat.z.maintenance.main.model.dao.entities.DkxhDao;

/**
 * 
 * @author oscardelatorre
 * @date Dec, 2021
 */
public class DKX030R_FBRUKT_U {
	private static Logger logger = LogManager.getLogger(DKX030R_FBRUKT_U.class.getName());
	
	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(DkxhDao dao, String user){
		boolean retval = true;
		
		if( StringUtils.isNotEmpty(user)){ 
			//OK
			if(StringUtils.isNotEmpty(dao.getThavd()) && StringUtils.isNotEmpty(dao.getThtdn()) && StringUtils.isNotEmpty(dao.getThsg()) ) {
				//OK
			}else {
				retval = false;
			}
			
		}else{
			retval = false;
		}
		return retval;
	}
	
	
}
