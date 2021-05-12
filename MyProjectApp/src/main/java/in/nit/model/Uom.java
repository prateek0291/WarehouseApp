package in.nit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author:RAGHU SIR 
 *  Generated F/w:SHWR-Framework 
 */
@Entity
@Table(name="uomtab")
public final class Uom {
	@Id
	@GeneratedValue
	@Column(name = "umid")
	private Integer id;
	
	@Column(name = "umtyp")
	private String uomType;
	
	@Column(name = "ummdl",unique = true,nullable = false)
	private String uomModel;
	
	@Column(name = "umdsc")
	private String note;
	

	public Uom() {
		super();
	}


	public Uom(Integer id) {
		super();
		this.id = id;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getUomType() {
		return uomType;
	}


	public void setUomType(String uomType) {
		this.uomType = uomType;
	}


	public String getUomModel() {
		return uomModel;
	}


	public void setUomModel(String uomModel) {
		this.uomModel = uomModel;
	}


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}


	@Override
	public String toString() {
		return "Uom [id=" + id + ", uomType=" + uomType + ", uomModel=" + uomModel + ", note=" + note + "]";
	}
	
	
	
}







