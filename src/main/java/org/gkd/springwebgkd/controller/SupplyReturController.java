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
import org.gkd.springwebgkd.bean.WhsStoScan;
import org.gkd.springwebgkd.bean.WhsSupplyScan;
import org.gkd.springwebgkd.common.controller.AbstractController;

@Controller
@RequestMapping(value = "/supplyret")
public class SupplyReturController extends AbstractController {

    private static final String MAIN_ENTITY_NAME = "whsSupplyScanEntity";
    private static final String MAIN_LIST_NAME = "WhsSupplyScanEntities";
    private static final String TAG_ENTITY_NAME = "tagLpbEntitiy";
    private String openPeriod;

    public SupplyReturController() {
        super(SupplyReturController.class, MAIN_ENTITY_NAME);
        log("SupplyReturController created.");
    }
    
    @Resource
    private WhsSupplyScanService whsSupplyScanService;
    
    @Resource
    private TagLpbService tagLpbService;
    

    @RequestMapping(value = {"","/","/list"}, method = RequestMethod.GET)
    public ModelAndView listOfSupply() {
        log("Action 'list'");
        WhsSupplyScan whsSupplyScan = new WhsSupplyScan();
        TagLpb tagLpb = new TagLpb();
        openPeriod = "Open Period : "+whsSupplyScanService.openPeriodeLpb();
//        List<WhsSupplyScan> list = whsSupplyScanService.findAll();
        ModelAndView modelAndView = new ModelAndView("supply/supply-retur");
//        modelAndView.addObject(MAIN_LIST_NAME, list);
        modelAndView.addObject(MAIN_ENTITY_NAME, whsSupplyScan);
        modelAndView.addObject(TAG_ENTITY_NAME, tagLpb);
        modelAndView.addObject("openPeriod", openPeriod);
        return modelAndView;
    }
    
    @RequestMapping(value = "supplyretlist.json", method = RequestMethod.GET)
    public @ResponseBody List<WhsSupplyScan> findWhsSupplyScan(@RequestParam(value = "kdGudang", required = true) String kdGudang) {

        log("SupplyRet/findBarcode-GET:  scan today");
        
		Map<String, Object> filters = new HashMap<>();
		filters.put("kdGudang =", kdGudang);
		filters.put("tanggalTrx =", new Date());
		filters.put("stInout =", "IN");

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

        TagLpb tagLpb = new TagLpb();
        Map<String, Object> filter = new HashMap<>();
    	filter.put("noBarcode =", noReg);
    	filter.put("kdGudang =", kdGudang);
    	filter.put("tanggalTrx =", new Date());
    	filter.put("stInout =", "IN");
    	List<WhsSupplyScan> whsSupplyScans = whsSupplyScanService.search(filter, 0);
    	if(whsSupplyScans.isEmpty()){
    		tagLpb = tagLpbService.findById(noReg);
            if(tagLpb == null){
            	tagLpb = new TagLpb();
            } else {
    			BigDecimal qtyScan = tagLpbService.getQtySupply(noReg, kdGudang);
    			if (qtyScan != null){
    				tagLpb.setQtySupply(qtyScan);
    			}
    		}
    	}
        
        return tagLpb;
    }
    
    @RequestMapping(value = "/pushe", method = RequestMethod.POST)
    public @ResponseBody void saveData(@RequestBody TagLpb tagLpb, @RequestParam(value = "kdGudang", required = true) String kdGudang, @RequestParam(value = "qtyRetur") BigDecimal qtyRetur) {
    	WhsSupplyScan whsSupplyScan = new WhsSupplyScan();
        if (tagLpb != null){
        	BigDecimal kdTrx = whsSupplyScanService.getMaxId();
        	if(kdTrx != null){
        		whsSupplyScan.setKdTrx(kdTrx);
        		whsSupplyScan.setNoBarcode(tagLpb.getNoReg());
        		BigDecimal qtyBpb = BigDecimal.ZERO;
        		if(tagLpb.getQtySupply() != null){
        			qtyBpb = tagLpb.getQtySupply().subtract(qtyRetur);
        		}
            	whsSupplyScan.setQtyBpb(qtyBpb);
            	whsSupplyScan.setQtySupply(BigDecimal.ZERO);
            	whsSupplyScan.setQtyRetur(qtyRetur);
            	whsSupplyScan.setStInout("IN");
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
    	filter.put("stInout =", "IN");
    	List<WhsSupplyScan> whsSupplyScans = whsSupplyScanService.search(filter, 0);
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