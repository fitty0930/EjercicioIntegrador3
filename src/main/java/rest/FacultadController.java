package rest;

import javax.ws.rs.Path;

import repository.implementation.FacultadImplementation;

@Path("/facultades")
public class FacultadController {
	private FacultadImplementation facuRepo;

//	http://localhost:8080/EjercicioIntegrador3/registroestudiantes/facultades/
	public FacultadController() {
		facuRepo = FacultadImplementation.getInstance();
	}
}
