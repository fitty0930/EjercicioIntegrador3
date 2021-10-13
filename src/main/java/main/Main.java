package main;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import DTO.ReporteCarreras;
import registro.estudiantes.csv.CSVtoMYSQL;
import registro.estudiantes.dao.Carrera;
import registro.estudiantes.dao.Estudiante;
import repository.implementation.CarreraImplementation;
import repository.implementation.CiudadImplementation;
import repository.implementation.EstudianteImplementation;
import repository.implementation.FacultadImplementation;
import repository.implementation.SituacionAcademicaImplementation;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/**
		 * Apertura de conexiones
		 */
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("registroestudiantesdb");
		CarreraImplementation carreraImplementation = CarreraImplementation.getInstance();
		CiudadImplementation ciudadImplementation = CiudadImplementation.getInstance();
		EstudianteImplementation estudianteImplementation = EstudianteImplementation.getInstance();
		FacultadImplementation facultadImplementation = FacultadImplementation.getInstance();
		SituacionAcademicaImplementation situacionAcademicaImplementation = SituacionAcademicaImplementation.getInstance();

		/**
		 * Importar Datos del CSV
		 */
		CSVtoMYSQL importador = new CSVtoMYSQL();

		importador.importarCSVFacultad(facultadImplementation);
		importador.importarCSVCarrera(facultadImplementation, carreraImplementation);
		importador.importarCSVCiudad(ciudadImplementation);
		importador.importarCSVEstudiante(estudianteImplementation, ciudadImplementation);
		importador.importarCSVSituacionAcademica(situacionAcademicaImplementation, carreraImplementation,
				estudianteImplementation);

//		/**
//		 * Operaciones
//		 */
//
//		/**
//		 * 2) A dar de alta a un estudiante
//		 */
//		Estudiante estudiante = new Estudiante("benjamin", "hoffman", (long) 4333333, "difuso");
//
//		estudianteImplementation.create(estudiante);
//
//		/**
//		 * 2) B matricular un estudiante a una carrera
//		 */
//		estudianteImplementation.matricularEstudiante((long) 4333333, "African black crake", carreraImplementation,
//				situacionAcademicaImplementation);
//
//		/**
//		 * 2) C recupera todos los estudiante con un ordenamiento por apellido
//		 */
//		List<Estudiante> estudiantes = estudianteImplementation.getEstudiantesSortByApellido();
//		for (Estudiante estudiante1 : estudiantes) {
//			System.out.println(estudiante1.toString());
//		}
//
//		/**
//		 * 2) D recupera un estudiante en base de su libreta universitaria
//		 */
//		System.out.println(estudianteImplementation.get(2));
//		/**
//		 * 2) E recupera todos los estudiante en base a su genero
//		 */
//		estudiantes = estudianteImplementation.getEstudiantesByGenero("Male");
//		for (Estudiante estudiante2 : estudiantes) {
//			System.out.println(estudiante2.toString());
//		}
//		/**
//		 * 2) F recupera las carreras con estudiantes inscriptos y ordenar por cantidad
//		 * de inscriptos
//		 */
//		List<Carrera> carreras = carreraImplementation.getCarrerasConEstudiantesSortByCantidad();
//		for (Carrera carrera : carreras) {
//			System.out.println(carrera.toString());
//		}
//		/**
//		 * 2) G recupera los estudiantes por una determinada carrera, filtrados por
//		 * ciudad de residencia
//		 */
//		estudiantes = estudianteImplementation.getEstudiantesByCiudad(254, 22);
//		for (Estudiante estudiante3 : estudiantes) {
//			System.out.println(estudiante3.toString());
//		}
//		/**
//		 * 3 Generar un reporte de las carreras, que para cada carrera incluya
//		 * informaci�n de los inscriptos y egresados por a�o. Se deben ordenar las
//		 * carreras alfab�ticamente, y presentar los a�os de manera cronol�gica.
//		 */
//
//		List<ReporteCarreras> reportesCarreras = carreraImplementation.getReporte();
//		for (ReporteCarreras reporte : reportesCarreras) {
//			System.out.println(reporte.toString());
//		}

		/**
		 * Cierre de conexiones
		 */
//		carreraImplementation.closeConnection();
//		ciudadImplementation.closeConnection();
//		estudianteImplementation.closeConnection();
//		facultadImplementation.closeConnection();
//		situacionAcademicaImplementation.closeConnection();
//		emf.close();
	}

}
