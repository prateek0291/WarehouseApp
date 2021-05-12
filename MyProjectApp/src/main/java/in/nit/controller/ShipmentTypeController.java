package in.nit.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import in.nit.model.ShipmentType;
import in.nit.service.IShipmentTypeService;
import in.nit.util.ShipmentTypeUtil;
import in.nit.view.ShipmentTypeExcelView;
import in.nit.view.ShipmentTypePdfView;

@Controller
@RequestMapping("/shipment")
public class ShipmentTypeController {
	@Autowired
	private IShipmentTypeService service;
	
	@Autowired
	private ServletContext context;
	
	@Autowired
	private ShipmentTypeUtil util;
	
	/** 1.
	 *  This method is used to display
	 *  ShipmentType Register page
	 *  on URL : /register , GET
	 */
	//public  String <anyMethodName>(){ return "PageName"; }

	@RequestMapping("/register") //GET
	public String showRegPage(Model model) {
		//form backing object
		model.addAttribute("shipmentType", new ShipmentType());
		return "ShipmentTypeRegister";
	}
	
	/**
	 * 2. Read Form Data as ModelAttribute
	 *    call service layer save method
	 *    Return Message back to UI
	 *    Page : ShipmentTypeRegister
	 *    URL : /save , Type: POST
	 */
	@RequestMapping(value = "/save",
			method = RequestMethod.POST)
	public String saveShipment(
			@ModelAttribute ShipmentType shipmentType,
			Model model) 
	
	{
		Integer id=service.saveShipmentType(shipmentType);
		String message="Shipment Type '"+id+"' saved";
		model.addAttribute("message", message);
		//form backing object
		model.addAttribute("shipmentType", new ShipmentType());
		return "ShipmentTypeRegister";
	}
	
	/***
	 * 3. On enter URL : /all (GET)
	 * this method will fetch all rows as 
	 * List<T> and sends to UI
	 */
	@RequestMapping("/all") //GET
	public String getAllShipmentTypes(Model model)
	{
		List<ShipmentType> list=service.getAllShipmentTypes();
		model.addAttribute("list", list);
		model.addAttribute("opr",null);
		return "ShipmentTypeData";
	}
	
	/**
	 * 4. Read one ID from URL using
	 *  RequestParam("key") and remove
	 *  row based on ID.
	 *  Send new data to UI using
	 *  service.getAll__() with Model Help
	 *  URL : /delete, type: GET
	 *  method : deleteShipmentType
	 * */
	@RequestMapping("/delete")
	public String deleteShipment(
			@RequestParam("sid")Integer id,
			Model model)
	{
		service.deleteShipmentType(id);
		String message="Shipment '"+id+"' Deleted";
		model.addAttribute("message", message);
		model.addAttribute("opr","DEL");
		
		//fetch new data
		List<ShipmentType> list=service.getAllShipmentTypes();
		model.addAttribute("list", list);
		
		return "ShipmentTypeData"; //pageName
	}
	
	/***
	 * 5. Show Edit Page on click link
	 *    EDIT (URL : /edit?sid=__ , GET)
	 *    Read id using @ReqParam
	 *    method: showEditPage
	 *    View : ShipmentTypeEdit
	 */
	@RequestMapping("/edit")
	public String showEditPage(
			@RequestParam("sid")Integer id,
			Model model)
	{
		ShipmentType st=service.getOneShipmentType(id);
		model.addAttribute("shipmentType", st);
		return "ShipmentTypeEdit";
	}
	
	
	/**
	 * 6. On click Form submit UPDATE
	 * Read Form Data as ModelAttribute
	 * call service update method
	 * URL : /update, TYPE:POST
	 * method: updateShipmentType
	 * View: ShipmentTypeData
	 *   with all shipment types
	 */
	@RequestMapping(value = "/update",
			method = RequestMethod.POST)
	public String updateShipmentType(
			@ModelAttribute ShipmentType shipmentType,
			Model model) 
	{
		service.updateShipmentType(shipmentType);
		String message="ShipmentType '"+shipmentType.getShipId()+"' Updated";
		model.addAttribute("message", message);
		List<ShipmentType> list=service.getAllShipmentTypes();
		model.addAttribute("list", list);
		model.addAttribute("opr","UPDATE");
		return "ShipmentTypeData";
	}
	
	
	/**
	 * 7. Display One ShipmentType data on
	 * click 'View' Hyper Link
	 * URL : /view?sid=10 , GET
	 * showOneShipment(), ShipmentTypeView.jsp
	 * send data to UI using Model
	 */
	@RequestMapping("/view")
	public String showOneShipment(
			@RequestParam("sid")Integer id,
			Model model) 
	{
		ShipmentType st=service.getOneShipmentType(id);
		model.addAttribute("ob", st);
		return "ShipmentTypeView";
	}
	
	/**
	 * 8. Export DB data to Excel
	 * 
	 */
	@RequestMapping("/excel")
	public ModelAndView showExcel(
			@RequestParam(value = "id",required = false)Integer id
			) 
	{
		ModelAndView m=new ModelAndView();
		m.setView(new ShipmentTypeExcelView());
		if(id==null) {
			//export all rows
			List<ShipmentType> list=service.getAllShipmentTypes();
			m.addObject("list", list);
		}else {
			//export one row by id
			ShipmentType st=service.getOneShipmentType(id);
			m.addObject("list", Arrays.asList(st));
			
		}
		return m;
	}
	
	/**
	 * 9. Export to PDF File
	 */
	@RequestMapping("/pdf")
	public ModelAndView showPdf(
			@RequestParam(value = "id", required = false)Integer id
			) 
	{
		ModelAndView m=new ModelAndView();
		m.setView(new ShipmentTypePdfView());
		if(id==null) { //show all
			List<ShipmentType> list=service.getAllShipmentTypes();
			m.addObject("list", list);
		}else { //show one
			ShipmentType st=service.getOneShipmentType(id);
			m.addObject("list", Arrays.asList(st));
		}
		return m;
	}
	
	/**
	 * 10. Show Charts
	 */
	@RequestMapping("/charts")
	public String showCharts() {
		List<Object[]> list=service.getShipmentModeCount();
		String path=context.getRealPath("/");
		util.generatePie(path, list);
		util.generateBar(path, list);
		return "ShipmentTypeCharts";
	}
	
	
	
	
}


