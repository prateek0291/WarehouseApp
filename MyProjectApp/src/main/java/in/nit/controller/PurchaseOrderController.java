package in.nit.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import in.nit.model.PurchaseDtl;
import in.nit.model.PurchaseOrder;
import in.nit.service.IPartService;
import in.nit.service.IPurchaseOrderService;
import in.nit.service.IShipmentTypeService;
import in.nit.service.IWhUserTypeService;
import in.nit.util.CommonUtil;
import in.nit.view.VendorInvoicePdf;

@Controller
@RequestMapping("/po")
public class PurchaseOrderController {
	@Autowired
	private IPurchaseOrderService service;
	
	@Autowired
	private IPartService partService;
	@Autowired
	private IShipmentTypeService shipmentService;
	@Autowired
	private IWhUserTypeService whUserTypeService;

	private void commonUi(Model model) {
		List<Object[]> shipList=shipmentService.getShipmentIdAndCode();
		Map<Integer,String> shipMap=CommonUtil.convert(shipList);
		model.addAttribute("shipMap", shipMap);
		
		List<Object[]> whVenList= whUserTypeService.getWhUserTypeIdAndCode("Vendor");
		Map<Integer,String> whVenMap=CommonUtil.convert(whVenList);
		model.addAttribute("whVenMap", whVenMap);
		
	}


	//1. show Reg Page
	@RequestMapping("/register")
	public String showPage(Model model) {
		PurchaseOrder po=new PurchaseOrder();
		po.setStatus("OPEN");
		model.addAttribute("purchaseOrder",po);

		//for DropDown
		commonUi(model);
		return "PurchaseOrderRegister";
	}

	//2. read form data and save
	@RequestMapping(value = "/save",method = POST)
	public String save(
			@ModelAttribute PurchaseOrder purchaseOrder,
			Model model) 
	{

		int id=service.savePurchaseOrder(purchaseOrder);
		model.addAttribute("message","saved with id:"+id);

		//clear form backing object
		PurchaseOrder po=new PurchaseOrder();
		po.setStatus("OPEN");
		model.addAttribute("purchaseOrder",po);

		//for DropDown
		commonUi(model);
		return "PurchaseOrderRegister";
	}
	
	@RequestMapping("/all")
	public String showAll(Model model) {
		List<PurchaseOrder> poList=service.getAllPurchaseOrders();
		model.addAttribute("list", poList);
		return "PurchaseOrderData";
	}

	///**********************************************************///
	///-----------------------------SCREEn #2 CODING ------------///
	///**********************************************************///
	@RequestMapping("/parts")
	public String showChilds(
			@RequestParam("poId")Integer poId,
			Model model
			) 
	{
		//1.get Purchase Order Data
		PurchaseOrder po=service.getOnePurchaseOrder(poId);
		model.addAttribute("parent", po);
		
		
		//2.Form Backing Object
		PurchaseDtl purchaseDtl=new PurchaseDtl();
		model.addAttribute("purchaseDtl", purchaseDtl);
		
		//3.DropDown Data
		List<Object[]> partsList=partService.getPartIdAndCodes();
		Map<Integer,String> partsMap=CommonUtil.convert(partsList);
		model.addAttribute("partsMap", partsMap);
		
		//4.Display all Parts which are added for this PO
		List<PurchaseDtl> childs=po.getChilds();
		int i=1; //slno setting
		for(PurchaseDtl dtl:childs) {
			dtl.setSlno(i++);
		}
		model.addAttribute("childs", childs);
		
		
		return "PurchaseParts";
	}
	
	//on click add part button
	@RequestMapping(value = "/addPart",method = RequestMethod.POST)
	public String addPart(@ModelAttribute PurchaseDtl purchaseDtl) {
		service.savePurchaseDtl(purchaseDtl);
		Integer poId=purchaseDtl.getPo().getId();
		
		//at least one row added then status is picking
		service.updatePoStatus(poId, "PICKING");
		
		return "redirect:parts?poId="+poId;
	}
	
	//delete part by Id and send to PurchaseParts with parentId(poId)
	@RequestMapping("/removePart")
	public String deletePart(
			@RequestParam("dtlId")Integer dtlId,
			@RequestParam("poId")Integer poId
			) 
	{
		service.deletePurchaseDtl(dtlId);
		if(service.getOnePurchaseOrder(poId).getChilds().isEmpty()) {
			service.updatePoStatus(poId, "OPEN");
		}
		return "redirect:parts?poId="+poId;
	}
	
	@RequestMapping("/placeOrder")
	public String placeOrderConfirm(
			@RequestParam("poId")Integer poId
			) 
	{
		service.updatePoStatus(poId, "ORDERED");
		return "redirect:parts?poId="+poId;
	}
	
	@RequestMapping("/invoceOrder")
	public String placeOrderForInvoice(
			@RequestParam("poId")Integer poId
			) 
	{
		service.updatePoStatus(poId, "INVOICED");
		return "redirect:all";
	}
	
	//------------Generate PDF--------------
	@RequestMapping("/downloadInvoce")
	public ModelAndView downloadInvoiceAsPdf(
			@RequestParam("poId")Integer id
			)
	{
		PurchaseOrder po=service.getOnePurchaseOrder(id);
		ModelAndView m=new ModelAndView();
		m.setView(new VendorInvoicePdf());
		m.addObject("po", po);
		return m;
	}
	
}


