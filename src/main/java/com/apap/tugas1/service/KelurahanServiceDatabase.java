package com.apap.tugas1.service;

import com.apap.tugas1.dao.KelurahanMapper;
import com.apap.tugas1.model.KelurahanModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class KelurahanServiceDatabase implements KelurahanService{
    @Autowired
    private KelurahanMapper kelurahanMapper;

    @Override
    public KelurahanModel selectIdAndCodeKelurahan(String namaKelurahan, String idKecamatan) {
        return kelurahanMapper.selectIdAndCodeKelurahan(namaKelurahan, idKecamatan);
    }

    @Override
    public String selectKelurahanById(String id){
        return kelurahanMapper.selectKelurahanById(id);
    }

    @Override
    public List<KelurahanModel> selectAllKelurahan(String idKecamatan) {
        return kelurahanMapper.selectAllKelurahan(idKecamatan);
    }
}
