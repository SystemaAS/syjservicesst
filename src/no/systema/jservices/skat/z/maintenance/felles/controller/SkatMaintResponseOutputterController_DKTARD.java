package no.systema.jservices.skat.z.maintenance.felles.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
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
import no.systema.jservices.skat.z.maintenance.felles.controller.rules.DKTARDR_U;
import no.systema.jservices.common.dao.services.DktardDaoService;
import no.systema.jservices.common.dao.DktardDao;
import no.systema.jservices.common.json.JsonResponseWriter2;
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
 * @date Mar 06, 2017
 * 
 */

@Controller
public class SkatMaintResponseOutputterController_DKTARD {
	private static Logger logger = Logger.getLogger(SkatMaintResponseOutputterController_DKTARD.class.getName());
	
	/**
	 * FreeForm Source:
	 * 	 File: 		DKTARD
	 * 	 PGM:		DKTARDR
	 * 	 Member: 	SKAT Felles Maintenance - SELECT LIST or SELECT SPECIFIC
	 *  
	 * 
	 * @return
	 * @Example SELECT *: http://gw.systema.no:8080/syjservicesst/syjsDKTARDR.do?user=OSCAR&dktard=66%
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicesst/syjsDKTARDR.do?user=OSCAR&dktard01=0101210000&...
	 * 
	 */
	@RequestMapping(value="syjsDKTARDR.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsRList( HttpSession session, HttpServletRequest request) {
		String SQL_WILD_CARD = "%";
		JsonResponseWriter2<DktardDao> jsonWriter = new JsonResponseWriter2<DktardDao>();
		StringBuffer sb = new StringBuffer();
		List<DktardDao> list = null;
		try{
			logger.info("Inside syjsDKT058R");
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
				DktardDao dao = new DktardDao();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
	            binder.bind(request);
	            
				//do SELECT
				logger.info("Before SELECT ...");
				
				//only if it is not an exact value. If not, then put a wild card to allow a wide - SQL LIKE instead of index-friendly "="
				if(dao.getDktard01()!=null && !"".equals(dao.getDktard01()) ) {
					if(dao.getDktard01().length()<10 ){  dao.setDktard01(dao.getDktard01() + SQL_WILD_CARD); }
				}
				if(dao.getDktard02()!=null && !"".equals(dao.getDktard02()) ) {
					if(dao.getDktard02().length()<10 ){  dao.setDktard02(dao.getDktard02() + SQL_WILD_CARD); }
				}
				//put search columns as needed
				Map<String, Object> params = new HashMap<String, Object>();
				if(dao.getDktard01()!=null && !"".equals(dao.getDktard01()) ) { params.put("dktard01", dao.getDktard01()); }
				if(dao.getDktard02()!=null && !"".equals(dao.getDktard02()) ) { params.put("dktard02", dao.getDktard02()); }
				//logger.info("PARAMS:" + dao.getDktard01());
				list = new ArrayList<DktardDao>(); //default
				if(this.isValidSearchListDao(dao)){
					//get list only if dktard01 is present and > 1-character (to avoid to big results)
					list = this.dktardDaoService.findAll(params);
				}
				
				if (list != null){
					sb.append(jsonWriter.setJsonResult_Common_GetList(userName, list));
				}else{
					//write JSON error output
					errMsg = "ERROR on SELECT: list is NULL?  Try to check: <DaoServices>.findAll";
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
	
	
	/**
	 * 
	 * Update Database DML operations
	 * File: 	DKTARD
	 * PGM:		DKTARD
	 * Member: 	SKAT Maintenance - UPDATE SPECIFIC
	 * Note: This method does not contain UPDATE. Only CREATE/DELETE. Ref. AS400 UC (go dki: certifikatkoder)
	 * 
	 * @Example UPDATE: http://gw.systema.no:8080/syjservicesst/syjsDKTARDR_U.do?user=OSCAR&mode=U/A/D&dktard01=...
	 *
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	
	@RequestMapping(value="syjsDKTARDR_U.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String syjsR_U( HttpSession session, HttpServletRequest request) {
		JsonResponseWriter jsonWriter = new JsonResponseWriter();
		StringBuffer sb = new StringBuffer();
		
		try{
			logger.info("Inside syjsDKTARDR_U");
			//TEST-->logger.info("Servlet root:" + AppConstants.VERSION_SYJSERVICES);
			String user = request.getParameter("user");
			String mode = request.getParameter("mode");
			//Check ALWAYS user in BRIDF
            String userName = this.bridfDaoServices.findNameById(user);
            //DEBUG --> logger.info("USERNAME:" + userName + "XX");
            String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();
			
			//bind attributes is any
			DktardDao dao = new DktardDao();
			DktardDao resultDao = new DktardDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
            binder.bind(request);
            //rules
            DKTARDR_U rulerLord = new DKTARDR_U();
			//Key population in order to check if the record exists (for CREATE) and DELETE.
            Map params = new HashMap();
            params.put("dktard01", dao.getDktard01());
			params.put("dktard02", dao.getDktard02());
			params.put("dktard03", dao.getDktard03());
			
			//Start processing now
            if(userName!=null && !"".equals(userName)){
				if("D".equals(mode)){
					if(rulerLord.isValidInputForDelete(dao, userName, mode)){
						logger.info("Before DELETE ...");
						this.dktardDaoService.deleteAll(params);
					}else{
						//write JSON error output
						errMsg = "ERROR on DELETE: invalid?  Try to check: <DaoServices>.delete";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					}
				}else{
				  if(rulerLord.isValidInput(dao, userName, mode)){
						List<DktardDao> list = new ArrayList<DktardDao>();
						//do ADD
						if("A".equals(mode)){
							list = this.dktardDaoService.findAll(params);
							if(list!=null && list.size()>0){
								errMsg = "ERROR on CREATE/UPDATE: Record exists already";
								status = "error";
								sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
							}else{
								logger.info("CREATE new ...");
								resultDao = this.dktardDaoService.create(dao);
							}
						}else if("U".equals(mode)){
								logger.info("UPDATE ...");
								resultDao = this.dktardDaoService.update(dao);
						}
						if(resultDao == null){
							errMsg = "ERROR on CREATE/UPDATE";
							status = "error";
							dbErrorStackTrace.append("Could not add dao=" + ReflectionToStringBuilder.toString(dao));
							sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
						}else{
							// OK UPDATE
							sb.append(jsonWriter.setJsonSimpleValidResult(userName, status));
						}
					
				  }else{
						//write JSON error output
						errMsg = "ERROR on CREATE/UPDATE: invalid (rulerLord)?  Try to check: <DaoServices>.update";
						status = "error";
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				  }
				}
				
			}else{
				//write JSON error output
				errMsg = "ERROR on UPDATE";
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
		logger.info(sb.toString());
		return sb.toString();
	}
	/**
	 * 
	 * @param dao
	 * @return
	 */
	private boolean isValidSearchListDao(DktardDao dao){
		boolean retval = false;
		//this key MUST exist. All other keys are not relevant for this method
		if(dao.getDktard01()!=null && !"".equals(dao.getDktard01()) ){
			if(dao.getDktard01().length() > 2){ //including wild card
			  retval = true;
			}
		}
		
		return retval;
	}
	//----------------
	//WIRED SERVICES
	//----------------
	@Qualifier ("dktardDaoService")
	private DktardDaoService dktardDaoService;
	@Autowired
	@Required
	public void setDktardDaoService (DktardDaoService value){ this.dktardDaoService = value; }
	public DktardDaoService getDktardDaoService(){ return this.dktardDaoService; }
	
	
	
	@Qualifier ("bridfDaoServices")
	private BridfDaoServices bridfDaoServices;
	@Autowired
	@Required
	public void setBridfDaoServices (BridfDaoServices value){ this.bridfDaoServices = value; }
	public BridfDaoServices getBridfDaoServices(){ return this.bridfDaoServices; }
	
}

