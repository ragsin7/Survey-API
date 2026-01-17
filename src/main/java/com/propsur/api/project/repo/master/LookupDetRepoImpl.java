package com.propsur.api.project.repo.master;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.propsur.api.project.entity.master.TmLookup;
import com.propsur.api.project.entity.master.TmLookupDet;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class LookupDetRepoImpl implements LookupDetRepo {

    @Autowired
    private org.hibernate.SessionFactory sessionFactory;

    @Override
    public TmLookupDet findById(Integer lookupDetId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(TmLookupDet.class, lookupDetId);
    }

    @Override
    public List<TmLookupDet> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM TmLookupDet", TmLookupDet.class).getResultList();
    }
    
    
    @Override
    public List<TmLookupDet> save(List<TmLookupDet> tmLookupDetList) {
        Session session = sessionFactory.getCurrentSession();
        List<TmLookupDet> lookupdet = new ArrayList<>();
        int batchSize = 50;
        int count = 0;

        for (TmLookupDet tmLookupDet : tmLookupDetList) {
            if (tmLookupDet.getLookupDetId() == null || session.get(TmLookupDet.class, tmLookupDet.getLookupDetId()) == null) {
                session.persist(tmLookupDet);
                session.flush();
            } else {
                session.merge(tmLookupDet);
            }
            lookupdet.add(tmLookupDet);

            // Batch processing
            if (++count % batchSize == 0) {
                session.flush();
                session.clear();
            }
        }
        session.flush(); 
        session.clear();
        return lookupdet;
    }



    @Override
    public void updateTmLookupDet(TmLookupDet tmLookupDet) {
        Session session = sessionFactory.getCurrentSession();
        if (Objects.isNull(session.get(TmLookupDet.class, tmLookupDet.getLookupDetId()))) {
            session.persist(tmLookupDet);
        } else {
            session.merge(tmLookupDet);
        }
    }

    @Override
    public void delete(Integer lookupDetId) {
        Session session = sessionFactory.getCurrentSession();
        TmLookupDet tmLookupDet = session.get(TmLookupDet.class, lookupDetId);
        if (tmLookupDet != null) {
            session.remove(tmLookupDet);
        }
    }
    
    @Override
    public TmLookupDet findByLookupDetCode(String lookupCode,String lookupDetCode) {
        try {
            Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<TmLookupDet> criteriaQuery = criteriaBuilder.createQuery(TmLookupDet.class);
            Root<TmLookupDet> root = criteriaQuery.from(TmLookupDet.class);

            Predicate lookupDetCodePredicate = criteriaBuilder.equal(root.get("lookupDetValue"), lookupDetCode);
            Predicate lookupCodePredicate = criteriaBuilder.equal(root.get("tmLookup").get("lookupCode"), lookupCode);

            criteriaQuery.select(root)
                         .where(criteriaBuilder.and(lookupDetCodePredicate, lookupCodePredicate));

            Query<TmLookupDet> query = session.createQuery(criteriaQuery);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

	@Override
	public List<TmLookupDet> findByLookupDetLkpCode(String lookupCode) {
		
		try {
		    Session session = sessionFactory.getCurrentSession();
		    CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		    CriteriaQuery<TmLookupDet> criteriaQuery = criteriaBuilder.createQuery(TmLookupDet.class);
		    Root<TmLookupDet> root = criteriaQuery.from(TmLookupDet.class);

		    // Base conditions
		    Predicate lookupDetCodePredicate = criteriaBuilder.equal(root.get("tmLookup").get("lookupCode"), lookupCode);
		    Predicate statusPredicate = criteriaBuilder.equal(root.get("status"), 1);

		    // Combine conditions using AND
		    List<Predicate> predicates = new ArrayList<>();
		    predicates.add(lookupDetCodePredicate);
		    predicates.add(statusPredicate);

		    // Additional dynamic conditions can be added here
		    // For example, you could add more conditions like:
		    // predicates.add(criteriaBuilder.equal(root.get("someField"), someValue));

		    // Apply the combined predicates
		    criteriaQuery.select(root)
		        .where(criteriaBuilder.and(predicates.toArray(new Predicate[0])))
		        .orderBy(criteriaBuilder.asc(root.get("lookupDetParentLevel")));

		    // Execute query
		    Query<TmLookupDet> query = session.createQuery(criteriaQuery);
		    return query.getResultList();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<TmLookupDet> findByLookupDetByLookupId(Integer lookupId) {
		 try {
	            Session session = sessionFactory.getCurrentSession();
	            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
	            CriteriaQuery<TmLookupDet> criteriaQuery = criteriaBuilder.createQuery(TmLookupDet.class);
	            Root<TmLookupDet> root = criteriaQuery.from(TmLookupDet.class);

	            Predicate lookupDetCodePredicate = criteriaBuilder.equal(root.get("tmLookup").get("lookupId"), lookupId);

	            criteriaQuery.select(root)
	            .where(criteriaBuilder.and(lookupDetCodePredicate))
	            .orderBy(criteriaBuilder.asc(root.get("lookupDetParentLevel")));

	            
	            Query<TmLookupDet> query = session.createQuery(criteriaQuery);
	            return query.getResultList();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    
	}

	@Override
	public TmLookupDet save(TmLookupDet tmLookupDet) {
		Session session = sessionFactory.getCurrentSession();

	    if (tmLookupDet.getLookupDetId() == null || session.get(TmLookupDet.class, tmLookupDet.getLookupDetId()) == null) {
	        session.persist(tmLookupDet); // Save new entity
	        session.flush(); // Ensure ID is generated immediately
	    } else {
	        session.merge(tmLookupDet); // Update existing entity
	    }

	    return tmLookupDet;
	    
	}

	@Override
	public List<TmLookupDet> getVehicleUser(String lookupCode, String lookupDetCode) {
		try {
	        Session session = sessionFactory.getCurrentSession();
	        CriteriaBuilder cb = session.getCriteriaBuilder();

	        CriteriaQuery<TmLookupDet> cq = cb.createQuery(TmLookupDet.class);
	        Root<TmLookupDet> root = cq.from(TmLookupDet.class);

	        // Join with TmLookup
	        Join<TmLookupDet, TmLookup> joinLookup = root.join("tmLookup");

	        // Build predicates
	        Predicate codePredicate = cb.equal(joinLookup.get("lookupCode"), lookupCode);
	        Predicate detCodePredicate = cb.equal(root.get("lookupDetValue"), lookupDetCode);

	        // Final query
	        cq.select(root).where(cb.and(codePredicate, detCodePredicate));

	        Query<TmLookupDet> query = session.createQuery(cq);
	        return query.getResultList();

	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	@Override
	public Object[] getUserType(Long userId) {
	    try {
	        // The query using HQL with the necessary join and field selection
	        String hql = "SELECT u.userId, u.userName, l.lookupDetValue " +
	                     "FROM TmUsers u " +
	                     "LEFT JOIN TmLookupDet l ON u.lookupDetIdRoleType = l.lookupDetId " +
	                     "WHERE u.userId = :userId";

	        // Get the session and execute the query
	        Session session = sessionFactory.getCurrentSession();
	        Query<Object[]> query = session.createQuery(hql, Object[].class);
	        query.setParameter("userId", userId);

	        // Get the result of the query
	        Object[] result = query.getSingleResult(); // This will return Object[] containing the desired fields
	        return result;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	@Override
	public Object[] getRoleTypeById(Long userId) {
		try {
			String hql = "SELECT tld.lookupDetValue " +
		             "FROM TmUsers tu " +
		             "LEFT JOIN TmLookupDet tld " +
		             " on tu.lookupDetIdRoleType = tld.lookupDetId" +
		             " WHERE tu.userId = :userId";
			Session session =sessionFactory.getCurrentSession();
			Query<Object[]> query = session.createQuery(hql, Object[].class);
			query.setParameter("userId", userId);
			Object[] result = query.getSingleResult();
				return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}



}