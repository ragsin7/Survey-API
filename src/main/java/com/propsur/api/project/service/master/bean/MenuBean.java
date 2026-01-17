package com.propsur.api.project.service.master.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuBean implements Serializable {

    private static final long serialVersionUID = 1L;
	private Integer menuId;
    private Integer roleMapId;
    private String menuFeatureNameEn;
    private String menuController;
    private Integer menuParentId;
    private String menuFeatureFlag;
    private String menusrno;
    private String topMenuFlag = "N";
    private Integer createdBy;
    private Date createdDate;
    private Integer updatedBy;
    private Date updatedDate;
    private String macId;
    private String ipAddress;
    private String deviceFrom;
    private Integer status;
    private String menuControllerMobile;
    private String menuIcon;
    private String mode;
    private Integer lookupDetIdRoleType;
    private String menuFeatureNameRg;

  

    List<MenuBean> childMenuLevel1;
    List<MenuBean> childMenuLeve2;

}
