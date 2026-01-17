package com.propsur.api.project.entity;

import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tm_user", schema = "master")
public class TmUsers extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "user_name", length = 20, unique = true)
    private String userName;

    @Column(name = "user_full_name", length = 200)
    private String userFullName;
    
    @Column(name = "ulb_id")
    private Integer ulbId;

    @Column(name = "passwd", length = 250)
    private String password;

    @Column(name = "mobile_number", length = 20)
    private String mobileNumber;

    @Column(name = "email_id", length = 250)
    private String emailId;

    @Column(name = "flogin_pwreset", columnDefinition = "bpchar(1)")
    private String floginPwreset;

    @Column(name = "last_login")
    private Timestamp lastLogin;

    @Column(name = "lookup_det_id_role_type")
    private Integer lookupDetIdRoleType;

    @Column(name = "expiry_date")
    private Date expiryDate;
        
    @Column(name = "date_of_birth")
    private Date dateofbirth;
    
    @Column(name = "lookup_det_id_gen")
    private Integer lookupDetUdGen;
    
    @Column(name = "lookup_det_hier_district_id")
    private Integer lookupDetHierDistrictId;

    @Column(name = "lookup_det_id_usr_type")
    private Integer lookupDetIdUsrType;

    @Column(name = "upload_path_url")
    private String uploadPathUrl;
}
