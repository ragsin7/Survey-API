package com.propsur.api.project.repo.master;

import java.util.List;

import com.propsur.api.project.entity.master.TmLookup;

public interface LookupRepo {


	  TmLookup findById(Integer lookupId);
	  
	  TmLookup findByLookupCode(String lookupCode);

	  List<Object[]> findAll();

	    TmLookup save(TmLookup tmLookup);

	    void updateTmLookup(TmLookup tmLookup);

	    void delete(Integer lookupId);

		List<Object[]> findByQuery(String trim);
}
