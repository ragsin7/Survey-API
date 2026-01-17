package com.propsur.api.project.service.master;

import java.util.List;

import com.propsur.api.project.service.master.bean.MenuBean;
import com.propsur.api.project.service.master.bean.TmRoleMenuMapBean;

public interface RoleMenuMapService {

	List<MenuBean> getMenuItemsByLookupDetRoleTypeId(Integer lookupDetIdRoleType);
	List<MenuBean> getMenuItemsByLookupDetRoleTypeId(Integer lookupDetIdRoleType,Integer status);

	boolean saveMenuData(List<TmRoleMenuMapBean> menuDataList,Integer CreatedBy);

//	List<MenuBean> getSubMenuItemsByParentId(Integer parentId, Integer lookupDetIdRoleType);

}
