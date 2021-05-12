package in.nit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.nit.dao.IPurchaseOrderDao;
import in.nit.model.PurchaseDtl;
import in.nit.model.PurchaseOrder;
import in.nit.service.IPurchaseOrderService;

@Service
public class PurchaseOrderServiceImpl implements IPurchaseOrderService {
	@Autowired
	private IPurchaseOrderDao dao;
	
	@Transactional
	public Integer savePurchaseOrder(PurchaseOrder po) {
		return dao.savePurchaseOrder(po);
	}

	@Transactional(readOnly = true)
	public PurchaseOrder getOnePurchaseOrder(Integer id) {
		return dao.getOnePurchaseOrder(id);
	}
	
	@Transactional(readOnly = true)
	public List<PurchaseOrder> getAllPurchaseOrders() {
		return dao.getAllPurchaseOrders();
	}
	
	@Transactional
	public Integer savePurchaseDtl(PurchaseDtl dtl) {
		return dao.savePurchaseDtl(dtl);
	}
	
	@Transactional
	public void deletePurchaseDtl(Integer id) {
		dao.deletePurchaseDtl(id);
	}
	
	@Transactional
	public void updatePoStatus(Integer poId, String status) {
		dao.updatePoStatus(poId, status);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Object[]> getPurchaseOrderIdAndCode() {
		return dao.getPurchaseOrderIdAndCode();
	}
}



