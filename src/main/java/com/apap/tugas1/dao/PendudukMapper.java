package com.apap.tugas1.dao;

import com.apap.tugas1.model.PendudukModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface PendudukMapper {
    @Select("SELECT id, nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, is_wni, id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, is_wafat " +
            "FROM penduduk " +
            "WHERE nik=#{nik} LIMIT 1")
    PendudukModel selectPenduduk (@Param("nik") String nik);

    @Select("SELECT nik, nama, jenis_kelamin, tempat_lahir, tanggal_lahir, id_keluarga, alamat, rt, rw, nama_kelurahan, nama_kecamatan, nama_kota, golongan_darah, agama, status_perkawinan, pekerjaan, is_wni, is_wafat " +
            "FROM penduduk, keluarga, kelurahan, kecamatan, kota " +
            "WHERE nik=#{nik} AND id_keluarga=keluarga.id AND id_kelurahan=kelurahan.id AND id_kecamatan=kecamatan.id AND id_kota=kota.id LIMIT 1")
    @Results(value = {
            @Result(property="keluarga.alamat", column="alamat"),
            @Result(property="keluarga.rt", column="rt"),
            @Result(property="keluarga.rw", column="rw"),
            @Result(property="keluarga.kelurahan", column="nama_kelurahan"),
            @Result(property="keluarga.kecamatan", column="nama_kecamatan"),
            @Result(property="keluarga.kota", column="nama_kota")
    })
    PendudukModel selectProfilPenduduk (@Param("nik") String nik);

    @Select("SELECT nik, nama, jenis_kelamin, tempat_lahir, tanggal_lahir, golongan_darah, agama, status_perkawinan, pekerjaan, is_wni, is_wafat " +
            "FROM penduduk, keluarga WHERE penduduk.id_keluarga=keluarga.id AND keluarga.id_kelurahan=#{idKelurahan}")
    List<PendudukModel> selectAllPenduduk (@Param("idKelurahan") String idKelurahan);

    @Select("SELECT nik, nama, tanggal_lahir " +
            "FROM penduduk, keluarga " +
            "WHERE penduduk.id_keluarga=keluarga.id AND keluarga.id_kelurahan=#{idKelurahan} " +
            "ORDER BY tanggal_lahir DESC LIMIT 1")
    PendudukModel selectYoungestPenduduk (@Param("idKelurahan") String idKelurahan);

    @Select("SELECT nik, nama, tanggal_lahir " +
            "FROM penduduk, keluarga " +
            "WHERE penduduk.id_keluarga=keluarga.id AND keluarga.id_kelurahan=#{idKelurahan} " +
            "ORDER BY tanggal_lahir ASC LIMIT 1")
    PendudukModel selectOldestPenduduk (@Param("idKelurahan") String idKelurahan);

    @Select("SELECT kode_kelurahan " +
            "FROM keluarga, kelurahan " +
            "WHERE keluarga.id=#{idKeluarga} AND kelurahan.id=keluarga.id_kelurahan LIMIT 1")
    String fetchNikFromKelurahan (@Param("idKeluarga") int idKeluarga);

    @Select("SELECT MAX(id) FROM penduduk")
    int selectMaxId();

    @Select("SELECT COUNT(nik) FROM penduduk WHERE nik LIKE #{nik}")
    int countAllSimiliarNik (@Param("nik") String nik);

    @Insert("INSERT INTO penduduk (id, nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, is_wni, id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, is_wafat) " +
            "VALUES (#{p.id}, #{p.nik}, #{p.nama}, #{p.tempat_lahir}, #{p.tanggal_lahir}, #{p.jenis_kelamin}, #{p.is_wni}, #{p.id_keluarga}, #{p.agama}, #{p.pekerjaan}, #{p.status_perkawinan}, #{p.status_dalam_keluarga}, #{p.golongan_darah}, #{p.is_wafat})")
    void addPenduduk (@Param("p") PendudukModel penduduk);

    @Update("UPDATE penduduk SET nik=#{p.nik}, nama=#{p.nama}, tempat_lahir=#{p.tempat_lahir}, tanggal_lahir=#{p.tanggal_lahir}, jenis_kelamin=#{p.jenis_kelamin}, " +
            "is_wni=#{p.is_wni}, id_keluarga=#{p.id_keluarga}, agama=#{p.agama}, pekerjaan=#{p.pekerjaan}, status_perkawinan=#{p.status_perkawinan}, status_dalam_keluarga=#{p.status_dalam_keluarga}, golongan_darah=#{p.golongan_darah}, is_wafat=#{p.is_wafat} " +
            "WHERE id=#{p.id}")
    void updatePenduduk (@Param("p") PendudukModel penduduk);

    @Update("UPDATE penduduk SET is_wafat=1 WHERE nik=#{p.nik}")
    void deactivatePenduduk (@Param("p") PendudukModel penduduk);

    @Select("SELECT COUNT(nik) FROM penduduk " +
            "WHERE id_keluarga=#{idKeluarga} AND is_wafat=0")
    int countAliveFamilyMembers (@Param("idKeluarga") int idKeluarga);
}
