package com.nitin.roomate;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RoomateController {
	
	private AtomicLong counter = new AtomicLong();
	private String template = "Hello, %s";
	
	@RequestMapping("/hello")
	@ResponseBody
	public Roomate hello(@RequestParam(name = "name", defaultValue = "World!")
			String name) {
		return new Roomate(counter.incrementAndGet(), String.format(template, name));
	}

	@RequestMapping("/")
	public String root() {
		return "redirect:/index";
	}
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/user/index")
	public String userIndex() {
		return "user/index";
	}
	
	@RequestMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		
		return "login";
	}
}
