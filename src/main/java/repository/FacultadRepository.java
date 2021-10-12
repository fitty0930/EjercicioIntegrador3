package repository;

import java.util.List;

import registro.estudiantes.dao.Facultad;

public interface FacultadRepository extends CrudRepo<Facultad, Integer> {

	Facultad get(Integer id);

	Facultad getByName(String name);

	void create(Facultad pojo);

	boolean delete(Integer id);

	List<Facultad> getAll();
}
