package org.gkd.springwebgkd.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.gkd.springwebgkd.bean.TagLpb;
import org.gkd.springwebgkd.bean.WhsStoScan;
import org.gkd.springwebgkd.common.controller.AbstractController;
import org.gkd.springwebgkd.service.TagLpbService;
import org.gkd.springwebgkd.service.WhsStoScanService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/sto")
public class StoController extends AbstractController {

	private static final String MAIN_ENTITY_NAME = "whsStoScanEntity";
	private static final String TAG_ENTITY_NAME = "tagLpbEntitiy";
	private String openPeriod;

	public StoController() {
		super(StoController.class, MAIN_ENTITY_NAME);
		log("StoController created.");
	}

	@Resource
	private TagLpbService tagLpbService;

	@Resource
	private WhsStoScanService whsStoScanService;

	@RequestMapping(value = { "", "/", "/list" }, method = RequestMethod.GET)
	public ModelAndView listOfSto() {
		log("Action 'list'");
		WhsStoScan whsStoScan = new WhsStoScan();
		TagLpb tagLpb = new TagLpb();
		openPeriod = "Open Period : " + whsStoScanService.openPeriodeSto();
		ModelAndView modelAndView = new ModelAndView("sto/sto-list");
		modelAndView.addObject(MAIN_ENTITY_NAME, whsStoScan);
		modelAndView.addObject(TAG_ENTITY_NAME, tagLpb);
		modelAndView.addObject("openPeriod", openPeriod);
		return modelAndView;
	}

	@RequestMapping(value = "stolist.json", method = RequestMethod.GET)
	public @ResponseBody List<WhsStoScan> findWhsStoScan(
			@RequestParam(value = "kdGudang", required = true) String kdGudang) {

		log("STO/findBarcode-GET:  scan today");

		Date dtperiod = tagLpbService.openPeriodeStoDt();
		Map<String, Object> filters = new HashMap<>();
		filters.put("kdGudang =", kdGudang);
		filters.put("bulan =", DTFORMAT_MM.format(dtperiod));
		filters.put("tahun =", DTFORMAT_YYYY.format(dtperiod));
		filters.put("stTarik =", "F");
		filters.put("stDel !=", "T");

		List<WhsStoScan> whsStoScans = whsStoScanService.search(filters);
		if (whsStoScans == null) {
			whsStoScans = new ArrayList<>();
		}
		return whsStoScans;
	}

	@RequestMapping(value = "/fi", method = RequestMethod.GET)
	public @ResponseBody TagLpb findBarcodePage(@RequestParam(value = "noReg", required = true) String noReg,
			@RequestParam(value = "kdGudang", required = true) String kdGudang) {

		log("STO/findBarcode-GET:  ID to query = " + noReg);

		TagLpb tagLpb = new TagLpb();
		// Map<String, Object> filter = new HashMap<>();
		// filter.put("noBarcode =", noReg);
		// filter.put("kdGudang =", kdGudang);
		// filter.put("tanggalTrx =", new Date());
		// List<WhsSupplyScan> whsSupplyScans =
		// whsSupplyScanService.search(filter);
		// if(whsSupplyScans.isEmpty()){
		tagLpb = tagLpbService.findById(noReg);
		if (tagLpb == null) {
			tagLpb = new TagLpb();
		} else {
			Date dtperiod = tagLpbService.openPeriodeStoDt();
			BigDecimal qtyScan = tagLpbService.getQtyMutasiTag(DTFORMAT_YYYY.format(dtperiod), DTFORMAT_MM.format(dtperiod), kdGudang, noReg);
			if (qtyScan != null){
				tagLpb.setQtyAkhir(qtyScan);
			}
		}
		// }

		return tagLpb;
	}

