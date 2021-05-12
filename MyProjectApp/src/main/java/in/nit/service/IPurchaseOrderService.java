package in.nit.service;

import java.util.List;

import in.nit.model.PurchaseDtl;
import in.nit.model.PurchaseOrder;

public interface IPurchaseOrderService {

	public Integer savePurchaseOrder(PurchaseOrder po);
	public PurchaseOrder getOnePurchaseOrder(Integer id) ;
	public List<PurchaseOrder> getAllPurchaseOrders();
	
	public Integer savePurchaseDtl(PurchaseDtl dtl);
	public void deletePurchaseDtl(Integer id);
	public void updatePoStatus(Integer poId, String status);
	
	public List<Object[]> getPurchaseOrderIdAndCode();
}
