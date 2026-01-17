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
@Table(name = "tm_lookup_det_hierarchical", schema = "master")
public class TmLookupDetHierarchical extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lookup_det_hier_id", nullable = false)
    private Integer lookupDetHierId;

    @ManyToOne
    @JoinColumn(name = "lookup_det_id", nullable = false)
    private TmLookupDet tmLookupDet;

    @Column(name = "ulb_id", nullable = false)
    private Integer ulbId;

    @Column(name = "lookup_det_hier_value", length = 50, nullable = false)
    private String lookupDetHierValue;

    @Column(name = "lookup_det_hier_desc_en", length = 200, nullable = false)
    private String lookupDetHierDescEn;

    @Column(name = "lookup_det_hier_desc_rg", length = 200, nullable = false)
    private String lookupDetHierDescRg;

    @Column(name = "lookup_det_hier_parent_id")
    private Integer lookupDetHierParentId;

    @Column(name = "lookup_det_hier_order_by")
    private Integer lookupDetHierOrderBy;
}
