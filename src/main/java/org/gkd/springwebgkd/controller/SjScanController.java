package org.gkd.springwebgkd.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.gkd.springwebgkd.bean.TagLpb;
import org.gkd.springwebgkd.bean.WhsSjScan;
import org.gkd.springwebgkd.common.controller.AbstractController;
import org.gkd.springwebgkd.service.TagLpbService;
import org.gkd.springwebgkd.service.WhsSjScanService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/sj")
public class SjScanController extends AbstractController {

	private static final String MAIN_ENTITY_NAME = "whsSjScanEntity";
	private static final String TAG_ENTITY_NAME = "tagLpbEntitiy";
	private String openPeriod;
	private Principal principal;

	public SjScanController() {
		super(SjScanController.class, MAIN_ENTITY_NAME);
		log("SjScanController created.");
	}

	@Resource
	private TagLpbService tagLpbService;

	@Resource
	private WhsSjScanService whsSjScanService;

	@RequestMapping(value = { "", "/", "/list" }, method = RequestMethod.GET)
	public ModelAndView listOfSj(Principal principal) {
		log("Action 'list'");
		WhsSjScan whsSjScan = new WhsSjScan();
		TagLpb tagLpb = new TagLpb();
		this.principal = principal;
		openPeriod = "Open Period : " + whsSjScanService.openPeriodeBpb();
		ModelAndView modelAndView = new ModelAndView("sj/sj-list");
		modelAndView.addObject(MAIN_ENTITY_NAME, whsSjScan);
		modelAndView.addObject(TAG_ENTITY_NAME, tagLpb);
		modelAndView.addObject("openPeriod", openPeriod);
		return modelAndView;
	}

