package com.propsur.api.project.repo;

import java.util.List;

import com.propsur.api.project.entity.TmUsers;

public interface UserRepository {

	
	List<TmUsers> findAll();
	
	TmUsers getUserById(Long userId);
	
	TmUsers save(TmUsers user);
	
	TmUsers findByLoginName(String username);
	
	List<Object[]> getUser();
	
	void updateTmUsers(TmUsers obj);

	List<Object[]> getAllUser();

	List<Object[]> getUserByUserNmae(Integer roletype, String username);

	//void SaveTtUserLoginLogoutLogs(TtUserLoginLogoutLogs ttUserLoginLogoutLogs);

	List<Object[]> getHcfMasterDropDown(Integer distId);

	List<Object[]> getAllusers(Integer roletype, String username);

	List<Object[]> getUserDetailsByRole(Integer roletype, String username);

	List<Object[]> getusersLstByIds(Integer districtId);

	//TtDocumentUpload saveTtDocumentUpload(TtDocumentUpload ttDocumentUpload);

	List<Object[]> getdocuments(Integer docTypeId);

	List<Object[]> getUserDetailsByRoleId(Integer roletype);
	
}
