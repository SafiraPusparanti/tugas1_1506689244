package com.apap.tugas1.dao;

import com.apap.tugas1.model.KeluargaModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface KeluargaMapper {
    @Select("SELECT nomor_kk, alamat, rt, rw, id_kelurahan, is_tidak_berlaku FROM keluarga WHERE nomor_kk=#{nomor_kk}")
    KeluargaModel selectKeluarga (@Param("nomor_kk") String nomorKk);

    @Insert("INSERT INTO penduduk (id, nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, is_wni, id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, is_wafat) " +
            "VALUES (0, #{p.nik}, #{p.nama}, #{p.tempatLahir}, #{p.tanggalLahir}, #{p.jenisKelamin}, #{p.isWni}, #{p.idKeluarga}, #{p.agama}, #{p.pekerjaan}, #{p.statusPerkawinan}, #{p.statusDalamKeluarga}, #{p.golonganDarah}, #{p.isWafat})")
    void addKeluarga (@Param("k") KeluargaModel keluarga);
}
