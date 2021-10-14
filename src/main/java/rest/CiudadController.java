package rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import registro.estudiantes.dao.Ciudad;
import repository.implementation.CiudadImplementation;

@Path("/ciudades")
public class CiudadController {
	private CiudadImplementation cityRepo;
//	http://localhost:8080/EjercicioIntegrador3/registroestudiantes/ciudades/

	public CiudadController() {
		cityRepo = CiudadImplementation.getInstance();
	}
	
	// BASICOS

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Ciudad> getAll() {
		// TODO Auto-generated method stub
		return this.cityRepo.getAll();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Ciudad getCiudad(@PathParam("id") int id) {
		return cityRepo.get(id);
	}

	@GET
	@Path("searchname/{name}") // REVISAR ESTA RUTA
	@Produces(MediaType.APPLICATION_JSON)
	public Ciudad getCiudadByName(@PathParam("name") String name) {
		return cityRepo.getByName(name);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public void save(Ciudad ciudad) {
		this.cityRepo.create(ciudad);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public void deleteCity(@PathParam("id") int id) {
		this.cityRepo.delete(id);
	}
}
