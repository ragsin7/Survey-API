package com.propsur.api.project.repo.master;

import java.util.List;
import java.util.Objects;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.propsur.api.project.entity.master.TmLookup;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class LookupRepoImpl implements LookupRepo {

	   @Autowired
	    private SessionFactory sessionFactory;

	    @Override
	    public TmLookup findById(Integer lookupId) {
	        try {
	            Session session = sessionFactory.getCurrentSession();
	            return session.get(TmLookup.class, lookupId);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	    
	    @Override
	    public TmLookup findByLookupCode(String lookupCode) {
	        try {
	            Session session = sessionFactory.getCurrentSession();
	            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

	            CriteriaQuery<TmLookup> criteriaQuery = criteriaBuilder.createQuery(TmLookup.class);
	            Root<TmLookup> root = criteriaQuery.from(TmLookup.class);

	            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("lookupCode"), lookupCode));

	            Query<TmLookup> query = session.createQuery(criteriaQuery);
	            return query.uniqueResult();  
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }


	    @Override
	    public List<Object[]> findAll() {
	        try {
	            Session session = sessionFactory.getCurrentSession();
	            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			    CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
			    
			   
	            Root<TmLookup> root = criteriaQuery.from(TmLookup.class);
	            criteriaQuery.multiselect(
				        root.get("lookupId"),
				        root.get("lookupCode"),
				        root.get("lookupDescEn"),
				        root.get("hierarchical"),
				        root.get("hierarchicalNoLevel"),
				        root.get("lookupAddFlag"),
				        root.get("lookupEditFlag"),
				        root.get("status")
				    );
	            Query<Object[]> query = session.createQuery(criteriaQuery);
			    return query.getResultList();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    @Override
	    public TmLookup save(TmLookup tmLookup) {
	        try {
	            Session session = sessionFactory.getCurrentSession();
	            if ( Objects.isNull(tmLookup.getLookupId()) || Objects.isNull(session.find(TmLookup.class, tmLookup.getLookupId()))) {
	                session.persist(tmLookup); // Insert new record if not found
	            } else {
	                session.merge(tmLookup); // Update if record exists
	            }
	            return tmLookup;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    @Override
	    public void updateTmLookup(TmLookup tmLookup) {
	        try {
	            Session session = sessionFactory.getCurrentSession();
	            if (Objects.isNull(session.find(TmLookup.class, tmLookup.getLookupId()))) {
	                session.persist(tmLookup); // Insert new record if not found
	            } else {
	                session.merge(tmLookup); // Update if record exists
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public void delete(Integer lookupId) {
	        try {
	            Session session = sessionFactory.getCurrentSession();
	            TmLookup tmLookup = session.find(TmLookup.class, lookupId);
	            if (tmLookup != null) {
	                session.remove(tmLookup); // Delete the record if found
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public List<Object[]> findByQuery(String query) {
	        try {
	            Session session = sessionFactory.getCurrentSession();
	            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
	            CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

	            Root<TmLookup> root = criteriaQuery.from(TmLookup.class);
	            criteriaQuery.multiselect(
	                    root.get("lookupId"),
	                    root.get("lookupCode"),
	                    root.get("lookupDescEn"),
	                    root.get("hierarchical"),
	                    root.get("hierarchicalNoLevel"),
	                    root.get("lookupAddFlag"),
	                    root.get("lookupEditFlag"),
	                    root.get("status")
	            );

	            // Add WHERE clause for filtering by lookupCode or lookupDescEn
	            Predicate lookupCodePredicate = criteriaBuilder.like(
	                criteriaBuilder.lower(root.get("lookupCode")), "%" + query.toLowerCase() + "%"
	            );
	            Predicate lookupDescPredicate = criteriaBuilder.like(
	                criteriaBuilder.lower(root.get("lookupDescEn")), "%" + query.toLowerCase() + "%"
	            );

	            criteriaQuery.where(criteriaBuilder.or(lookupCodePredicate, lookupDescPredicate));
	            Query<Object[]> queryResult = session.createQuery(criteriaQuery);

	            return queryResult.getResultList();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }


	}
