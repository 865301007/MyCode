package com.wcf.SpringHibernate.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wcf.SpringHibernate.service.UserService;

//����һ��������
@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private UserService userService;
	//����ԡ�/����GET����
	@RequestMapping(method=RequestMethod.GET)
	public String home(Model model)
	{
		model.addAttribute("username",this.userService.getUserNameById(1));
		return "home";
	}
}