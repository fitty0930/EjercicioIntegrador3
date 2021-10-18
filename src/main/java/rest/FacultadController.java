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
import javax.ws.rs.core.Response;

import registro.estudiantes.dao.Facultad;
import repository.implementation.FacultadImplementation;

@Path("/facultades")
public class FacultadController {
	private FacultadImplementation facuRepo;

//	http://localhost:8080/EjercicioIntegrador3/registroestudiantes/facultades/
	public FacultadController() {
		facuRepo = FacultadImplementation.getInstance();
	}

	// BASICOS

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Facultad> getAll() {
		return this.facuRepo.getAll();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Facultad getFacultad(@PathParam("id") int id) {
		return facuRepo.get(id);
	}

	@GET
	@Path("searchname/{name}") // REVISAR ESTA RUTA
	@Produces(MediaType.APPLICATION_JSON)
	public Facultad getFacultadByName(@PathParam("name") String name) {
		return facuRepo.getByName(name);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response save(Facultad facultad) {
		this.facuRepo.create(facultad);
		return Response.status(201).entity(facultad).build();
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public void deleteFacultad(@PathParam("id") int id) {
		if(this.facuRepo.delete(id)){
			System.out.println("Facultad eliminada con exito");
		}else{
			System.out.println("Por favor verifique el orden de borrado");
		}
		
	}
}