	@RequestMapping(value = "sjlist.json", method = RequestMethod.GET)
	public @ResponseBody List<WhsSjScan> findWhsSjScan(
			@RequestParam(value = "kdGudang", required = true) String kdGudang) {

		log("SPAG/findBarcode-GET:  scan today");

		Date dtperiod = tagLpbService.openPeriodeBpb();
		Map<String, Object> filters = new HashMap<>();
		filters.put("kdGudang =", kdGudang);
		filters.put("bulan =", DTFORMAT_MM.format(dtperiod));
		filters.put("tahun =", DTFORMAT_YYYY.format(dtperiod));
		filters.put("stTarik =", "F");
		filters.put("stDel !=", "T");

		List<WhsSjScan> whsSjScans = whsSjScanService.search(filters);
		if (whsSjScans == null) {
			whsSjScans = new ArrayList<>();
		}
		return whsSjScans;
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

	@RequestMapping(value = "/pushe", method = RequestMethod.POST)
	public @ResponseBody void saveData(@RequestBody TagLpb tagLpb,
			@RequestParam(value = "kdGudang", required = true) String kdGudang, Principal principal) {
		WhsSjScan whsSjScan = new WhsSjScan();
		if (tagLpb != null) {
			BigDecimal kdTrx = whsSjScanService.getMaxId();
			Date dtperiod = tagLpbService.openPeriodeBpb();
			if (kdTrx != null) {
				whsSjScan.setKdTrx(kdTrx);
				whsSjScan.setNoBarcode(tagLpb.getNoReg());
				whsSjScan.setQtyScan(tagLpb.getQtyAkhir());
				whsSjScan.setStTarik("F");
				whsSjScan.setStDel("F");
				whsSjScan.setBulan(DTFORMAT_MM.format(dtperiod));
				whsSjScan.setTahun(DTFORMAT_YYYY.format(dtperiod));
				whsSjScan.setTglTrx(new Date());
				whsSjScan.setKdGudang(kdGudang);
				whsSjScan.setCreateby("web");
				whsSjScan.setCreatedate(new Date());
				System.out.println(principal.getName());
				try {
					whsSjScanService.create(whsSjScan);
				} catch (Exception ex) {
					Map<String, Object> filters = new HashMap<>();
					filters.put("noBarcode =", tagLpb.getNoReg());
					filters.put("kdGudang =", kdGudang);
					filters.put("bulan =", DTFORMAT_MM.format(dtperiod));
					filters.put("tahun =", DTFORMAT_YYYY.format(dtperiod));
					filters.put("stTarik =", "F");
					
					List<WhsSjScan> whsSjScans = whsSjScanService.search(filters);
					if (!whsSjScans.isEmpty()) {
						whsSjScan = whsSjScans.get(0);
						whsSjScan.setStDel("F");
					}
//					whsSjScanService.update(whsSjScan);
				}
				
				log("SPAG/saveData-GET:  whsSjScan = " + whsSjScan.toString());
				String message = "SPAG " + whsSjScan.getNoBarcode() + " was successfully Saved";
				// modelAndView.addObject("message", message);
			}
		}

	}

	@RequestMapping(value = "/upd", method = RequestMethod.POST)
	public @ResponseBody void updateData(@RequestBody BigDecimal qtyScan,
			@RequestParam(value = "noReg", required = true) String noReg,
			@RequestParam(value = "kdGudang", required = true) String kdGudang) {
		WhsSjScan whsSjScan = new WhsSjScan();

		Date dtperiod = tagLpbService.openPeriodeBpb();
		Map<String, Object> filters = new HashMap<>();
		filters.put("noBarcode =", noReg);
		filters.put("kdGudang =", kdGudang);
		filters.put("bulan =", DTFORMAT_MM.format(dtperiod));
		filters.put("tahun =", DTFORMAT_YYYY.format(dtperiod));
		filters.put("stTarik =", "F");

		List<WhsSjScan> whsSjScans = whsSjScanService.search(filters);
		if (!whsSjScans.isEmpty()) {
			whsSjScan = whsSjScans.get(0);
		}

		if (whsSjScan != null) {
			whsSjScan.setQtyScan(qtyScan);
			whsSjScan.setModiby("web");
			whsSjScan.setModidate(new Date());

			whsSjScanService.update(whsSjScan);
			log("SPAG/updateData-GET:  whsSjScan = " + whsSjScan.toString());
			String message = "SPAG " + whsSjScan.getNoBarcode() + " was successfully Updated";
			// modelAndView.addObject("message", message);
		}

	}

	@RequestMapping(value = "/rmv", method = RequestMethod.GET)
	public @ResponseBody String[] removeData(@RequestParam(value = "noReg", required = true) String noReg,
			@RequestParam(value = "kdGudang", required = true) String kdGudang) {
		String[] objReturn = new String[2];
		try {
			WhsSjScan whsSjScan = new WhsSjScan();

			Date dtperiod = tagLpbService.openPeriodeBpb();
			Map<String, Object> filters = new HashMap<>();
			filters.put("noBarcode =", noReg);
			filters.put("kdGudang =", kdGudang);
			filters.put("bulan =", DTFORMAT_MM.format(dtperiod));
			filters.put("tahun =", DTFORMAT_YYYY.format(dtperiod));
			filters.put("stTarik =", "F");

			List<WhsSjScan> whsSjScans = whsSjScanService.search(filters);
			if (!whsSjScans.isEmpty()) {
				whsSjScan = whsSjScans.get(0);
			}

			if (whsSjScan != null) {
				whsSjScan.setStDel("T");
				whsSjScan.setModiby("web");
				whsSjScan.setModidate(new Date());

				whsSjScanService.update(whsSjScan);
				log("SPAG/updateData-GET:  whsSjScan = " + whsSjScan.toString());
				objReturn[0] = "SPAG " + whsSjScan.getNoBarcode() + " was successfully Updated"; //message
				objReturn[1] = "success"; //status
				// modelAndView.addObject("message", message);
			}
		} catch (Exception e) {
			objReturn[0] = "Tag with No " + noReg + " was failed to Update"; //message
			objReturn[1] = "fail"; //fail
		}
		return objReturn;

	}

	public String getOpenPeriod() {
		return openPeriod;
	}

	public void setOpenPeriod(String openPeriod) {
		this.openPeriod = openPeriod;
	}

	public TagLpbService getTagLpbService() {
		return tagLpbService;
	}

	public void setTagLpbService(TagLpbService tagLpbService) {
		this.tagLpbService = tagLpbService;
	}

	public WhsSjScanService getWhsSjScanService() {
		return whsSjScanService;
	}

	public void setWhsSjScanService(WhsSjScanService whsSjScanService) {
		this.whsSjScanService = whsSjScanService;
	}
}
