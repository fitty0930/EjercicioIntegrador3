package entitymanagerfactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class EMF implements ServletContextListener {
	private static javax.persistence.EntityManagerFactory emf;

	@Override
	public void contextInitialized(ServletContextEvent arg) {
		emf = Persistence.createEntityManagerFactory("registroestudiantesdb");
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg) {
		emf.close();
	}

	public static EntityManager createEntityManager() {
		if (emf == null) {
			throw new IllegalStateException("Context is not initialized yet.");
		}
		return emf.createEntityManager();
	}
}
