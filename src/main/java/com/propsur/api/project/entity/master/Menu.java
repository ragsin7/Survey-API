package com.propsur.api.project.entity.master;

import com.propsur.api.project.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tm_menu", schema = "master")
public class Menu extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_id_seq")
    @SequenceGenerator(name = "menu_id_seq", sequenceName = "user_role_right.tm_cm_menu_menu_id_seq", allocationSize = 1)
    @Column(name = "menu_id")
    private Integer menuId;

    @Column(name = "menu_feature_name_en", nullable = false, length = 200)
    private String menuFeatureNameEn;

    @Column(name = "menu_controller", length = 200)
    private String menuController;

    @Column(name = "menu_parent_id")
    private Integer menuParentId;

    @Column(name = "menu_feature_flag", length = 1)
    private String menuFeatureFlag;

    @Column(name = "menusrno", length = 20)
    private String menusrno;

    @Column(name = "top_menu_flag", length = 1, columnDefinition = "bpchar(1) default 'N'")
    private String topMenuFlag = "N";

    @Column(name = "menu_controller_mobile", length = 200)
    private String menuControllerMobile;

    @Column(name = "menu_icon", length = 100)
    private String menuIcon;
    
    @Column(name = "menu_feature_name_rg", nullable = false, length = 200)
    private String menuFeatureNameRg;

}