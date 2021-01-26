package no.systema.jservices.skat.z.maintenance.main.controller.rules;

import org.apache.commons.lang3.StringUtils;

import no.systema.jservices.skat.z.maintenance.main.model.dao.entities.DkekDao;
/**
 * 
 * @author oscardelatorre
 * @date Jan, 2021
 */
public class DKEK_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(DkekDao dao, String user, String mode){
		boolean retval = true;
		
		if( StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(mode) ){
			//check dao
			if( StringUtils.isNotEmpty(dao.getDkek_knr()) && StringUtils.isNotEmpty(dao.getDkek_vnr()) ){
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
	public boolean isValidInputForDelete(DkekDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( StringUtils.isNotEmpty(dao.getDkek_knr()) && StringUtils.isNotEmpty(dao.getDkek_vnr()) ){
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
