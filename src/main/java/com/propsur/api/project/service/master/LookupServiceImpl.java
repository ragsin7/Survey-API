package com.propsur.api.project.service.master;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.propsur.api.project.entity.master.TmLookup;
import com.propsur.api.project.repo.master.LookupRepo;
import com.propsur.api.project.service.master.bean.LookupBean;

import jakarta.transaction.Transactional;

@Service
public class LookupServiceImpl implements LookupService {

	@Autowired
	LookupRepo lookupRepo;
	
     @Autowired
       ModelMapper modelMapper;
	
	@Override
	@Transactional
	public LookupBean findById(Integer lookupId) {
	       return modelMapper.map(lookupRepo.findById(lookupId),LookupBean.class);

	}

	@Override
	@Transactional
	public List<LookupBean> findAll() {
		List<Object[]> lookupdata=lookupRepo.findAll();
		return lookupdata.stream().map(row -> {
            LookupBean lookupbean = new LookupBean();
            lookupbean.setLookupId((Integer) Optional.ofNullable(row[0]).orElse(null));             
            lookupbean.setLookupCode((String) Optional.ofNullable(row[1]).orElse(null)); 
            lookupbean.setLookupDescEn((String) Optional.ofNullable(row[2]).orElse(null)); 
            lookupbean.setHierarchical((String) Optional.ofNullable(row[3]).orElse(null));
            lookupbean.setHierarchicalNoLevel((Integer) Optional.ofNullable(row[4]).orElse(null));  
            lookupbean.setLookupAddFlag((String) Optional.ofNullable(row[5]).orElse(null));
            lookupbean.setLookupEditFlag((String) Optional.ofNullable(row[6]).orElse(null));
            lookupbean.setStatus((Integer) Optional.ofNullable(row[7]).orElse(null)); 
            return lookupbean;
        }).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public TmLookup save(TmLookup tmLookup) {
		return lookupRepo.save(tmLookup);
				
	}

	@Override
	@Transactional
	public void updateTmLookup(TmLookup tmLookup) {
		lookupRepo.updateTmLookup(tmLookup);	
	}

	@Override
	@Transactional
	public void delete(Integer lookupId) {
		lookupRepo.delete(lookupId);
	}

	@Override
	@Transactional
	public TmLookup findByLookupCode(String lookupCode) {
		return lookupRepo.findByLookupCode(lookupCode);
	}

	@Override
	@Transactional
	public List<LookupBean> findLookupsByQuery(String query) {
	    List<Object[]> lookupData;
	    if (query != null && !query.trim().isEmpty()) {
	        lookupData = lookupRepo.findByQuery(query.trim());
	    } else {
	        lookupData = lookupRepo.findAll();
	    }
	    return lookupData.stream().map(row -> {
	        LookupBean lookupBean = new LookupBean();
	        lookupBean.setLookupId((Integer) Optional.ofNullable(row[0]).orElse(null));
	        lookupBean.setLookupCode((String) Optional.ofNullable(row[1]).orElse(null));
	        lookupBean.setLookupDescEn((String) Optional.ofNullable(row[2]).orElse(null));
	        lookupBean.setHierarchical((String) Optional.ofNullable(row[3]).orElse(null));
	        lookupBean.setHierarchicalNoLevel((Integer) Optional.ofNullable(row[4]).orElse(null));
	        lookupBean.setLookupAddFlag((String) Optional.ofNullable(row[5]).orElse(null));
	        lookupBean.setLookupEditFlag((String) Optional.ofNullable(row[6]).orElse(null));
	        lookupBean.setStatus((Integer) Optional.ofNullable(row[7]).orElse(null));
	        return lookupBean;
	    }).collect(Collectors.toList());
	}


}
