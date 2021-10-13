package repository.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import DTO.ReporteCarreras;
import entitymanagerfactory.EMF;
import registro.estudiantes.dao.Carrera;
import repository.CarreraRepository;

public class CarreraImplementation implements CarreraRepository {

	private static CarreraImplementation instance;
	private EntityManager em;

	public static CarreraImplementation getInstance() {
		if (instance == null) {
			instance = new CarreraImplementation();
		}
		return instance;
	}

	private CarreraImplementation() {
		em = EMF.createEntityManager();
	}

	/**
	 * Permite obtener las carreras apartir de su ID
	 * 
	 * @param id es el id de la carrera
	 * @return retorna una carera
	 */
	@Override
	public Carrera get(Integer id) {

		@SuppressWarnings("unchecked")
		List<Carrera> idCarrera = em.createQuery("SELECT c FROM Carrera c WHERE c.id=:id").setParameter("id", id)
				.getResultList();
		if (!idCarrera.isEmpty()) {
			return idCarrera.get(0);
		}
		return null;
	}

	/**
	 * Permite obtener una carrera a partir de su nombre
	 * 
	 * @param name es el nombre de la carrera
	 * @return retorna una carrera
	 */
	@Override
	public Carrera getByName(String name) {
		@SuppressWarnings("unchecked")
		List<Carrera> idCarrera = em.createQuery("SELECT c FROM Carrera c WHERE c.nombreCarrera=:nombreCarrera")
				.setParameter("nombreCarrera", name).getResultList();
		if (!idCarrera.isEmpty()) {
			return idCarrera.get(0);
		}
		return null;
	}

	/**
	 * Permite guardar una carrera
	 * 
	 * @param carrera es la carrera a guardar
	 * @return retorna la carrera guardada
	 */
	@Override
	public void create(Carrera carrera) {
		em.getTransaction().begin();
		em.persist(carrera);
		em.getTransaction().commit();
	}

	/**
	 * Permite borrar una carrera, pasando como parametro la misma
	 * 
	 * @param carrera es la carrera a borrar
	 */
	@Override
	public boolean delete(Integer id) {
		int idCarrera = id;
		em.createQuery("DELETE FROM Carrera c WHERE c.idCarrera=:idCarrera").setParameter("idCarrera", idCarrera);
		return true;
	}

	/**
	 * Permite obtener las carreras que tienen estudiantes inscriptos ordenadas por
	 * cantidad de inscriptos
	 * 
	 * @return retorna una lista de carreras
	 */
	public List<Carrera> getCarrerasConEstudiantesSortByCantidad() {
		@SuppressWarnings("unchecked")
		List<Carrera> retornedList = em.createQuery(
				"SELECT c FROM Carrera c JOIN  c.estudiantes s GROUP BY c.idCarrera ORDER BY COUNT(s.estudiante)")
				.getResultList();
		if (!retornedList.isEmpty()) {
			return retornedList;
		}
		return null;
	}

	/**
	 * Permite obtener un reporte de las carreras con la cantidad de inscriptos y
	 * egresados por año
	 * 
	 * @return retorna una lista de ReportesCarrera, un DTO creado especificamente
	 *         para esta consulta
	 */
	public List<ReporteCarreras> getReporte() {
		@SuppressWarnings("unchecked")
		List<ReporteCarreras> retornoDTOCarreras = em.createQuery(
				"SELECT new DTO.ReporteCarreras(c.idCarrera, c.nombreCarrera, YEAR(s.fechaInscripcion),sum(s.egresado+0), COUNT(s.estudiante)) "
						+ "FROM Carrera c JOIN c.estudiantes s GROUP BY (c.idCarrera) ORDER BY YEAR(s.fechaInscripcion) ASC, c.nombreCarrera ASC ")
				.getResultList();
		if (!retornoDTOCarreras.isEmpty()) {
			return retornoDTOCarreras;
		}
		return null;

	}

	public void closeConnection() {
		this.em.close();
	}

	@Override
	public List<Carrera> getAll() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Carrera> retornedList = em.createQuery("SELECT c FROM Carrera c").getResultList();
		if (!retornedList.isEmpty()) {
			return retornedList;
		}
		return null;
	}
}
