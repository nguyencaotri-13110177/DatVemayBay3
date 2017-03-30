/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CustomerDAO;
import javax.servlet.http.HttpSession;
import model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author anhtuong
 */
@Controller
public class loginController {

    /**
     * Inject từ @Repository CustomerDAO
     */
    private CustomerDAO dao;

    /**
     * GET: login.ute
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model) {
        model.addAttribute("user", new Customer());
        return "Login";
    }

    /**
     * GET: login.ute
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(ModelMap model,
            @ModelAttribute("user") Customer user, HttpSession session)
            throws ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        try {
            Customer cust = dao.getById(user.getId());
            if (cust.getPassword().equals(user.getPassword())) {
                session.setAttribute("username", user.getId());
                return "redirect:main.ute";
            } else {
                model.addAttribute("errors", "Sai mật khẩu !");
            }
        } catch (Exception ex) {
            model.addAttribute("errors", "Sai mã đăng nhập !");
        }
        return "Login";
    }
}
