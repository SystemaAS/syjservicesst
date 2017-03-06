package no.systema.jservices.skat.z.maintenance.skatimport.controller.rules;

import no.systema.jservices.common.dao.DktseDao;
/**
 * 
 * @author oscardelatorre
 * @date Mar 6, 2017
 */
public class DKT058R_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(DktseDao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) &&
				(dao.getDkse_knr()!=null && !"".equals(dao.getDkse_knr())) ){
			//check dao
			if( (dao.getDkse_331()!=null && !"".equals(dao.getDkse_331()))  
				
				  ){
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
	public boolean isValidInputForDelete(DktseDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getDkse_knr()!=null && !"".equals(dao.getDkse_knr())) &&
				(dao.getDkse_331()!=null && !"".equals(dao.getDkse_331())) &&
				(dao.getDkse_34()!=null && !"".equals(dao.getDkse_34())) &&
				(dao.getDkse_4421()!=null && !"".equals(dao.getDkse_4421()))
				
				){
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
