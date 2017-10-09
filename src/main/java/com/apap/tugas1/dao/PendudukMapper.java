package com.apap.tugas1.dao;

import com.apap.tugas1.model.PendudukModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface PendudukMapper {
    @Select("SELECT nik, nama, tempat_lahir, jenis_kelamin, is_wni, id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, is_wafat FROM penduduk WHERE nik=#{nik}")
    PendudukModel selectPenduduk (@Param("nik") String nik);

    @Insert("INSERT INTO penduduk (id, nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, is_wni, id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, is_wafat) " +
            "VALUES (0, #{p.nik}, #{p.nama}, #{p.tempatLahir}, #{p.tanggalLahir}, #{p.jenisKelamin}, #{p.isWni}, #{p.idKeluarga}, #{p.agama}, #{p.pekerjaan}, #{p.statusPerkawinan}, #{p.statusDalamKeluarga}, #{p.golonganDarah}, #{p.isWafat})")
    void addPenduduk (@Param("p") PendudukModel penduduk);
}
