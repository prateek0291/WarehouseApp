package in.nit.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author:RAGHU SIR 
 *  Generated F/w:SHWR-Framework 
 */
@Entity
@Table(name = "grntab")
public class Grn {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;

	private String grnCode;
	private String grnType;
	private String note;

	/**Integrations**/
	@ManyToOne  //1...1  = *(unique)...1
	@JoinColumn(name="poIdFk",unique = true)
	private PurchaseOrder po;
	
	@OneToMany(mappedBy = "grn")
	private List<GrnDtl> grnDtls;
	
	public Grn() {
	}

	public Grn(Integer id) {
		this.id=id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGrnCode() {
		return grnCode;
	}

	public void setGrnCode(String grnCode) {
		this.grnCode = grnCode;
	}

	public String getGrnType() {
		return grnType;
	}

	public void setGrnType(String grnType) {
		this.grnType = grnType;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public PurchaseOrder getPo() {
		return po;
	}

	public void setPo(PurchaseOrder po) {
		this.po = po;
	}

	public List<GrnDtl> getGrnDtls() {
		return grnDtls;
	}

	public void setGrnDtls(List<GrnDtl> grnDtls) {
		this.grnDtls = grnDtls;
	}

	
}
