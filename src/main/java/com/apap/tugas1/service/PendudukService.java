package com.apap.tugas1.service;

import com.apap.tugas1.dao.PendudukMapper;
import com.apap.tugas1.model.PendudukModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Slf4j
@Service
public class PendudukService {
    @Autowired
    private PendudukMapper pendudukMapper;

    public PendudukModel selectPenduduk(String nik){
//        log.info("Select penduduk with NIK ()", nik);
        return pendudukMapper.selectPenduduk(nik);
    }

    public void addPenduduk(PendudukModel penduduk){
        System.out.println("masuk serfis brah");
        pendudukMapper.addPenduduk(penduduk);
    }

}
