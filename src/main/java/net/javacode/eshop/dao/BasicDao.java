package net.javacode.eshop.dao;

import net.javacode.eshop.entity.Cat;
import org.hibernate.Session;

import java.util.List;

public interface BasicDao<T> {

    /**
     * method for receiving all entities from DB
     *
     * @return List of all entities
     */
    List<T> getList();

    /**
     * method for receiving current session
     *
     * @return current session
     */
    Session getSession();

    T getById(Long id);

    /**
     * method for creating entity
     *
     * @param entity = new Entity
     * @return created entity
     */
    T add(T entity);

    /**
     * method for deleting entity
     *
     * @param entity = entity for deletion
     * @return deleted entity
     */
    T delete(T entity);

    /**
     * method for updating entity
     *
     * @param entity = entity for update
     * @return updated entity
     */
    T update(T entity);

}
