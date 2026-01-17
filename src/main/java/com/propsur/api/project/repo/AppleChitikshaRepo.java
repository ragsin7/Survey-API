package com.propsur.api.project.repo;

import java.util.List;

public interface AppleChitikshaRepo {

	List<Object[]> getApplechikitshaMapDetails(String hcftype, String ward);

	List<String> getWardList();
}
