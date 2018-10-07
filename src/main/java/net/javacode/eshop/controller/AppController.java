package net.javacode.eshop.controller;

import net.javacode.eshop.entity.Cat;
import net.javacode.eshop.model.CreateTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppController {

    @Autowired
    private CreateTable createTable;

    @RequestMapping("/")
    @ResponseBody
    public String helloPage() {
        return "Ok!";
    }

    @RequestMapping("/create")
    @ResponseBody
    public String createTable() {
       return createTable.createTableStatus();
    }

    @RequestMapping("/admin/page")
    public String getSecurePage() {
        return "admin";
    }

    @RequestMapping("/user/**")
    public String getUserPage() {
        return "user";
    }

    @RequestMapping(value = "/password/{password}", method = RequestMethod.GET)
    @ResponseBody
    public String encode(@PathVariable ("password") String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

}
