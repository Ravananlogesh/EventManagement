package event.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ClientService {
	@Override
	public String toString() {
		return "ClientService [clientServiceId=" + clientServiceId + ", clientServiceName=" + clientServiceName
				+ ", clientServiceCost=" + clientServiceCost + ", clientServiceNoOfDays=" + clientServiceNoOfDays
				+ ", clientServiceCostPerPerson=" + clientServiceCostPerPerson + "]";
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clientServiceId;
	private String clientServiceName;
	private double clientServiceCost;
	private int clientServiceNoOfDays;
	private double clientServiceCostPerPerson;
	public int getClientServiceId() {
		return clientServiceId;
	}
	public void setClientServiceId(int clientServiceId) {
		this.clientServiceId = clientServiceId;
	}
	public String getClientServiceName() {
		return clientServiceName;
	}
	public void setClientServiceName(String clientServiceName) {
		this.clientServiceName = clientServiceName;
	}
	public double getClientServiceCost() {
		return clientServiceCost;
	}
	public void setClientServiceCost(double clientServiceCost) {
		this.clientServiceCost = clientServiceCost;
	}
	public int getClientServiceNoOfDays() {
		return clientServiceNoOfDays;
	}
	public void setClientServiceNoOfDays(int clientServiceNoOfDays) {
		this.clientServiceNoOfDays = clientServiceNoOfDays;
	}
	public double getClientServiceCostPerPerson() {
		return clientServiceCostPerPerson;
	}
	public void setClientServiceCostPerPerson(double clientServiceCostPerPerson) {
		this.clientServiceCostPerPerson = clientServiceCostPerPerson;
	}
	
	

}
