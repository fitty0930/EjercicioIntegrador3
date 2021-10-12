package repository;

import java.util.List;

import registro.estudiantes.dao.SituacionAcademica;

public interface SituacionAcademicaRepository extends CrudRepo<SituacionAcademica, Integer> {

	SituacionAcademica get(Integer id);

	SituacionAcademica getByName(String name);

	void create(SituacionAcademica pojo);

	boolean delete(Integer id);

	List<SituacionAcademica> getAll();
}
