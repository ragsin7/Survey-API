package com.propsur.api.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.propsur.api.project.bean.TmUsersBean;
import com.propsur.api.project.entity.TmUsers;
import com.propsur.api.project.repo.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImplApi implements  UserServiceApi{

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
    @Override
    @Transactional
    public List<TmUsers> getUsers() {
         return userRepo.findAll();
    }
    
	
    @Override
    @Transactional
    public TmUsers saveUser(TmUsers user) {
    	 user.setPassword(passwordEncoder.encode(user.getPassword()));
    	 return userRepo.save(user);
     }
    	
    @Override
    @Transactional
    public TmUsers updatePassword(TmUsersBean userBean) {
    	TmUsers user=userRepo.getUserById(userBean.getUserId());
    	 user.setPassword(passwordEncoder.encode(userBean.getPassword()));
    	 if(userBean.getFloginPwreset() !=null && !userBean.getFloginPwreset().isEmpty())
    	 user.setFloginPwreset(userBean.getFloginPwreset());
    	 userRepo.updateTmUsers(user);
    	 return user;
     }


    
	@Override
	@Transactional
	public TmUsers getUserById(Long userId) {
		return userRepo.getUserById(userId);
	}


	@Override
	@Transactional
	public TmUsers getUserByUserLoginName(String loginName) {
		return userRepo.findByLoginName(loginName);
	}

	@Override
	@Transactional
	public TmUsersBean getUserByUserLoginNameBean(String loginName) {
		 TmUsers tmUsers = userRepo.findByLoginName(loginName);

		    if (tmUsers == null) {
		        return null; 
		    }

		    TmUsersBean tmUsersBean = new TmUsersBean();
		    tmUsersBean.setUserId(tmUsers.getUserId());
		    tmUsersBean.setUserName(tmUsers.getUserName());
		    tmUsersBean.setUserFullName(tmUsers.getUserFullName());
		    tmUsersBean.setMobileNumber(tmUsers.getMobileNumber());
		    tmUsersBean.setEmailId(tmUsers.getEmailId());
		    tmUsersBean.setLastLogin(tmUsers.getLastLogin());

		    return tmUsersBean;
	}

	/*
	@Override
	@Transactional
	public void updateLoginDetails(JwtRequest request) {
		TtUserLoginLogoutLogs ttUserLoginLogoutLogs=new TtUserLoginLogoutLogs();	
		ttUserLoginLogoutLogs.setUserId(request.getUserId());
		ttUserLoginLogoutLogs.setUserName(request.getUsername());
		ttUserLoginLogoutLogs.setAppWebVersion(request.getAppversion());
		ttUserLoginLogoutLogs.setLoginDatetime(DateTimeZoneHelper.getCurrentSysTimestamp());
		if(request.getCurrLoginOutFlag().equals("O"))
		ttUserLoginLogoutLogs.setLogoutDatetime(DateTimeZoneHelper.getCurrentSysTimestamp());
		ttUserLoginLogoutLogs.setCreatedBy(Integer.parseInt(request.getUserId().toString()));
		ttUserLoginLogoutLogs.setCreatedDate(DateTimeZoneHelper.getCurrentSysTimestamp());
		ttUserLoginLogoutLogs.setCurrLoginOutFlag(request.getCurrLoginOutFlag());
		ttUserLoginLogoutLogs.setOsVersion(request.getOsVersion());
		ttUserLoginLogoutLogs.setStatus(1);
		userRepo.SaveTtUserLoginLogoutLogs(ttUserLoginLogoutLogs);
	}
	*/


}
