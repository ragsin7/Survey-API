package com.propsur.api.project.bean;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TmUsersBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long userId;

	private String userName;

	private String userFullName;
	
	private String userType;

	private String password;
	
	private String newPassword;
	
	private String oldPassword;
	
	private String confirmPassword;

	private String mobileNumber;

	private String emailId;

	private String floginPwreset;

	private Timestamp lastLogin;

	private Integer lookupDetIdRoleType;
	
	private String lookupDetIdRoleTypeDesc;

	private Integer lookupDetUdGen;

	private String LookupDetDescEn;

	private Date dateofbirth;

	private Date expiryDate;

	private Character statusCheckbox;

	private String orgName;

	private String projName;

	private String supervisorName;
	
	private Integer supervisorId;

	private String supervisorMobNo;

	private Integer lookupDetHierDistrictId;
	
	private String lookupDetHierDistrictIdDesc;

	private Integer lookupDetIdUsrType;
	
	private String lookupDetIdUsrTypeDesc;
	
	private MultipartFile imageUpload;
	
	private String uploadPathUrl;
	
	private Integer hcfId;
	
	private String hcfCode;
	
	private String address;
	
	private String pincode;
	
	private String nameofHcf;
	
	private String vendorName;
	
	private Integer cbwtfId;
	
	private BigInteger totalSurvey;
	
	private BigInteger totalCompleteSurvey;
	
	private String distName;
	
	private String docUploadPath;

}