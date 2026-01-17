package com.propsur.api.project.repo.master;

import java.util.List;

import com.propsur.api.project.entity.master.TmLookupDetHierarchical;

public interface LookupDetHierarchicalRepo {

	  List<TmLookupDetHierarchical> findAll();

	    TmLookupDetHierarchical findById(Integer id);

	    TmLookupDetHierarchical save(TmLookupDetHierarchical tmLookupDetHierarchical);

	    void delete(Integer id);

		List<Object[]> getLookupHirarchicaldata(Integer lookupDetId, String lookupcode);


		Object[] getLookupHirarchicalDataByLookupDet(Integer lookupId, Integer lookupDetId);

		List<TmLookupDetHierarchical> getLopokupHierarchicalDataById(Integer lookupDetId);

		List<TmLookupDetHierarchical> findByLookupHierarchicalLkpCodeandDetCode(String lookupCode,String lookupDetvalue);

		List<TmLookupDetHierarchical> getLopokupHierarchicalDataByParentId(Integer lookupDetHeirId);

	    
}
