package no.systema.jservices.skat.z.maintenance.main.model.dao.entities;
import java.io.Serializable;
import no.systema.jservices.model.dao.entities.IDao;

import java.math.BigDecimal;

import lombok.Data;
/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Jan, 2021
 * 
 * 
 */
@Data
public class DkekDao implements Serializable, IDao {
		                       								//Start/end_pos Byte Dig Dec Type
	private String dkek_knr = ""; //   Kundens kundenr          1     8      8   8   0 S  
	private String dkek_vnr = ""; //   Kundens varenr           9      36    28           A (Artikkelnr)  
	private String dkek_311 = ""; //   Kollimærke               37   52    16           A  
	private String dkek_314 = ""; //   Kolliart                 53   54      2           A  
	private String dkek_315 = ""; //   Varebeskrivelse          55   99    45           A  
	private String dkek_331 = ""; //   Varekode                 100  107     8           A  
	private String dkek_332 = ""; //   ym tillægskode           108  111     4           a  
	private String dkek_sikk = "";//   sikkerhedsstillesle      112  113     2           a  
	private String dkek_34a = ""; //   oprindelsesland          114  115     2           a  
	private String dkek_37 = "";  //   procedure                116  122     7           a  
	private String dkek_401a = "";//   for.dok.kat              123  123     1           a  
	private String dkek_402a = "";//   for.dok.type             124  126     3           a  
	private String dkek_403a = "";//   for.dok.nr               127  146    20          a  
	private String dkek_411 = ""; //   suppl.enhed              147  149     3           a  
	private String dkek_441 = ""; //   bevillingsnr             150  162   13          a
	private String dkek_4421 = "";//   certifikatkode           163  166     4           a
	private String dkek_442a = "";//   certifikatnr             167  201    35          a
	private String dkek_443 = ""; //   vab bestemmelse       	 202  202     1          a
	private String dkek_444 = ""; //   fn farlig gods undg 	 203  206     4          a
	private String dkek_446a = "";//   suppl.vareopl.txt        207  276    70         a
	private String dkek_447  = "";//   delsendinger             277  281     5          a
	private String dkek_449a = "";//   oplysningstype kode      282  301   20         a
	private String dkek_49 = "";  //   id af oplag              302  318   17          a
	private String dkek_bem1 = "";//   bemærkninger             319  388   70         a

		
}
