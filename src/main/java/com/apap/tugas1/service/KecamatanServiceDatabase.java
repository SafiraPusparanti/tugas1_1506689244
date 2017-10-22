package com.apap.tugas1.service;

import com.apap.tugas1.dao.KecamatanMapper;
import com.apap.tugas1.model.KecamatanModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class KecamatanServiceDatabase implements KecamatanService {
    @Autowired
    private KecamatanMapper kecamatanMapper;

    @Override
    public String selectIdKecamatan(String namaKecamatan, String idKota) {
        return kecamatanMapper.selectIdKecamatan(namaKecamatan, idKota);
    }

    @Override
    public String selectKecamatanById(String id) {
        return kecamatanMapper.selectKecamatanById(id);
    }

    @Override
    public List<KecamatanModel> selectAllKecamatan(String idKota) {
        return kecamatanMapper.selectAllKecamatan(idKota);
    }
}
