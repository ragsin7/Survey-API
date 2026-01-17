package com.propsur.api.project.service.master.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TmRoleMenuMapBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer roleMapId;
    private Integer lookupDetIdRoleType;
    private Integer menuId;
    private Integer status;
    private Integer createdBy;
    private Date createdDate;
    private Integer updatedBy;
    private Date updatedDate;
    private String macId;
    private String ipAddress;
    private String deviceFrom;
    private List<SubmenuDetail> submenuIds;
    private List<SubmenuDetail> uncheckedMenus;
    private List<TmRoleMenuMapBean> menuDataList;
    private String menuFeatureNameEn;
    private String parentMenuName;
    
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class SubmenuDetail implements Serializable {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Integer menuId;  
        private Integer roleMapId;
       
    }
}