package in.nit.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class PurchaseOrder {
	@Id
	@GeneratedValue
	private Integer id;
	
	private String orderCode;
	private String refNum;
	private String qltyChk;
	private String status;
	private String note;
	
	@ManyToOne
	@JoinColumn(name="shpIdFk")
	private ShipmentType sob;
	
	@ManyToOne
	@JoinColumn(name="venIdFk")
	private WhUserType vendor;
	
	//We want to fetch all dtls when we get parent object
	@OneToMany(mappedBy = "po",fetch = FetchType.EAGER)
	private List<PurchaseDtl> childs;
	
	public PurchaseOrder() {
		super();
	}
	public PurchaseOrder(Integer id) {
		super();
		this.id = id;
	}
	
	public List<PurchaseDtl> getChilds() {
		return childs;
	}
	public void setChilds(List<PurchaseDtl> childs) {
		this.childs = childs;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getRefNum() {
		return refNum;
	}
	public void setRefNum(String refNum) {
		this.refNum = refNum;
	}
	public String getQltyChk() {
		return qltyChk;
	}
	public void setQltyChk(String qltyChk) {
		this.qltyChk = qltyChk;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public ShipmentType getSob() {
		return sob;
	}
	public void setSob(ShipmentType sob) {
		this.sob = sob;
	}
	public WhUserType getVendor() {
		return vendor;
	}
	public void setVendor(WhUserType vendor) {
		this.vendor = vendor;
	}
	@Override
	public String toString() {
		return "PurchaseOrder [id=" + id + ", orderCode=" + orderCode + ", refNum=" + refNum + ", qltyChk=" + qltyChk
				+ ", status=" + status + ", note=" + note + ", sob=" + sob + ", vendor=" + vendor + "]";
	}
	
	
	
	
}
