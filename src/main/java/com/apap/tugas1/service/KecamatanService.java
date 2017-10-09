package com.apap.tugas1.service;

import com.apap.tugas1.dao.KecamatanMapper;
import com.apap.tugas1.model.KecamatanModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KecamatanService {
    @Autowired
    private KecamatanMapper kecamatanMapper;

    public String selectKecamatan(String nama) {
        return kecamatanMapper.selectKecamatan(nama);
    }

}
