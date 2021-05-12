package in.nit.service;

import java.util.List;

import in.nit.model.Grn;
import in.nit.model.GrnDtl;

/**
 * @author:RAGHU SIR 
 *  Generated F/w:SHWR-Framework 
 */
public interface IGrnService {
	Integer saveGrn(Grn grn);
	void updateGrn(Grn grn);
	void deleteGrn(Integer id);
	Grn getOneGrn(Integer id);
	List<Grn> getAllGrns();
	
	Integer saveGrnDtl(GrnDtl grnDtl);
	List<GrnDtl> getGrnDtlByGrnId(Integer grnId);
	void updateGrnDtlPartStatus(Integer grnDtl,String status);
}
