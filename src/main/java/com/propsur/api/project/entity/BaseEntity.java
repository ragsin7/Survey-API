package com.propsur.api.project.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public class BaseEntity {

    @Column(name = "status")
    private Integer status;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "created_date")
    private Date createdDate;
    
    
    @Column(name = "updated_by")
    private Integer updatedBy;

    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name = "mac_id", length = 50)
    private String macId;

    @Column(name = "ip_address", length = 50)
    private String ipAddress;

    @Column(name = "device_from", columnDefinition = "bpchar(1)")
    private Character deviceFrom;
    
}
