package repository;

import java.util.List;

import DTO.ReporteCarreras;
import registro.estudiantes.dao.Carrera;

public interface CarreraRepository extends CrudRepo<Carrera, Integer> {

	Carrera get(Integer id);

	Carrera getByName(String name);

	void create(Carrera pojo);

	boolean delete(Integer id);

	List<Carrera> getAll();

	List<Carrera> getCarrerasConEstudiantesSortByCantidad();

	List<ReporteCarreras> getReporte();
}
