/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Arrays;
import java.util.List;
import model.KetQuaTimVe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author anhtuong
 */
@Controller
public class searchTicketController {

    @RequestMapping(value = "/timve", method = RequestMethod.POST)
    public String timve(ModelMap mm, @RequestParam(value = "MaSanBayDi") String MaSanBayDi, @RequestParam(value = "MaSanBayDen") String MaSanBayDen, @RequestParam(value = "NgayDi") String NgayDi) {

        try {
            //-------------Cua VNAirLine----------------
            final String uri = "http://nguyencaotri.somee.com/api/ve?MaSanBayDi=" + MaSanBayDi + "&MaSanBayDen=" + MaSanBayDen + "&NgayDi=" + NgayDi + "";

            RestTemplate restTemplate = new RestTemplate();
            List<KetQuaTimVe> listketquatimve = Arrays.asList(restTemplate.getForObject(uri, KetQuaTimVe[].class));

            mm.addAttribute("ListKetQuaTimVe", listketquatimve);

            if (listketquatimve.isEmpty()) {
                return "khongtimthayve";
            }

            return "timve";
        } catch (Exception e) {

            return "khongtimthayve";
        }
    }
}
