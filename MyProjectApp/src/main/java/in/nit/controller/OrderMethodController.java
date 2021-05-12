package in.nit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import in.nit.model.OrderMethod;
import in.nit.service.IOrderMethodService;

@Controller
@RequestMapping("/ordermethod")
public class OrderMethodController {
	@Autowired
	private IOrderMethodService service;
	
	//1. show Reg page
	@RequestMapping("/register")
	public String showReg(Model model) {
		model.addAttribute("orderMethod", new OrderMethod());
		return "OrderMethodRegister";
	}
	//2. on click submit save data
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public String saveData(
			@ModelAttribute OrderMethod orderMethod,
			Model model)
	{
		Integer id=service.saveOrderMethod(orderMethod);
		model.addAttribute("message", "saved with :"+id);
		//clear form
		model.addAttribute("orderMethod", new OrderMethod());
		return "OrderMethodRegister";
	}
	
	//3. display all rows
	@RequestMapping("/all")
	public String showAll(Model model) {
		List<OrderMethod> list=service.getAllOrderMethods();
		model.addAttribute("list", list);
		return "OrderMethodData";
	}
	//4. delete one row by id
	@RequestMapping("/delete")
	public String deleteData(
			@RequestParam("oid")Integer id,
			Model model)
	{
		service.deleteOrderMethod(id);
		model.addAttribute("message", "Deleted "+id);
		//show new data
		List<OrderMethod> list=service.getAllOrderMethods();
		model.addAttribute("list", list);
		return "OrderMethodData";
	}
	
	//5. view one row
	@RequestMapping("/view")
	public String showOne(
			@RequestParam("oid")Integer id,
			Model model)
	{
		OrderMethod om=service.getOneOrderMethod(id);
		model.addAttribute("ob", om);
		return "OrderMethodView";
	}
	
	//6. show edit page
	@RequestMapping("/edit")
	public String showEdit(
			@RequestParam("oid")Integer id,
			Model model	)
	{
		OrderMethod om=service.getOneOrderMethod(id);
		model.addAttribute("orderMethod", om);
		return "OrderMethodEdit";
	}
	//7. update data on click submit
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	public String updateData(
			@ModelAttribute OrderMethod orderMethod,
			Model model) 
	{
		service.updateOrderMethod(orderMethod);
		model.addAttribute("message", "updated "+orderMethod.getOrderId());
		//show new data
		List<OrderMethod> list=service.getAllOrderMethods();
		model.addAttribute("list", list);
		return "OrderMethodData";
	}
	
	
	
	
	
	
}
