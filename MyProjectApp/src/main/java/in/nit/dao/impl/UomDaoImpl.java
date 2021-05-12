package in.nit.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import in.nit.dao.IUomDao;
import in.nit.model.Uom;

/**
 * @author:RAGHU SIR 
 *  Generated F/w:SHWR-Framework 
 */
@Repository
public class UomDaoImpl implements IUomDao {
	@Autowired
	private HibernateTemplate ht;

	@Override
	public Integer saveUom(Uom uom) {
		return (Integer)ht.save(uom);
	}

	@Override
	public void updateUom(Uom uom) {
		ht.update(uom);
	}

	@Override
	public void deleteUom(Integer id) {
		ht.delete(new Uom(id));
	}

	@Override
	public Uom getOneUom(Integer id) {
		return ht.get(Uom.class,id);
	}

	@Override
	public List<Uom> getAllUoms() {
		return ht.loadAll(Uom.class);
	}
	
	@Override
	public List<Object[]> getUomIdAndUomModel() {
		String hql=" select id,uomModel from in.nit.model.Uom";
		@SuppressWarnings({ "deprecation", "unchecked" })
		List<Object[]> list=(List<Object[]>) ht.find(hql);
		return list;
	}
	
	
	@Override
	public boolean isUomModelExist(String uomModel) {
		long count=ht.execute(new HibernateCallback<Long>() {
			@Override
			public Long doInHibernate(Session ses) 
					throws HibernateException {
				String hql=" SELECT COUNT(id) FROM in.nit.model.Uom WHERE uomModel=:a ";
				
				Long count=(Long) ses.createQuery(hql)
				.setParameter("a", uomModel)
				.uniqueResult();
				
				return count;
			}
		});
		//if count>0 then record exist(true) else not exist false
		return count>0?true:false;
	}
	
	
	
	
	
	
}
