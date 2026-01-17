package com.propsur.api.project.entity.master;

import com.propsur.api.project.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tm_lookup", schema = "master")
public class TmLookup extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lookup_id", nullable = false)
    private Integer lookupId;

    @Column(name = "lookup_code", length = 5, nullable = false, unique = true)
    private String lookupCode;

    @Column(name = "lookup_desc_en", length = 200, nullable = false)
    private String lookupDescEn;

    @Column(name = "lookup_desc_rg", length = 200)
    private String lookupDescRg;

    @Column(name = "mod_id")
    private Integer modId;

    @Column(name = "hierarchical", columnDefinition = "bpchar(1)")
    private String hierarchical;

    @Column(name = "hierarchical_no_level")
    private Integer hierarchicalNoLevel;

    @Column(name = "lookup_add_flag", columnDefinition = "bpchar(1)")
    private String lookupAddFlag;

    @Column(name = "lookup_edit_flag", columnDefinition = "bpchar(1)")
    private String lookupEditFlag;

    @Column(name = "flag_state_ulb", columnDefinition = "bpchar(1)")
    private String flagStateUlb;

    @Column(name = "lookup_status", columnDefinition = "bpchar(1)", nullable = false)
    private String lookupStatus;

    @Column(name = "ac_map_flag", length = 1)
    private String acMapFlag;

    @Column(name = "det_validation", length = 30)
    private String detValidation;
}
