package in.nit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="grndtltab")
public class GrnDtl {
	@Id
	@GeneratedValue
	private Integer id;
	
	private String partCode;
	private Double baseCost;
	private Integer qnty;
	
	private String partStatus;
	
	@ManyToOne
	@JoinColumn(name="grn_id_fk")
	private Grn grn;

	public GrnDtl() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPartCode() {
		return partCode;
	}

	public void setPartCode(String partCode) {
		this.partCode = partCode;
	}

	public Double getBaseCost() {
		return baseCost;
	}

	public void setBaseCost(Double baseCost) {
		this.baseCost = baseCost;
	}

	public Integer getQnty() {
		return qnty;
	}

	public void setQnty(Integer qnty) {
		this.qnty = qnty;
	}

	public String getPartStatus() {
		return partStatus;
	}

	public void setPartStatus(String partStatus) {
		this.partStatus = partStatus;
	}

	public Grn getGrn() {
		return grn;
	}

	public void setGrn(Grn grn) {
		this.grn = grn;
	}
	
	
	
	
}
