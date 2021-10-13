package repository.implementation;

import java.util.List;
import javax.persistence.EntityManager;
import entitymanagerfactory.EMF;
import registro.estudiantes.dao.Facultad;
import repository.FacultadRepository;

public class FacultadImplementation implements FacultadRepository {

	private EntityManager em;
	private static FacultadImplementation instance;

	public static FacultadImplementation getInstance() {
		if (instance == null) {
			instance = new FacultadImplementation();
		}
		return instance;
	}

	private FacultadImplementation() {
		this.em = EMF.createEntityManager();
	}

	/**
	 * Permite recuperar una facultad por su id
	 * 
	 * @param id es el id de la facultad
	 * @return retorna un objeto facultad
	 */
	@Override
	public Facultad get(Integer id) {
		@SuppressWarnings("unchecked")
		List<Facultad> c = em.createQuery("SELECT f FROM Facultad f WHERE f.idFacultad=:idFacultad")
				.setParameter("idFacultad", id).getResultList();
		if (!c.isEmpty()) {
			return (Facultad) c.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Permite recuperar una facultad por su nombre
	 * 
	 * @param name es el nombre de la facultad
	 * @return retorna un objeto facultad
	 */
	@Override
	public Facultad getByName(String name) {
		@SuppressWarnings("unchecked")
		List<Facultad> c = em.createQuery("SELECT f FROM Facultad f WHERE f.nombreFacultad=:nombreFacultad")
				.setParameter("nombreFacultad", name).getResultList();
		if (!c.isEmpty()) {
			return (Facultad) c.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Permite guardar una facultad
	 * 
	 * @param facultad es la facultad a guardar
	 * @return retorna la facultad guardada
	 */
	@Override
	public void create(Facultad facultad) {
		em.getTransaction().begin();
		em.persist(facultad);
		em.getTransaction().commit();
	}

	/**
	 * Permite eliminar una facultad
	 * 
	 * @param facultad es la facultad a eliminar
	 */
	@Override
	public boolean delete(Integer facultad) {
		int idFacultad = facultad;
		em.createQuery("DELETE FROM Facultad f WHERE f.idFacultad=:idFacultad").setParameter("idFacultad", idFacultad);
		return true;
	}

	public void closeConnection() {
		this.em.close();
	}

	@Override
	public List<Facultad> getAll() {
		@SuppressWarnings("unchecked")
		List<Facultad> c = em.createQuery("SELECT f FROM Facultad f").getResultList();
		if (!c.isEmpty()) {
			return c;
		} else {
			return null;
		}
	}

}
