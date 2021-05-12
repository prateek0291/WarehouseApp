package in.nit.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.nit.model.WhUserType;
import in.nit.service.IWhUserTypeService;
import in.nit.util.EmailUtil;

/**
 * @author:RAGHU SIR 
 *  Generated F/w:SHWR-Framework 
 */
@Controller
@RequestMapping("/whusertype")
public class WhUserTypeController {
	@Autowired
	private IWhUserTypeService service;
	@Autowired
	private EmailUtil emailUtil;

	@RequestMapping("/register")
	public String regWhUserType(ModelMap map) {
		map.addAttribute("whusertype",new WhUserType());
		return "WhUserTypeRegister";
	}

	@RequestMapping(
			value = "/save",
			method = POST
			)
	public String saveWhUserType(
			@ModelAttribute WhUserType whusertype,

			ModelMap map) {
		Integer id=service.saveWhUserType(whusertype);
		final String message="WhUserType created with Id:"+id;


		new Thread() {
			public void run() {
				if(id!=null) { //send email
					String text="Welcome to WhUser ="+whusertype.getUserCode()
					+", type="+whusertype.getUserType()
					+", All the best!!";

					emailUtil.sendEmail(
							whusertype.getUserEmail(), 
							"Welcome WhUser", 
							text,null);
				}
			}
		}.start();

		map.addAttribute("message",message);
		map.addAttribute("whusertype",new WhUserType()) ;
		return "WhUserTypeRegister";
	}

	@RequestMapping(
			value = "/update",
			method = POST
			)
	public String updateWhUserType(@ModelAttribute WhUserType whusertype, ModelMap map) {
		service.updateWhUserType(whusertype);
		map.addAttribute("message","WhUserType updated");
		List<WhUserType> list=service.getAllWhUserTypes();
		map.addAttribute("list",list);
		return "WhUserTypeData";
	}

	@RequestMapping("/delete")
	public String deleteWhUserType(@RequestParam Integer id, ModelMap map) {
		service.deleteWhUserType(id);
		map.addAttribute("message","WhUserType deleted with Id:"+id);
		List<WhUserType> list=service.getAllWhUserTypes();
		map.addAttribute("list",list);
		return "WhUserTypeData";
	}

	@RequestMapping("/edit")
	public String editWhUserType(@RequestParam Integer id, ModelMap map) {
		WhUserType ob=service.getOneWhUserType(id);
		map.addAttribute("whusertype",ob);
		return "WhUserTypeEdit";
	}

	@RequestMapping("/all")
	public String getAllWhUserTypes(ModelMap map) {
		List<WhUserType> list=service.getAllWhUserTypes();
		map.addAttribute("list",list);
		return "WhUserTypeData";
	}
}
