package no.systema.jservices.skat.z.maintenance.skatexport.model.dao.entities;
import java.io.Serializable;
import no.systema.jservices.model.dao.entities.IDao;

import java.math.BigDecimal;
/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Feb 28, 2017
 * 
 */
public class DktkdDao implements Serializable, IDao {

	private String dkkd_typ = "";                                
	public String getDkkd_typPropertyName (){ return "dkkd_typ"; }
	public void setDkkd_typ (String value){ this.dkkd_typ = value;   }   
	public String getDkkd_typ (){ return this.dkkd_typ;   }  
	
	private String dkkd_kd = ""; 
	public String getDkkd_kdPropertyName (){ return "dkkd_kd"; }
	public void setDkkd_kd (String value){ this.dkkd_kd = value;   }   
	public String getDkkd_kd (){ return this.dkkd_kd;   }              

	private String dkkd_kd2 = "";
	public String getDkkd_kd2PropertyName (){ return "dkkd_kd2"; }
	public void setDkkd_kd2 (String value){ this.dkkd_kd2 = value;   }   
	public String getDkkd_kd2 (){ return this.dkkd_kd2;   }              

	private String dkkd_kd3 = "";
	public String getDkkd_kd3PropertyName (){ return "dkkd_kd3"; }
	public void setDkkd_kd3 (String value){ this.dkkd_kd3 = value;   }   
	public String getDkkd_kd3 (){ return this.dkkd_kd3;   }              

	private String dkkd_txt = ""; 
	public String getDkkd_txtPropertyName (){ return "dkkd_txt"; }
	public void setDkkd_txt (String value){ this.dkkd_txt = value;   }   
	public String getDkkd_txt (){ return this.dkkd_txt;   }              

	
	
}
