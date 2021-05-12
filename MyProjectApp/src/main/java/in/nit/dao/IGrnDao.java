package in.nit.dao;

import java.util.List;

import in.nit.model.Grn;
import in.nit.model.GrnDtl;

/**
 * @author:RAGHU SIR 
 *  Generated F/w:SHWR-Framework 
 */
public interface IGrnDao {
	Integer saveGrn(Grn grn);
	void updateGrn(Grn grn);
	void deleteGrn(Integer id);
	Grn getOneGrn(Integer id);
	List<Grn> getAllGrns();
	
	//---childs screen#2--------
	Integer saveGrnDtl(GrnDtl grnDtl);
	List<GrnDtl> getGrnDtlByGrnId(Integer grnId);
	void updateGrnDtlPartStatus(Integer grnDtl,String status);
}
