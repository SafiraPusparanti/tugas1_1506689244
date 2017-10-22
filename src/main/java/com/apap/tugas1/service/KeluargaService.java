package com.apap.tugas1.service;

import com.apap.tugas1.model.KeluargaModel;

public interface KeluargaService {

    KeluargaModel selectKeluarga(String nomor_kk);

    KeluargaModel selectProfilKeluarga(String nomor_kk);

    int selectMaxId();

    int countAllSimiliarNkk(String nkk);

    void addKeluarga(KeluargaModel keluarga);

    void updateKeluarga(KeluargaModel keluarga);

    void deactivateKeluarga(int id);
}
