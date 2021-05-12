package in.nit.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import in.nit.model.Uom;
import in.nit.service.IUomService;

/**
 * @author:RAGHU SIR 
 *  Generated F/w:SHWR-Framework 
 */
@Controller
@RequestMapping("/uom")
@SessionAttributes("userName")
public class UomController {

	private static Logger log=Logger.getLogger(UomController.class);

	@Autowired
	private IUomService service;

	@RequestMapping("/setup")
	public String setup(Principal p,HttpSession ses,Model model) {
		model.addAttribute("userName", p.getName());
		ses.setAttribute("loginDate", new Date());
		return "redirect:register";
	}


	@RequestMapping("/register")
	public String regUom(ModelMap map) {
		map.addAttribute("uom",new Uom());
		return "UomRegister";
	}

	@RequestMapping(value = "/save",method = POST)
	public String saveUom(@ModelAttribute Uom uom, ModelMap map) {
		log.info("Entered into method");
		try {
			Integer id=service.saveUom(uom);
			log.debug("Uom saved with Id:"+id);
			map.addAttribute("message","Uom created with Id:"+id);
			log.info("Uom Formbaking object is about to set");
			map.addAttribute("uom",new Uom()) ;
		} catch (Exception e) {
			log.error("Problem in saving Uom"+e.getMessage());
			e.printStackTrace();
		}
		log.info("Moveing to UI to render message");
		return "UomRegister";
	}

	@RequestMapping(
			value = "/update",
			method = POST
			)
	public String updateUom(@ModelAttribute Uom uom, ModelMap map) {
		log.info("Entered into method");
		try {
			log.info("About call service update");
			service.updateUom(uom);
			log.debug("Uom updated with id"+uom.getId());
			map.addAttribute("message","Uom updated");
			
			List<Uom> list=service.getAllUoms();
			map.addAttribute("list",list);
			
			log.debug("Uom Updated details"+uom);
		} catch (Exception e) {
			log.error("Uom unable to update with id:"+uom.getId());
			e.printStackTrace();
		}
		log.info("Exist for UI Render..");
		return "UomData";
	}

	@RequestMapping("/delete")
	public String deleteUom(@RequestParam Integer id, ModelMap map) {
		log.info("Entered into method");
		try {
			log.info("About to call delete operation");
			service.deleteUom(id);
			log.debug("Uom deleted with id:"+id);
			
			log.info("Fetching new data for DATA page");
			map.addAttribute("message","Uom deleted with Id:"+id);
			List<Uom> list=service.getAllUoms();
			map.addAttribute("list",list);
		} catch (Exception e) {
			log.debug("unble to delete Uom with id:"+id);
			e.printStackTrace();
		}
		log.info("Exist for UI Render");
		return "UomData";
	}

	@RequestMapping("/edit")
	public String editUom(@RequestParam Integer id, ModelMap map) {
		Uom ob=service.getOneUom(id);
		map.addAttribute("uom",ob);
		return "UomEdit";
	}

	@RequestMapping("/all")
	public String getAllUoms(ModelMap map) {
		List<Uom> list=service.getAllUoms();
		map.addAttribute("list",list);
		return "UomData";
	}
	//---------AJAX CALLS CHECK----------------
	@RequestMapping("/checkmodel")
	public @ResponseBody String validateUomModel(
			@RequestParam("uomModel")String uomModel
			) 
	{
		System.out.println("welcome to AJAX Call");
		String msg="";
		if(service.isUomModelExist(uomModel)) {
			msg="Uom '"+uomModel+"' Model already exist";
		}
		return msg;
	}
	
	
	
	
	
	
	
	
	
}
