package online.blickle.persist;

import java.util.List;

public interface PersistenceService {

	public  void persist(Object entity);
	public  void update(Object entity);
	public  void remove(Object entity);
	public  List performNamedQuery(String query);
	public  List performNamedQuery(String query, int maxresult);
}
