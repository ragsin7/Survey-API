package com.propsur.api.project.service.master;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.propsur.api.project.config.MapListComponent;
import com.propsur.api.project.entity.master.TmLookupDet;
import com.propsur.api.project.entity.master.TmLookupDetHierarchical;
import com.propsur.api.project.repo.master.LookupDetHierarchicalRepo;
import com.propsur.api.project.service.master.bean.LookupHierarchicalBean;

import jakarta.transaction.Transactional;

@Service
public class LookupDetHierarchicalServiceImpl implements LookupDetHierarchicalService {

    @Autowired
    private LookupDetHierarchicalRepo lookupDetHierarchicalRepo;
    
    @Autowired 
    ModelMapper modelMapper;
    	 
    
    @Autowired
	 private MapListComponent mapListComponent;

 
    @Override
    @Transactional
    public List<TmLookupDetHierarchical> findAll() {
        return lookupDetHierarchicalRepo.findAll();
    }

    @Override
    @Transactional
    public TmLookupDetHierarchical findById(Integer id) {
        return lookupDetHierarchicalRepo.findById(id);
    }
    
    
    @Override
    @Transactional
    public TmLookupDetHierarchical save(TmLookupDetHierarchical tmLookupDetHierarchical) {
        return lookupDetHierarchicalRepo.save(tmLookupDetHierarchical);
    }

    @Override
    @Transactional
    public List<LookupHierarchicalBean> saveLookupHirarchicalData(List<LookupHierarchicalBean> lookupHierarchicalBeans) {
        List<TmLookupDetHierarchical> tmLookupDetList = mapListComponent.mapList(lookupHierarchicalBeans, TmLookupDetHierarchical.class);

        for (TmLookupDetHierarchical tmLookupDet : tmLookupDetList) {
            LookupHierarchicalBean lookupBean = lookupHierarchicalBeans.get(tmLookupDetList.indexOf(tmLookupDet));

                if (lookupBean.getLookupDetId() != null) {
                    TmLookupDet newLookupDet = new TmLookupDet();
                    newLookupDet.setLookupDetId(lookupBean.getLookupDetId());
                    tmLookupDet.setTmLookupDet(newLookupDet);
                }
        
            lookupDetHierarchicalRepo.save(tmLookupDet);
        }
        return mapListComponent.mapList(tmLookupDetList, LookupHierarchicalBean.class);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        lookupDetHierarchicalRepo.delete(id);
    }

	@Override
	@Transactional
	public List<LookupHierarchicalBean> getLookupHirarchicaldata(Integer lookupDetId, String lookupcode) {
	    List<Object[]> rawResults = lookupDetHierarchicalRepo.getLookupHirarchicaldata(lookupDetId, lookupcode);
	    return rawResults.stream()
	            .map(result -> {
	                LookupHierarchicalBean bean = new LookupHierarchicalBean();
	                bean.setLookupDetHierId((Integer) result[0]); 
	                bean.setLookupDetHierDescEn((String) result[1]); 
	                return bean;
	            })
	            .collect(Collectors.toList());
	}

	@Override
	@Transactional
	public LookupHierarchicalBean getLookupHirarchicalDataByLookupDet(Integer lookupId, Integer lookupDetId) {
	    Object[] lookDet = lookupDetHierarchicalRepo.getLookupHirarchicalDataByLookupDet(lookupId, lookupDetId);

	    if (lookDet == null) {
	        return null; 
	    }

	    LookupHierarchicalBean beanObj = new LookupHierarchicalBean();

	    if (lookDet[0] != null) {
	        beanObj.setLookupDetHierId(Integer.parseInt(lookDet[0].toString()));
	        beanObj.setLookupDetId(Integer.parseInt(lookDet[0].toString()));
	        beanObj.setLookupId(Integer.parseInt(lookDet[1].toString()));
	    }

	    if (lookDet[6] != null || lookDet[7] != null)
	        beanObj.setLookupDetHierDescEn(lookDet[6].toString() + " ( " + lookDet[7].toString() + " )");

	    if (lookDet[3] != null || lookDet[4] != null)
	        beanObj.setLookupDetHierDescRg(lookDet[3].toString() + " ( " + lookDet[4].toString() + " )");

	    if (lookDet[2] != null)
	        beanObj.setLookupDetHierValue(lookDet[2].toString());

	    return beanObj;
	}

	@Override
	@Transactional
	public List<LookupHierarchicalBean> getLopokupHierarchicalDataById(Integer lookupDetId) {
		 List<TmLookupDetHierarchical> tmLookupDetList = lookupDetHierarchicalRepo.getLopokupHierarchicalDataById(lookupDetId);		 
		 return  mapListComponent.mapList(tmLookupDetList, LookupHierarchicalBean.class);
	}

	@Override
	@Transactional
	public List<LookupHierarchicalBean> findByLookupHierarchicalLkpCodeandDetCode(String lookupCode,String lookupDetvalue) {
		List<TmLookupDetHierarchical> lookuphir= lookupDetHierarchicalRepo.findByLookupHierarchicalLkpCodeandDetCode(lookupCode,lookupDetvalue);
		 return mapListComponent.mapList(lookuphir, LookupHierarchicalBean.class);
		
	}

	@Override
	@Transactional
	public List<LookupHierarchicalBean> getLopokupHierarchicalDataByParentId(Integer lookupDetHeirId) {
		List<TmLookupDetHierarchical> lookuphir= lookupDetHierarchicalRepo.getLopokupHierarchicalDataByParentId(lookupDetHeirId);
		 return mapListComponent.mapList(lookuphir, LookupHierarchicalBean.class);
	}

	

}