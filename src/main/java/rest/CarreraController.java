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
		// TODO Auto-generated method stub
//		return this.carreraRepo.getAll();
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
	public Carrera getCarrera(@PathParam("id") int id) {
		return carreraRepo.get(id);
	}

	@GET
	@Path("searchname/{name}") // REVISAR ESTA RUTA
	@Produces(MediaType.APPLICATION_JSON)
	public Carrera getCarreraByName(@PathParam("name") String name) {
		return carreraRepo.getByName(name);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response save(Carrera carrera) {
		this.carreraRepo.create(carrera);
		return Response.status(201).entity(carrera).build();
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteCareer(@PathParam("id") int id) {
		if(this.carreraRepo.delete(id)) {
			return Response.status(200).build();
		}else {
			return Response.status(500).build();
		}
	}

	// ADICIONALES

	@GET
	@Path("inscriptosSortByCant")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Carrera> getAllWithEnrolled() {
		// PUNTO F
		return this.carreraRepo.getCarrerasConEstudiantesSortByCantidad();
	}

	@GET
	@Path("reporte")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ReporteCarreras> getReport() {
		// PUNTO H
		return this.carreraRepo.getReporte();
	}

}
