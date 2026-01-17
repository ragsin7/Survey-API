package com.propsur.api.project.service.master.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.propsur.api.project.bean.BaseBean;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LookupBean extends BaseBean implements Serializable {
	
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		private Integer lookupId;

	    private String lookupCode;

	    private String lookupDescEn;

	    private String lookupDescRg;

	    private String hierarchical;

	    private Integer hierarchicalNoLevel;

	    private String lookupAddFlag;

	    private String lookupEditFlag;

	    private String flagAdminOrg;

}
