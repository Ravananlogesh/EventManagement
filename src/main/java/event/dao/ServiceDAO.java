package event.dao;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


import event.dto.Service;

import event.dto.Admin;

public class ServiceDAO {
	 private EntityManagerFactory emf = Persistence.createEntityManagerFactory("logesh");
	    private EntityManager em = emf.createEntityManager();
	    private EntityTransaction et = em.getTransaction();

	    public Service saveService(Service service) {
	        if (service != null) {
	            et.begin();
	            em.persist(service);
	            et.commit();
	            return service;
	        } else {
	            return null;
	        }
	    }

	    public Service findService(int serviceId) {
	    	Service service = em.find(Service.class, serviceId);
	        if (service != null) {
	            return service;
	        } else {
	            return null;
	        }
	    }

	    public Service deleteAdmin(int serviceId) {
	    	Service service = em.find(Service.class, serviceId);
	        if (service != null) {
	            et.begin();
	            em.remove(service);
	            et.commit();
	            return service;
	        } else {
	            return null;
	        }
	    }

	    public Service updateService(Service service) {
	        if (service != null) {
	            et.begin();
	            Service updatedService = em.merge(service);
	            et.commit();
	            return updatedService;
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
	  

}

