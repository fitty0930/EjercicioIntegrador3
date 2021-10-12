package repository;

import java.util.List;

public interface CrudRepo<T, K> {

	T get(K id);

	T getByName(String name);

	void create(T pojo);

	boolean delete(K id);

	List<T> getAll();

}
