package in.nit.service;

import java.util.List;

import in.nit.model.OrderMethod;

public interface IOrderMethodService {

	public Integer saveOrderMethod(OrderMethod ob);
	public List<OrderMethod> getAllOrderMethods();
	public void deleteOrderMethod(Integer id);
	
	public OrderMethod getOneOrderMethod(Integer id);
	public void updateOrderMethod(OrderMethod ob);
	public List<Object[]> getOrderIdAndCode(String mode);
}









