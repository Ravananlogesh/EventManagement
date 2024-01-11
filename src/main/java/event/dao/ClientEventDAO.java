package event.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import event.dto.ClientEvent;
import event.dto.ClientService;
import event.dto.EvenType;

public class ClientEventDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("logesh");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction et = em.getTransaction();

    public ClientEvent saveClientEvent(ClientEvent clientEvent) {
        if (clientEvent != null) {
            et.begin();
            em.persist(clientEvent);
            et.commit();
            return clientEvent;
        } else {
            return null;
        }
    }

    public ClientEvent findClientEvent(int clientEventId) {
        return em.find(ClientEvent.class, clientEventId);
    }

    public ClientEvent deleteClientEvent(int clientEventId) {
        ClientEvent clientEvent = em.find(ClientEvent.class, clientEventId);
        if (clientEvent != null) {
            et.begin();
            em.remove(clientEvent);
            et.commit();
            return clientEvent;
        } else {
            return null;
        }
    }

    public ClientEvent updateClientEvent(ClientEvent clientEvent, int clientEventId) {
        clientEvent = em.find(ClientEvent.class, clientEventId);
        if (clientEvent != null) {
            et.begin();
            ClientEvent updatedClientEvent = em.merge(clientEvent);
            et.commit();
            return updatedClientEvent;
        } else {
            return null;
        }
    }

    public ClientEvent deleteClientServiceInEvent(int clientEventId, int index) {
        ClientEvent clientEvent = em.find(ClientEvent.class, clientEventId);
        if (clientEvent != null && clientEvent.getClientservice() != null && index < clientEvent.getClientservice().size()) {
            clientEvent.getClientservice().remove(index);
            et.begin();
            ClientEvent updatedClientEvent = em.merge(clientEvent);
            et.commit();
            return updatedClientEvent;
        } else {
            return null;
        }
    }
}
