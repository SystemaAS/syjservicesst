package no.systema.jservices.skat.z.maintenance.felles.controller.rules;

import no.systema.jservices.common.dao.DktardDao;
/**
 * 
 * @author oscardelatorre
 * @date Mar 09, 2016
 */
public class DKTARDR_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(DktardDao dao, String user, String mode){
		boolean retval = true;
		
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			if( (dao.getDktard01()!=null && !"".equals(dao.getDktard01())) &&
				(dao.getDktard02()!=null && !"".equals(dao.getDktard02())) && 
				(dao.getDktard03()!=null && !"".equals(dao.getDktard03())) && 
				(dao.getDktard48()!=null && !"".equals(dao.getDktard48()))   ){
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
	public boolean isValidInputForDelete(DktardDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			if( (dao.getDktard01()!=null && !"".equals(dao.getDktard01())) &&
					(dao.getDktard02()!=null && !"".equals(dao.getDktard02())) && 
					(dao.getDktard03()!=null && !"".equals(dao.getDktard03())) && 
					(dao.getDktard48()!=null && !"".equals(dao.getDktard48()))   ){
				}else{
					retval = false;
				}
		}else{
			retval = false;
		}
		
		return retval;
	}
}
