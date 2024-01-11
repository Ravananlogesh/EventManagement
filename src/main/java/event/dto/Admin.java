package event.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Admin {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int adminId;
private String adminName;
private String adminMail;
private String adminPassword;
private long adminContact;
@OneToMany(cascade = CascadeType.ALL)
private List<Service> services;
public int getAdminId() {
	return adminId;
}
public void setAdminId(int adminId) {
	this.adminId = adminId;
}
public String getAdminName() {
	return adminName;
}
public void setAdminName(String adminName) {
	this.adminName = adminName;
}
public String getAdminMail() {
	return adminMail;
}
public void setAdminMail(String adminMail) {
	this.adminMail = adminMail;
}
public String getAdminPassword() {
	return adminPassword;
}
public void setAdminPassword(String adminPassword) {
	this.adminPassword = adminPassword;
}
public long getAdminContact() {
	return adminContact;
}
public void setAdminContact(long adminContact) {
	this.adminContact = adminContact;
}
public List<Service> getServices() {
	return services;
}
public void setServices(List<Service> services) {
	this.services = services;
}
@Override
public String toString() {
	return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", adminMail=" + adminMail + ", adminPassword="
			+ adminPassword + ", adminContact=" + adminContact + ", services=" + services + "]";
}


}
