package in.nit.dao;

import java.util.List;

import in.nit.model.PurchaseDtl;
import in.nit.model.PurchaseOrder;

public interface IPurchaseOrderDao {

	public Integer savePurchaseOrder(PurchaseOrder po);
	public PurchaseOrder getOnePurchaseOrder(Integer id);
	public List<PurchaseOrder> getAllPurchaseOrders();
	
	
	//----------------Screen#2 Operations---------------
	public Integer savePurchaseDtl(PurchaseDtl dtl);
	public void deletePurchaseDtl(Integer id);

	public void updatePoStatus(Integer poId,String status);
	
	//-----------------------Integration methods--------
	public List<Object[]> getPurchaseOrderIdAndCode();
	
}





