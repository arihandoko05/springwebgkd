package org.gkd.springwebgkd.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;

import javax.annotation.Resource;

import org.gkd.springwebgkd.bean.TagLpb;
import org.gkd.springwebgkd.common.controller.AbstractController;
import org.gkd.springwebgkd.service.TagLpbService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController extends AbstractController {
	
	public HomeController() {
		super(HomeController.class, "home");
		log("HomeController created.");
	}

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
	
	@RequestMapping(value = "/fi", method = RequestMethod.GET)
	public @ResponseBody TagLpb findBarcodePage(@RequestParam(value = "noReg", required = true) String noReg,
			@RequestParam(value = "kdGudang", required = true) String kdGudang) {

		log("SPAG/findBarcode-GET:  ID to query = " + noReg);

		TagLpb tagLpb = new TagLpb();
		tagLpb = tagLpbService.findById(noReg);
		if (tagLpb == null) {
			tagLpb = new TagLpb();
		} else {
			Date dtperiod = tagLpbService.openPeriodeBpb();
			BigDecimal qtyScan = tagLpbService.getQtyMutasiTag(DTFORMAT_YYYY.format(dtperiod), DTFORMAT_MM.format(dtperiod), kdGudang, noReg);
			if (qtyScan != null){
				tagLpb.setQtyAkhir(qtyScan);
			}
		}
		
		return tagLpb;
	}
}
