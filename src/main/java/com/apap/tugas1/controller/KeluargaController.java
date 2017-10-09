package com.apap.tugas1.controller;

import com.apap.tugas1.model.KeluargaModel;
import com.apap.tugas1.service.KeluargaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Slf4j
@Controller
public class KeluargaController {
    @Autowired
    KeluargaService keluargaDAO;

    @RequestMapping("/keluarga")
    public String view(Model model, @RequestParam(value="nomor_kk") String nomor_kk){
        KeluargaModel keluarga = keluargaDAO.selectKeluarga(nomor_kk);

        if(keluarga != null){

            model.addAttribute("keluarga", keluarga);
            return "view-keluarga";
        } else {
            return "not-found";
        }
    }

    @RequestMapping("/keluarga/tambah")
    public String add(Model model){
        KeluargaModel keluarga = new KeluargaModel();
        model.addAttribute("keluarga", keluarga);
        return "add-keluarga";
    }

    @RequestMapping("/keluarga/tambah/submit")
    public String submitPenduduk(Model model, KeluargaModel keluarga) {
        String kecamatan = keluarga.getIdKelurahan();
       
//        model.addAttribute("nkk", nkk);
        return "add-keluarga-success";
    }
}
