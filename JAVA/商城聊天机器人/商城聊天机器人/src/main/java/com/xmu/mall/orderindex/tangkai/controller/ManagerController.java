package com.xmu.mall.orderindex.tangkai.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xmu.mall.orderindex.tangkai.service.UserService;

@Controller
@RequestMapping(value="/manager")
public class ManagerController {
	@Autowired
	@Qualifier("tangkai_UserService")
	private UserService userService;
	
	@RequestMapping("login")
	public String managerLogin(Model model)
	{
		model.addAttribute("result", "");
		return "tangkai/manager_login";
	}
	
	@RequestMapping("check")
	public String checkManager(Model model, HttpServletRequest request)
	{
		String manager_id = request.getParameter("manager_id");
		String psw = request.getParameter("psw");
		if(userService.checkManager(manager_id, psw) == 0) {
			model.addAttribute("result", "管理员id或密码不正确，请重试！");
			return "tangkai/manager_login";
		}
		
		request.getSession().setAttribute("manager_id", manager_id);
		return "tangkai/manager_success";
	}
	
}
