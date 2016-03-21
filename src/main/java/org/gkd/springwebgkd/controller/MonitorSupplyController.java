package org.gkd.springwebgkd.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.gkd.springwebgkd.service.TagLpbService;
import org.gkd.springwebgkd.service.WhsSupplyScanService;
import org.gkd.springwebgkd.bean.TagLpb;
import org.gkd.springwebgkd.bean.WhsSupplyScan;
import org.gkd.springwebgkd.common.controller.AbstractController;

@Controller
@RequestMapping(value = "/monsupply")
public class MonitorSupplyController extends AbstractController {

    private static final String MAIN_ENTITY_NAME = "whsSupplyScanEntity";
    private static final String TAG_ENTITY_NAME = "tagLpbEntitiy";
    private String openPeriod;

    public MonitorSupplyController() {
        super(MonitorSupplyController.class, MAIN_ENTITY_NAME);
        log("SupplyController created.");
    }
    
    @Resource
    private WhsSupplyScanService whsSupplyScanService;
    
    @Resource
    private TagLpbService tagLpbService;
    
    @RequestMapping(value = "/findnpk", method = RequestMethod.GET)
	public @ResponseBody String findName(@RequestParam(value = "npk", required = true) String npk) {

		return tagLpbService.getNameNpk(npk);
	}

    @RequestMapping(value = {"","/","/list"}, method = RequestMethod.GET)
    public ModelAndView listOfSupply() {
        log("Action 'list'");
        WhsSupplyScan whsSupplyScan = new WhsSupplyScan();
        TagLpb tagLpb = new TagLpb();
        openPeriod = "Open Period : "+whsSupplyScanService.openPeriodeBpb();
//        System.out.println("Login as "+princ.getName());
//        List<WhsSupplyScan> list = whsSupplyScanService.findAll();
        ModelAndView modelAndView = new ModelAndView("supply/supply-list");
//        modelAndView.addObject(MAIN_LIST_NAME, list);
        modelAndView.addObject(MAIN_ENTITY_NAME, whsSupplyScan);
        modelAndView.addObject(TAG_ENTITY_NAME, tagLpb);
        modelAndView.addObject("openPeriod", openPeriod);
        return modelAndView;
    }
    
    @RequestMapping(value = "supplylist.json", method = RequestMethod.GET)
    public @ResponseBody List<Object[]> findWhsSupplyScan(@RequestParam(value = "kdGudang", required = true) String kdGudang, 
    		@RequestParam(value = "bulan") String bulan, @RequestParam(value = "tahun") String tahun) {

        log("Supply/findBarcode-GET:  scan today");
        
//        Map<String, Object> filters = new HashMap<>();
//		filters.put("kdGudang =", kdGudang);
//		filters.put("bulan =", bulan);
//		filters.put("tahun =", tahun);

        List<Object[]> whsSupplyScans = whsSupplyScanService.findItemScan(bulan, tahun, kdGudang);
        if(whsSupplyScans == null){
        	whsSupplyScans = new ArrayList<>();
        }
        return whsSupplyScans;
    }

    public WhsSupplyScanService getWhsSupplyScanService() {
        return whsSupplyScanService;
    }

    public void setWhsSupplyScanService(WhsSupplyScanService whsSupplyScanService) {
        this.whsSupplyScanService = whsSupplyScanService;
    }

	public TagLpbService getTagLpbService() {
		return tagLpbService;
	}

	public void setTagLpbService(TagLpbService tagLpbService) {
		this.tagLpbService = tagLpbService;
	}

}
