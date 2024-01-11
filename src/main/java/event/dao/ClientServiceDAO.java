package event.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import event.dto.ClientService;

public class ClientServiceDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("logesh");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction et = em.getTransaction();

    public ClientService saveClientService(ClientService clientService) {
        if (clientService != null) {
            et.begin();
            em.persist(clientService);
            et.commit();
            return clientService;
        } else {
            return null;
        }
    }

    public ClientService findClientService(int clientServiceId) {
        return em.find(ClientService.class, clientServiceId);
    }

    public ClientService deleteClientService(int clientServiceId) {
        ClientService clientService = em.find(ClientService.class, clientServiceId);
        if (clientService != null) {
            et.begin();
            em.remove(clientService);
            et.commit();
            return clientService;
        } else {
            return null;
        }
    }

    public ClientService updateClientService(ClientService clientService, int clientServiceId) {
        clientService = em.find(ClientService.class, clientServiceId);
        if (clientService != null) {
            et.begin();
            ClientService updatedClientService = em.merge(clientService);
            et.commit();
            return updatedClientService;
        } else {
            return null;
        }
    }
}
