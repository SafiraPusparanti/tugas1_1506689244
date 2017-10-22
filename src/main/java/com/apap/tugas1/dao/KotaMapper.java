package com.apap.tugas1.dao;

import com.apap.tugas1.model.KotaModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface KotaMapper {
    @Select("SELECT nama_kota, id FROM kota")
    List<KotaModel> selectAllKota ();

    @Select("SELECT nama_kota FROM kota WHERE id=#{idKota} LIMIT 1")
    String selectKotaById (@Param("idKota") String idKota);
}
