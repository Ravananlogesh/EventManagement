package event.driver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.persistence.Query;

import event.dao.AdminDAO;
import event.dao.ClientDAO;
import event.dao.ClientServiceDAO;
import event.dao.ServiceDAO;
import event.dto.Admin;
import event.dto.Client;
import event.dto.ClientEvent;
import event.dto.EvenType;
import event.dto.Service;

public class EventManagement {
 AdminDAO adao=new AdminDAO();
 ServiceDAO sdao=new ServiceDAO();
 ClientDAO cdao=new ClientDAO();
 ClientServiceDAO csdao=new ClientServiceDAO();
private Scanner sc=new Scanner(System.in);
 
 
 public static void main(String[] args) {
	EventManagement em=new EventManagement();
	System.out.println(em.clientLogin());
	
}
 public Admin saveAdmin() {
	 Admin admin=new Admin();
	 
	 System.out.print("Enter Admin Name : ");
	 admin.setAdminName(sc.next());
     System.out.print("Enter Admin Mail : ");
     admin.setAdminMail(sc.next());
     System.out.print("Enter Admin Pasword : ");
     admin.setAdminPassword(sc.next());
     System.out.print("Enter Admin Contact Number : ");
     admin.setAdminContact(sc.nextLong());
     return adao.saveAdmin(admin);
 }
 public Admin adminLogin() {

	 System.out.print("Enter Admin Mail : ");
     String mail=sc.next();
     System.out.print("Enter Admin Pasword : ");
     String pass=sc.next();
	 List<Admin> admin=adao.findByQuery();
	 Iterator<Admin> i=admin.iterator();
	 while (i.hasNext()) {
		 Admin a=i.next();
		if (mail.equals(a.getAdminMail())&&pass.equals(a.getAdminPassword())) {
			return a;
		} else {
            System.out.println("Admin Email or password incorrect ");
            
		}
	}
	
	return null;
 }
 public Service saveService() {
	    
		 Service service=new Service(); 
		 System.out.print("Enter Service Name : ");
		 service.setServiceName(sc.next());
	     System.out.print("Enter Cost PerDay : ");
	     service.setServiceCostPerDay(sc.nextDouble());
	     System.out.print("Enter Cost PerPerson : ");
	     service.setServiceCostPerPerson(sc.nextDouble());
	     
	     
	     return sdao.saveService(service);
	
 }
 public Admin saveServiceInAdmin(){
	
	 Admin a=adminLogin();
	 if (a !=null) {
		 System.out.print("Enter the Number of Services : ");
		 int n=sc.nextInt();
		 
		 List<Service> service=new ArrayList<>();
		 while (n>0) {
			 Service s=saveService();
			 service.add(s);
			
			 n--;
		}
		 a.setServices(service);
		Admin admin= adao.updateAdmin(a);
		
		 return admin;
	} 
	 return null; 
 }
 public Admin  updateService() {
	 System.out.println("Enter Admin  credentials proceed");
	 Admin exadmin=adminLogin();
	 if (exadmin !=null) {
		 List<Service> service=exadmin.getServices();
		 for (Service s : service) {
			 
			 System.out.println("   ServiceId  "+"  Service Name  "+" Cost_Per_Person  "+"Cost_Per_day  ");
			 System.out.println("       "+s.getServiceId()+"          "+s.getServiceName()+"                 "+s.getServiceCostPerPerson()+"             "+s.getServiceCostPerDay());
		}
		 Iterator<Service> i=service.iterator();
		 System.out.print("Enter the Service Name : ");
		 String serviceName=sc.next();
		 while (i.hasNext()) {
			 Service s=i.next();
			 
			if (serviceName.equals(s.getServiceName())) {
				System.out.print("Enter the new Cost Per Day : ");
				s.setServiceCostPerDay(sc.nextDouble());
				System.out.print("Enter the new Cost Per Person : ");
				s.setServiceCostPerPerson(sc.nextDouble());
				Service newService=sdao.updateService(s);
				service.remove(s);
				service.add(newService);
				return exadmin;
			} 
		} 
	}
	 return null;
	
 }
 public Service deleteService() {
	 System.out.println("Enter Admin  credentials proceed");
	 Admin exadmin=adminLogin();
	 if (exadmin !=null) {
		List<Service> service=exadmin.getServices();
		for (Service s : service) {
			 System.out.println("   ServiceId  "+"  Service Name  "+" Cost_Per_Person  "+"Cost_Per_day  ");
			 System.out.println("       "+s.getServiceId()+"          "+s.getServiceName()+"                 "+s.getServiceCostPerPerson()+"             "+s.getServiceCostPerDay());
		}
		System.out.println("*************************choose service id from above to update (**************************");
		System.out.print("Enter the Service Id : ");
		 int id=sc.nextInt();
		 Service deleteService=sdao.findService(id);
		 service.remove(deleteService);
		 exadmin.setServices(service);
		 adao.updateAdmin(exadmin);
		 return deleteService;
		 
	} 

	return null;
 }
    
 public Client saveClient() {
 Client client=new Client();
	 
	 System.out.print("Enter Client Name : ");
	 client.setClientName(sc.next());
     System.out.print("Enter Cliet Mail : ");
     client.setClientMail(sc.next());
     System.out.print("Enter Client Pasword : ");
     client.setClientPassword(sc.next());
     System.out.print("Enter Client Contact Number : ");
     client.setClientContact(sc.nextLong());
     
     return cdao.saveClient(client);
 }
 public Client clientLogin() {
	 System.out.print("Enter Client Mail : ");
     String mail=sc.next();
     System.out.print("Enter Client Pasword : ");
     String pass=sc.next();
     Client c=cdao.findByQuery(mail);
     if (c!=null) {
		if (pass.equals(c.getClientPassword())) {
			return c;
		}
    	 
	} 
     else {
    	 System.out.println("Email incorrect");
     }
     return null;
 }
 public EvenType saveEvent() {
	 Client c=clientLogin();
	 if (c!=null) {
		
	}
	 else {
		 System.out.println("login failed");
		 return saveEvent();
	 }
	 return null;
 }
}
