package dany.DTOMapStruct.repository;

import java.util.List;
import java.util.Optional;

public interface IRepository <T>
{
    List<T> findAll();

    T save(T entity);

    boolean delete(T entity);

    T update(T entity);

    Optional<T> findById(long id);

}
