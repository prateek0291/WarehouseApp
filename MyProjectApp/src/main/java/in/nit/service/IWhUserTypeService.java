package in.nit.service;

import java.util.List;

import in.nit.model.WhUserType;

/**
 * @author:RAGHU SIR 
 *  Generated F/w:SHWR-Framework 
 */
public interface IWhUserTypeService {
	Integer saveWhUserType(WhUserType whusertype);

	void updateWhUserType(WhUserType whusertype);

	void deleteWhUserType(Integer id);

	WhUserType getOneWhUserType(Integer id);

	List<WhUserType> getAllWhUserTypes();
	
	List<Object[]> getWhUserTypeIdAndCode(String userType);
}
