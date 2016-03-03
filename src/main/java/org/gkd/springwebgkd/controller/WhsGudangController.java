package org.gkd.springwebgkd.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.gkd.springwebgkd.service.MwhsGudangService;
import org.gkd.springwebgkd.bean.MwhsGudang;
import org.gkd.springwebgkd.common.controller.AbstractController;

@Controller
@RequestMapping(value = "/whs")
public class WhsGudangController extends AbstractController {

    private static final String MAIN_ENTITY_NAME = "mwhsGudangEntity";
    private static final String MAIN_LIST_NAME = "mwhsGudangEntities";

    public WhsGudangController() {
        super(WhsGudangController.class, MAIN_ENTITY_NAME);
        log("SupplyController created.");
    }
    
    @Resource
    private MwhsGudangService mwhsGudangService;
        
    @RequestMapping(value = "whslist.json", method = RequestMethod.GET)
    public @ResponseBody List<MwhsGudang> findWhsAllGudang(Principal principal) {

        log("Supply/findBarcode-GET:  scan today");

        Map<String, Object> filter = new HashMap<>();
        filter.put("flagAktif =", "T");
        String userLogin = principal.getName();
        String kdSite = mwhsGudangService.getKodeSite(userLogin);
        if(kdSite != null){
        	if(kdSite .equals("GKDC")){
        		filter.put("kode =", "002");
        	}
        }
        System.out.println(userLogin+ "site :"+ kdSite);
        
        List<MwhsGudang> mwhsGudangs = mwhsGudangService.search(filter);
        if(mwhsGudangs == null){
        	mwhsGudangs = new ArrayList<>();
        }
        return mwhsGudangs;
    }

	public MwhsGudangService getMwhsGudangService() {
		return mwhsGudangService;
	}

	public void setMwhsGudangService(MwhsGudangService mwhsGudangService) {
		this.mwhsGudangService = mwhsGudangService;
	}


}
