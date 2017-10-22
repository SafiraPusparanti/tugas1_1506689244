package com.apap.tugas1.service;

import com.apap.tugas1.dao.PendudukMapper;
import com.apap.tugas1.model.PendudukModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;


@Service
public class PendudukServiceDatabase implements PendudukService {
    @Autowired
    private PendudukMapper pendudukMapper;

    @Override
    public PendudukModel selectPenduduk(String nik){
        return pendudukMapper.selectPenduduk(nik);
    }

    @Override
    public PendudukModel selectProfilPenduduk(String nik){
        return pendudukMapper.selectProfilPenduduk(nik);
    }

    @Override
    public List<PendudukModel> selectAllPenduduk(String idKelurahan) {
        return pendudukMapper.selectAllPenduduk(idKelurahan);
    }

    @Override
    public PendudukModel selectYoungestPenduduk(String idKelurahan){
        return pendudukMapper.selectYoungestPenduduk(idKelurahan);
    }

    @Override
    public PendudukModel selectOldestPenduduk(String idKelurahan){
        return pendudukMapper.selectOldestPenduduk(idKelurahan);
    }

    @Override
    public int selectMaxId(){
        return pendudukMapper.selectMaxId();
    }

    @Override
    public String fetchNikFromKelurahan (int idKeluarga) {
        return pendudukMapper.fetchNikFromKelurahan(idKeluarga);
    }

    @Override
    public int countAllSimiliarNik (String nik) {
        return pendudukMapper.countAllSimiliarNik(nik);
    }

    @Override
    public void addPenduduk(PendudukModel penduduk){
        pendudukMapper.addPenduduk(penduduk);
    }

    @Override
    public void updatePenduduk(PendudukModel penduduk){
        pendudukMapper.updatePenduduk(penduduk);
    }

    @Override
    public void deactivatePenduduk(PendudukModel penduduk){
        pendudukMapper.deactivatePenduduk(penduduk);
    }

    @Override
    public int countAliveFamilyMembers (int idKeluarga) {
        return pendudukMapper.countAliveFamilyMembers(idKeluarga);
    }
}
