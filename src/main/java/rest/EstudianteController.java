package rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import registro.estudiantes.dao.Estudiante;
import repository.implementation.EstudianteImplementation;

@Path("/estudiantes")
public class EstudianteController {
	private EstudianteImplementation studentRepo;

//	http://localhost:8080/EjercicioIntegrador3/registroestudiantes/estudiantes/
	public EstudianteController() {
		studentRepo = EstudianteImplementation.getInstance();
	}

	// completar

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Estudiante> getAll() {
		return this.studentRepo.getAll();
	}

	@GET
	@Path("/sort")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Estudiante> getAllSort() {
		// PUNTO C
		return this.studentRepo.getEstudiantesSortByApellido();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Estudiante getEstudiante(@PathParam("id") int id) {
		// PUNTO D
		return studentRepo.get(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public void save(Estudiante estudiante) {
		// PUNTO A
		this.studentRepo.create(estudiante);
	}

}
