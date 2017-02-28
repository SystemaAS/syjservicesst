package no.systema.jservices.skat.z.maintenance.skatexport.model.dao.services;
import java.util.*;

import no.systema.jservices.skat.z.maintenance.main.model.dao.services.IDaoServices;


/**
 * 
 * @author oscardelatorre
 * @date Feb 28, 2017
 * 
 */
public interface DktkdDaoServices extends IDaoServices { 
	public List findForUpdate(String code, String id, StringBuffer errorStackTrace);
	public List getList(String code, StringBuffer errorStackTrace);
	public List findById (String code, String id, StringBuffer errorStackTrace );

}
