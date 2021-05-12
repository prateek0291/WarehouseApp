package in.nit.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import in.nit.dao.IPurchaseOrderDao;
import in.nit.model.PurchaseDtl;
import in.nit.model.PurchaseOrder;

@Repository
public class PurchaseOrderDaoImpl implements IPurchaseOrderDao {

	@Autowired
	private HibernateTemplate ht;

	public Integer savePurchaseOrder(PurchaseOrder po) {
		return (Integer) ht.save(po);
	}

	@Override
	public PurchaseOrder getOnePurchaseOrder(Integer id) {
		return ht.get(PurchaseOrder.class, id);
	}
	@Override
	public List<PurchaseOrder> getAllPurchaseOrders() {
		return ht.loadAll(PurchaseOrder.class);
	}

	@Override
	public Integer savePurchaseDtl(PurchaseDtl dtl) {
		return (Integer) ht.save(dtl);
	}

	@Override
	public void deletePurchaseDtl(Integer id) {
		PurchaseDtl dtl=new PurchaseDtl();
		dtl.setId(id);
		ht.delete(dtl);
	}

	@Override
	public void updatePoStatus(Integer poId, String status) {
		ht.execute(new HibernateCallback<Integer>() {
			@Override
			public Integer doInHibernate(Session session) 
					throws HibernateException 
			{
				String hql=" update in.nit.model.PurchaseOrder "
						+" set status=:a "
						+" where id=:b ";
				int count=session.createQuery(hql)
						.setParameter("a", status)
						.setParameter("b", poId)
						.executeUpdate();
				return count;
			}
		});
	}

	@Override
	public List<Object[]> getPurchaseOrderIdAndCode() {
		List<Object[]> result=
				ht.execute(new HibernateCallback<List<Object[]>>() {

					@Override
					public List<Object[]> doInHibernate(Session session)
							throws HibernateException {
						/*
						String hql=" select id, orderCode from in.nit.model.PurchaseOrder where status = 'INVOICED' ";
						Query q=session.createQuery(hql);
						List<Object[]> list=q.list();
						return list;
						*/
						CriteriaBuilder builder=session.getCriteriaBuilder();
						//specify output type
						CriteriaQuery<Object[]> query=builder.createQuery(Object[].class);
						//specify from clause
						Root<PurchaseOrder> root=query.from(PurchaseOrder.class);
						//specify select clause
						query.multiselect(root.get("id"),root.get("orderCode"));
						
						//where condition
						query.where(builder.equal(root.get("status"), "INVOICED"));
						
						//--execute query---------
						Query<Object[]> q=session.createQuery(query);
						List<Object[]> list=q.list();
						
						return list;
					}
				});
		return result;
	}
}



