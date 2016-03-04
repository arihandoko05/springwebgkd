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
import org.gkd.springwebgkd.service.WhsSupplyScanxService;
import org.gkd.springwebgkd.bean.TagLpb;
import org.gkd.springwebgkd.bean.WhsSupplyScanx;
import org.gkd.springwebgkd.common.controller.AbstractController;

@Controller
@RequestMapping(value = "/supplyx")
public class SupplyxController extends AbstractController {

	private static final String MAIN_ENTITY_NAME = "whsSupplyScanxEntity";
	private static final String MAIN_LIST_NAME = "WhsSupplyScanxEntities";
	private static final String TAG_ENTITY_NAME = "tagLpbEntitiy";
	private String openPeriod;

	public SupplyxController() {
		super(SupplyxController.class, MAIN_ENTITY_NAME);
		log("SupplyController created.");
	}

	@Resource
	private WhsSupplyScanxService whsSupplyScanxService;

	@Resource
	private TagLpbService tagLpbService;

	@RequestMapping(value = "/findnpk", method = RequestMethod.GET)
	public @ResponseBody String findName(@RequestParam(value = "npk", required = true) String npk) {

		return tagLpbService.getNameNpk(npk);
	}

	@RequestMapping(value = { "", "/", "/list" }, method = RequestMethod.GET)
	public ModelAndView listOfSupply() {
		log("Action 'list'");
		WhsSupplyScanx whsSupplyScanx = new WhsSupplyScanx();
		TagLpb tagLpb = new TagLpb();
		openPeriod = "Open Period : " + whsSupplyScanxService.openPeriodeBpb();

		ModelAndView modelAndView = new ModelAndView("supply/supplyx");
		modelAndView.addObject(MAIN_ENTITY_NAME, whsSupplyScanx);
		modelAndView.addObject(TAG_ENTITY_NAME, tagLpb);
		modelAndView.addObject("openPeriod", openPeriod);
		return modelAndView;
	}

	@RequestMapping(value = "supplylist.json", method = RequestMethod.GET)
	public @ResponseBody List<WhsSupplyScanx> findWhsSupplyScan(
			@RequestParam(value = "kdGudang", required = true) String kdGudang) {

		log("Supply/findBarcode-GET:  scan today");

		Map<String, Object> filters = new HashMap<>();
		filters.put("kdGudang =", kdGudang);
		filters.put("tanggalTrx =", new Date());
		filters.put("noBpb", null);

		List<WhsSupplyScanx> whsSupplyScans = whsSupplyScanxService.search(filters, 10);
		if (whsSupplyScans == null) {
			whsSupplyScans = new ArrayList<>();
		}
		return whsSupplyScans;
	}

	@RequestMapping(value = "/fi", method = RequestMethod.GET)
	public @ResponseBody TagLpb findBarcodePage(@RequestParam(value = "noReg", required = true) String noReg,
			@RequestParam(value = "kdGudang", required = true) String kdGudang) {

		log("Supply/findBarcode-GET:  ID to query = " + noReg);

		// if(!"".equals(id)){
		// id = id.replaceAll("%2F", "/");
		// }
		TagLpb tagLpb = new TagLpb();
		Map<String, Object> filter = new HashMap<>();
		filter.put("noBarcode =", noReg);
		filter.put("kdGudang =", kdGudang);
		filter.put("tanggalTrx =", new Date());
		filter.put("noBpb", null);
		List<WhsSupplyScanx> whsSupplyScans = whsSupplyScanxService.search(filter, 0);

		tagLpb = tagLpbService.findById(noReg);
		if (tagLpb == null) {
			tagLpb = new TagLpb();
		} else {
			if (whsSupplyScans.isEmpty()) {
				Date dtperiod = tagLpbService.openPeriodeBpb();
				BigDecimal qtyScan = tagLpbService.getQtyMutasiTag(DTFORMAT_YYYY.format(dtperiod),
						DTFORMAT_MM.format(dtperiod), kdGudang, noReg);
				if (qtyScan != null) {
					if (qtyScan.compareTo(BigDecimal.ZERO) == 0) {
						tagLpb.setQtySupplyx(tagLpb.getQty());
					} else {
						tagLpb.setQtySupplyx(qtyScan);
					}
				}
			} else {
				WhsSupplyScanx whsSupplyScanx = whsSupplyScans.get(0);
				tagLpb.setQtySupplyx(whsSupplyScanx.getQtySupply());
				tagLpb.setQtyRetur(whsSupplyScanx.getQtyRetur());
			}
		}

		

		return tagLpb;
	}

