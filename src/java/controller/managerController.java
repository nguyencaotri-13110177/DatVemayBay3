/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author anhtuong
 */
@Controller
public class managerController {

    @RequestMapping(value = "/quanly1", method = RequestMethod.GET)
    public String quanly(ModelMap mm, @RequestParam(value = "thongbao") String thongbao) {
        mm.put("thongbao", thongbao);
        return "quanly";
    }

    @RequestMapping(value = "/quanly", method = RequestMethod.GET)
    public String quanly(ModelMap mm) {
        return "quanly";
    }
}
