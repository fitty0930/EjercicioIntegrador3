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

import registro.estudiantes.dao.SituacionAcademica;
import repository.implementation.SituacionAcademicaImplementation;

@Path("/situaciones")
public class SituacionAcademicaController {
	private SituacionAcademicaImplementation situRepo;

//	http://localhost:8080/EjercicioIntegrador3/registroestudiantes/situaciones/
	public SituacionAcademicaController() {
		situRepo = SituacionAcademicaImplementation.getInstance();
	}

	// BASICOS

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<SituacionAcademica> getAll() {
		return this.situRepo.getAll();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public SituacionAcademica getSituacion(@PathParam("id") int id) {
		return situRepo.get(id);
	}

	@GET
	@Path("searchname/{name}") // REVISAR ESTA RUTA
	@Produces(MediaType.APPLICATION_JSON)
	public SituacionAcademica getSituacionByName(@PathParam("name") String name) {
		return situRepo.getByName(name);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response save(SituacionAcademica situacion) {
		this.situRepo.create(situacion);
		return Response.status(201).entity(situacion).build();
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public void deleteSituacion(@PathParam("id") int id) {
		this.situRepo.delete(id);
	}
}
