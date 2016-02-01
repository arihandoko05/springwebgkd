package org.gkd.springwebgkd.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Principal principal) {
//		System.out.println("Nama : "+ principal.getName());
		return principal != null ? "home/home" : "home/superhome";
	}
	
	@RequestMapping(value = "tablex")
	public String tables() {
        return "basic_table";
    }
}
