package no.systema.jservices.skat.z.maintenance.skatexport.controller.rules;

import no.systema.jservices.skat.z.maintenance.skatexport.model.dao.entities.DktkdDao;
/**
 * 
 * @author oscardelatorre
 * @date Mar 2, 2017
 */
public class DKG210R_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(DktkdDao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) &&
				(dao.getDkkd_typ()!=null && !"".equals(dao.getDkkd_typ())) ){
			//check dao
			if( (dao.getDkkd_kd()!=null && !"".equals(dao.getDkkd_kd())) && 
				(dao.getDkkd_txt()!=null && !"".equals(dao.getDkkd_txt())) 
				
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
	public boolean isValidInputForDelete(DktkdDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getDkkd_typ()!=null && !"".equals(dao.getDkkd_typ())) &&
				(dao.getDkkd_kd()!=null && !"".equals(dao.getDkkd_kd()))	){
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
