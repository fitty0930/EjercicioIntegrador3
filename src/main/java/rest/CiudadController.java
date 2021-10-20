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

import registro.estudiantes.dao.Ciudad;
import repository.implementation.CiudadImplementation;

@Path("ciudades")
public class CiudadController {
	private CiudadImplementation cityRepo;
//	http://localhost:8080/EjercicioIntegrador3/registroestudiantes/ciudades/

	public CiudadController() {
		cityRepo = CiudadImplementation.getInstance();
	}

	// BASICOS

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		// TODO Auto-generated method stub
		 try {
			 return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(this.cityRepo.getAll()).build();
		 } catch (Exception e) {
            System.out.println(e.getMessage());
            return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
		 }
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCiudad(@PathParam("id") int id) {
		try {
			 return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(cityRepo.get(id)).build();
		 } catch (Exception e) {
           System.out.println(e.getMessage());
           return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
		 }
	}

	@GET
	@Path("searchname/{name}") // REVISAR ESTA RUTA
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCiudadByName(@PathParam("name") String name) {
		try {
			 return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(cityRepo.getByName(name)).build();
		 } catch (Exception e) {
          System.out.println(e.getMessage());
          return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
		 }
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(Ciudad ciudad) {
		try {
			this.cityRepo.create(ciudad);
			return Response.status(201).header("Access-Control-Allow-Origin", "*").build();	
		 } catch (Exception e) {
         System.out.println(e.getMessage());
         return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
		 }
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteCity(@PathParam("id") int id) {
		if(this.cityRepo.delete(id)) {
			return Response.status(200).header("Access-Control-Allow-Origin", "*").build();
		}else {
			return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
		}
	}
}
