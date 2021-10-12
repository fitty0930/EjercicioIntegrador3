package repository;

import java.util.List;

import registro.estudiantes.dao.Ciudad;

public interface CiudadRepository extends CrudRepo<Ciudad, Integer> {
	Ciudad get(int id);

	Ciudad getByName(String name);

	void create(Ciudad pojo);

	boolean delete(Integer id);

	void update(Integer id);

	List<Ciudad> getAll();
}
