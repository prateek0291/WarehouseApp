package in.nit.dao;

import java.util.List;

import in.nit.model.Part;

/**
 * @author:RAGHU SIR 
 *  Generated F/w:SHWR-Framework 
 */
public interface IPartDao {
	Integer savePart(Part part);

	void updatePart(Part part);

	void deletePart(Integer id);

	Part getOnePart(Integer id);

	List<Part> getAllParts();
	
	List<Object[]> getPartIdAndCodes();
}
