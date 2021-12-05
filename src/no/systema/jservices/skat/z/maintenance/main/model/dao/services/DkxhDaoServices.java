package no.systema.jservices.skat.z.maintenance.main.model.dao.services;
import java.util.*;


/**
 * 
 * @author oscardelatorre
 * @date Dec, 2021
 * 
 */
public interface DkxhDaoServices extends IDaoServices {
	public List getListReservedGuarantee ( StringBuffer errorStackTrace );
	public List getListReservedGuaranteeWithId ( String id, StringBuffer errorStackTrace );
	public List findById (Object obj, StringBuffer errorStackTrace );
	public int releaseGuarantee (Object obj, StringBuffer errorStackTrace );
}
