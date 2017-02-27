package no.systema.jservices.skat.z.maintenance.felles.model.dao.entities;
import java.io.Serializable;
import no.systema.jservices.model.dao.entities.IDao;

import java.math.BigDecimal;
/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Jun 13, 2016
 * 
 */
public class DktvkDao implements Serializable, IDao {

	private String dkvk_kd = "";                                
	public String getDkvk_kdPropertyName (){ return "dkvk_kd"; }
	public void setDkvk_kd (String value){ this.dkvk_kd = value;   }   
	public String getDkvk_kd (){ return this.dkvk_kd;   }  
	
	private String dkvk_dts = ""; 
	public String getDkvk_dtsPropertyName (){ return "dkvk_dts"; }
	public void setDkvk_dts (String value){ this.dkvk_dts = value;   }   
	public String getDkvk_dts (){ return this.dkvk_dts;   }              

	private String dkvk_dte = "";
	public String getDkvk_dtePropertyName (){ return "dkvk_dte"; }
	public void setDkvk_dte (String value){ this.dkvk_dte = value;   }   
	public String getDkvk_dte (){ return this.dkvk_dte;   }              

	private String dkvk_omr = "0";
	public String getDkvk_omrPropertyName (){ return "dkvk_omr"; }
	public void setDkvk_omr (String value){ this.dkvk_omr = value;   }   
	public String getDkvk_omr (){ return this.dkvk_omr;   }              

	private String dkvk_krs = "0"; 
	public String getDkvk_krsPropertyName (){ return "dkvk_krs"; }
	public void setDkvk_krs (String value){ this.dkvk_krs = value;   }   
	public String getDkvk_krs (){ return this.dkvk_krs;   }              

	
	
}
