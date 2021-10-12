package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import registro.estudiantes.dao.Ciudad;
import repository.implementation.CiudadImplementation;

@Path("/ciudades")
public class CiudadController {
	private CiudadImplementation cityRepo;
	
	public CiudadController() {
		cityRepo= new CiudadImplementation(null);
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Ciudad getUsuario(@PathParam("id") int id) {
		return cityRepo.get(id);
	}
}
