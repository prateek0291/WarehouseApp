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

import in.nit.model.Grn;
import in.nit.model.GrnDtl;
import in.nit.model.PurchaseDtl;
import in.nit.model.PurchaseOrder;
import in.nit.service.IGrnService;
import in.nit.service.IPurchaseOrderService;
import in.nit.util.CommonUtil;

/**
 * @author:RAGHU SIR 
 *  Generated F/w:SHWR-Framework 
 */
@Controller
@RequestMapping("/grn")
public class GrnController {
	@Autowired
	private IGrnService service;
	@Autowired
	private IPurchaseOrderService poService;
	
	
	public void addCommonUi(Model model) {
		List<Object[]> poList=poService.getPurchaseOrderIdAndCode();
		Map<Integer,String> poMap=CommonUtil.convert(poList);
		model.addAttribute("poMap", poMap);
	}

	@RequestMapping("/register")
	public String regGrn(Model map) {
		map.addAttribute("grn",new Grn());
		addCommonUi(map);
		return "GrnRegister";
	}

	@RequestMapping(value = "/save",method = POST)
	public String saveGrn(@ModelAttribute Grn grn, Model map) {
		Integer id=service.saveGrn(grn);
		
		mapPoDtlToGrnDtlAndSave(grn); // One PoDtl-> oneGrnDtl
		
		map.addAttribute("message","Grn created with Id:"+id);
		poService.updatePoStatus(grn.getPo().getId(), "RECEVIED");
		map.addAttribute("grn",new Grn()) ;
		addCommonUi(map);
		return "GrnRegister";
	}

	@RequestMapping(value = "/update",method = POST)
	public String updateGrn(@ModelAttribute Grn grn, Model map) {
		service.updateGrn(grn);
		map.addAttribute("message","Grn updated");
		List<Grn> list=service.getAllGrns();
		map.addAttribute("list",list);
		return "GrnData";
	}

	@RequestMapping("/delete")
	public String deleteGrn(@RequestParam Integer id, Model map) {
		service.deleteGrn(id);
		map.addAttribute("message","Grn deleted with Id:"+id);
		List<Grn> list=service.getAllGrns();
		map.addAttribute("list",list);
		return "GrnData";
	}

	@RequestMapping("/edit")
	public String editGrn(@RequestParam Integer id, Model map) {
		Grn ob=service.getOneGrn(id);
		map.addAttribute("grn",ob);
		addCommonUi(map);
		return "GrnEdit";
	}

	@RequestMapping("/all")
	public String getAllGrns(Model map) {
		List<Grn> list=service.getAllGrns();
		map.addAttribute("list",list);
		return "GrnData";
	}
	
	//------ Screen#2 Methods-------------------------
	public void mapPoDtlToGrnDtlAndSave(Grn grn) {
		//1. Get PO ID by using Grn link
		Integer poId=grn.getPo().getId();
		//2. load PO object by using PO ID
		PurchaseOrder po=poService.getOnePurchaseOrder(poId);
		//3. Read PO Dtls
		List<PurchaseDtl> poDtls=po.getChilds();
		
		//4. one PoDtl data take into one GrnDtl and save in Db
		for(PurchaseDtl poDtl:poDtls) {
			GrnDtl grnDtl=new GrnDtl();
			grnDtl.setPartCode(poDtl.getPart().getPartCode());
			grnDtl.setBaseCost(poDtl.getPart().getBaseCost());
			grnDtl.setQnty(poDtl.getQty());
			grnDtl.setPartStatus("NONE");
			//link with Grn (Scrren#1)
			grnDtl.setGrn(grn);
			
			//finally save into DB
			service.saveGrnDtl(grnDtl);
			
		}
		
	}
	@RequestMapping("/viewGrnDtls")
	public String showGrnParts(
			@RequestParam("id")Integer id,
			Model model
			)
	{
		List<GrnDtl> grnDtls=service.getGrnDtlByGrnId(id);
		model.addAttribute("list", grnDtls);
		return "GrnParts";
	}
	
	@RequestMapping("/partStatus")
	public String updateDtlPartStatus(
			@RequestParam("id")Integer dtlId,
			@RequestParam("status")String status,
			@RequestParam("grnId")Integer grnId
			) 
	{
		service.updateGrnDtlPartStatus(dtlId, status);
		
		return "redirect:viewGrnDtls?id="+grnId;
	}
	
}


