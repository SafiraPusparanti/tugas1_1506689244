package com.apap.tugas1.service;

import com.apap.tugas1.dao.KotaMapper;
import com.apap.tugas1.model.KotaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KotaServiceDatabase implements KotaService {
    @Autowired
    KotaMapper kotaMapper;

    @Override
    public List<KotaModel> selectAllKota() {
        return kotaMapper.selectAllKota();
    }

    @Override
    public String selectKotaById(String id) {
        return kotaMapper.selectKotaById(id);
    }
}
