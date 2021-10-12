package repository;

import java.util.List;

import registro.estudiantes.dao.Estudiante;

public interface EstudianteRepository extends CrudRepo<Estudiante, Integer> {
	Estudiante get(Integer id);

	Estudiante getByName(String name);

	void create(Estudiante pojo);

	boolean delete(Integer id);

	List<Estudiante> getAll();
}
