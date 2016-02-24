package org.gkd.springwebgkd.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@RequestMapping(value = "/supply")
public class SupplyController extends AbstractController {

    private static final String MAIN_ENTITY_NAME = "whsSupplyScanEntity";
    private static final String MAIN_LIST_NAME = "WhsSupplyScanEntities";
    private static final String TAG_ENTITY_NAME = "tagLpbEntitiy";
    private String openPeriod;

    public SupplyController() {
        super(SupplyController.class, MAIN_ENTITY_NAME);
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
        openPeriod = "Open Period : "+whsSupplyScanService.openPeriodeLpb();
//        List<WhsSupplyScan> list = whsSupplyScanService.findAll();
        ModelAndView modelAndView = new ModelAndView("supply/supply-list");
//        modelAndView.addObject(MAIN_LIST_NAME, list);
        modelAndView.addObject(MAIN_ENTITY_NAME, whsSupplyScan);
        modelAndView.addObject(TAG_ENTITY_NAME, tagLpb);
        modelAndView.addObject("openPeriod", openPeriod);
        return modelAndView;
    }
    
    @RequestMapping(value = "supplylist.json", method = RequestMethod.GET)
    public @ResponseBody List<WhsSupplyScan> findWhsSupplyScan(@RequestParam(value = "kdGudang", required = true) String kdGudang) {

        log("Supply/findBarcode-GET:  scan today");
        
        Map<String, Object> filters = new HashMap<>();
		filters.put("kdGudang =", kdGudang);
		filters.put("tanggalTrx =", new Date());
		filters.put("stInout =", "OUT");
		filters.put("noBpb", null);

        List<WhsSupplyScan> whsSupplyScans = whsSupplyScanService.search(filters, 10);
        if(whsSupplyScans == null){
        	whsSupplyScans = new ArrayList<>();
        }
        return whsSupplyScans;
    }

    @RequestMapping(value = "/fi", method = RequestMethod.GET)
    public @ResponseBody TagLpb findBarcodePage(@RequestParam(value = "noReg", required = true) String noReg, 
    		@RequestParam(value = "kdGudang", required = true) String kdGudang) {

        log("Supply/findBarcode-GET:  ID to query = " + noReg);

//        if(!"".equals(id)){
//        	id = id.replaceAll("%2F", "/");
//        }
        TagLpb tagLpb = new TagLpb();
        Map<String, Object> filter = new HashMap<>();
    	filter.put("noBarcode =", noReg);
    	filter.put("kdGudang =", kdGudang);
    	filter.put("tanggalTrx =", new Date());
    	filter.put("stInout =", "OUT");
    	filter.put("noBpb", null);
    	List<WhsSupplyScan> whsSupplyScans = whsSupplyScanService.search(filter, 0);
    	if(whsSupplyScans.isEmpty()){
    		tagLpb = tagLpbService.findById(noReg);
            if(tagLpb == null){
            	tagLpb = new TagLpb();
            } else {
    			Date dtperiod = tagLpbService.openPeriodeBpb();
    			BigDecimal qtyScan = tagLpbService.getQtyMutasiTag(DTFORMAT_YYYY.format(dtperiod), DTFORMAT_MM.format(dtperiod), kdGudang, noReg);
    			if (qtyScan != null){
    				tagLpb.setQtyAkhir(qtyScan);
    			}
    		}
    	}
        
        return tagLpb;
    }
    
    @RequestMapping(value = "/pushe", method = RequestMethod.POST)
    public @ResponseBody void saveData(@RequestBody TagLpb tagLpb, @RequestParam(value = "kdGudang", required = true) String kdGudang) {
    	WhsSupplyScan whsSupplyScan = new WhsSupplyScan();
        if (tagLpb != null){
        	BigDecimal kdTrx = whsSupplyScanService.getMaxId();
        	if(kdTrx != null){
        		whsSupplyScan.setKdTrx(kdTrx);
        		whsSupplyScan.setNoBarcode(tagLpb.getNoReg());
            	whsSupplyScan.setQtyBpb(tagLpb.getQtyAkhir());
            	whsSupplyScan.setQtySupply(tagLpb.getQtyAkhir());
            	whsSupplyScan.setQtyRetur(BigDecimal.ZERO);
            	whsSupplyScan.setStInout("OUT");
            	whsSupplyScan.setBulan((new SimpleDateFormat("MM")).format(new Date()));
            	whsSupplyScan.setTahun((new SimpleDateFormat("yyyy")).format(new Date()));
            	whsSupplyScan.setTanggalTrx(new Date());
            	whsSupplyScan.setKdGudang(kdGudang);
            	whsSupplyScan.setCreateby("web");
            	whsSupplyScan.setCreatedate(new Date());
            	
            	whsSupplyScanService.create(whsSupplyScan);
            	log("Supply/saveData-GET:  whsSupplyScan = " + whsSupplyScan.toString());
                String message = "Supply " + whsSupplyScan.getNoBarcode() + " was successfully Saved";
//                modelAndView.addObject("message", message);
        	}
        }
        
    }

    @RequestMapping(value = "/sedup", method = RequestMethod.GET)
    public @ResponseBody List<WhsSupplyScan> findDup(@RequestParam(value = "id", required = true) String id) {
    	Map<String, Object> filter = new HashMap<>();
    	filter.put("noBarcode =", id);
    	filter.put("tanggalTrx =", new Date());
    	filter.put("stInout =", "OUT");
    	List<WhsSupplyScan> whsSupplyScans = whsSupplyScanService.search(filter, 0);
        return whsSupplyScans;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editingSupply(@ModelAttribute WhsSupplyScan whsSupplyScan,
            @RequestParam(value = "action", required = true) String action) {

        ModelAndView modelAndView = new ModelAndView("redirect:/supply/list");
        String message = null;

        if (action.equals("save")) {

            whsSupplyScanService.update(whsSupplyScan);
            log("Supply/edit-POST:  " + whsSupplyScan.toString());
            message = "Supply " + whsSupplyScan.getNoBarcode() + " was successfully edited";
            modelAndView.addObject("message", message);
        }

        if (action.equals("cancel")) {
            message = "Supply " + whsSupplyScan.getNoBarcode() + " edit cancelled";
        }

        return modelAndView;

    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView deleteSupplyPage(@RequestParam(value = "id", required = true) BigDecimal id,
            @RequestParam(value = "phase", required = true) String phase) {

        WhsSupplyScan whsSupplyScan = whsSupplyScanService.findById(id);
        log("Supply/delete-GET | id = " + id + " | phase = " + phase + " | " + whsSupplyScan.toString());
        ModelAndView modelAndView = null;

        if (phase.equals("stage")) {
            modelAndView = new ModelAndView("supply/supply-delete");

            String message = "Supply " + whsSupplyScan.getKdTrx() + " queued for display.";
            modelAndView.addObject("whsSupplyScan", whsSupplyScan);
            modelAndView.addObject("message", message);
        }

        if (phase.equals("confirm")) {
            modelAndView = new ModelAndView("redirect:/supply/list");
            whsSupplyScanService.delete(whsSupplyScan, id);
            String message = "Supply " + whsSupplyScan.getKdTrx() + " was successfully deleted";
            modelAndView.addObject("message", message);
        }

        if (phase.equals("cancel")) {
            modelAndView = new ModelAndView("redirect:/supply/list");
            String message = "Supply delete was cancelled.";
            modelAndView.addObject("message", message);
        }

        return modelAndView;
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
