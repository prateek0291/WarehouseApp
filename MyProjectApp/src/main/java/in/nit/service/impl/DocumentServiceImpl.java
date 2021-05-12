package in.nit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.nit.dao.IDocumentDao;
import in.nit.model.Document;
import in.nit.service.IDocumentService;

@Service
public class DocumentServiceImpl 
	implements IDocumentService
{
	@Autowired
	private IDocumentDao dao;
	
	@Transactional 
	public Integer saveDocument(Document doc) {
		return dao.saveDocument(doc);
	}
	
	@Transactional(readOnly = true)
	public List<Object[]> getFileIdAndNames() {
		return dao.getFileIdAndNames();
	}
	@Transactional(readOnly = true)
	public Document getOneDocument(Integer id) {
		return dao.getOneDocument(id);
	}
}




