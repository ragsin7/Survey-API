package com.propsur.api.project.service.master;

import java.util.List;

import com.propsur.api.project.bean.TmUsersBean;
import com.propsur.api.project.entity.master.TmLookupDet;
import com.propsur.api.project.service.master.bean.LookupDetBean;

public interface LookupDetService {

	    TmLookupDet findById(Integer lookupDetId);

	    List<TmLookupDet> findAll();

	    List<LookupDetBean> saveLookupDet(List<LookupDetBean> lookupDetBean,Integer createdBy);

	    void updateTmLookupDet(TmLookupDet tmLookupDet);

	    void delete(Integer lookupDetId);

	    TmLookupDet findByLookupDetCode(String lookupCode,String lookupDetCode);
	    
	    LookupDetBean findByLookupDetCodeLkpCode(String lookupCode,String lookupDetCode);

		List<LookupDetBean> findByLookupDetLkpCode(String lookupCode);

		List<LookupDetBean> save(List<LookupDetBean> lookupdetbean);

		List<TmLookupDet> findByLookupDetByLookupId(Integer lookupId);

		List<TmLookupDet> getVehicleUser(String string, String string2);

		TmUsersBean getUserType(Long userId);

		List<LookupDetBean> getLookupDetByLkpCodeDistirctId(String lookupCode, Integer lookupDetHierIdDistrict);

		TmUsersBean getroleType(Long userId);
	
}
