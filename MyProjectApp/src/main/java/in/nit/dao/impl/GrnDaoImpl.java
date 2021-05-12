package in.nit.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import in.nit.dao.IGrnDao;
import in.nit.model.Grn;
import in.nit.model.GrnDtl;

/**
 * @author:RAGHU SIR 
 *  Generated F/w:SHWR-Framework 
 */
@Repository
public class GrnDaoImpl implements IGrnDao {
	@Autowired
	private HibernateTemplate ht;

	@Override
	public Integer saveGrn(Grn grn) {
		return (Integer)ht.save(grn);
	}

	@Override
	public void updateGrn(Grn grn) {
		ht.update(grn);
	}

	@Override
	public void deleteGrn(Integer id) {
		ht.delete(new Grn(id));
	}

	@Override
	public Grn getOneGrn(Integer id) {
		return ht.get(Grn.class,id);
	}

	@Override
	public List<Grn> getAllGrns() {
		return ht.loadAll(Grn.class);
	}
	
	@Override
	public Integer saveGrnDtl(GrnDtl grnDtl) {
		return (Integer) ht.save(grnDtl);
	}
	
	@Override
	public List<GrnDtl> getGrnDtlByGrnId(Integer grnId) {
		return ht.execute(new HibernateCallback<List<GrnDtl>>() {
			@Override
			public List<GrnDtl> doInHibernate(Session ses) 
					throws HibernateException {
				String hql=" select dtl "
						+ " from in.nit.model.GrnDtl dtl "
						+ " join dtl.grn as grn"
						+" where grn.id=:grnId ";
				@SuppressWarnings("unchecked")
				List<GrnDtl> dtls=ses.createQuery(hql)
				.setParameter("grnId", grnId)
				.list();
				return dtls;
			}
		});
	}
	
	@Override
	public void updateGrnDtlPartStatus(Integer grnDtl,String status) {
		ht.execute(new HibernateCallback<Integer>() {
			@Override
			public Integer doInHibernate(Session session) 
					throws HibernateException {
				String hql=" update in.nit.model.GrnDtl dtl "
						+ "  set dtl.partStatus=:status "
						+ "  where dtl.id=:grnDtl ";
				
				int count=
						session.createQuery(hql)
				.setParameter("status", status)
				.setParameter("grnDtl", grnDtl)
				.executeUpdate();
				
				return count;
			}
		});
	}
}




