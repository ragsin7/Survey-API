package com.propsur.api.project.service.master;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.propsur.api.project.bean.TmUsersBean;
import com.propsur.api.project.config.MapListComponent;
import com.propsur.api.project.entity.master.TmLookup;
import com.propsur.api.project.entity.master.TmLookupDet;
import com.propsur.api.project.entity.master.TmLookupDetHierarchical;
import com.propsur.api.project.repo.master.LookupDetHierarchicalRepo;
import com.propsur.api.project.repo.master.LookupDetRepo;
import com.propsur.api.project.service.master.bean.LookupDetBean;
import com.propsur.api.project.service.utils.StringHelperUtils;

import jakarta.transaction.Transactional;

@Service
public class LookupDetServiceImpl implements LookupDetService {

	@Autowired
	private LookupDetRepo lookupDetRepo;

	@Autowired
	private LookupDetHierarchicalRepo lookupDetHierarchicalRepo;

	@Autowired
	private MapListComponent mapListComponent;

	@Autowired
	ModelMapper modelMapper;

	@Override
	@Transactional
	public TmLookupDet findById(Integer lookupDetId) {
		return lookupDetRepo.findById(lookupDetId);
	}

	@Override
	@Transactional
	public List<TmLookupDet> findAll() {
		return lookupDetRepo.findAll();
	}

	@Override
	@Transactional
	public List<LookupDetBean> saveLookupDet(List<LookupDetBean> lookupDetBeanList, Integer createdBy) {
		List<TmLookupDet> tmLookupDetList = mapListComponent.mapList(lookupDetBeanList, TmLookupDet.class);
		TmLookupDet parentLookupDet = null;
		for (TmLookupDet tmLookupDet : tmLookupDetList) {
			tmLookupDet.setCreatedBy(createdBy);
			LookupDetBean lookupDetBean = lookupDetBeanList.get(tmLookupDetList.indexOf(tmLookupDet));
			if (lookupDetBean.getLookupDetId() != null) {
				TmLookupDet existingRecord = lookupDetRepo.findById(lookupDetBean.getLookupDetId());
				if (existingRecord != null) {
					tmLookupDet.setTmLookup(existingRecord.getTmLookup());
					tmLookupDet.setLookupDetId(lookupDetBean.getLookupDetId());
				}
			} else {
				if (lookupDetBean.getLookupId() != null) {
					TmLookup tmLookup = new TmLookup();
					tmLookup.setLookupId(lookupDetBean.getLookupId());
					tmLookupDet.setTmLookup(tmLookup);
				}

				if (parentLookupDet != null) {
					tmLookupDet.setLookupDetParentId(parentLookupDet.getLookupDetId());
				}
			}
			TmLookupDet savedLookupDet = lookupDetRepo.save(tmLookupDet);
			parentLookupDet = savedLookupDet;
		}
		return mapListComponent.mapList(tmLookupDetList, LookupDetBean.class);
	}

	@Override
	@Transactional
	public void updateTmLookupDet(TmLookupDet tmLookupDet) {
		lookupDetRepo.updateTmLookupDet(tmLookupDet);
	}

	@Override
	@Transactional
	public void delete(Integer lookupDetId) {
		lookupDetRepo.delete(lookupDetId);
	}

	@Override
	@Transactional
	public TmLookupDet findByLookupDetCode(String lookupCode, String lookupDetCode) {
		return lookupDetRepo.findByLookupDetCode(lookupCode, lookupDetCode);
	}
	
	@Override
	@Transactional
	public LookupDetBean findByLookupDetCodeLkpCode(String lookupCode, String lookupDetCode) {
		return modelMapper.map(lookupDetRepo.findByLookupDetCode(lookupCode, lookupDetCode),LookupDetBean.class);
	}

