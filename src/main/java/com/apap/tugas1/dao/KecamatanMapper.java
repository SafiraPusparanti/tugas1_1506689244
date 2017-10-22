package com.apap.tugas1.dao;

import com.apap.tugas1.model.KecamatanModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface KecamatanMapper {
    @Select("SELECT kecamatan.id FROM kecamatan, kota WHERE kecamatan.id_kota=#{idKota} AND nama_kecamatan=#{namaKecamatan} LIMIT 1")
    String selectIdKecamatan (@Param("namaKecamatan") String namaKecamatan, @Param("idKota") String idKota);

    @Select("SELECT nama_kecamatan FROM kecamatan WHERE id=#{idKecamatan} LIMIT 1")
    String selectKecamatanById (@Param("idKecamatan") String idKecamatan);

    @Select("SELECT nama_kecamatan, id FROM kecamatan WHERE id_kota=#{idKota}")
    List<KecamatanModel> selectAllKecamatan (@Param("idKota") String idKota);
}
