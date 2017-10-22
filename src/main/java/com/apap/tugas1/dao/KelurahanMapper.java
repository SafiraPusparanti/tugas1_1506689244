package com.apap.tugas1.dao;

import com.apap.tugas1.model.KelurahanModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface KelurahanMapper {
    @Select("SELECT kode_kelurahan, kelurahan.id FROM kelurahan, kecamatan WHERE id_kecamatan=#{idKecamatan} AND nama_kelurahan=#{namaKelurahan} LIMIT 1")
    KelurahanModel selectIdAndCodeKelurahan (@Param("namaKelurahan") String namaKelurahan, @Param("idKecamatan") String idKecamatan);

    @Select("SELECT nama_kelurahan FROM kelurahan WHERE id=#{idKelurahan} LIMIT 1")
    String selectKelurahanById (@Param("idKelurahan") String idKelurahan);

    @Select("SELECT nama_kelurahan, id FROM kelurahan WHERE id_kecamatan=#{idKecamatan}")
    List<KelurahanModel> selectAllKelurahan (@Param("idKecamatan") String idKecamatan);
}