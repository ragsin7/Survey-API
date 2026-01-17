package com.propsur.api.project.entity.master;
import com.propsur.api.project.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tm_role_menu_map", schema = "master")
@Getter
@Setter
public class TmRoleMenuMap extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_map_id_seq")
    @SequenceGenerator(name = "role_map_id_seq", sequenceName = "user_role_right.tm_cm_role_menu_map_role_map_id_seq", allocationSize = 1)
    @Column(name = "role_map_id", nullable = false)
    private Integer roleMapId;

    @Column(name = "lookup_det_id_role_type")
    private Integer lookupDetIdRoleType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", referencedColumnName = "menu_id")
    private Menu menu;
}
