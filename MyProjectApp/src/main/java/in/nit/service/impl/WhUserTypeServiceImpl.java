package in.nit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.nit.dao.IWhUserTypeDao;
import in.nit.model.WhUserType;
import in.nit.service.IWhUserTypeService;

/**
 * @author:RAGHU SIR 
 *  Generated F/w:SHWR-Framework 
 */
@Service
public class WhUserTypeServiceImpl implements IWhUserTypeService {
	@Autowired
	private IWhUserTypeDao dao;

	@Override
	@Transactional
	public Integer saveWhUserType(WhUserType whusertype) {
		return dao.saveWhUserType(whusertype);
	}

	@Override
	@Transactional
	public void updateWhUserType(WhUserType whusertype) {
		dao.updateWhUserType(whusertype);
	}

	@Override
	@Transactional
	public void deleteWhUserType(Integer id) {
		dao.deleteWhUserType(id);
	}

	@Override
	@Transactional(
			readOnly = true
			)
	public WhUserType getOneWhUserType(Integer id) {
		return dao.getOneWhUserType(id);
	}

	@Override
	@Transactional(
			readOnly = true
			)
	public List<WhUserType> getAllWhUserTypes() {
		return dao.getAllWhUserTypes();
	}
	
	
	@Transactional(readOnly = true)
	public List<Object[]> getWhUserTypeIdAndCode(String userType) {
		return dao.getWhUserTypeIdAndCode(userType);
	}
}





