package no.systema.jservices.skat.z.maintenance.skatncts.model.dao.services;
import java.util.*;

import no.systema.jservices.skat.z.maintenance.main.model.dao.services.IDaoServices;
/**
 * 
 * @author oscardelatorre
 * @date Apr 10, 2016
 * 
 */
public interface DkxkodfDaoServices extends IDaoServices { 
	public List getList(String code, StringBuffer errorStackTrace);
	public List findById(String code, String id, StringBuffer errorStackTrace);
}
