package no.systema.jservices.skat.z.maintenance.main.model.dao.services;
import java.util.*;


/**
 * 
 * @author oscardelatorre
 * @date Sep 2, 2016
 * 
 */
public interface DkxghDaoServices extends IDaoServices { 
	public List findByIdExactMatch (String id, StringBuffer errorStackTrace );
	public int adjustBruktGuarantee (Object obj, StringBuffer errorStackTrace );
}
