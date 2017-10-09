package com.apap.tugas1.service;

import com.apap.tugas1.dao.KeluargaMapper;
import com.apap.tugas1.model.KeluargaModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KeluargaService {
    @Autowired
    private KeluargaMapper keluargaMapper;

    public KeluargaModel selectKeluarga(String nomor_kk){
//        log.info("Select penduduk with NIK ()", nik);
        return keluargaMapper.selectKeluarga(nomor_kk);
    }
}
