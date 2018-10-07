package net.javacode.eshop.service.impl;

import net.javacode.eshop.dao.CatDao;
import net.javacode.eshop.entity.Cat;
import net.javacode.eshop.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("catService")
public class CatServiceImpl implements CatService {

    @Autowired
    private CatDao catDao;

    @Override
    public List<Cat> getAllCats() {
       return catDao.getList();
    }

    @Override
    public Cat getById(long id) {
        return catDao.getById(id);
    }

    @Override
    public Cat add(Cat cat) {
       return catDao.add(cat);
    }

    @Override
    public Cat delete(long id) {
        return catDao.delete(getById(id));
    }

    @Override
    public Cat update(Cat cat) {
       return catDao.update(cat);
    }
}
