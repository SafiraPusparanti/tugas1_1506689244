package com.apap.tugas1.service;

import com.apap.tugas1.model.KotaModel;

import java.util.List;

public interface KotaService {

    List<KotaModel> selectAllKota();

    String selectKotaById(String id);
}
