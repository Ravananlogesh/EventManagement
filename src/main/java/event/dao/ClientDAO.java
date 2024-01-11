package event.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import event.dto.Client;


public class ClientDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("logesh");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction et = em.getTransaction();

    public Client saveClient(Client client) {
        if (client != null) {
            et.begin();
            em.persist(client);
            et.commit();
            return client;
        } else {
            return null;
        }
    }

    public Client findClient(int clientId) {
        return em.find(Client.class, clientId);
    }

    public Client deleteClient(int clientId) {
        Client client = em.find(Client.class, clientId);
        if (client != null) {
            et.begin();
            em.remove(client);
            et.commit();
            return client;
        } else {
            return null;
        }
    }

    public Client updateClient(Client client, int clientId) {
        client = em.find(Client.class, clientId);
        if (client != null) {
            et.begin();
            Client updatedClient = em.merge(client);
            et.commit();
            return updatedClient;
        } else {
            return null;
        }
    }

    public Client deleteEventInClient(int clientId, int index) {
        Client client = em.find(Client.class, clientId);
        if (client != null && client.getEvents() != null && index < client.getEvents().size()) {
            client.getEvents().remove(index);
            et.begin();
            Client updatedClient = em.merge(client);
            et.commit();
            return updatedClient;
        } else {
            return null;
        }
    }
}

