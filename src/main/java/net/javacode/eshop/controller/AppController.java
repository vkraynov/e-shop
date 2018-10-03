package net.javacode.eshop.controller;

import net.javacode.eshop.model.Cat;
import net.javacode.eshop.model.CreateTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController {
    @Autowired
    private Cat cat;

    @Autowired
    private CreateTable createTable;

    @RequestMapping("/")
    @ResponseBody
    public Cat helloPage(Model model) {
        cat.setName("Barsik");
        model.addAttribute("name", "Vasya Pupkin");
        return cat;
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
