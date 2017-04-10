package no.systema.jservices.skat.z.maintenance.skatncts.controller.rules;

import org.apache.log4j.Logger;

import no.systema.jservices.skat.z.maintenance.skatncts.controller.SkatMaintResponseOutputterController_DKX001R;
import no.systema.jservices.skat.z.maintenance.skatncts.model.dao.entities.DkxkodfDao;
/**
 * 
 * @author oscardelatorre
 * @date Apr 10, 2017
 */
public class DKX001R_U {
	private static Logger logger = Logger.getLogger(DKX001R_U.class.getName());
	
	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(DkxkodfDao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) &&
				(dao.getTkunik()!=null && !"".equals(dao.getTkunik())) ){
			//check dao
			if( (dao.getTkkode()!=null && !"".equals(dao.getTkkode())) && (dao.getTktxtn()!=null && !"".equals(dao.getTktxtn())) ){
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
	public boolean isValidInputForDelete(DkxkodfDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getTkunik()!=null && !"".equals(dao.getTkunik())) &&
				(dao.getTkkode()!=null && !"".equals(dao.getTkkode()))	){
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
