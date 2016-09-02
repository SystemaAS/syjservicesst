package no.systema.jservices.skat.z.maintenance.main.controller.rules;

import no.systema.jservices.skat.z.maintenance.main.model.dao.entities.DkxghDao;
/**
 * 
 * @author oscardelatorre
 * @date Sep 2, 2016
 */
public class DKX030R_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(DkxghDao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) &&
			(mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getTggnr()!=null && !"".equals(dao.getTggnr())) 
				/*&& TODO
				(dao.getDkvk_dts()!=null && !"".equals(dao.getDkvk_dts())) && 
				(dao.getDkvk_dte()!=null && !"".equals(dao.getDkvk_dte())) && 
				(dao.getDkvk_omr()!=null && !"".equals(dao.getDkvk_omr())) && 
				(dao.getDkvk_krs()!=null && !"".equals(dao.getDkvk_krs()))
				*/
				  ){
				
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
	public boolean isValidInputForDelete(DkxghDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getTggnr()!=null && !"".equals(dao.getTggnr())) ){
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
