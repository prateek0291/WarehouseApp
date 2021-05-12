package in.nit.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import in.nit.model.Document;
import in.nit.service.IDocumentService;

@Controller
@RequestMapping("/docs")
public class DocumentController {
	
	@Autowired
	private IDocumentService service;
	
	//1. show Documents Upload Page
	@RequestMapping("/show")
	public String showUploadPage(Model model) {
		List<Object[]> list=service.getFileIdAndNames();
		model.addAttribute("list", list);
		return "Documents";
	}
	
	//2. upload data into DB
	@RequestMapping(value = "/upload",method = RequestMethod.POST)
	public String doUpload(
			@RequestParam Integer fileId,
			@RequestParam CommonsMultipartFile fileOb,
			Model model) 
	
	{
		if(fileOb!=null) {
			Document doc=new Document();
			doc.setFileId(fileId);
			doc.setFileName(fileOb.getOriginalFilename());
			doc.setFileData(fileOb.getBytes());
			service.saveDocument(doc);
		}
		//response.sendRedirect
		return "redirect:show";
	}
	
	//3. Download document
	@RequestMapping("/download")
	public void doDownload(
			@RequestParam Integer fid,
			HttpServletResponse resp) 
	{
		//read one object based on ID
		Document doc=service.getOneDocument(fid);
		
		resp.addHeader("Content-Disposition", "attachment;filename="+doc.getFileName());
		
		try {
			//copy data to OS
			FileCopyUtils.copy(
					doc.getFileData(),
					resp.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}



