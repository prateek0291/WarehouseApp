package in.nit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author:RAGHU SIR 
 *  Generated F/w:SHWR-Framework 
 */
@Entity
@Table(name="parttab")
public class Part {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	private String partCode;
	
	private Double partLen;
	private Double partWid;
	private Double partHgt;
	
	private Double baseCost;
	private String baseCurrency;
	private String note;
	
	@ManyToOne
	@JoinColumn(name="uomIdFk")
	private Uom uomOb; //HAS-A
	
	@ManyToOne
	@JoinColumn(name="orderSaleIdFk")
	private OrderMethod omSaleOb; //HAS-A
	
	@ManyToOne
	@JoinColumn(name="orderPurchaseIdFk")
	private OrderMethod omPurcasheOb;
	
	
	
	public Part() {
		super();
	}
	
	public Part(Integer id) {
		super();
		this.id = id;
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
	public Double getPartLen() {
		return partLen;
	}
	public void setPartLen(Double partLen) {
		this.partLen = partLen;
	}
	public Double getPartWid() {
		return partWid;
	}
	public void setPartWid(Double partWid) {
		this.partWid = partWid;
	}
	public Double getPartHgt() {
		return partHgt;
	}
	public void setPartHgt(Double partHgt) {
		this.partHgt = partHgt;
	}
	public Double getBaseCost() {
		return baseCost;
	}
	public void setBaseCost(Double baseCost) {
		this.baseCost = baseCost;
	}
	public String getBaseCurrency() {
		return baseCurrency;
	}
	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	public Uom getUomOb() {
		return uomOb;
	}

	public void setUomOb(Uom uomOb) {
		this.uomOb = uomOb;
	}

	public OrderMethod getOmSaleOb() {
		return omSaleOb;
	}

	public void setOmSaleOb(OrderMethod omSaleOb) {
		this.omSaleOb = omSaleOb;
	}

	public OrderMethod getOmPurcasheOb() {
		return omPurcasheOb;
	}

	public void setOmPurcasheOb(OrderMethod omPurcasheOb) {
		this.omPurcasheOb = omPurcasheOb;
	}

	@Override
	public String toString() {
		return "Part [id=" + id + ", partCode=" + partCode + ", partLen=" + partLen + ", partWid=" + partWid
				+ ", partHgt=" + partHgt + ", baseCost=" + baseCost + ", baseCurrency=" + baseCurrency + ", note="
				+ note + ", uomOb=" + uomOb + ", omSaleOb=" + omSaleOb + ", omPurcasheOb=" + omPurcasheOb + "]";
	}

	

	
	
	
	
	
}



