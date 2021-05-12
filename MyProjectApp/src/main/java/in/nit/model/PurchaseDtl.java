package in.nit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;


@Entity
public class PurchaseDtl {
	@Id
	@GeneratedValue
	@Column(name="dtlid")
	private Integer id;
	
	//Do now Store in Db, show only at UI
	@Transient
	private Integer slno; 
	
	@Column(name="dqty")
	private Integer qty;

	@ManyToOne
	@JoinColumn(name="dtl_part_fk")
	private Part part;
	
	@ManyToOne
	@JoinColumn(name="dtl_po_fk")
	private PurchaseOrder po;

	public PurchaseDtl() {
		super();
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSlno() {
		return slno;
	}

	public void setSlno(Integer slno) {
		this.slno = slno;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Part getPart() {
		return part;
	}

	public void setPart(Part part) {
		this.part = part;
	}

	public PurchaseOrder getPo() {
		return po;
	}

	public void setPo(PurchaseOrder po) {
		this.po = po;
	}

	@Override
	public String toString() {
		return "PurchaseDtl [id=" + id + ", slno=" + slno + ", qty=" + qty + ", part=" + part + ", po=" + po + "]";
	}
	
	
}
