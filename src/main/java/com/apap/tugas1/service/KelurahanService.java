package com.apap.tugas1.service;

import com.apap.tugas1.model.KelurahanModel;

import java.util.List;

public interface KelurahanService {

    KelurahanModel selectIdAndCodeKelurahan(String namaKelurahan, String idKecamatan);

    String selectKelurahanById(String id);

    List<KelurahanModel> selectAllKelurahan(String idKecamatan);
}
