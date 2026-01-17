package com.propsur.api.project.service;

import java.util.List;

import com.propsur.api.project.bean.TmUsersBean;
import com.propsur.api.project.entity.TmUsers;

public interface UserServiceApi {

 public List<TmUsers> getUsers();
 
 public TmUsers getUserByUserLoginName(String loginName);

 TmUsers saveUser(TmUsers user);
 
 TmUsers updatePassword(TmUsersBean user);
 
 TmUsers getUserById(Long userId);

// void updateLoginDetails(JwtRequest request);

 TmUsersBean getUserByUserLoginNameBean(String username);

}
