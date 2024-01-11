	package event.dao;

	import java.util.List;

import javax.persistence.EntityManager;
	import javax.persistence.EntityManagerFactory;
	import javax.persistence.EntityTransaction;
	import javax.persistence.Persistence;
import javax.persistence.Query;

import event.dto.Admin;


	public class AdminDAO {

	    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("logesh");
	    private EntityManager em = emf.createEntityManager();
	    private EntityTransaction et = em.getTransaction();

	    public Admin saveAdmin(Admin admin) {
	        if (admin != null) {
	            et.begin();
	            em.persist(admin);
	            et.commit();
	            return admin;
	        } else {
	            return null;
	        }
	    }

	    public Admin findAdmin(int adminId) {
	        Admin admin = em.find(Admin.class, adminId);
	        if (admin != null) {
	            return admin;
	        } else {
	            return null;
	        }
	    }

	    public Admin deleteAdmin(int adminId) {
	        Admin admin = em.find(Admin.class, adminId);
	        if (admin != null) {
	            et.begin();
	            em.remove(admin);
	            et.commit();
	            return admin;
	        } else {
	            return null;
	        }
	    }

	    public Admin updateAdmin(Admin admin) {
	        
	        if (admin != null) {
	            et.begin();
	            Admin updatedAdmin = em.merge(admin);
	            et.commit();
	            return updatedAdmin;
	        } else {
	            return null;
	        }
	    }

	    public Admin deleteServiceInAdmin(int adminId, int index) {
	        Admin admin = em.find(Admin.class, adminId);
	        if (admin != null && admin.getServices() != null && index < admin.getServices().size()) {
	            admin.getServices().remove(index);
	            et.begin();
	            Admin updatedAdmin = em.merge(admin);
	            et.commit();
	            return updatedAdmin;
	        } else {
	            return null;
	        }
	    }
	    public List<Admin> findByQuery() {
	    	String ql="select a from Admin a";
	    	Query q=em.createQuery(ql);
	    	List<Admin> admin=q.getResultList();
	    	
	    	if (admin !=null) {
				return admin;
			} else {
                return null;
			}
	    }
	

}

