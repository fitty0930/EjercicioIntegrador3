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
	public List<Estudiante> getAll() {
		return this.studentRepo.getAll();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Estudiante getEstudiante(@PathParam("id") int id) {
		// PUNTO D
		return studentRepo.get(id);
	}

	@GET
	@Path("searchname/{name}") // REVISAR ESTA RUTA
	@Produces(MediaType.APPLICATION_JSON)
	public Estudiante getEstudianteByName(@PathParam("name") String name) {
		return studentRepo.getByName(name);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response save(Estudiante estudiante) {
		// PUNTO A
		this.studentRepo.create(estudiante);
		return Response.status(201).entity(estudiante).build();
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public void deleteStudent(@PathParam("id") int id) {
		if(this.studentRepo.delete(id)) {
			System.out.println("Estudiante eliminado con exito");
		}else {
			System.out.println("Por favor verifique el orden de borrado");
		}
	}

	// ADICIONALES

	@GET
	@Path("/sort")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Estudiante> getAllSort() {
		// PUNTO C
		return this.studentRepo.getEstudiantesSortByApellido();
	}

	@POST
	@Path("/matricular")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response matricular(EstudianteAndCareer stuCar) {
		// PUNTO B
		int nroestudiante = stuCar.getNroEstudiante();
		String nombrecarrera = stuCar.getNombreCarrera();
		this.studentRepo.matricularEstudiante(nroestudiante, nombrecarrera);
		return Response.status(201).build();
	}

	@GET
	@Path("/genero/{genero}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Estudiante> getEstudiantesByGender(@PathParam("genero") String genero) {
		// PUNTO E
		return this.studentRepo.getEstudiantesByGenero(genero);
	}

	@GET
	@Path("/ciudad/{idcit}/carrera/{idcar}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Estudiante> getEstudianteByCiudad(@PathParam("idcar") int idcar, @PathParam("idcit") int idcit) {
		// PUNTO G
//		254 22
		return studentRepo.getEstudiantesByCiudad(idcit, idcar);
	}

}
