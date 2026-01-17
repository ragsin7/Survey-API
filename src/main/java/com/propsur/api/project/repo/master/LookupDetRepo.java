package com.propsur.api.project.repo.master;

import java.util.List;

import com.propsur.api.project.entity.master.TmLookupDet;

public interface LookupDetRepo {

	 TmLookupDet findById(Integer lookupDetId);

	    List<TmLookupDet> findAll();
	    
	    TmLookupDet save(TmLookupDet tmLookupDet);


	    List<TmLookupDet> save(List<TmLookupDet> tmLookupDet);

	    void updateTmLookupDet(TmLookupDet tmLookupDet);

	    void delete(Integer lookupDetId);

	    TmLookupDet findByLookupDetCode(String lookupCode,String lookupDetCode);

		List<TmLookupDet> findByLookupDetLkpCode(String lookupCode);

		List<TmLookupDet> findByLookupDetByLookupId(Integer lookupId);

		List<TmLookupDet> getVehicleUser(String lookupCode, String lookupDetCode);

		Object[] getUserType(Long userId);

		Object[] getRoleTypeById(Long userId);
}
