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

import DTO.EstudianteAndCareer;
import registro.estudiantes.dao.Estudiante;
import repository.implementation.EstudianteImplementation;

@Path("/estudiantes")
public class EstudianteController {
	private EstudianteImplementation studentRepo;

//	http://localhost:8080/EjercicioIntegrador3/registroestudiantes/estudiantes/
	public EstudianteController() {
		studentRepo = EstudianteImplementation.getInstance();
		
	}

	// BASICOS

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		 try {
			 return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(this.studentRepo.getAll()).build();
		 } catch (Exception e) {
            System.out.println(e.getMessage());
            return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
		 }
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEstudiante(@PathParam("id") int id) {
		// PUNTO D
		try {
			 return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(studentRepo.get(id)).build();
		 } catch (Exception e) {
           System.out.println(e.getMessage());
           return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
		 }
	}

	@GET
	@Path("searchname/{name}") // REVISAR ESTA RUTA
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEstudianteByName(@PathParam("name") String name) {
		try {
			 return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(studentRepo.getByName(name)).build();
		 } catch (Exception e) {
          System.out.println(e.getMessage());
          return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
		 }
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(Estudiante estudiante) {
		// PUNTO A
		try {
			this.studentRepo.create(estudiante);
			return Response.status(200).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "POST").build();	
		 } catch (Exception e) {
         System.out.println(e.getMessage());
         return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
		 }
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteStudent(@PathParam("id") int id) {
		if (this.studentRepo.delete(id)) {
			return Response.status(200).header("Access-Control-Allow-Origin", "*").build();
		} else {
			return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
		}
	}

	// ADICIONALES

	@GET
	@Path("/sort")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllSort() {
		// PUNTO C
		try {
			 return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(this.studentRepo.getEstudiantesSortByApellido()).build();
		 } catch (Exception e) {
           System.out.println(e.getMessage());
           return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
		 }
	}

	@POST
	@Path("/matricular")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response matricular(EstudianteAndCareer stuCar) {
		// PUNTO B
		try {
			 int nroestudiante = stuCar.getNroEstudiante();
			 String nombrecarrera = stuCar.getNombreCarrera();
			 this.studentRepo.matricularEstudiante(nroestudiante, nombrecarrera);
			 return Response.status(201).header("Access-Control-Allow-Origin", "*").build();
		 } catch (Exception e) {
          System.out.println(e.getMessage());
          return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
		 }
	}

	@GET
	@Path("/genero/{genero}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEstudiantesByGender(@PathParam("genero") String genero) {
		// PUNTO E
		try {
			 return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(this.studentRepo.getEstudiantesByGenero(genero)).build();
		 } catch (Exception e) {
          System.out.println(e.getMessage());
          return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
		 }
	}

	@GET
	@Path("/ciudad/{idcit}/carrera/{idcar}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEstudianteByCiudad(@PathParam("idcar") int idcar, @PathParam("idcit") int idcit) {
		// PUNTO G
//		254 22
		try {
			 return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(studentRepo.getEstudiantesByCiudad(idcit, idcar)).build();
		 } catch (Exception e) {
         System.out.println(e.getMessage());
         return Response.status(500).header("Access-Control-Allow-Origin", "*").build();
		 }
	}

}
