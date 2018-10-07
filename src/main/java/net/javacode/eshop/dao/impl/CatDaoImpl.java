package net.javacode.eshop.dao.impl;

import net.javacode.eshop.dao.BasicDao;
import net.javacode.eshop.dao.CatDao;
import net.javacode.eshop.entity.Cat;

public class CatDaoImpl extends BasicDaoImpl<Cat> implements CatDao {
    public CatDaoImpl(Class<Cat> entityClass) {
        super(entityClass);
    }
}
