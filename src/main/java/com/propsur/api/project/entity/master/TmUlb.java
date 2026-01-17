package com.propsur.api.project.entity.master;

import java.math.BigDecimal;
import java.util.Date;

import com.propsur.api.project.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tm_ulb", schema = "master")
public class TmUlb extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ulb_id", nullable = false)
    private Integer ulbId;

    @Column(name = "ulb_name", length = 255, nullable = false)
    private String ulbName;

    @Column(name = "ulb_name_local", length = 255)
    private String ulbNameLocal;

    @Column(name = "address", columnDefinition = "text")
    private String address;

    @Column(name = "ulb_code", length = 50, unique = true)
    private String ulbCode;

    @Column(name = "go_live_date")
    private Date goLiveDate;

    @Column(name = "state")
    private Integer state;

    @Column(name = "district")
    private Integer district;

    @Column(name = "latitude", precision = 10, scale = 7)
    private BigDecimal latitude;

    @Column(name = "longitude", precision = 10, scale = 7)
    private BigDecimal longitude;
}
