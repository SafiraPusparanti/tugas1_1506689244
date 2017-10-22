package com.apap.tugas1.service;

import com.apap.tugas1.model.PendudukModel;

import java.util.List;

public interface PendudukService {

    PendudukModel selectPenduduk(String nik);

    PendudukModel selectProfilPenduduk(String nik);

    List<PendudukModel> selectAllPenduduk(String idKelurahan);

    PendudukModel selectYoungestPenduduk(String idKelurahan);

    PendudukModel selectOldestPenduduk(String idKelurahan);

    int selectMaxId();

    String fetchNikFromKelurahan (int idKeluarga);

    int countAllSimiliarNik (String nik);

    void addPenduduk(PendudukModel penduduk);

    void updatePenduduk(PendudukModel penduduk);

    void deactivatePenduduk(PendudukModel penduduk);

    int countAliveFamilyMembers (int idKeluarga);
}
