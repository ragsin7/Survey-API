package com.propsur.api.project.repo.master;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.propsur.api.project.entity.master.TmLookup;
import com.propsur.api.project.entity.master.TmLookupDet;
import com.propsur.api.project.entity.master.TmLookupDetHierarchical;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class LookupDetHierarchicalRepoImpl implements LookupDetHierarchicalRepo {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public TmLookupDetHierarchical findById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(TmLookupDetHierarchical.class, id);
    }

    @Override
    public List<TmLookupDetHierarchical> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM TmLookupDetHierarchical", TmLookupDetHierarchical.class).getResultList();
    }

    @Override
    public TmLookupDetHierarchical save(TmLookupDetHierarchical tmLookupDetHierarchical) {
		Session session = sessionFactory.getCurrentSession();

        if (Objects.isNull(tmLookupDetHierarchical.getLookupDetHierId())|| Objects.isNull(session.get(TmLookupDetHierarchical.class, tmLookupDetHierarchical.getLookupDetHierId()))) {
            session.persist(tmLookupDetHierarchical);
	        session.flush();
        } else {
            session.merge(tmLookupDetHierarchical);
        }
        return tmLookupDetHierarchical;
    }


    @Override
    public void delete(Integer id) {
   //     Session session = sessionFactory.getCurrentSession();
    //    TmLookupDetHierarchical tmLookupDetHierarchical = session.get(TmLookupDetHierarchical.class, id);
//        if (tmLookupDetHierarchical != null) {
//            session.delete(tmLookupDetHierarchical);
//        }
    }

    public List<Object[]> getLookupHirarchicaldata(Integer lookupDetId, String lookupcode) {
        StringBuilder sql = new StringBuilder("SELECT ")
                .append("three.lookup_det_hier_id, ")
                .append("three.lookup_det_hier_desc_en ")
                .append("FROM ")
                .append("master.tm_lookup one ")
                .append("CROSS JOIN master.tm_lookup_det two ")
                .append("CROSS JOIN master.tm_lookup_det_hierarchical three ")
                .append("WHERE ")
                .append("one.lookup_id = two.lookup_id ")
                .append("AND two.lookup_det_id = three.lookup_det_id ");
        
        if (lookupcode != null) {
            sql.append("AND one.lookup_code = :lookupCode ")
               .append("AND two.lookup_det_id = :lookupDetId ");
        } else {
            sql.append("AND three.lookup_det_hier_parent_id = :lookupDetId ");
        }

        Query query = sessionFactory.getCurrentSession().createNativeQuery(sql.toString());

        if (lookupcode != null) {
            query.setParameter("lookupCode", lookupcode);
            query.setParameter("lookupDetId", lookupDetId);
        } else {
            query.setParameter("lookupDetId", lookupDetId);
        }

        List<Object[]> result = query.getResultList();

        if (result == null || result.isEmpty()) {
            return Collections.emptyList();
        }
        return result;
    }


    @Override
    public Object[] getLookupHirarchicalDataByLookupDet(Integer lookupId, Integer lookupDetId) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<Object[]> searchQuery = builder.createQuery(Object[].class);
        Root<TmLookupDet> lDetRoot = searchQuery.from(TmLookupDet.class);
        Root<TmLookup> lRoot = searchQuery.from(TmLookup.class);

        searchQuery.multiselect(
            lDetRoot.get("lookupDetId"),
            lRoot.get("lookupId"), lRoot.get("lookupCode"), lRoot.get("lookupDescEn"), lRoot.get("lookupDescRg"),
            lDetRoot.get("lookupDetValue"), lDetRoot.get("lookupDetDescEn"), lDetRoot.get("lookupDetDescRg"),
            lDetRoot.get("lookupDetParentId"), lDetRoot.get("lookupDetParentLevel"),
            lDetRoot.get("status")
        );

        Predicate where = builder.equal(lDetRoot.get("tmLookup").get("lookupId"), lRoot.get("lookupId"));
        where = builder.and(where, builder.equal(lDetRoot.get("status"), 1));
        if (lookupId != 0)
            where = builder.and(where, builder.equal(lRoot.get("lookupId"), lookupId));
        if (lookupDetId != 0)
            where = builder.and(where, builder.equal(lDetRoot.get("lookupDetId"), lookupDetId));

        searchQuery.where(where);

        Query<Object[]> query = session.createQuery(searchQuery);
        query.setMaxResults(1);

        return query.uniqueResultOptional().orElse(null);
    }

	@Override
	public List<TmLookupDetHierarchical> getLopokupHierarchicalDataById(Integer lookupDetId) {
		 try {
	            Session session = sessionFactory.getCurrentSession();
	            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
	            CriteriaQuery<TmLookupDetHierarchical> criteriaQuery = criteriaBuilder.createQuery(TmLookupDetHierarchical.class);
	            Root<TmLookupDetHierarchical> root = criteriaQuery.from(TmLookupDetHierarchical.class);

	            Predicate lookupDetCodePredicate = criteriaBuilder.equal(root.get("tmLookupDet").get("lookupDetId"), lookupDetId);
	            Predicate lookupDetCodePredicateStatus = criteriaBuilder.equal(root.get("status"), 1);
	            
	            criteriaQuery.select(root)
	            .where(criteriaBuilder.and(lookupDetCodePredicate,lookupDetCodePredicateStatus))
	            .orderBy(criteriaBuilder.asc(root.get("lookupDetHierOrderBy")));

	            
	            Query<TmLookupDetHierarchical> query = session.createQuery(criteriaQuery);
	            return query.getResultList();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	}

	@Override
	public List<TmLookupDetHierarchical> findByLookupHierarchicalLkpCodeandDetCode(String lookupCode,String lookupDetvalue) {
		   try {
	            Session session = sessionFactory.getCurrentSession();
	            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
	            CriteriaQuery<TmLookupDetHierarchical> criteriaQuery = criteriaBuilder.createQuery(TmLookupDetHierarchical.class);
	            Root<TmLookupDetHierarchical> root = criteriaQuery.from(TmLookupDetHierarchical.class);

	            Predicate lookupDetCodePredicate = criteriaBuilder.equal(root.get("tmLookupDet").get("lookupDetValue"), lookupDetvalue);
	            Predicate lookupDetCodePredicate1 = criteriaBuilder.equal(root.get("tmLookupDet").get("tmLookup").get("lookupCode"), lookupCode);
	            Predicate lookupDetCodePredicateStatus = criteriaBuilder.equal(root.get("status"), 1);
	            criteriaQuery.select(root)
	            .where(criteriaBuilder.and(lookupDetCodePredicate,lookupDetCodePredicate1,lookupDetCodePredicateStatus))
	            .orderBy(criteriaBuilder.asc(root.get("tmLookupDet").get("lookupDetParentLevel")));

	            
	            Query<TmLookupDetHierarchical> query = session.createQuery(criteriaQuery);
	            return query.getResultList();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    
	}

	@Override
	public List<TmLookupDetHierarchical> getLopokupHierarchicalDataByParentId(Integer lookupDetHeirId) {

		 try {
	            Session session = sessionFactory.getCurrentSession();
	            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
	            CriteriaQuery<TmLookupDetHierarchical> criteriaQuery = criteriaBuilder.createQuery(TmLookupDetHierarchical.class);
	            Root<TmLookupDetHierarchical> root = criteriaQuery.from(TmLookupDetHierarchical.class);

	            Predicate lookupDetCodePredicate = criteriaBuilder.equal(root.get("lookupDetHierParentId"), lookupDetHeirId);
	            Predicate lookupDetCodePredicateStatus = criteriaBuilder.equal(root.get("status"), 1);
	            
	            criteriaQuery.select(root)
	            .where(criteriaBuilder.and(lookupDetCodePredicate,lookupDetCodePredicateStatus))
	            .orderBy(criteriaBuilder.asc(root.get("lookupDetHierOrderBy")));

	            
	            Query<TmLookupDetHierarchical> query = session.createQuery(criteriaQuery);
	            return query.getResultList();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	}

}