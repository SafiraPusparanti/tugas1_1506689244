package com.apap.tugas1.service;

import com.apap.tugas1.dao.KeluargaMapper;
import com.apap.tugas1.model.KeluargaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeluargaServiceDatabase implements KeluargaService {
    @Autowired
    private KeluargaMapper keluargaMapper;

    @Override
    public KeluargaModel selectKeluarga(String nomor_kk){
        return keluargaMapper.selectKeluarga(nomor_kk);
    }

    @Override
    public KeluargaModel selectProfilKeluarga(String nomor_kk){
        return keluargaMapper.selectProfilKeluarga(nomor_kk);
    }

    @Override
    public int selectMaxId(){
        return keluargaMapper.selectMaxId();
    }

    @Override
    public int countAllSimiliarNkk (String nkk) {
        return keluargaMapper.countAllSimiliarNkk(nkk);
    }

    @Override
    public void addKeluarga(KeluargaModel keluarga){
        keluargaMapper.addKeluarga(keluarga);
    }

    @Override
    public void updateKeluarga(KeluargaModel keluarga){
        keluargaMapper.updateKeluarga(keluarga);
    }

    @Override
    public void deactivateKeluarga (int id) {
        keluargaMapper.deactivateKeluarga(id);
    }
}
