package repository.implementation;

import java.util.List;
import javax.persistence.EntityManager;
import entitymanagerfactory.EMF;
import registro.estudiantes.dao.Ciudad;
import repository.CiudadRepository;

public class CiudadImplementation implements CiudadRepository {

	private static CiudadImplementation instance;
	private EntityManager em;

	public static CiudadImplementation getInstance() {
		if (instance == null) {
			instance = new CiudadImplementation();
		}
		return instance;
	}

	private CiudadImplementation() {
		this.em = EMF.createEntityManager();
	}

	/**
	 * Permite obtener una ciudad apartir de su id
	 * 
	 * @param id de la ciudad
	 * @return retorna una ciudad
	 */
	@Override
	public Ciudad get(Integer id) {
		@SuppressWarnings("unchecked")
		List<Ciudad> c = em.createQuery("SELECT c FROM Ciudad c WHERE c.idCiudad =: idCiudad")
				.setParameter("idCiudad", id).getResultList();
		if (!c.isEmpty()) {
			return (Ciudad) c.get(0);
		}
		return null;
	}

	/**
	 * Permite obtener una ciudad apartir de su nombre
	 * 
	 * @param nombre de la ciudad
	 * @return retorna una ciudad
	 */
	@Override
	public Ciudad getByName(String name) {
		@SuppressWarnings("unchecked")
		List<Ciudad> c = em.createQuery("SELECT c FROM Ciudad c WHERE c.nombreCiudad=:nombreCiudad")
				.setParameter("nombreCiudad", name).getResultList();
		if (!c.isEmpty()) {
			return (Ciudad) c.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Permite guardar una ciudad
	 * 
	 * @param la ciudad a guardar
	 * @return retorna la ciudad guardada
	 */
	@Override
	public void create(Ciudad ciudad) {
		em.getTransaction().begin();
		em.persist(ciudad);
		em.getTransaction().commit();
	}

	/**
	 * Permite borrar una ciudad, la cual se pasa por parametro
	 * 
	 * @param la ciudad a borrar
	 */
	@Override
	public boolean delete(Integer id) {
		int idCiudad = id;
		em.createQuery("DELETE FROM Ciudad c WHERE c.idCiudad=:idCiudad").setParameter("idCiudad", idCiudad);
		return true;
	}

	public void closeConnection() {
		this.em.close();
	}

	@Override
	public void update(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Ciudad> getAll() {
		@SuppressWarnings("unchecked")
		List<Ciudad> c = em.createQuery("SELECT c FROM Ciudad c").getResultList();
		if (!c.isEmpty()) {
			return c;
		} else {
			return null;
		}
	}

}