	@RequestMapping(value = "/pushe", method = RequestMethod.POST)
	public @ResponseBody void saveData(@RequestBody TagLpb tagLpb,
			@RequestParam(value = "kdGudang", required = true) String kdGudang, Principal principal) {
		WhsStoScan whsStoScan = new WhsStoScan();
		if (tagLpb != null) {
			BigDecimal kdTrx = whsStoScanService.getMaxId();
			Date dtperiod = tagLpbService.openPeriodeStoDt();
			if (kdTrx != null) {
				whsStoScan.setKdTrx(kdTrx);
				whsStoScan.setNoBarcode(tagLpb.getNoReg());
				whsStoScan.setQtyScan(tagLpb.getQtyAkhir());
				whsStoScan.setStTarik("F");
				whsStoScan.setStDel("F");
				whsStoScan.setBulan(DTFORMAT_MM.format(dtperiod));
				whsStoScan.setTahun(DTFORMAT_YYYY.format(dtperiod));
				whsStoScan.setTglTrx(new Date());
				whsStoScan.setKdGudang(kdGudang);
				whsStoScan.setCreateby("web");
				whsStoScan.setCreatedate(new Date());
				System.out.println(principal.getName());
				try {
					whsStoScanService.create(whsStoScan);
				} catch (Exception ex) {
					Map<String, Object> filters = new HashMap<>();
					filters.put("noBarcode =", tagLpb.getNoReg());
					filters.put("kdGudang =", kdGudang);
					filters.put("bulan =", DTFORMAT_MM.format(dtperiod));
					filters.put("tahun =", DTFORMAT_YYYY.format(dtperiod));
					filters.put("stTarik =", "F");
					
					List<WhsStoScan> whsStoScans = whsStoScanService.search(filters);
					if (!whsStoScans.isEmpty()) {
						whsStoScan = whsStoScans.get(0);
						whsStoScan.setStDel("F");
					}
					whsStoScanService.update(whsStoScan);
				}
				
				log("STO/saveData-GET:  whsStoScan = " + whsStoScan.toString());
				String message = "STO " + whsStoScan.getNoBarcode() + " was successfully Saved";
				// modelAndView.addObject("message", message);
			}
		}

	}

	@RequestMapping(value = "/upd", method = RequestMethod.POST)
	public @ResponseBody void updateData(@RequestBody BigDecimal qtyScan,
			@RequestParam(value = "noReg", required = true) String noReg,
			@RequestParam(value = "kdGudang", required = true) String kdGudang) {
		WhsStoScan whsStoScan = new WhsStoScan();

		Date dtperiod = tagLpbService.openPeriodeStoDt();
		Map<String, Object> filters = new HashMap<>();
		filters.put("noBarcode =", noReg);
		filters.put("kdGudang =", kdGudang);
		filters.put("bulan =", DTFORMAT_MM.format(dtperiod));
		filters.put("tahun =", DTFORMAT_YYYY.format(dtperiod));
		filters.put("stTarik =", "F");

		List<WhsStoScan> whsStoScans = whsStoScanService.search(filters);
		if (!whsStoScans.isEmpty()) {
			whsStoScan = whsStoScans.get(0);
		}

		if (whsStoScan != null) {
			whsStoScan.setQtyScan(qtyScan);
			whsStoScan.setModiby("web");
			whsStoScan.setModidate(new Date());

			whsStoScanService.update(whsStoScan);
			log("STO/updateData-GET:  whsStoScan = " + whsStoScan.toString());
			String message = "STO " + whsStoScan.getNoBarcode() + " was successfully Updated";
			// modelAndView.addObject("message", message);
		}

	}

	@RequestMapping(value = "/rmv", method = RequestMethod.GET)
	public @ResponseBody String[] removeData(@RequestParam(value = "noReg", required = true) String noReg,
			@RequestParam(value = "kdGudang", required = true) String kdGudang) {
		String[] objReturn = new String[2];
		try {
			WhsStoScan whsStoScan = new WhsStoScan();

			Date dtperiod = tagLpbService.openPeriodeStoDt();
			Map<String, Object> filters = new HashMap<>();
			filters.put("noBarcode =", noReg);
			filters.put("kdGudang =", kdGudang);
			filters.put("bulan =", DTFORMAT_MM.format(dtperiod));
			filters.put("tahun =", DTFORMAT_YYYY.format(dtperiod));
			filters.put("stTarik =", "F");

			List<WhsStoScan> whsStoScans = whsStoScanService.search(filters);
			if (!whsStoScans.isEmpty()) {
				whsStoScan = whsStoScans.get(0);
			}

			if (whsStoScan != null) {
				whsStoScan.setStDel("T");
				whsStoScan.setModiby("web");
				whsStoScan.setModidate(new Date());

				whsStoScanService.update(whsStoScan);
				log("STO/updateData-GET:  whsStoScan = " + whsStoScan.toString());
				objReturn[0] = "STO " + whsStoScan.getNoBarcode() + " was successfully Updated"; //message
				objReturn[1] = "success"; //status
				// modelAndView.addObject("message", message);
			}
		} catch (Exception e) {
			objReturn[0] = "STO " + noReg + " was failed to Update"; //message
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

	public WhsStoScanService getWhsStoScanService() {
		return whsStoScanService;
	}

	public void setWhsStoScanService(WhsStoScanService whsStoScanService) {
		this.whsStoScanService = whsStoScanService;
	}
}
