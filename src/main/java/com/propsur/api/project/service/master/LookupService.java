package com.propsur.api.project.service.master;

import java.util.List;

import com.propsur.api.project.entity.master.TmLookup;
import com.propsur.api.project.service.master.bean.LookupBean;

public interface LookupService {


	  LookupBean findById(Integer lookupId);

	    List<LookupBean> findAll();

	    TmLookup save(TmLookup tmLookup);

	    void updateTmLookup(TmLookup tmLookup);

	    void delete(Integer lookupId);
	    
	    TmLookup findByLookupCode(String lookupCode);

		List<LookupBean> findLookupsByQuery(String query);
	
}
