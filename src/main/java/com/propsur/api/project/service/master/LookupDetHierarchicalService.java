package com.propsur.api.project.service.master;


import java.util.List;

import com.propsur.api.project.entity.master.TmLookupDetHierarchical;
import com.propsur.api.project.service.master.bean.LookupHierarchicalBean;

public interface LookupDetHierarchicalService {

    List<TmLookupDetHierarchical> findAll();

    TmLookupDetHierarchical findById(Integer id);

    List<LookupHierarchicalBean> saveLookupHirarchicalData(List<LookupHierarchicalBean> lookupHirarchicalBean);

    void delete(Integer id);

	List<LookupHierarchicalBean> getLookupHirarchicaldata(Integer lookupDetId, String lookupcode);
	
	public LookupHierarchicalBean getLookupHirarchicalDataByLookupDet(Integer lookupId,Integer lookupDetId);

	List<LookupHierarchicalBean> getLopokupHierarchicalDataById(Integer lookupDetId);

	TmLookupDetHierarchical save(TmLookupDetHierarchical tmLookupDetHierarchical);
	
	List<LookupHierarchicalBean> findByLookupHierarchicalLkpCodeandDetCode(String lookupCode,String lookupDetvalue);

	List<LookupHierarchicalBean> getLopokupHierarchicalDataByParentId(Integer lookupDetHeirId);


}
