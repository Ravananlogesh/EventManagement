package event.driver;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import event.dao.AdminDAO;
import event.dao.ClientDAO;
import event.dao.ClientEventDAO;
import event.dao.ClientServiceDAO;
import event.dao.ServiceDAO;
import event.dto.Admin;
import event.dto.Client;
import event.dto.ClientEvent;
import event.dto.ClientService;
import event.dto.EvenType;
import event.dto.Service;

public class EventManagement {
 AdminDAO adao=new AdminDAO();
 ServiceDAO sdao=new ServiceDAO();
 ClientDAO cdao=new ClientDAO();
 ClientEventDAO cedao=new ClientEventDAO();
 ClientServiceDAO csdao=new ClientServiceDAO();
private Scanner sc=new Scanner(System.in);
 
 
 public static void main(String[] args) {
	EventManagement em=new EventManagement();
	System.out.println(em.deleteClientEvent());
	
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
		else {
			return null;
		} 
	} 
     else {
    	 System.out.println("Email incorrect");
     }
     return null;
 }
 public LocalDate getDate() {
	 System.out.println();
	 System.out.println("Enter the Date");
	 byte date=sc.nextByte();
	 System.out.println("Enter the Month");
	 byte month=sc.nextByte();
	 System.out.println("Enter The year");
	 short year=sc.nextShort();
	 return LocalDate.of(year, month, date);
 }
 public EvenType getEventType() {
	 System.out.println("Choose The Event Type");
	 System.out.println("1) Marriage");
	 System.out.println("2) Engagement");
	 System.out.println("3) BirthDay");
	 System.out.println("4) Anniversary");
	 System.out.println("5) babyShower");
	 System.out.println("6) Reunion");
	 System.out.println("7) NamingCoremony");
	 System.out.println("8) BachelorParty");
	 
	 switch (sc.nextByte()) {
	case 1:
		return EvenType.Marriage;
	case 2:
		return EvenType.Engagement;
	case 3:
		return EvenType.BirthDay;
	case 4:
		return EvenType.Anniversary;
	case 5:
		return EvenType.babyShower;
	case 6:
		return EvenType.Reunion;
	case 7:
		return EvenType.NamingCoremony;
	case 8:
		return EvenType.BachelorParty;
	default:
		return null;
	}
	 
	
 }
  
 
 
 
public ClientEvent createCliendEvent() {
	System.out.println("Login Client");
	Client c=clientLogin();
	if (c !=null) {
	   List<Service> service=sdao.getAllService();
	   System.out.println("   ServiceId  "+"  Service Name  "+" Cost_Per_Person  "+"Cost_Per_day  ");
	   for (Service s : service) {
			 
			 System.out.println("       "+s.getServiceId()+"          "+s.getServiceName()+"                 "+s.getServiceCostPerPerson()+"             "+s.getServiceCostPerDay());
		}
		System.out.println("*************************    choose service id     **************************"+"\n");
		System.out.print("Enter the Service Id : ");
	    int id=sc.nextInt();
	    Service s=sdao.findService(id);
	    if (s!=null) {
	    	
	    	EvenType event=getEventType();
	    	
	    	System.out.print("Enter the People of "+s.getServiceName()+" : ");
	    	int people=sc.nextInt();
	    	System.out.println();
	    	System.out.print("Enter the Event Location : ");
	    	String location=sc.next();
	    	System.out.println();
	    	System.out.print("Enter the Starting Day : ");
	    	LocalDate l=getDate();
	    	System.out.println();
	    	System.out.print("Enter the Number of Days : ");
	    	int days=sc.nextInt();
	    	System.out.print("Enter the Price : ");
	    	double price=sc.nextDouble();
	    	System.out.println();
	    	
	        ClientEvent ce=new ClientEvent();
	        
	        ce.setClient(c);
	        ce.setClientNoOfPeople(people);
	        ce.setClientEventLocation(location);
	        ce.setStartDate(l);
	        ce.setEvenType(event);
	        ce.setClientEventPrice(price);
	        ce.setClientEventNoOfDays(days);
	        
	        
	        
	        
	        ClientService cs=new ClientService();
	        double costPerday=s.getServiceCostPerDay();
	        cs.setClientServiceCost(costPerday);
	        double perPerson=s.getServiceCostPerPerson();
	        cs.setClientServiceCostPerPerson(perPerson);
	        String name=s.getServiceName();
	        cs.setClientServiceName(name);
	        
	        ClientService clientservice=csdao.saveClientService(cs);
	        
	        List<ClientService> clientService=new ArrayList<>();
	        clientService.add(clientservice);
            
	        
	        ce.setClientservice(clientService);
	        ClientEvent clientEvent=cedao.saveClientEvent(ce); 
	        List<ClientEvent> cli=new ArrayList<>();
	        cli.add(clientEvent);
	        c.setEvents(cli);
	        cdao.updateClient(c, id);
	        
	        
	        return clientEvent;	
			
		} 
	    else {
               System.out.println("Unknown Services");
               
		}
	
		
	} else {
       System.out.println("Login Failed");
	}
	return null;
}
public ClientEvent deleteClientEvent() {
	Client c=clientLogin();
	
	
	if (c !=null) {
		
		
		List<ClientEvent> clientevent=c.getEvents();
		
		for (ClientEvent clientEvent2 : clientevent) {
		     System.out.println(clientEvent2.getClientEventId()+"\t"+clientEvent2.getClientservice());  
		}
		System.out.print("Enter the Event Id : ");
		int id=sc.nextInt();
        
		ClientEvent ce=cedao.findClientEvent(id);
		if (ce !=null) {
			ce.setClient(null);
			cedao.updateClientEvent(ce, ce.getClientEventId());
			c.setEvents(null);
			cdao.updateClient(c, c.getClientId());
			return cedao.deleteClientEvent(id);
		}
		else {
			return null;
		}
	} 
	else {
         return null;
	}
}

}
