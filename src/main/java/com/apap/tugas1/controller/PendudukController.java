package com.apap.tugas1.controller;

import com.apap.tugas1.model.*;
import com.apap.tugas1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class PendudukController {
    @Autowired
    PendudukService pendudukDAO;
    @Autowired
    KecamatanService kecamatanDAO;
    @Autowired
    KeluargaService keluargaDAO;
    @Autowired
    KotaService kotaDAO;
    @Autowired
    KelurahanService kelurahanDAO;

    @RequestMapping("/")
    public String index(Model model) {

        model.addAttribute("activeNav", "index");
        return "index";
    }

    @RequestMapping("/penduduk")
    public String view(Model model, @RequestParam(value = "nik") String nik) throws ParseException {

        PendudukModel penduduk = pendudukDAO.selectProfilPenduduk(nik);

        if (penduduk != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(penduduk.getTanggal_lahir());

            SimpleDateFormat formatString = new SimpleDateFormat("dd MMM yyyy");
            penduduk.setTanggal_lahir(formatString.format(date));

            model.addAttribute("penduduk", penduduk);
            return "view-penduduk";
        } else {
            return "error/404";
        }
    }

    @RequestMapping("/penduduk/tambah")
    public String add(Model model) {
        PendudukModel penduduk = new PendudukModel();

        model.addAttribute("title", "Tambah Penduduk");
        model.addAttribute("action", "/penduduk/tambah");
        model.addAttribute("penduduk", penduduk);

        model.addAttribute("activeNav", "tambahPenduduk");
        return "form-penduduk";
    }

    @RequestMapping(value = "/penduduk/tambah", method = RequestMethod.POST)
    public String submitPenduduk(Model model, PendudukModel penduduk) {
        String nik = pendudukDAO.fetchNikFromKelurahan(penduduk.getId_keluarga());

        String rtrn = pendudukProfileValidator(model, nik, penduduk.getTanggal_lahir());
        if(rtrn != null) {
            return rtrn;
        }

        int id = pendudukDAO.selectMaxId();
        penduduk.setId(id+1);

        nik = nik.substring(0, 6);
        String[] tglLahir = penduduk.getTanggal_lahir().split("-");
        if (penduduk.getJenis_kelamin() == 1) {
            int tanggal = Integer.parseInt(tglLahir[0]) + 40;
            nik = nik + tanggal + tglLahir[1] + tglLahir[2].substring(2, 4);
        }

        nik = nik + tglLahir[0] + tglLahir[1] + tglLahir[2].substring(2, 4);
        int lastNikDigits = pendudukDAO.countAllSimiliarNik(nik + "%") + 1;
        nik = nik + String.format("%04d", lastNikDigits);
        penduduk.setNik(nik);

        penduduk.setTanggal_lahir(tglLahir[2] + "-" + tglLahir[1] + "-" + tglLahir[0]);

        pendudukDAO.addPenduduk(penduduk);

        model.addAttribute("title", "Tambah Penduduk");
        model.addAttribute("done", "Tambah");
        model.addAttribute("nik", nik);
        return "form-penduduk-success";
    }

    @RequestMapping("/penduduk/ubah/{nik}")
    public String edit(Model model, @PathVariable(value = "nik") String nik) {
        PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);

        if(penduduk == null){
            model.addAttribute("message", "NIK tidak ditemukan.");
            return "error/wrong-input";
        }

        String[] tanggalSplit = penduduk.getTanggal_lahir().split("-");
        penduduk.setTanggal_lahir(tanggalSplit[2] + "-" + tanggalSplit[1] + "-" + tanggalSplit[0]);

        model.addAttribute("title", "Ubah Penduduk");
        model.addAttribute("action", "/penduduk/ubah/{nik}");
        model.addAttribute("penduduk", penduduk);
        return "form-penduduk";
    }

    @RequestMapping(value = "/penduduk/ubah/{nik}", method = RequestMethod.POST)
    public String submitEdit(Model model, PendudukModel penduduk, @PathVariable(value = "nik") String nikLama) {

        String nik = pendudukDAO.fetchNikFromKelurahan(penduduk.getId_keluarga());

        String rtrn = pendudukProfileValidator(model, nik, penduduk.getTanggal_lahir());
        if(rtrn != null) {
            return rtrn;
        }

        nik = nik.substring(0, 6);

        String[] tglLahir = penduduk.getTanggal_lahir().split("-");
        if (penduduk.getJenis_kelamin() == 1) {
            int tanggal = Integer.parseInt(tglLahir[0]) + 40;
            nik = nik + tanggal + tglLahir[1] + tglLahir[2].substring(2, 4);
        }

        nikLama = penduduk.getNik();

        if (!nik.equals(nikLama.substring(0, 12))) {
            int lastNikDigits = pendudukDAO.countAllSimiliarNik(nik + "%") + 1;
            nik = nik + String.format("%04d", lastNikDigits);
            penduduk.setNik(nik);
        } else {
            penduduk.setNik(nikLama);
        }

        penduduk.setTanggal_lahir(tglLahir[2] + "-" + tglLahir[1] + "-" + tglLahir[0]);

        pendudukDAO.updatePenduduk(penduduk);

        model.addAttribute("done", "Ubah");
        model.addAttribute("nik", nikLama);
        return "form-penduduk-success";
    }

    @RequestMapping(value = "/penduduk/mati", method = RequestMethod.POST)
    public String deactivatePost(Model model, PendudukModel penduduk) {
        pendudukDAO.deactivatePenduduk(penduduk);

        if (pendudukDAO.countAliveFamilyMembers(penduduk.getId_keluarga()) == 0) {
            keluargaDAO.deactivateKeluarga(penduduk.getId_keluarga());
        }

        model.addAttribute("penduduk", penduduk);
        return "deactivate-penduduk-success";
    }

    @RequestMapping("/penduduk/cari")
    public String viewAllPenduduk(Model model,
                                  @RequestParam(value = "kt", required = false) String idKota,
                                  @RequestParam(value = "kc", required = false) String idKecamatan,
                                  @RequestParam(value = "kl", required = false) String idKelurahan) {

        model.addAttribute("activeNav", "cari");

        if (idKelurahan != null) {
            return showData(model, idKota, idKecamatan, idKelurahan);

        } else if (idKecamatan != null) {
            return findKelurahan(model, idKota, idKecamatan);

        } else if (idKota != null) {
            return findKecamatan(model, idKota);

        } else {
            List<KotaModel> kotas = kotaDAO.selectAllKota();

            model.addAttribute("kotas", kotas);
            return "find-kota";
        }
    }

    public String showData(Model model, String idKota, String idKecamatan, String idKelurahan){
        List<PendudukModel> penduduks = pendudukDAO.selectAllPenduduk(idKelurahan);
        PendudukModel youngestPenduduk = pendudukDAO.selectYoungestPenduduk(idKelurahan);
        PendudukModel oldestPenduduk = pendudukDAO.selectOldestPenduduk(idKelurahan);

        String namaKota = kotaDAO.selectKotaById(idKota).toLowerCase();
        String namaKecamatan = kecamatanDAO.selectKecamatanById(idKecamatan).toLowerCase();
        String namaKelurahan = kelurahanDAO.selectKelurahanById(idKelurahan).toLowerCase();

        model.addAttribute("youngest", youngestPenduduk);
        model.addAttribute("oldest", oldestPenduduk);
        model.addAttribute("namaKota", namaKota);
        model.addAttribute("namaKecamatan", namaKecamatan);
        model.addAttribute("namaKelurahan", namaKelurahan);
        model.addAttribute("penduduks", penduduks);
        return "view-all-penduduk-by-kelurahan";
    }

    public String findKelurahan(Model model, String idKota, String idKecamatan){
        List<KelurahanModel> kelurahans = kelurahanDAO.selectAllKelurahan(idKecamatan);
        List<KecamatanModel> kecamatans = kecamatanDAO.selectAllKecamatan(idKota);

        String namaKota = kotaDAO.selectKotaById(idKota).toLowerCase();
        String namaKecamatan = kecamatanDAO.selectKecamatanById(idKecamatan).toLowerCase();

        model.addAttribute("kecamatans", kecamatans);
        model.addAttribute("kelurahans", kelurahans);
        model.addAttribute("namaKota", namaKota);
        model.addAttribute("idKota", idKota);
        model.addAttribute("namaKecamatan", namaKecamatan);
        model.addAttribute("idKecamatan", idKecamatan);
        return "find-kelurahan";
    }

    public String findKecamatan(Model model, String idKota){
        List<KecamatanModel> kecamatans = kecamatanDAO.selectAllKecamatan(idKota);
        List<KotaModel> kotas = kotaDAO.selectAllKota();

        String namaKota = kotaDAO.selectKotaById(idKota).toLowerCase();

        model.addAttribute("namaKota", namaKota);
        model.addAttribute("idKota", idKota);
        model.addAttribute("kotas", kotas);
        model.addAttribute("kecamatans", kecamatans);
        return "find-kecamatan";
    }

    public String pendudukProfileValidator(Model model, String nik, String tglLahir) {
        if (nik == null) {
            model.addAttribute("message", "ID Keluarga tidak ditemukan.");
            return "error/wrong-input";
        }

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Date date = formatter.parse(tglLahir);

            if (!tglLahir.equals(formatter.format(date))) {
                model.addAttribute("message", "Format penulisan tanggal salah.");
                return "error/wrong-input";
            }
            return null;
        } catch (ParseException e) {

            model.addAttribute("message", "Format penulisan tanggal salah.");
            return "error/wrong-input";
        }
    }
}
