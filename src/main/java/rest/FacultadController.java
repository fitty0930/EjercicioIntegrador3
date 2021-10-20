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
	public Response getAll() {
		try {
			return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(this.facuRepo.getAll())
					.build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
		}
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFacultad(@PathParam("id") int id) {
		try {
			return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(facuRepo.get(id)).build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
		}
	}

	@GET
	@Path("searchname/{name}") // REVISAR ESTA RUTA
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFacultadByName(@PathParam("name") String name) {
		try {
			return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(facuRepo.getByName(name))
					.build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(Facultad facultad) {
		try {
			this.facuRepo.create(facultad);
			return Response.status(201).header("Access-Control-Allow-Origin", "*").build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
		}
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteFacultad(@PathParam("id") int id) {
		if (this.facuRepo.delete(id)) {
			return Response.status(200).header("Access-Control-Allow-Origin", "*").build();
		} else {
			return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
		}

	}
}
