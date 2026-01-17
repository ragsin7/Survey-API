package com.propsur.api.project.bean;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseBean {
	
    private Integer status;

    private Integer createdBy;

    private Date createdDate;

    private Integer updatedBy;

    private Date updatedDate;

    private String macId;

    private String ipAddress;

    private Character deviceFrom;

	
}
