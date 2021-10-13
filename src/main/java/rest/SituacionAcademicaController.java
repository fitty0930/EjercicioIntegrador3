package rest;

import javax.ws.rs.Path;

import repository.implementation.SituacionAcademicaImplementation;

@Path("/situaciones")
public class SituacionAcademicaController {
	private SituacionAcademicaImplementation situRepo;

//	http://localhost:8080/EjercicioIntegrador3/registroestudiantes/situaciones/
	public SituacionAcademicaController() {
		situRepo = SituacionAcademicaImplementation.getInstance();
	}
}
