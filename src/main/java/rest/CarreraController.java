package rest;

import javax.ws.rs.Path;

import repository.implementation.CarreraImplementation;

@Path("/carreras")
public class CarreraController {
	private CarreraImplementation carreraRepo;

//	http://localhost:8080/EjercicioIntegrador3/registroestudiantes/carreras/
	public CarreraController() {
		this.carreraRepo = CarreraImplementation.getInstance();
	}
}
