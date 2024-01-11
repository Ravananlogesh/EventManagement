package event.driver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


import event.dao.AdminDAO;
import event.dao.ClientServiceDAO;
import event.dao.ServiceDAO;
import event.dto.Admin;
import event.dto.Service;

public class EventManagement {
 AdminDAO adao=new AdminDAO();
 ServiceDAO sdao=new ServiceDAO();
 ClientServiceDAO csdao=new ClientServiceDAO();
private Scanner sc=new Scanner(System.in);
 
 
 public static void main(String[] args) {
	EventManagement em=new EventManagement();
	System.out.println(em.saveServiceInAdmin());
	
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
 
}