	@Override
	@Transactional
	public List<LookupDetBean> findByLookupDetLkpCode(String lookupCode) {
		List<TmLookupDet> tmLookupDetList = lookupDetRepo.findByLookupDetLkpCode(lookupCode);
		try {
			List<LookupDetBean> data = mapListComponent.mapList(tmLookupDetList, LookupDetBean.class);
			// data.get(0).setLookupDescEn(tmLookupDetList.get(0).getTmLookup().getLookupDescEn());
			return data;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional
	public List<LookupDetBean> getLookupDetByLkpCodeDistirctId(String lookupCode, Integer lookupDetHierIdDistrict) {
		List<TmLookupDet> tmLookupDetList = lookupDetRepo.findByLookupDetLkpCode(lookupCode);
		List<LookupDetBean> data = new ArrayList<>();
		try {
			if (lookupCode.equals("MDB")) {
				TmLookupDetHierarchical tmLookupDetHierarchical = lookupDetHierarchicalRepo.findById(lookupDetHierIdDistrict);
				for (TmLookupDet lookupDet : tmLookupDetList) {
					if (tmLookupDetHierarchical.getLookupDetHierValue() != null
							&& tmLookupDetHierarchical.getTmLookupDet().getLookupDetValue() != null
							&& tmLookupDetHierarchical.getTmLookupDet().getTmLookup().getLookupCode() != null) {
						if (tmLookupDetHierarchical.getTmLookupDet().getLookupDetValue().equals("DIS")
								&& tmLookupDetHierarchical.getTmLookupDet().getTmLookup().getLookupCode()
										.equals("TRY")) {
							if (tmLookupDetHierarchical.getLookupDetHierValue().equals("AKO")
									|| tmLookupDetHierarchical.getLookupDetHierValue().equals("BUL")
									|| tmLookupDetHierarchical.getLookupDetHierValue().equals("BHA")
									|| tmLookupDetHierarchical.getLookupDetHierValue().equals("GON")
									|| tmLookupDetHierarchical.getLookupDetHierValue().equals("WAS")) {
								if (!lookupDet.getLookupDetValue().equals("C")) {
									LookupDetBean bean = modelMapper.map(lookupDet, LookupDetBean.class);
									data.add(bean);
								}
							} else if (tmLookupDetHierarchical.getLookupDetHierValue().equals("YAV")) {
								if (!lookupDet.getLookupDetValue().equals("D")) {
									LookupDetBean bean = modelMapper.map(lookupDet, LookupDetBean.class);
									data.add(bean);
								}
							} else if (tmLookupDetHierarchical.getLookupDetHierValue().equals("AMR")) {
									LookupDetBean bean = modelMapper.map(lookupDet, LookupDetBean.class);
									data.add(bean);
							}else {
								if (!lookupDet.getLookupDetValue().equals("D") && !lookupDet.getLookupDetValue().equals("C")) {
								LookupDetBean bean = modelMapper.map(lookupDet, LookupDetBean.class);
								data.add(bean);
								}
							}
						}
					}
				}
			}else {
				data = mapListComponent.mapList(tmLookupDetList, LookupDetBean.class);
				return data;
			}
			
			return data;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<LookupDetBean> save(List<LookupDetBean> lookupDetBeanList) {
		List<TmLookupDet> tmLookupDetList = mapListComponent.mapList(lookupDetBeanList, TmLookupDet.class);
		tmLookupDetList.forEach(tmLookupDet -> {
			LookupDetBean lookupDetBean = lookupDetBeanList.get(tmLookupDetList.indexOf(tmLookupDet));
			if (lookupDetBean.getLookupId() != null) {
				TmLookup tmLookup = new TmLookup();
				tmLookup.setLookupId(lookupDetBean.getLookupId());
				tmLookupDet.setTmLookup(tmLookup);
			}
		});
		List<TmLookupDet> lookupdet = lookupDetRepo.save(tmLookupDetList);
		return mapListComponent.mapList(lookupdet, LookupDetBean.class);
	}

	@Override
	@Transactional
	public List<TmLookupDet> findByLookupDetByLookupId(Integer lookupId) {

		return lookupDetRepo.findByLookupDetByLookupId(lookupId);
	}

	@Override
	@Transactional
	public List<TmLookupDet> getVehicleUser(String LookupCode, String lookupDetCode) {
		return lookupDetRepo.getVehicleUser(LookupCode, lookupDetCode);
	}

	@Override
	@Transactional
	public TmUsersBean getUserType(Long userId) {
		Object[] data = lookupDetRepo.getUserType(userId);
		TmUsersBean bean = new TmUsersBean();
		bean.setUserType(StringHelperUtils.isNullString(data[2]));
		return bean;

	}

	@Override
	@Transactional
	public TmUsersBean getroleType(Long userId) {
		try {
			Object[] data =lookupDetRepo.getRoleTypeById(userId);
			TmUsersBean bean = new TmUsersBean();
			bean.setLookupDetIdRoleTypeDesc(StringHelperUtils.isNullString(data[0]));
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