	@RequestMapping(value = "/pushe", method = RequestMethod.POST)
	public @ResponseBody void saveData(@RequestBody TagLpb tagLpb, @RequestParam(value = "kdGudang", required = true) String kdGudang) {
		
		WhsSupplyScanx whsSupplyScanx = new WhsSupplyScanx();

		Map<String, Object> filters = new HashMap<>();
		filters.put("noBarcode =", tagLpb.getNoReg());
		filters.put("kdGudang =", kdGudang);
		filters.put("noBpb", null);

		List<WhsSupplyScanx> whsSupplyScans = whsSupplyScanxService.search(filters, 0);
		if (!whsSupplyScans.isEmpty()) {
			whsSupplyScanx = whsSupplyScans.get(0);
			
			whsSupplyScanx.setQtySupply(tagLpb.getQtySupplyx());
			whsSupplyScanx.setQtyRetur(tagLpb.getQtyRetur());
			BigDecimal qtyBpb = tagLpb.getQtySupplyx().subtract(tagLpb.getQtyRetur());
			whsSupplyScanx.setQtyBpb(qtyBpb);
			whsSupplyScanx.setModiby("web");
			whsSupplyScanx.setModidate(new Date());

			whsSupplyScanxService.update(whsSupplyScanx);

			log("STO/updateData-GET:  whsStoScan = " + whsSupplyScanx.toString());
			String message = "STO " + whsSupplyScanx.getNoBarcode() + " was successfully Updated";
		} else {
			BigDecimal kdTrx = whsSupplyScanxService.getMaxId();
        	if(kdTrx != null){
        		whsSupplyScanx.setKdTrx(kdTrx);
        		whsSupplyScanx.setNoBarcode(tagLpb.getNoReg());
            	
            	BigDecimal qtySupply = tagLpb.getQtyAkhir();
            	if(qtySupply.equals(BigDecimal.ZERO)){
            		qtySupply = tagLpb.getQty();
            	}
            	whsSupplyScanx.setQtyBpb(qtySupply);
            	whsSupplyScanx.setQtySupply(qtySupply);
            	whsSupplyScanx.setQtyRetur(BigDecimal.ZERO);
            	whsSupplyScanx.setBulan((new SimpleDateFormat("MM")).format(new Date()));
            	whsSupplyScanx.setTahun((new SimpleDateFormat("yyyy")).format(new Date()));
            	whsSupplyScanx.setTanggalTrx(new Date());
            	whsSupplyScanx.setKdGudang(kdGudang);
            	whsSupplyScanx.setCreateby("web");
            	whsSupplyScanx.setCreatedate(new Date());
            	
            	whsSupplyScanxService.create(whsSupplyScanx);
            	log("Supply/saveData-GET:  whsSupplyScan = " + whsSupplyScanx.toString());
                String message = "Supply " + whsSupplyScanx.getNoBarcode() + " was successfully Saved";
//                modelAndView.addObject("message", message);
        	}
			
		}

	}


	@RequestMapping(value = "/sedup", method = RequestMethod.GET)
	public @ResponseBody List<WhsSupplyScanx> findDup(@RequestParam(value = "id", required = true) String id) {
		Map<String, Object> filter = new HashMap<>();
		filter.put("noBarcode =", id);
		filter.put("tanggalTrx =", new Date());
		List<WhsSupplyScanx> whsSupplyScans = whsSupplyScanxService.search(filter, 0);
		return whsSupplyScans;
	}

	public WhsSupplyScanxService getWhsSupplyScanxService() {
		return whsSupplyScanxService;
	}

	public void setWhsSupplyScanxService(WhsSupplyScanxService whsSupplyScanxService) {
		this.whsSupplyScanxService = whsSupplyScanxService;
	}

	public TagLpbService getTagLpbService() {
		return tagLpbService;
	}

	public void setTagLpbService(TagLpbService tagLpbService) {
		this.tagLpbService = tagLpbService;
	}

}
