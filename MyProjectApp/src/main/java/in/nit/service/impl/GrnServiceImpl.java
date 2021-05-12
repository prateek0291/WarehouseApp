package in.nit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.nit.dao.IGrnDao;
import in.nit.model.Grn;
import in.nit.model.GrnDtl;
import in.nit.service.IGrnService;

/**
 * @author:RAGHU SIR 
 *  Generated F/w:SHWR-Framework 
 */
@Service
public class GrnServiceImpl implements IGrnService {
	@Autowired
	private IGrnDao dao;

	@Override
	@Transactional
	public Integer saveGrn(Grn grn) {
		return dao.saveGrn(grn);
	}

	@Override
	@Transactional
	public void updateGrn(Grn grn) {
		dao.updateGrn(grn);
	}

	@Override
	@Transactional
	public void deleteGrn(Integer id) {
		dao.deleteGrn(id);
	}

	@Override
	@Transactional(	readOnly = true)
	public Grn getOneGrn(Integer id) {
		return dao.getOneGrn(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Grn> getAllGrns() {
		return dao.getAllGrns();
	}
	
	@Override
	@Transactional
	public Integer saveGrnDtl(GrnDtl grnDtl) {
		return dao.saveGrnDtl(grnDtl);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<GrnDtl> getGrnDtlByGrnId(Integer grnId) {
		return dao.getGrnDtlByGrnId(grnId);
	}
	
	@Override
	@Transactional
	public void updateGrnDtlPartStatus(Integer grnDtl, String status) {
		dao.updateGrnDtlPartStatus(grnDtl, status);
	}
}
