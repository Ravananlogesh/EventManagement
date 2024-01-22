package event.dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ClientEvent {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int clientEventId;
private int clientNoOfPeople;
private LocalDate startDate;
private int  clientEventNoOfDays;
private String  clientEventLocation;										
private double clientEventPrice;
@ManyToOne
private Client client;
@OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
private List<ClientService> clientservice;
private EvenType evenType;

public int getClientEventId() {
	return clientEventId;
}
public void setClientEventId(int clientEventId) {
	this.clientEventId = clientEventId;
}
public int getClientNoOfPeople() {
	return clientNoOfPeople;
}
public void setClientNoOfPeople(int clientNoOfPeople) {
	this.clientNoOfPeople = clientNoOfPeople;
}
public LocalDate getStartDate() {
	return startDate;
}
public void setStartDate(LocalDate startDate) {
	this.startDate = startDate;
}
public int getClientEventNoOfDays() {
	return clientEventNoOfDays;
}
public void setClientEventNoOfDays(int clientEventNoOfDays) {
	this.clientEventNoOfDays = clientEventNoOfDays;
}
public String getClientEventLocation() {
	return clientEventLocation;
}
public void setClientEventLocation(String clientEventLocation) {
	this.clientEventLocation = clientEventLocation;
}
public double getClientEventPrice() {
	return clientEventPrice;
}
public void setClientEventPrice(double clientEventPrice) {
	this.clientEventPrice = clientEventPrice;
}
public Client getClient() {
	return client;
}
public void setClient(Client client) {
	this.client = client;
}
public List<ClientService> getClientservice() {
	return clientservice;
}
public void setClientservice(List<ClientService> clientservice) {
	this.clientservice = clientservice;
}
public EvenType getEvenType() {
	return evenType;
}
public void setEvenType(EvenType evenType) {
	this.evenType = evenType;
}
@Override
public String toString() {
	return "ClientEvent [clientEventId=" + clientEventId + ", clientNoOfPeople=" + clientNoOfPeople + ", startDate="
			+ startDate + ", clientEventNoOfDays=" + clientEventNoOfDays + ", clientEventLocation="
			+ clientEventLocation + ", clientEventPrice=" + clientEventPrice +  ", Client=" + client+ ",  clientservice="
			+ clientservice + ", evenType=" + evenType + "]";
}
}
