package no.systema.jservices.skat.z.maintenance.felles.model.dao.services;
import java.util.*;

import no.systema.jservices.skat.z.maintenance.main.model.dao.services.IDaoServices;


/**
 * 
 * @author oscardelatorre
 * @date Jun 13, 2016
 * 
 */
public interface DktvkDaoServices extends IDaoServices { 
	public List findForUpdate(String id, String alfa, StringBuffer errorStackTrace);
	public List getListDistinct(StringBuffer errorStackTrace);
}
