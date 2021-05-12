package in.nit.service;

import java.util.List;

import in.nit.model.Uom;

/**
 * @author:RAGHU SIR 
 *  Generated F/w:SHWR-Framework 
 */
public interface IUomService {
	Integer saveUom(Uom uom);

	void updateUom(Uom uom);

	void deleteUom(Integer id);

	Uom getOneUom(Integer id);

	List<Uom> getAllUoms();
	
	List<Object[]> getUomIdAndUomModel();
	
	boolean isUomModelExist(String uomModel); 
}




