package com.apap.tugas1.controller;

import com.apap.tugas1.model.KeluargaModel;
import com.apap.tugas1.model.KelurahanModel;
import com.apap.tugas1.model.KotaModel;
import com.apap.tugas1.model.PendudukModel;
import com.apap.tugas1.service.KecamatanService;
import com.apap.tugas1.service.KeluargaService;
import com.apap.tugas1.service.KelurahanService;
import com.apap.tugas1.service.KotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Controller
public class KeluargaController {
    @Autowired
    KeluargaService keluargaDAO;
    @Autowired
    KelurahanService kelurahanDAO;
    @Autowired
    KecamatanService kecamatanDAO;
    @Autowired
    KotaService kotaDAO;

    @RequestMapping("/keluarga")
    public String view(Model model, @RequestParam(value="nomor_kk") String nomor_kk) throws ParseException {
        KeluargaModel keluarga = keluargaDAO.selectProfilKeluarga(nomor_kk);

        if(keluarga != null){
            Iterator<PendudukModel> pendudukItr = keluarga.getPenduduks().iterator();
            while (pendudukItr.hasNext()) {
                PendudukModel penduduk = pendudukItr.next();
                String tglLahir = penduduk.getNik().substring(6, 12);

                if (penduduk.getJenis_kelamin() == 1){
                    int tanggal = Integer.parseInt(tglLahir.substring(0, 2)) - 50;
                    tglLahir = tanggal + tglLahir.substring(2, 6);
                    if(tglLahir.length()==5) {
                        tglLahir = "0" + tglLahir;
                    }
                }

                SimpleDateFormat formatter = new SimpleDateFormat("ddMMyy");
                Date date = formatter.parse(tglLahir);
                SimpleDateFormat formatString = new SimpleDateFormat("dd MMM yyyy");
                penduduk.setTanggal_lahir(formatString.format(date));
            }

            model.addAttribute("keluarga", keluarga);
            return "view-keluarga";
        } else {
            return "error/404";
        }
    }

    @RequestMapping("/keluarga/tambah")
    public String add(Model model){
        List<KotaModel> kotas = kotaDAO.selectAllKota();
        KeluargaModel keluarga = new KeluargaModel();

        model.addAttribute("title", "Tambah Keluarga");
        model.addAttribute("action", "/keluarga/tambah");
        model.addAttribute("kotas", kotas);
        model.addAttribute("keluarga", keluarga);
        return "form-keluarga";
    }

    @RequestMapping(value="/keluarga/tambah", method= RequestMethod.POST)
    public String submitKeluarga(Model model, KeluargaModel keluarga) {
        String idKecamatan = kecamatanDAO.selectIdKecamatan(keluarga.getKecamatan(), keluarga.getId_kota());

        if(idKecamatan == null) {
            model.addAttribute("message", "Kecamatan tidak ditemukan dan/atau kombinasi Kecamatan dengan Kota salah.");
            return "error/wrong-input";
        }

        KelurahanModel idNkode = kelurahanDAO.selectIdAndCodeKelurahan(keluarga.getKelurahan(), idKecamatan);

        if(idNkode != null) {
            int id = keluargaDAO.selectMaxId();
            keluarga.setId(id+1);

            String nkk = idNkode.getKode_kelurahan();//mencari kelurahan dari isian nama keluarhan dan mengembalikan kode kelurahan
            keluarga.setId_kelurahan(Integer.toString(idNkode.getId()));

            nkk = nkk.substring(0, 6);

            LocalDate localDate = LocalDate.now();
            String date = DateTimeFormatter.ofPattern("dd-MM-yy").format(localDate);
            String[] splitDate = date.split("-");
            nkk += splitDate[0] + splitDate[1] + splitDate[2];

            int lastNkkDigits = keluargaDAO.countAllSimiliarNkk(nkk + "%") + 1;
            nkk = nkk + String.format("%04d", lastNkkDigits);

            keluarga.setNomor_kk(nkk);
            keluarga.setIs_tidak_berlaku(0);

            keluargaDAO.addKeluarga(keluarga);

            model.addAttribute("title", "Tambah Keluarga");
            model.addAttribute("done", "Penambahan");
            model.addAttribute("nkk", nkk);
            return "form-keluarga-success";
        } else {
            model.addAttribute("message", "Kelurahan tidak ditemukan dan/atau kombinasi Kelurahan dengan Kecamatan dan Kota salah.");
            return "error/wrong-input";
        }
    }

    @RequestMapping("/keluarga/ubah/{nkk}")
    public String edit(Model model, @PathVariable(value="nkk") String nkk){
        List<KotaModel> kotas = kotaDAO.selectAllKota();
        KeluargaModel keluarga = keluargaDAO.selectKeluarga(nkk);

        if(keluarga == null){
            model.addAttribute("message", "Nomor KK tidak ditemukan.");
            return "error/wrong-input";
        }

        model.addAttribute("title", "Ubah Keluarga");
        model.addAttribute("action", "/keluarga/ubah/{nkk}");
        model.addAttribute("kotas", kotas);
        model.addAttribute("keluarga", keluarga);
        return "form-keluarga";
    }

    @RequestMapping(value="/keluarga/ubah/{nkk}", method= RequestMethod.POST)
    public String submitEdit(Model model, KeluargaModel keluarga){
        String idKecamatan = kecamatanDAO.selectIdKecamatan(keluarga.getKecamatan(), keluarga.getId_kota());

        if(idKecamatan == null) {
            model.addAttribute("message", "Kecamatan tidak ditemukan dan/atau kombinasi Kecamatan dengan Kota salah.");
            return "error/wrong-input";
        }

        KelurahanModel idNkode = kelurahanDAO.selectIdAndCodeKelurahan(keluarga.getKelurahan(), idKecamatan);

        if(idNkode != null) {
            String nkk = idNkode.getKode_kelurahan();
            keluarga.setId_kelurahan(Integer.toString(idNkode.getId()));

            nkk = nkk.substring(0, 6);

            LocalDate localDate = LocalDate.now();
            String date = DateTimeFormatter.ofPattern("dd-MM-yy").format(localDate);
            String[] splitDate = date.split("-");
            nkk += splitDate[0] + splitDate[1] + splitDate[2];

            String nkkLama = keluarga.getNomor_kk();

            if (!nkk.equals(nkkLama.substring(0, 12))) {
                int lastNkkDigits = keluargaDAO.countAllSimiliarNkk(nkk + "%") + 1;
                nkk = nkk + String.format("%04d", lastNkkDigits);
                keluarga.setNomor_kk(nkk);
            } else {
                keluarga.setNomor_kk(nkkLama);
            }

            keluargaDAO.updateKeluarga(keluarga);

            model.addAttribute("title", "Ubah Keluarga");
            model.addAttribute("done", "Pengubahan");
            model.addAttribute("nkk", nkkLama);
            return "form-keluarga-success";
        } else {
            model.addAttribute("message", "Kelurahan tidak ditemukan dan/atau kombinasi Kelurahan dengan Kecamatan dan Kota salah.");
            return "error/wrong-input";
        }
    }
}
