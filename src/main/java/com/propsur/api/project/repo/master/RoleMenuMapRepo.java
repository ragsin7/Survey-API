package com.propsur.api.project.repo.master;


import java.util.List;

import com.propsur.api.project.entity.master.Menu;
import com.propsur.api.project.entity.master.TmRoleMenuMap;

public interface RoleMenuMapRepo {

	List<Object[]> getMenuItemsByParentId(Integer roleTypeId);
	List<Object[]> getMenuItemsByParentId(Integer roleTypeId,Integer status);

	TmRoleMenuMap save(TmRoleMenuMap menuData);
       
	Menu getMenu(Integer subMenuId);

//	List<Object[]> getSubMenuByParentId(Integer parentId, Integer roleTypeId);

}
