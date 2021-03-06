package repository.implementation;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import entitymanagerfactory.EMF;
import registro.estudiantes.dao.Carrera;
import registro.estudiantes.dao.Ciudad;
import registro.estudiantes.dao.Estudiante;
import registro.estudiantes.dao.SituacionAcademica;
import repository.EstudianteRepository;

public class EstudianteImplementation implements EstudianteRepository {

	private EntityManager em;
	private static EstudianteImplementation instance;
	private SituacionAcademicaImplementation situ = SituacionAcademicaImplementation.getInstance();
	private CarreraImplementation career = CarreraImplementation.getInstance();

	public static EstudianteImplementation getInstance() {
		if (instance == null) {
			instance = new EstudianteImplementation();
		}
		return instance;
	}

	private EstudianteImplementation() {
		this.em = EMF.createEntityManager();
	}

	/**
	 * Permite recuperar un estudiante por su numero de estudiante o numero de
	 * libreta
	 * 
	 * @param nroEstudiante el numero de libreta
	 * @return retorna un objeto estudiante
	 */
	@Override
	public Estudiante get(Integer nroEstudiante) {
		@SuppressWarnings("unchecked")
		List<Estudiante> nroEstudianteList = em
				.createQuery("SELECT e FROM Estudiante e WHERE e.nroEstudiante=:nroEstudiante")
				.setParameter("nroEstudiante", nroEstudiante).getResultList();
		if (!nroEstudianteList.isEmpty()) {
			return nroEstudianteList.get(0);
		}
		return null;
	}

	/**
	 * Permite recuperar un estudiante por su nombre
	 * 
	 * @param nombre del estudiante
	 * @return retorna un objeto estudiante
	 */
	@Override
	public Estudiante getByName(String name) {
		@SuppressWarnings("unchecked")
		List<Estudiante> e = em.createQuery("SELECT e FROM Estudiante e WHERE e.nombre=:nombre")
				.setParameter("nombre", name).getResultList();
		if (!e.isEmpty()) {
			return e.get(0);
		}
		return null;

	}

	/**
	 * Permite recuperar un estudiante por su nombre
	 * 
	 * @param estudiante a guardar
	 * @return retorna un objeto estudiante
	 */
	@Override
	public void create(Estudiante estudiante) {
		if (this.getByDNI(estudiante.getDni()) == null) {
			em.getTransaction().begin();
			em.persist(estudiante);
			em.getTransaction().commit();
		}
	}

	/**
	 * Permite eliminar un estudiante
	 */
	@Override
	public boolean delete(Integer estudiante) {
		int nroEstudiante = estudiante;
		em.getTransaction().begin();
		em.createQuery("DELETE FROM Estudiante e WHERE e.nroEstudiante=:nroEstudiante").setParameter("nroEstudiante",nroEstudiante).executeUpdate();
		em.getTransaction().commit();
		if(this.get(nroEstudiante) != null) {
			return false;
		}
		return true;
	}

	/**
	 * Permite buscar el numero de estudiante por su dni, por si el estudiante no lo
	 * recuerda
	 * 
	 * @param dni el dni
	 * @return un numero de estudiante si esta registrado o null si no lo esta
	 */
	public Estudiante getByDNI(Long dni) {
		@SuppressWarnings("unchecked")
		List<Estudiante> nroEstudiante = em.createQuery("SELECT e FROM Estudiante e WHERE e.dni=:dni")
				.setParameter("dni", dni).getResultList();
		if (!nroEstudiante.isEmpty()) {
			return nroEstudiante.get(0);
		}
		return null;
	}

	/**
	 * Permite recuperar los estudiantes de una determinada carrera filtrado por
	 * ciudad de residencia
	 * 
	 * @param idCiudad  el identificador de la ciudad
	 * @param idCarrera el identificador de la carrera
	 * @return retorna una lista de estudiantes
	 */
	public List<Estudiante> getEstudiantesByCiudad(int idCiudad, int idCarrera) {
		@SuppressWarnings("unchecked")
		List<Estudiante> retornedList = em.createQuery(
				"SELECT e FROM Estudiante e JOIN e.carreras s WHERE s.carrera.idCarrera =: idCarrera AND  e.ciudad.idCiudad =: idCiudad")
				.setParameter("idCarrera", idCarrera).setParameter("idCiudad", idCiudad).getResultList();
		if (!retornedList.isEmpty()) {
			return retornedList;
		}
		return null;
	}

