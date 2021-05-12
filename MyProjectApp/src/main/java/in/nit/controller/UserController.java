package in.nit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.nit.model.User;
import in.nit.service.IUserService;
import in.nit.util.EmailUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService service;
	@Autowired
	private EmailUtil emailUtil;
	
	//1. Show User Reg page
	@RequestMapping("/register")
	public String showReg() {
		return "UserRegister";
	}
	//2. on click submit save user in DB table
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public String saveUser(
			@ModelAttribute User user,
			Model model
			) 
	{
		String pwd=user.getUserPwd();

		Integer id=service.saveUser(user);
		if(id!=null) {
			model.addAttribute("message", "User '"+user.getUserName()+"' Registered!");
			String text="<h3>Welcome to: "+user.getUserName()
				+"</h3>,<b> Your Login Details are:"+user.getUserEmail()+"/"+pwd
				+"</b>, Your Roles are: "+user.getRoles()
				+"  <i>All the best!!</i>";
			emailUtil.sendEmail(user.getUserEmail(), "Welcome to User!", text);
		}
		
		return "UserRegister";
	}
	@RequestMapping("/login")
	public String showLogin() {
		return "UserLogin";
	}
	
	
}






