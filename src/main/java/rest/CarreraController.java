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

import DTO.ReporteCarreras;
import registro.estudiantes.dao.Carrera;
import repository.implementation.CarreraImplementation;

@Path("/carreras")
public class CarreraController {
	private CarreraImplementation carreraRepo;

//	http://localhost:8080/EjercicioIntegrador3/registroestudiantes/carreras/
	public CarreraController() {
		this.carreraRepo = CarreraImplementation.getInstance();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		 try {
			 return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(this.carreraRepo.getAll()).build();
		 } catch (Exception e) {
            System.out.println(e.getMessage());
            return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
		 }
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCarrera(@PathParam("id") int id) {
		try {
			return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(carreraRepo.get(id)).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
        }
		
	}

	@GET
	@Path("searchname/{name}") // REVISAR ESTA RUTA
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCarreraByName(@PathParam("name") String name) {
		try {
			return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(carreraRepo.getByName(name)).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
        }
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response save(Carrera carrera) {
		try {
			this.carreraRepo.create(carrera);
			return Response.status(201).header("Access-Control-Allow-Origin", "*").build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
        }
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteCareer(@PathParam("id") int id) {
		if(this.carreraRepo.delete(id)) {
			return Response.status(200).header("Access-Control-Allow-Origin", "*").build();
		}else {
			return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
		}
	}

	// ADICIONALES

	@GET
	@Path("inscriptosSortByCant")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllWithEnrolled() {
		 try {
			 return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(this.carreraRepo.getCarrerasConEstudiantesSortByCantidad()).build();
		 } catch (Exception e) {
            System.out.println(e.getMessage());
            return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
		 }
	}

	@GET
	@Path("reporte")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReport() {
		try {
			 return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(this.carreraRepo.getReporte()).build();
		 } catch (Exception e) {
           System.out.println(e.getMessage());
           return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
		 }
	}

}
