package com.propsur.api.project.repo.master;

	
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.propsur.api.project.entity.master.Menu;
import com.propsur.api.project.entity.master.TmRoleMenuMap;

@Repository
public class RoleMenuMapRepoImpl implements RoleMenuMapRepo {
	
	 @Autowired
    private SessionFactory sessionFactory;

	 
	 @Override
	 public List<Object[]> getMenuItemsByParentId(Integer roleTypeId) {
	     List<Object[]> menuItemsList = new ArrayList<>();

	     try {
	         Session session = sessionFactory.getCurrentSession();
	         String sql = "SELECT * FROM master.fn_get_menu_by_role_type(:roleTypeId)";

	         menuItemsList = session.createNativeQuery(sql, Object[].class)
	                                .setParameter("roleTypeId", roleTypeId)
	                                .getResultList();
	     } catch (Exception e) {
	         e.printStackTrace();
	     }

	     return menuItemsList;
	 }

	@Override
	public List<Object[]> getMenuItemsByParentId(Integer roleTypeId, Integer status) {
		List<Object[]> menuItemsList = new ArrayList<>();

		try {
			Session session = sessionFactory.getCurrentSession();
			String sql = "SELECT * FROM user_role_right.fn_get_menu_by_role_type(:roleTypeId) where status=:status";

			menuItemsList = session.createNativeQuery(sql, Object[].class)
					.setParameter("roleTypeId", roleTypeId)
					.setParameter("status", status)
					.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return menuItemsList;
	}


//	@Override
//	public List<Object[]> getSubMenuByParentId(Integer parentId, Integer lookupDetIdRoleType) {
//		List<Object[]> menuItemsList = new ArrayList<>();
//		try {
//			Session session = sessionFactory.getCurrentSession();
//			String jpql = """
//					    SELECT m.menuId, m.menuFeatureNameEn, m.menuParentId,
//					           CASE
//					               WHEN rm.roleMapId IS NOT NULL THEN rm.roleMapId
//					               ELSE NULL
//					           END
//					    FROM Menu m
//					    LEFT JOIN TmRoleMenuMap rm
//					        ON m.menuId = rm.menu.menuId AND rm.lookupDetIdRoleType = :lookupDetIdRoleType AND rm.status =1
//					    WHERE m.menuParentId = :parentId
//					    ORDER BY m.menuId
//					""";
//
//			menuItemsList = session.createQuery(jpql, Object[].class).setParameter("parentId", parentId)
//					.setParameter("lookupDetIdRoleType", lookupDetIdRoleType).getResultList();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return menuItemsList;
//	}

	@Override
	public TmRoleMenuMap save(TmRoleMenuMap menuData) {
		Session session = sessionFactory.getCurrentSession();
		TmRoleMenuMap roleMenuMap = new TmRoleMenuMap();

		try {
			if (menuData.getRoleMapId() == null) {
				session.persist(menuData);
			} else {
				session.merge(menuData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return roleMenuMap;
	}

	@Override
	public Menu getMenu(Integer subMenuId) {
		return sessionFactory.getCurrentSession().get(Menu.class, subMenuId);
	}

}
