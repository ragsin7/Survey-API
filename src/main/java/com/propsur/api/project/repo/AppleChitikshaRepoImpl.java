package com.propsur.api.project.repo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AppleChitikshaRepoImpl implements AppleChitikshaRepo{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Object[]> getApplechikitshaMapDetails(String hcftype, String ward) {
	    StringBuilder sb = new StringBuilder();

	    /*
	    queryBuilder.append("SELECT ")
	        .append("map_id, zone, location, ward, category, name, structure_type, time_slot, function_time, ")
	        .append("working_days, working_hrs, address, landmark, ")
	        .append("CASE WHEN lat LIKE '%S' THEN -1 ELSE 1 END * (")
	        .append("split_part(lat, '°', 1)::double precision + ")
	        .append("split_part(split_part(lat, '°', 2), '''', 1)::double precision / 60 + ")
	        .append("split_part(split_part(lat, '''', 2), '\"', 1)::double precision / 3600")
	        .append(") AS latitude_decimal, ")
	        .append("CASE WHEN long LIKE '%W' THEN -1 ELSE 1 END * (")
	        .append("split_part(long, '°', 1)::double precision + ")
	        .append("split_part(split_part(long, '°', 2), '''', 1)::double precision / 60 + ")
	        .append("split_part(split_part(long, '''', 2), '\"', 1)::double precision / 3600")
	        .append(") AS longitude_decimal, ")
	        .append("google_map_link, json_lat_long, status, created_by, created_date, updated_by, updated_date ")
	        .append("FROM chikitsa_dev.mst_map_details ")
	        .append("WHERE lat IS NOT NULL AND long IS NOT NULL ")
	        .append("AND lat <> '' AND long <> '' ")
	        .append("AND (lat LIKE '%°%' OR lat LIKE '%Â°%') ")
	        .append("AND (long LIKE '%°%' OR long LIKE '%Â°%') ");
          */
	    
	    sb.append("SELECT ");
	    sb.append("    map_id, zone, location, ward, category, name, structure_type, ");
	    sb.append("    time_slot, function_time, working_days, working_hrs, ");
	    sb.append("    address, landmark, ");
	    sb.append("    CASE ");
	    sb.append("        WHEN lat LIKE '%°%' THEN ");
	    sb.append("            CASE WHEN lat LIKE '%S' THEN -1 ELSE 1 END * ");
	    sb.append("            ( ");
	    sb.append("                split_part(lat, '°', 1)::double precision + ");
	    sb.append("                split_part(split_part(lat, '°', 2), '''', 1)::double precision / 60 + ");
	    sb.append("                split_part(split_part(lat, '''', 2), '\"', 1)::double precision / 3600 ");
	    sb.append("            ) ");
	    sb.append("        ELSE lat::double precision ");
	    sb.append("    END AS latitude_decimal, ");
	    sb.append("    CASE ");
	    sb.append("        WHEN long LIKE '%°%' THEN ");
	    sb.append("            CASE WHEN long LIKE '%W' THEN -1 ELSE 1 END * ");
	    sb.append("            ( ");
	    sb.append("                split_part(long, '°', 1)::double precision + ");
	    sb.append("                split_part(split_part(long, '°', 2), '''', 1)::double precision / 60 + ");
	    sb.append("                split_part(split_part(long, '''', 2), '\"', 1)::double precision / 3600 ");
	    sb.append("            ) ");
	    sb.append("        ELSE long::double precision ");
	    sb.append("    END AS longitude_decimal, ");
	    sb.append("    google_map_link, json_lat_long, status, created_by, created_date, updated_by, updated_date ");
	    sb.append("FROM chikitsa_dev.mst_map_details ");
	    sb.append("WHERE TRIM(lat) <> '' AND TRIM(long) <> '' ");
	    sb.append("  AND lat IS NOT NULL AND long IS NOT NULL ");

	    
	    
	    
	    if (hcftype != null && !hcftype.trim().isEmpty()) {
	    	 sb.append(" AND category ILIKE :hcftype ");
	    }
	    
	    if (ward != null && !ward.trim().isEmpty()) {
	        sb.append(" AND ward = :ward ");
	    }

	    Session session = sessionFactory.getCurrentSession();
	    NativeQuery<Object[]> nativeQuery = session.createNativeQuery(sb.toString(), Object[].class);

	    if (hcftype != null && !hcftype.trim().isEmpty()) {
	    	 nativeQuery.setParameter("hcftype", "%" + hcftype.trim() + "%"); 
	    }
	    
	    if (ward != null && !ward.trim().isEmpty()) {
	    	 nativeQuery.setParameter("ward", ward);
	    }


	    return nativeQuery.getResultList();
	}

	@Override
	public List<String> getWardList() {

	    StringBuilder queryBuilder = new StringBuilder();

	    queryBuilder.append("SELECT distinct ward  FROM chikitsa_dev.mst_map_details ")
	        .append(" where status=1 ");

	    Session session = sessionFactory.getCurrentSession();
	    NativeQuery<String> nativeQuery = session.createNativeQuery(queryBuilder.toString(), String.class);


	    return nativeQuery.getResultList();
	}
}
