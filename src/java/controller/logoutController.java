/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package controller;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpSession;
import model.SanBay;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author anhtuong
 */
@Controller
public class logoutController {

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("username");
        return "redirect:main.ute";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String index(ModelMap mm) {
        final String uri = "http://nguyencaotri.somee.com/api/sanbay";

        RestTemplate restTemplate = new RestTemplate();
        List<SanBay> listsanbay = Arrays.asList(restTemplate.getForObject(uri, SanBay[].class));

        mm.addAttribute("ListSanBay", listsanbay);
        mm.put("sb", new SanBay());

        return "index";
    }

}
