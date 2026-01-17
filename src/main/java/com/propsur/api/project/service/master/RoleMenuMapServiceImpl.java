package com.propsur.api.project.service.master;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.propsur.api.project.entity.master.Menu;
import com.propsur.api.project.entity.master.TmRoleMenuMap;
import com.propsur.api.project.repo.master.RoleMenuMapRepo;
import com.propsur.api.project.service.master.bean.MenuBean;
import com.propsur.api.project.service.master.bean.TmRoleMenuMapBean;

import jakarta.transaction.Transactional;

@Service
public class RoleMenuMapServiceImpl implements RoleMenuMapService {

	@Autowired
	RoleMenuMapRepo roleMenuMapRepo;

	@Override
	@Transactional
	public List<MenuBean> getMenuItemsByLookupDetRoleTypeId(Integer lookupDetIdRoleType) {
		//List<Object[]> menuItemsList = roleMenuMapRepo.getMenuItemsByParentId(lookupDetIdRoleType);
		return getMenuItemsByLookupDetRoleTypeId(lookupDetIdRoleType,null);
	}

	@Override
	@Transactional
	public List<MenuBean> getMenuItemsByLookupDetRoleTypeId(Integer lookupDetIdRoleType, Integer status) {
		if(status != null) {
			List<Object[]> menuItemsList = roleMenuMapRepo.getMenuItemsByParentId(lookupDetIdRoleType, status);
			return getMenuItemList(menuItemsList);
		}else{
			List<Object[]> menuItemsList = roleMenuMapRepo.getMenuItemsByParentId(lookupDetIdRoleType);
			return getMenuItemList(menuItemsList);
		}
	}

	private List<MenuBean> getMenuItemList(List<Object[]> menuItemsList ){
		List<MenuBean> menuBeans = new ArrayList<>();
		MenuBean bean = null;
		for (Object[] menuItem : menuItemsList) {
			bean = new MenuBean();
			bean.setRoleMapId((Integer) menuItem[0]);
			bean.setMenuId((Integer) menuItem[1]);
			bean.setMenuFeatureNameEn((String) menuItem[2]);
			bean.setMenuController((String)menuItem[3]);
			bean.setMenuParentId((Integer) menuItem[4]);
			bean.setStatus((Integer) menuItem[6]);
			menuBeans.add(bean);
		}
		return menuBeans;
	}
//	@Override
//	@Transactional
//	public List<MenuBean> getSubMenuItemsByParentId(Integer parentId,Integer lookupDetIdRoleType) {
//		List<Object[]> submenuItemsList = roleMenuMapRepo.getSubMenuByParentId(parentId, lookupDetIdRoleType);
//		List<MenuBean> submenuBeans = new ArrayList<>();
//
//		for (Object[] submenuItem : submenuItemsList) {
//			MenuBean bean = new MenuBean();
//			bean.setMenuId((Integer) submenuItem[0]);
//			bean.setMenuFeatureNameEn((String) submenuItem[1]);
//			bean.setMenuParentId((Integer) submenuItem[2]);
//			bean.setRoleMapId((Integer) submenuItem[3]);
//			submenuBeans.add(bean);
//		}
//
//		return submenuBeans;
//	}

	@Override
	@Transactional
	public boolean saveMenuData(List<TmRoleMenuMapBean> menuDataList, Integer createdBy) {
		boolean response = false;

		for (TmRoleMenuMapBean menuData : menuDataList) {
			try {
				TmRoleMenuMap roleMenuMap = new TmRoleMenuMap();
				roleMenuMap.setLookupDetIdRoleType(menuData.getLookupDetIdRoleType());

				Menu menu = roleMenuMapRepo.getMenu(menuData.getMenuId());
				roleMenuMap.setMenu(menu);

				if (menuData.getRoleMapId() == null) {
					roleMenuMap.setCreatedBy(createdBy);
					roleMenuMap.setCreatedDate(new Date());
					if (menuData.getStatus() != null)
						roleMenuMap.setStatus(menuData.getStatus());
					else
						roleMenuMap.setStatus(1);
				} else {
					roleMenuMap.setRoleMapId(menuData.getRoleMapId());
					roleMenuMap.setUpdatedBy(createdBy);
					roleMenuMap.setUpdatedDate(new Date());
					if (menuData.getStatus() != null)
						roleMenuMap.setStatus(menuData.getStatus());
					else
						roleMenuMap.setStatus(1);
				}

				roleMenuMapRepo.save(roleMenuMap);
				response = true;

			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if (menuData.getUncheckedMenus() != null) {
					for (TmRoleMenuMapBean.SubmenuDetail uncheckDetail : menuData.getUncheckedMenus()) {
						TmRoleMenuMap roleMenuMap = new TmRoleMenuMap();
						roleMenuMap.setLookupDetIdRoleType(menuData.getLookupDetIdRoleType());

						Menu menu = roleMenuMapRepo.getMenu(uncheckDetail.getMenuId());
						roleMenuMap.setMenu(menu);

						if (uncheckDetail.getRoleMapId() == null) {
							roleMenuMap.setCreatedBy(createdBy);
							roleMenuMap.setCreatedDate(new Date());
							roleMenuMap.setStatus(0);
						} else {
							roleMenuMap.setRoleMapId(uncheckDetail.getRoleMapId());
							roleMenuMap.setUpdatedBy(createdBy);
							roleMenuMap.setUpdatedDate(new Date());
							roleMenuMap.setStatus(0);
						}

						roleMenuMapRepo.save(roleMenuMap);
						response = true;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return response;
	}

}
