package net.javacode.eshop.service;

import net.javacode.eshop.entity.Cat;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CatService {

    /**
     * method for receiving all cats from DB
     *
     * @return List of all cats
     */
    List<Cat> getAllCats();

    /**
     * method for receiving Cat by id
     *
     * @param id = Cat's id
     * @return Cat by id
     */
    Cat getById(long id);

    /**
     * method for creating Cat
     *
     * @param cat = new Cat
     * @return created Cat
     */
    Cat add(Cat cat);

    /**
     * method for deleting Cat by id
     *
     * @param id = Cat's id
     * @return deleted Cat
     */
    Cat delete(long id);

    /**
     * method for updating Cat
     *
     * @param cat = Cat for update
     * @return updated Cat
     */
    Cat update(Cat cat);
}
