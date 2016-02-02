package org.gkd.springwebgkd.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;

import javax.annotation.Resource;

import org.gkd.springwebgkd.bean.TagLpb;
import org.gkd.springwebgkd.service.TagLpbService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
	
	@Resource
	private TagLpbService tagLpbService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Principal principal) {
//		System.out.println("Nama : "+ principal.getName());
		return principal != null ? "home/home" : "home/superhome";
	}
	
	@RequestMapping(value = "tablex")
	public String tables() {
        return "basic_table";
    }
	
	@RequestMapping(value = "/findnpk", method = RequestMethod.GET)
	public @ResponseBody String findName(@RequestParam(value = "npk", required = true) String npk) {

		return tagLpbService.getNameNpk(npk);
	}
}
