package no.systema.jservices.skat.skatexport.controller.rules;

import org.apache.commons.lang3.StringUtils;

import no.systema.jservices.skat.skatexport.model.dao.entities.DkevDao;
/**
 * 
 * @author oscardelatorre
 * @date Feb, 2021
 */
public class DKEV_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(DkevDao dao, String user, String mode){
		boolean retval = true;
		
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode) ){
			//check dao
			if( StringUtils.isNotEmpty(dao.getDkev_syav()) && StringUtils.isNotEmpty(dao.getDkev_syop()) ){
				//OK
			}else{
				retval = false;
			}
		}else{
			retval = false;
		}
		return retval;
	}
	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInputForDeleteAll(DkevDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( StringUtils.isNotEmpty(dao.getDkev_syav()) && StringUtils.isNotEmpty(dao.getDkev_syop()) ){
				//OK
			}else{
				retval = false;
			}
		}else{
			retval = false;
		}
		
		return retval;
	}
	
	
	
}
