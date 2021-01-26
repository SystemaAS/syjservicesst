package no.systema.jservices.skat.z.maintenance.main.model.dao.services;
import java.util.*;


/**
 * 
 * @author oscardelatorre
 * @date Jan, 2021
 * 
 */
public interface DkekDaoServices extends IDaoServices { 
	public List getList(String kundnr, StringBuffer errorStackTrace);
	public List findById (String kundnr, String varenr, StringBuffer errorStackTrace );
}
