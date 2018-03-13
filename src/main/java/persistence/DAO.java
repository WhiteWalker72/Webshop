package persistence;

import java.util.List;

public interface DAO<T> {

    List<T> findAll();
    T findById(String identifier) throws ObjectNotFoundException;

    void insert(T dto) throws ObjectAlreadyExistsException;
    void update(T dto) throws ObjectNotFoundException;
    void delete(String identifier) throws ObjectNotFoundException;

}