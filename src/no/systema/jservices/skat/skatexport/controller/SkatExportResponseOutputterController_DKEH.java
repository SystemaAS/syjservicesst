package no.systema.jservices.skat.skatexport.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//Application
//import no.systema.jservices.model.dao.entities.GenericTableColumnsDao;
import no.systema.jservices.skat.skatexport.model.dao.entities.DkehDao;
import no.systema.jservices.skat.skatexport.model.dao.services.DkehDaoServices;
import no.systema.jservices.model.dao.services.BridfDaoServices;
import no.systema.jservices.jsonwriter.JsonResponseWriter;
//rules


/**
 * Service Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer.
 * All communication to the outside world is done through this gateway.
 * 
 * @author oscardelatorre
 * @date Mar, 2023
 * 
 */

@Controller
public class SkatExportResponseOutputterController_DKEH {
	private static Logger logger = LoggerFactory.getLogger(SkatExportResponseOutputterController_DKEH.class.getName());
	
	/**
	 * FreeForm Source:
	 * 	 File: 		DKEH
	 * 	 PGM:		N/A
	 * 	 Member: 	SKAT Maintenance - SELECT LIST or SELECT SPECIFIC
	 *  
	 * 
	 * @return
	 * @Example SELECT http://gw.systema.no:8080/syjservicesst/syjsDKEH.do?user=OSCAR&dkeh_07=
	 * 
	 */
	@RequestMapping(value="syjsDKEH.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList( HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.warn("Inside syjsDKEH");
			//TEST-->logger.info("Servlet root:" + AppConstants.VERSION_SYJSERVICES);
			String user = request.getParameter("user");
			//Check ALWAYS user in BRIDF
            String userName = this.bridfDaoServices.findNameById(user);
            //DEBUG --> logger.info("USERNAME:" + userName + "XX");
            String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			
			//Start processing now
			if(userName!=null && !"".equals(userName)){
				//bind attributes is any
				DkehDao dao = new DkehDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            //At this point we now know if we are selecting a specific or all the db-table content (select *)
	            List list = null;
				//do SELECT
				logger.warn("Before SELECT ...");
	            if( StringUtils.isNotEmpty(dao.getDkeh_07())){
	            	logger.warn("getList");
	            	list = this.dkehDaoServices.findByLrn(dao.getDkeh_07(), dbErrorStackTrace);
				}
				//process result
				if (list!=null){
					//write the final JSON output
					sb.append(jsonWriter.setJsonResult_Common_GetList(userName, list));
				}else{
					//write JSON error output
					errMsg = "ERROR on SELECT: list is NULL?  Try to check: <DaoServices>.getList";
					status = "error";
					logger.info("After SELECT:" + " " + status + errMsg );
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}
			}else{
				//write JSON error output
				errMsg = "ERROR on SELECT";
				status = "error";
				dbErrorStackTrace.append("request input parameters are invalid: <user>, <other mandatory fields>");
				sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
			}
			
		}catch(Exception e){
			//write std.output error output
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			return "ERROR [JsonResponseOutputterController]" + writer.toString();
		}
		session.invalidate();
		return sb.toString();
	}
	

	
	//----------------
	//WIRED SERVICES
	//----------------
	@Qualifier ("dkehDaoServices")
	private DkehDaoServices dkehDaoServices;
	@Autowired
	public void setDkehDaoServices (DkehDaoServices value){ this.dkehDaoServices = value; }
	public DkehDaoServices getDkehDaoServices(){ return this.dkehDaoServices; }
	
	
	@Qualifier ("bridfDaoServices")
	private BridfDaoServices bridfDaoServices;
	@Autowired
	public void setBridfDaoServices (BridfDaoServices value){ this.bridfDaoServices = value; }
	public BridfDaoServices getBridfDaoServices(){ return this.bridfDaoServices; }
	
}

