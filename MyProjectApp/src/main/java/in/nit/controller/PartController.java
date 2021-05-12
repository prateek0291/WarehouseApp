package in.nit.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import in.nit.model.Part;
import in.nit.service.IOrderMethodService;
import in.nit.service.IPartService;
import in.nit.service.IUomService;
import in.nit.util.CommonUtil;
import in.nit.view.PartExcelView;

/**
 * @author:RAGHU SIR 
 *  Generated F/w:SHWR-Framework 
 */
@Controller
@RequestMapping("/part")
public class PartController {
	@Autowired
	private IPartService service;
	@Autowired
	private IUomService uomService;
	
	@Autowired
	private IOrderMethodService orderMethodService;

	private void commonUi(Model map) {
		//It will show DropDowns at UI(Reg/Edit)
		List<Object[]> uomList=uomService.getUomIdAndUomModel();
		Map<Integer,String> uomMap=CommonUtil.convert(uomList);
		map.addAttribute("uomMap", uomMap);
		
		List<Object[]> omSaleList=orderMethodService.getOrderIdAndCode("Sale");
		Map<Integer,String> omSaleMap=CommonUtil.convert(omSaleList);
		map.addAttribute("omSaleMap", omSaleMap);
		
		List<Object[]> omPurcasheList=orderMethodService.getOrderIdAndCode("Purchase");
		Map<Integer,String> omPurchaseMap=CommonUtil.convert(omPurcasheList);
		map.addAttribute("omPurchaseMap", omPurchaseMap);
	}


	@RequestMapping("/register")
	public String regPart(Model map) {
		map.addAttribute("part",new Part());
		commonUi(map);
		return "PartRegister";
	}

	@RequestMapping(
			value = "/save",
			method = POST
			)
	public String savePart(@ModelAttribute Part part, Model map) {
		Integer id=service.savePart(part);
		map.addAttribute("message","Part created with Id:"+id);
		map.addAttribute("part",new Part()) ;
		commonUi(map);
		return "PartRegister";
	}

	@RequestMapping(
			value = "/update",
			method = POST
			)
	public String updatePart(@ModelAttribute Part part, Model map) {
		service.updatePart(part);
		map.addAttribute("message","Part updated");
		List<Part> list=service.getAllParts();
		map.addAttribute("list",list);
		return "PartData";
	}

	@RequestMapping("/delete")
	public String deletePart(@RequestParam Integer id, Model map) {
		service.deletePart(id);
		map.addAttribute("message","Part deleted with Id:"+id);
		List<Part> list=service.getAllParts();
		map.addAttribute("list",list);
		return "PartData";
	}

	@RequestMapping("/edit")
	public String editPart(@RequestParam Integer id, Model map) {
		Part ob=service.getOnePart(id);
		map.addAttribute("part",ob);
		commonUi(map);
		return "PartEdit";
	}

	@RequestMapping("/all")
	public String getAllParts(Model map) {
		List<Part> list=service.getAllParts();
		map.addAttribute("list",list);
		return "PartData";
	}
	
	@RequestMapping("/excel")
	public ModelAndView showExcel() {
		ModelAndView m=new ModelAndView();
		m.setView(new PartExcelView());
		List<Part> list=service.getAllParts();
		m.addObject("list", list);
		return m;
	}
	
	
	
	
	
	
	
	
}
