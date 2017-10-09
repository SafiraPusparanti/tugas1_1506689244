package com.apap.tugas1.controller;

import com.apap.tugas1.model.PendudukModel;
import com.apap.tugas1.service.KecamatanService;
import com.apap.tugas1.service.PendudukService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.StringTokenizer;
@Slf4j
@Controller
public class PendudukController {
    @Autowired
    PendudukService pendudukDAO;
    @Autowired
    KecamatanService kecamatanDAO;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/penduduk")
    public String view(Model model, @RequestParam(value="nik") String nik){
        PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);

        if(penduduk != null){
            model.addAttribute("penduduk", penduduk);
            return "view-penduduk";
        } else {
            return "not-found";
        }
    }

    @RequestMapping("/penduduk/tambah")
    public String add(Model model){
        PendudukModel penduduk = new PendudukModel();
        model.addAttribute("penduduk", penduduk);
        return "add-penduduk";
    }

    @RequestMapping("/penduduk/tambah/submit")
    public String submitPenduduk(Model model, PendudukModel penduduk){
        String tempat = penduduk.getTempatLahir();
        String[] split = tempat.split("(Kecamatan )");
        String kecamatan = split[1];
        String nik = kecamatanDAO.selectKecamatan(kecamatan);
        nik = nik.substring(0, 6);
//        System.out.println("NIK apa?" + nik);

        String tanggal = penduduk.getTanggalLahir();
        String[] str = tanggal.split("-");
        nik += str[0] + str[1] + str[2].substring(2);
        System.out.println("nik lengkap apa hayo? " + nik);
        penduduk.setNik(nik);
        System.out.println("masuk set nik tuh");
        penduduk.setTanggalLahir(str[2] + "-" + str[1] + "-" + str[0]);
        pendudukDAO.addPenduduk(penduduk);
        System.out.println("berhasil add line 62 penduduk controller");
        model.addAttribute("nik", nik);
        return "add-penduduk-success";
    }
}
