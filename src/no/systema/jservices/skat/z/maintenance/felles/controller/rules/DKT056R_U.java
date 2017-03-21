package no.systema.jservices.skat.z.maintenance.felles.controller.rules;

import no.systema.jservices.common.dao.DkthaDao;
/**
 * 
 * @author oscardelatorre
 * @date Mar 21, 2016
 */
public class DKT056R_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(DkthaDao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getDkth_sysg()!=null && !"".equals(dao.getDkth_sysg())) &&
				(dao.getDkth_namn()!=null && !"".equals(dao.getDkth_namn())) && 
				(dao.getDkth_usid()!=null && !"".equals(dao.getDkth_usid()))    ){
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
	public boolean isValidInputForDelete(DkthaDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			if( (dao.getDkth_sysg()!=null && !"".equals(dao.getDkth_sysg()))    ){
				}else{
					retval = false;
				}
		}else{
			retval = false;
		}
		
		return retval;
	}
}
