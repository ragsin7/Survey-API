package com.propsur.api.project.service.master.bean;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.propsur.api.project.bean.BaseBean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LookupDetBean extends BaseBean implements Serializable{

	     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		private Integer lookupId;

	    private Integer lookupDetId;	   

	    private String lookupDetValue;
	    
	    private String lookupDescEn;

	    private String lookupDetDescEn;

	    private String lookupDetDescRg;

	    private Integer lookupDetParentId;

	    private Integer lookupDetParentLevel;

	    private String lookupDetOthers;

	    private String lookupDetDefault;

	    private String stateOrgFlag;

	    private Integer lookupOrderby;

}
