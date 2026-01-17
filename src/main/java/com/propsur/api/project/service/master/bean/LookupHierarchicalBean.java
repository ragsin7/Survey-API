package com.propsur.api.project.service.master.bean;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.propsur.api.project.bean.BaseBean;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LookupHierarchicalBean extends BaseBean implements Serializable{
		   
	
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		private Integer lookupDetHierId;
	    
	    private Integer lookupDetId;	

	    private Integer lookupId;	

	    private String lookupDetHierValue;

	    private String lookupDetHierDescEn;

	    private String lookupDetHierDescRg;

	    private Integer lookupDetHierParentId;

	    private Integer lookupDetHierOrderBy;

	    private String lookupDetHierOtherValues;

	    List<LookupHierarchicalBean> distList;
	    
	    List<LookupHierarchicalBean> userList;
	    
	    private String userName;
	    
	    private Long userId;
}
