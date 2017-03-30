package controller;

import dao.CustomerDAO;
import model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class registerController {

    private CustomerDAO dao;

    /**
     * GET: register.ute
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(ModelMap model) {
        model.addAttribute("user", new Customer());
        return "Register";
    }

    /**
     * POST: register.ute
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(ModelMap model,
            @ModelAttribute("user") Customer user) {

        try {
            dao.insert(user);
            return "Login";
        } catch (Exception ex) {
            model.addAttribute("errors", "User Name đã được sử dụng !");
            return "Register";
        }
    }

}
