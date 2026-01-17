package com.propsur.api.project.repo;

import java.util.List;
import java.util.Objects;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.propsur.api.project.entity.TmUsers;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public  class UserRepositoryImpl implements UserRepository {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Object[]> getUser() {
    try {
	  Session session = sessionFactory.getCurrentSession();
      CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
      CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
      Root<TmUsers> root = criteriaQuery.from(TmUsers.class);
      criteriaQuery.multiselect(root);
      Query<Object[]> query = session.createQuery(criteriaQuery);
      return  query.getResultList();
  }catch(Exception e) {
      e.printStackTrace();
  }
		return null;
	}

	
	@Override
	public TmUsers findByLoginName(String username) {
		// Open a session
		Session session = sessionFactory.getCurrentSession();

		    // Create CriteriaBuilder and CriteriaQuery
		    CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		    CriteriaQuery<TmUsers> criteriaQuery = criteriaBuilder.createQuery(TmUsers.class);

		    Root<TmUsers> root = criteriaQuery.from(TmUsers.class);

		    criteriaQuery.select(root);

		    Predicate usernamePredicate = criteriaBuilder.equal(root.get("userName"), username);
		    criteriaQuery.where(usernamePredicate);

		    Query<TmUsers> query = session.createQuery(criteriaQuery);
		    TmUsers result = query.uniqueResult();

		    return result;
		

	}


	@Override
	public List<TmUsers> findAll() {
		 try {
			  Session session = sessionFactory.getCurrentSession();
		      CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		      CriteriaQuery<TmUsers> criteriaQuery = criteriaBuilder.createQuery(TmUsers.class);
		      Root<TmUsers> root = criteriaQuery.from(TmUsers.class);
		      criteriaQuery.select(root);
		      Query<TmUsers> query = session.createQuery(criteriaQuery);
		      return  query.getResultList();
		  }catch(Exception e) {
		      e.printStackTrace();
		  }
				return null;
	}


	@Override
	public TmUsers save(TmUsers user) {
		Session session= sessionFactory.getCurrentSession();
		if ( Objects.isNull(user.getUserId()) ||Objects.isNull(session.find(TmUsers.class, user.getUserId()))) {
		    session.persist(user);
		} else {
		    session.merge(user);
		}
		return user;
	}
	
	@Override
	public void updateTmUsers(TmUsers obj) {
		Session session= sessionFactory.getCurrentSession();
		session.merge(obj);
		}


	@Override
	public TmUsers getUserById(Long userId) {

		try {
	        Session session = sessionFactory.getCurrentSession();
	        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
	        CriteriaQuery<TmUsers> criteriaQuery = criteriaBuilder.createQuery(TmUsers.class);
	        
	        Root<TmUsers> root = criteriaQuery.from(TmUsers.class);
	        criteriaQuery.select(root)
	                     .where(criteriaBuilder.equal(root.get("userId"), userId));
	        
	        return session.createQuery(criteriaQuery).uniqueResult();
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Error fetching user with ID: " + userId, e);
	    }
	
	}


	@Override
	public List<Object[]> getAllUser() {
		try {
		    Session session = sessionFactory.getCurrentSession();
		    CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

		    // Create CriteriaQuery for TmUsers
		    CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

		    // Define roots for both tables
		    Root<TmUsers> root = criteriaQuery.from(TmUsers.class);
		   // Root<TmLookupDet> lookupRoot = criteriaQuery.from(TmLookupDet.class);

		    // Add join condition
//		    Predicate joinCondition = criteriaBuilder.equal(
//		            root.get("lookupDetIdRoleType"),
//		            lookupRoot.get("lookupDetId")
//		    );

		    // Add select columns
		    criteriaQuery.multiselect(
		        root.get("userId"),
		        root.get("emailId"),
		        root.get("userFullName"),
		        root.get("lookupDetIdRoleType"),
		        root.get("mobileNumber"),
		        root.get("status"),
		        root.get("userName")
		        //lookupRoot.get("lookupDetDescEn")
		    );

		    // Apply the join condition
		   // criteriaQuery.where(joinCondition);

		    // Add order by clause
		    criteriaQuery.orderBy(criteriaBuilder.desc(root.get("userId")));

		    // Execute the query
		    Query<Object[]> query = session.createQuery(criteriaQuery);
		    return query.getResultList();

		} catch (Exception e) {
		    e.printStackTrace();
		}
		return null;
	
		
}


	@Override
	public List<Object[]> getUserByUserNmae(Integer roletype, String username) {
	    try {
	        Session session = sessionFactory.getCurrentSession();
	        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

	        // Create CriteriaQuery for TmUsers
	        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

	        // Define roots for both tables
	        Root<TmUsers> root = criteriaQuery.from(TmUsers.class);
	        //Root<TmLookupDet> lookupRoot = criteriaQuery.from(TmLookupDet.class);

	        // Add join condition
//	        Predicate joinCondition = criteriaBuilder.equal(
//	                root.get("lookupDetIdRoleType"),
//	                lookupRoot.get("lookupDetId")
//	        );

	        // Add predicates for filtering
	        Predicate roleTypeCondition = criteriaBuilder.equal(root.get("lookupDetIdRoleType"), roletype);

	        Predicate statusCondition = criteriaBuilder.equal(root.get("status"), 1);

	        
	        Predicate usernameCondition = criteriaBuilder.like(
	                criteriaBuilder.lower(root.get("userName")), 
	                "%" + (username != null ? username.toLowerCase() : "") + "%"
	        );

	        // Combine conditions
	      //  Predicate finalCondition = criteriaBuilder.and(joinCondition, roleTypeCondition, statusCondition);

//	        if (username != null && !username.isEmpty()) {
//	            finalCondition = criteriaBuilder.and(finalCondition, usernameCondition);
//	        }

	        // Add select columns
	        criteriaQuery.multiselect(
	            root.get("userId"),
	            root.get("emailId"),
	            root.get("userFullName"),
	            root.get("lookupDetIdRoleType"),
	            root.get("mobileNumber"),
	            root.get("status"),
	            root.get("userName")
	         //   lookupRoot.get("lookupDetDescEn")
	        );

	        // Apply the combined condition
	       // criteriaQuery.where(finalCondition);

	        // Add order by clause
	        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("userId")));

	        // Execute the query
	        Query<Object[]> query = session.createQuery(criteriaQuery);
	        return query.getResultList();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}

