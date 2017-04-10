package no.systema.jservices.skat.z.maintenance.skatncts.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//Application
//import no.systema.jservices.model.dao.entities.GenericTableColumnsDao;
import no.systema.jservices.skat.z.maintenance.skatncts.model.dao.entities.DkxkodfDao;
import no.systema.jservices.skat.z.maintenance.skatncts.model.dao.services.DkxkodfDaoServices;
import no.systema.jservices.model.dao.services.BridfDaoServices;
import no.systema.jservices.jsonwriter.JsonResponseWriter;




/**
 * Service Response Controller
 * 
 * This class is the bridge and entry point to the syjservices-layer.
 * All communication to the outside world is done through this gateway.
 * 
 * @author oscardelatorre
 * @date Apr 10, 2016
 * 
 */

@Controller
public class SkatMaintResponseOutputterController_DKX001R {
	private static Logger logger = Logger.getLogger(SkatMaintResponseOutputterController_DKX001R.class.getName());
	
	/**
	 * FreeForm Source:
	 * 	 File: 		DKXKODF
	 * 	 PGM:		General (eg. DKX001R for maintenance Danish)
	 * 	 Member: 	MAINT - SAD NCTS  - DKXKODF  - Maintenance - SELECT LIST  or SELECT SPECIFIC 
	 *  
	 *  CODE file 
	 *  TKUNIK = 012 - Countries
	 *  TKUNIK = 106 - Tullkontor
	 *  ... etc
	 *  
	 * 
	 * @return
	 * @Example SELECT *: http://gw.systema.no:8080/syjservicesst/syjsDKX001R.do?user=OSCAR&tkunik=106
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicesst/syjsDKX001R.do?user=OSCAR&tkunik=106&tkkode=NO01011A
	 * 
	 */
	@RequestMapping(value="syjsDKX001R.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList( HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsDKXKODFR.do");
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
				DkxkodfDao dao = new DkxkodfDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            //At this point we now know if we are selecting a specific or all the db-table content (select *)
	            List list = null;
				//do SELECT
				logger.info("Before SELECT ...");
				if( (dao.getTkkode()!=null && !"".equals(dao.getTkkode())) ){
					logger.info("findById...");
					list = this.dkxkodfDaoServices.findById(dao.getTkunik(), dao.getTkkode(), dbErrorStackTrace);
				}else{
					logger.info("getList...");
					list = this.dkxkodfDaoServices.getList(dao.getTkunik(), dbErrorStackTrace);
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
	@Qualifier ("dkxkodfDaoServices")
	private DkxkodfDaoServices dkxkodfDaoServices;
	@Autowired
	@Required
	public void setDkxkodfDaoServices (DkxkodfDaoServices value){ this.dkxkodfDaoServices = value; }
	public DkxkodfDaoServices getTrkodfDaoServices(){ return this.dkxkodfDaoServices; }

	
	@Qualifier ("bridfDaoServices")
	private BridfDaoServices bridfDaoServices;
	@Autowired
	@Required
	public void setBridfDaoServices (BridfDaoServices value){ this.bridfDaoServices = value; }
	public BridfDaoServices getBridfDaoServices(){ return this.bridfDaoServices; }
	
	
}

