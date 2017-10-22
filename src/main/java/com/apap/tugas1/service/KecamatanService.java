package com.apap.tugas1.service;

import com.apap.tugas1.model.KecamatanModel;

import java.util.List;

public interface KecamatanService {

    String selectIdKecamatan(String namaKecamatan, String idKota);

    String selectKecamatanById(String id);

    List<KecamatanModel> selectAllKecamatan(String idKota);
}
