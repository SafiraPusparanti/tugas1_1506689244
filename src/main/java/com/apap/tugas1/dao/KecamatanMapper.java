package com.apap.tugas1.dao;

import com.apap.tugas1.model.KecamatanModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface KecamatanMapper {
    @Select("SELECT kode_kecamatan FROM kecamatan WHERE nama_kecamatan=#{namaKecamatan}")
    String selectKecamatan (@Param("namaKecamatan") String namaKecamatan);
}