/*
	@Override
	public void SaveTtUserLoginLogoutLogs(TtUserLoginLogoutLogs ttUserLoginLogoutLogs) {
		Session session= sessionFactory.getCurrentSession();
		if ( Objects.isNull(ttUserLoginLogoutLogs.getUserLoginOutId()) ||Objects.isNull(session.find(TtUserLoginLogoutLogs.class, ttUserLoginLogoutLogs.getUserLoginOutId()))) {
		    session.persist(ttUserLoginLogoutLogs);
		} else {
		    session.merge(ttUserLoginLogoutLogs);
		}
	
		
	}
*/

	@Override
	public List<Object[]> getHcfMasterDropDown(Integer distId) {
		try {

			Session session = sessionFactory.getCurrentSession();
			String hql = "select hcf.hcfId, hcf.hcfCode, hcf.nameOfHcf, hcf.address1, hcf.pincode " +
		             "from TtHcfMaster hcf " +
		             "where hcf.status = 1 ";
			if(distId !=null && distId !=0)
				hql=hql+" and hcf.lookupDetHierIdDistrict = "+distId+"";
		Query<Object[]> query = session.createQuery(hql, Object[].class);		
		return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public List<Object[]> getAllusers(Integer roletype, String username) {
		  try {
		        Session session = sessionFactory.getCurrentSession();
		        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

		        // Create CriteriaQuery for TmUsers
		        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

		        // Define roots for both tables
		        Root<TmUsers> root = criteriaQuery.from(TmUsers.class);
		        //Root<TmLookupDet> lookupRoot = criteriaQuery.from(TmLookupDet.class);

		        // Add join condition
//		        Predicate joinCondition = criteriaBuilder.equal(
//		                root.get("lookupDetIdRoleType"),
//		                lookupRoot.get("lookupDetId")
//		        );

		        // Add predicates for filtering
		        Predicate roleTypeCondition = criteriaBuilder.equal(root.get("lookupDetIdRoleType"), roletype);

		        Predicate usernameCondition = criteriaBuilder.like(
		                criteriaBuilder.lower(root.get("userName")), 
		                "%" + (username != null ? username.toLowerCase() : "") + "%"
		        );

		        // Combine conditions
		      //  Predicate finalCondition = criteriaBuilder.and(joinCondition, roleTypeCondition);

//		        if (username != null && !username.isEmpty()) {
//		            finalCondition = criteriaBuilder.and(finalCondition, usernameCondition);
//		        }

		        // Add select columns
		        criteriaQuery.multiselect(
		            root.get("userId"),
		            root.get("emailId"),
		            root.get("userFullName"),
		            root.get("lookupDetIdRoleType"),
		            root.get("mobileNumber"),
		            root.get("status"),
		            root.get("userName")
		            //lookupRoot.get("lookupDetDescEn")
		        );

		        // Apply the combined condition
		       // criteriaQuery.where(finalCondition);

		        // Add order by clause
		        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("userId")));

		        // Execute the query
		        Query<Object[]> query = session.createQuery(criteriaQuery);
		        return query.getResultList();

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return null;
		
	}


	@Override
	public List<Object[]> getUserDetailsByRole(Integer roletype, String username) {
		try {
			String sql="SELECT * from survey.fn_get_surveyor_summary_details(:username,:roletype)";
			Session session = sessionFactory.getCurrentSession();
			NativeQuery query = session.createNativeQuery(sql, Object[].class);
			query.setParameter("username", username);
			query.setParameter("roletype", roletype);
			List<Object[]> result = query.getResultList();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Object[]> getusersLstByIds(Integer districtId) {
	    try {
	        Session session = sessionFactory.getCurrentSession();
	        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

	        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

	        Root<TmUsers> root = criteriaQuery.from(TmUsers.class);
	       // Root<TmLookupDet> lookupRoot = criteriaQuery.from(TmLookupDet.class);

//	        Predicate joinCondition = criteriaBuilder.equal(
//	            root.get("lookupDetIdRoleType"),
//	            lookupRoot.get("lookupDetId")
//	        );

	        Predicate status = criteriaBuilder.equal(root.get("status"), 1);

	        Predicate finalPredicate;

	        if (districtId != null && districtId != 0) {
	            Predicate districtWise = criteriaBuilder.equal(root.get("lookupDetHierDistrictId"), districtId);
	            //finalPredicate = criteriaBuilder.and(joinCondition, status, districtWise);
	        } else {
	            //finalPredicate = criteriaBuilder.and(joinCondition, status);
	        }

	        criteriaQuery.multiselect(
	            root.get("userId"),
	            root.get("emailId"),
	            root.get("userFullName"),
	            root.get("lookupDetIdRoleType"),
	            root.get("mobileNumber"),
	            root.get("status"),
	            root.get("userName")
	            //lookupRoot.get("lookupDetDescEn")
	        );

	       // criteriaQuery.where(finalPredicate);
	        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("userId")));

	        Query<Object[]> query = session.createQuery(criteriaQuery);
	        return query.getResultList();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}

/*
	@Override
	public TtDocumentUpload saveTtDocumentUpload(TtDocumentUpload ttDocumentUpload) {
		Session session= sessionFactory.getCurrentSession();
		if ( Objects.isNull(ttDocumentUpload.getDocUploadId()) ||Objects.isNull(session.find(TtDocumentUpload.class, ttDocumentUpload.getDocUploadId()))) {
		    session.persist(ttDocumentUpload);
		} else {
		    session.merge(ttDocumentUpload);
		}
		return ttDocumentUpload;
	}
*/


	@Override
	public List<Object[]> getdocuments(Integer docTypeId) {
		try {
			String hql = "SELECT t.docUploadPath FROM TtDocumentUpload t WHERE t.docType = :docTypeId AND t.status = 1";
			
			Session session = sessionFactory.getCurrentSession();
			 Query<Object[]> query = session.createQuery(hql, Object[].class);
			query.setParameter("docTypeId", docTypeId);
			List<Object[]> result= query.getResultList();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public List<Object[]> getUserDetailsByRoleId(Integer roletype) {

		  try {
		        Session session = sessionFactory.getCurrentSession();
		        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

		        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

		        Root<TmUsers> root = criteriaQuery.from(TmUsers.class);
		       // Root<TmLookupDet> lookupRoot = criteriaQuery.from(TmLookupDet.class);

//		        Predicate joinCondition = criteriaBuilder.equal(
//		                root.get("lookupDetIdRoleType"),
//		                lookupRoot.get("lookupDetId")
//		        );

		        Predicate roleTypeCondition = criteriaBuilder.equal(root.get("lookupDetIdRoleType"), roletype);
		       // Predicate finalCondition = criteriaBuilder.and(joinCondition, roleTypeCondition);
		        criteriaQuery.multiselect(
		            root.get("userId"),
		            root.get("emailId"),
		            root.get("userFullName"),
		            root.get("lookupDetIdRoleType"),
		            root.get("mobileNumber"),
		            root.get("status"),
		            root.get("userName")
		           // lookupRoot.get("lookupDetDescEn")
		        );

		        //criteriaQuery.where(finalCondition);

		        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("userId")));

		        Query<Object[]> query = session.createQuery(criteriaQuery);
		        return query.getResultList();

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return null;
		
	}

	

	
}
