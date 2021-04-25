package no.systema.jservices.skat.nctsexport.controller.rules;

import org.apache.commons.lang3.StringUtils;

import no.systema.jservices.skat.nctsexport.model.dao.entities.DkxvDao;
/**
 * 
 * @author oscardelatorre
 * @date Apr, 2021
 */
public class DKXV_U {

	
	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInputForDeleteAll(DkxvDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( StringUtils.isNotEmpty(dao.getTvavd()) && StringUtils.isNotEmpty(dao.getTvtdn()) ){
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
