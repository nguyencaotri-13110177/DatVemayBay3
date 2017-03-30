/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import model.ChuyenBay;
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
public class ticketInfoController {

    @RequestMapping(value = "/thongtindatve", method = RequestMethod.GET)
    public String thongtindatve(ModelMap mm, @RequestParam(value = "MaChuyenBay") String MaChuyenBay, @RequestParam(value = "MaGhe") String MaGhe, @RequestParam(value = "Gia") String Gia, @RequestParam(value = "Hang") String Hang) {
        final String uri = "http://nguyencaotri.somee.com/api/chuyenbay?id=" + MaChuyenBay + "";

        RestTemplate restTemplate = new RestTemplate();
        ChuyenBay chuyenbay = restTemplate.getForObject(uri, ChuyenBay.class);

        mm.addAttribute("ChuyenBay", chuyenbay);
        mm.put("MaGhe", MaGhe);
        mm.put("Gia", Gia);
        mm.put("Hang", Hang);

        return "thongtindatve";
    }

    @RequestMapping(value = "/thongtindatve", method = RequestMethod.POST)
    public String thongtindatve(ModelMap mm, @RequestParam(value = "ChonNhieu") String[] ChonNhieu, HttpSession session) {
        List ds = new ArrayList<String>();
        List dsghe = new ArrayList<String>();
        List dsgia = new ArrayList<String>();
        List dshang = new ArrayList<String>();
        List<ChuyenBay> dschuyenbay = new ArrayList<ChuyenBay>();

        for (int i = 0; i < ChonNhieu.length; i++) {
            if (ChonNhieu[i] != null) {
                String[] words = ChonNhieu[i].split("\\s");//chia chuoi dua tren string phan cac nhau boi khoang trang (\\s) 
                //su dung vong lap foreach de in cac phan tu trong mang chuoi  
                for (String w : words) {
                    ds.add(w);
                }
            }
        }
        double tonggiave = 0;
        mm.put("sove", ds.size() / 4);

        for (int i = 0; i < ds.size(); i = i + 4) {
            String uri = "http://nguyencaotri.somee.com/api/chuyenbay?id=" + ds.get(i) + "";

            RestTemplate restTemplate = new RestTemplate();
            ChuyenBay chuyenbay = restTemplate.getForObject(uri, ChuyenBay.class);

            tonggiave += Double.parseDouble((String) ds.get(i + 2));

            dschuyenbay.add(chuyenbay);
            dsghe.add(ds.get(i + 1));
            dsgia.add(ds.get(i + 2));
            dshang.add(ds.get(i + 3));

        }

        mm.put("dschuyenbay", dschuyenbay);
        mm.put("dsghe", dsghe);
        mm.put("dsgia", dsgia);
        mm.put("dshang", dshang);

        NumberFormat formatter = NumberFormat.getCurrencyInstance();//định dạng format money
        mm.put("tonggiave", formatter.format(tonggiave));

        System.out.print(dschuyenbay.get(0).getNgayDi());

        //--------Giữ vé trên service
        for (int i = 0; i < ds.size() / 4; i++) {

            String UriGiuVe = "http://nguyencaotri.somee.com/api/ve?MaChuyenBay=" + dschuyenbay.get(i).getMaChuyenBay() + "&MaGhe=" + dsghe.get(i) + "";

            RestTemplate restTemplate = new RestTemplate();

            boolean a = false;
            a = restTemplate.getForObject(UriGiuVe, boolean.class);

            System.out.println(a);
        }

        //------Thông báo thời gian giữ vé
        long ONE_MINUTE_IN_MILLIS = 60000;//millisecs

        Calendar date = Calendar.getInstance();
        long t = date.getTimeInMillis();
        Date afterAddingTenMins = new Date(t + (2 * ONE_MINUTE_IN_MILLIS));

        mm.put("mssThoiGianGiuVe", "Thời gian giữ vé của quý khách đến hết " + afterAddingTenMins + ", vui lòng hoàn thành thanh toán trước thời gian trên!");

        return "thongtindatve2";
    }
}