	/**
	 * Permite dar de alta un estudiante en el registro Hace una busqueda para ver
	 * si la ciudad ingresada se encuentra en sistema y si no la encuentra da null
	 * 
	 * @param nombre               el nombre del estudiante
	 * @param apellido             apellido del estudiante
	 * @param dni                  el documento nacional de identidad
	 * @param genero               el genero del estudiante, puede ser cualquier
	 *                             cosa ya que es String
	 * @param Ciudad               el nombre de la ciudad
	 * @param CiudadImplementation recibe una implementacion de ciudad para
	 *                             consultas auxiliares
	 */
	public void darAltaEstudiante(String nombre, String apellido, Long dni, String genero, String Ciudad,
			CiudadImplementation city) {
		Ciudad ciudadIns = city.getByName(Ciudad);
		Estudiante estudiante = new Estudiante(nombre, apellido, dni, genero, ciudadIns);

		em.persist(estudiante);
	}

	/**
	 * Permite matricular un estudiante en una carrera con el nro de Libreta y el
	 * nombre de la carrera
	 * 
	 * @param nroLibreta    es el numero de estudiante
	 * @param nombreCarrera el nombre de la carrera
	 * @param career        una implementacion de carrera para consultas auxiliares
	 * @param situ          una implementacion de situacion academica para consultas
	 *                      auxiliares
	 */
	public void matricularEstudiante(int nroLibreta, String nombreCarrera) {
		Estudiante nroEstudiante = this.get(nroLibreta);
		Carrera idCarrera = career.getByName(nombreCarrera);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		if (idCarrera != null && nroEstudiante != null) {
			SituacionAcademica tempAcademica = new SituacionAcademica(nroEstudiante, idCarrera, 0, false, timestamp,
					null);
			situ.create(tempAcademica);
		}

	}

	/**
	 * Permite buscar el numero de estudiante por su dni, por si el estudiante no lo
	 * recuerda
	 * 
	 * @param dni el dni
	 * @return un numero de estudiante si esta registrado o null si no lo esta
	 */
	@SuppressWarnings("unused")
	private Estudiante getNroEstudiante(Long dni) {
		@SuppressWarnings("unchecked")
		List<Estudiante> nroEstudiante = em.createQuery("SELECT e FROM Estudiante e WHERE e.dni=:dni")
				.setParameter("dni", dni).getResultList();
		if (!nroEstudiante.isEmpty()) {
			return nroEstudiante.get(0);
		}
		return null;
	}

	/**
	 * Permite matricular un estudiante en una carrera con el dni del estudiante y
	 * el nombre de la carrera
	 * 
	 * @param dni           el dni del estudiante
	 * @param nombreCarrera el nombre de la carrera
	 * @param career        una implementacion de carrera para consultas auxiliares
	 * @param situ          una implementacion de situacion academica para consultas
	 *                      auxiliares
	 */
	public void matricularEstudiante(Long dni, String nombreCarrera) {
		em.getTransaction().begin();
		Estudiante nroEstudiante = this.getByDNI(dni);
		Carrera idCarrera = career.getByName(nombreCarrera);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		if (idCarrera != null && nroEstudiante != null) {
			SituacionAcademica tempAcademica = new SituacionAcademica(nroEstudiante, idCarrera, 0, false, timestamp,
					null);
			situ.create(tempAcademica);
		}
		em.getTransaction().commit();
	}

	/**
	 * Permite obtener la lista de estudiantes ordenada alfabeticamente por el
	 * apellido de los mismos
	 * 
	 * @return retorna una lista de estudiantes
	 */
	public List<Estudiante> getEstudiantesSortByApellido() {
		@SuppressWarnings("unchecked")
		List<Estudiante> retornedList = em.createQuery("SELECT e FROM Estudiante e ORDER BY e.apellido ASC")
				.getResultList();
		if (!retornedList.isEmpty()) {
			return retornedList;
		}
		return null;
	}

	/**
	 * Permite recuperar una lista de estudiantes de un determinado genero
	 * 
	 * @param genero un string con el genero de los estudiantes a filtrar
	 * @return retorna una lista de estudiantes
	 */
	public List<Estudiante> getEstudiantesByGenero(String genero) {
		@SuppressWarnings("unchecked")
		List<Estudiante> retornedList = em.createQuery("SELECT e FROM Estudiante e WHERE e.genero =: genero")
				.setParameter("genero", genero).getResultList();
		if (!retornedList.isEmpty()) {
			return retornedList;
		}
		return null;
	}

	public void closeConnection() {
		this.em.close();
	}

	@Override
	public List<Estudiante> getAll() {
		@SuppressWarnings("unchecked")
		List<Estudiante> retornedList = em.createQuery("SELECT e FROM Estudiante e").getResultList();
		if (!retornedList.isEmpty()) {
			return retornedList;
		}
		return null;
	}
}
