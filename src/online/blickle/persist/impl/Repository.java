package online.blickle.persist.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import online.blickle.persist.PersistenceService;

import org.hibernate.Session;

public class Repository  implements PersistenceService{

	protected EntityManagerFactory emf;

	public static final String KEY="online.blickle.persist.Repository";
	
	public  Repository() {
		emf = Persistence.createEntityManagerFactory("Scheduler");
	}

	public synchronized void persist(Object entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
	}

	public synchronized void update(Object entity) {
		
			EntityManager em = emf.createEntityManager();
			Session session = em.unwrap(Session.class);
			session.merge(entity);
			persist(session.merge(entity));
	}

	public synchronized void remove(Object entity) {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.remove(em.contains(entity) ? entity : em.merge(entity));
			em.getTransaction().commit();
	}

	public synchronized List performNamedQuery(String query) {
		EntityManager em = emf.createEntityManager();
		Query q = em.createNamedQuery(query);
		return q.getResultList();
	}

	public synchronized List performNamedQuery(String query, int maxresult) {
		EntityManager em = emf.createEntityManager();
		Query q = em.createNamedQuery(query).setMaxResults(maxresult);
		return q.getResultList();
	}
}