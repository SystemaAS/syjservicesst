package no.systema.jservices.skat.skatexport.model.dao.services;
import java.util.*;


/**
 * 
 * @author oscardelatorre
 * @date Feb, 2021
 * 
 */
public interface DkevDaoServices extends IDaoServices { 
	public List getList(String avd, String opd, StringBuffer errorStackTrace);
	public List findById (String avd, String opd, String li, StringBuffer errorStackTrace );
	public int deleteAll(Object daoObj, StringBuffer errorStackTrace);
}
