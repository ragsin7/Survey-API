package com.propsur.api.project.entity.master;

import com.propsur.api.project.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tm_lookup_det", schema = "master")
public class TmLookupDet extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lookup_det_id", nullable = false)
    private Integer lookupDetId;

    @Column(name = "ulb_id", nullable = false)
    private Integer ulbId;

    @ManyToOne
    @JoinColumn(name = "lookup_id", nullable = false)
    private TmLookup tmLookup;

    @Column(name = "lookup_det_value", length = 50, nullable = false)
    private String lookupDetValue;

    @Column(name = "lookup_det_desc_en", length = 200, nullable = false)
    private String lookupDetDescEn;

    @Column(name = "lookup_det_desc_rg", length = 200)
    private String lookupDetDescRg;

    @Column(name = "lookup_det_parent_id")
    private Integer lookupDetParentId;

    @Column(name = "lookup_det_parent_level")
    private Integer lookupDetParentLevel;

    @Column(name = "lookup_det_status", nullable = false)
    private Integer lookupDetStatus;

    @Column(name = "lookup_det_others", length = 200)
    private String lookupDetOthers;

    @Column(name = "lookup_det_default", columnDefinition = "bpchar(10)")
    private String lookupDetDefault;

    @Column(name = "state_ulb_flag", columnDefinition = "bpchar(10)")
    private String stateUlbFlag;

    @Column(name = "lookup_orderby")
    private Integer lookupOrderby;
}
