package in.nit.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import in.nit.dao.IPartDao;
import in.nit.model.Part;

/**
 * @author:RAGHU SIR 
 *  Generated F/w:SHWR-Framework 
 */
@Repository
public class PartDaoImpl implements IPartDao {
	@Autowired
	private HibernateTemplate ht;

	@Override
	public Integer savePart(Part part) {
		return (Integer)ht.save(part);
	}

	@Override
	public void updatePart(Part part) {
		ht.update(part);
	}

	@Override
	public void deletePart(Integer id) {
		ht.delete(new Part(id));
	}

	@Override
	public Part getOnePart(Integer id) {
		return ht.get(Part.class,id);
	}

	@Override
	public List<Part> getAllParts() {
		return ht.loadAll(Part.class);
	}
	
	@Override
	public List<Object[]> getPartIdAndCodes() {
		String hql=" select id,partCode from in.nit.model.Part ";
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<Object[]> list=(List<Object[]>) ht.find(hql);
		return list;
	}
}



