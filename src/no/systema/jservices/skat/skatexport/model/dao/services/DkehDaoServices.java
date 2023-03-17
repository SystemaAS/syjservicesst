package no.systema.jservices.skat.skatexport.model.dao.services;
import java.util.*;


/**
 * 
 * @author oscardelatorre
 * @date Feb, 2021
 * 
 */
public interface DkehDaoServices extends IDaoServices { 
	//public List getList(String avd, String opd, StringBuffer errorStackTrace);
	public List findByLrn (String lrn, StringBuffer errorStackTrace );
	
}
