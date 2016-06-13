package no.systema.jservices.skat.z.maintenance.main.model.dao.services;
import java.util.*;


/**
 * 
 * @author oscardelatorre
 * @date Jun 13, 2016
 * 
 */
public interface DktvkDaoServices extends IDaoServices { 
	public List findForUpdate(String id, String alfa, StringBuffer errorStackTrace);
}
