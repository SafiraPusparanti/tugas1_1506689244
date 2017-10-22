package com.apap.tugas1.dao;

import com.apap.tugas1.model.KeluargaModel;
import com.apap.tugas1.model.PendudukModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface KeluargaMapper {
    @Select("SELECT keluarga.id, nomor_kk, alamat, rt, rw, id_kelurahan, nama_kelurahan, nama_kecamatan, nama_kota, is_tidak_berlaku " +
            "FROM keluarga, kelurahan, kecamatan, kota " +
            "WHERE nomor_kk=#{nomor_kk} AND keluarga.id_kelurahan=kelurahan.id AND kelurahan.id_kecamatan=kecamatan.id AND kecamatan.id_kota=kota.id  LIMIT 1")
    @Results(value = {
            @Result(property = "nomor_kk", column = "nomor_kk"),
            @Result(property = "kelurahan", column = "nama_kelurahan"),
            @Result(property = "kecamatan", column = "nama_kecamatan"),
            @Result(property = "kota", column = "nama_kota")
    })
    KeluargaModel selectKeluarga (@Param("nomor_kk") String nomorKk);

    @Select("SELECT keluarga.id, nomor_kk, alamat, rt, rw, nama_kelurahan, nama_kecamatan, nama_kota " +
            "FROM keluarga, kelurahan, kecamatan, kota " +
            "WHERE nomor_kk=#{nomor_kk} AND id_kelurahan=kelurahan.id AND id_kecamatan=kecamatan.id AND id_kota=kota.id  LIMIT 1")
    @Results(value = {
            @Result(property="nomor_kk", column="nomor_kk"),
            @Result(property="kelurahan", column="nama_kelurahan"),
            @Result(property="kecamatan", column="nama_kecamatan"),
            @Result(property="kota", column="nama_kota"),
            @Result(property="penduduks", column="nomor_kk", javaType=List.class, many=@Many(select="selectPenduduks"))
    })
    KeluargaModel selectProfilKeluarga (@Param("nomor_kk") String nomorKk);

    @Select("SELECT nama, nik, jenis_kelamin, tempat_lahir, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, is_wni " +
            "FROM penduduk JOIN keluarga ON id_keluarga=keluarga.id WHERE nomor_kk=#{nomor_kk}")
    List<PendudukModel> selectPenduduks (@Param("nomor_kk") String nomor_kk);

    @Select("SELECT MAX(id) FROM keluarga")
    int selectMaxId();

    @Select("SELECT COUNT(nomor_kk) FROM keluarga WHERE nomor_kk LIKE #{nkk}")
    int countAllSimiliarNkk (@Param("nkk") String nkk);

    @Insert("INSERT INTO keluarga (id, nomor_kk, alamat, rt, rw, id_kelurahan, is_tidak_berlaku ) " +
            "VALUES (#{k.id}, #{k.nomor_kk}, #{k.alamat}, #{k.rt}, #{k.rw}, #{k.id_kelurahan}, #{k.is_tidak_berlaku})")
    void addKeluarga (@Param("k") KeluargaModel keluarga);

    @Update("UPDATE keluarga SET nomor_kk=#{k.nomor_kk}, alamat=#{k.alamat}, rt=#{k.rt}, rw=#{k.rw}, " +
            "id_kelurahan=#{k.id_kelurahan}, is_tidak_berlaku=#{k.is_tidak_berlaku} " +
            "WHERE id=#{k.id}")
    void updateKeluarga (@Param("k") KeluargaModel keluarga);


    @Update("UPDATE keluarga SET is_tidak_berlaku=1 WHERE id=#{id}")
    void deactivateKeluarga (@Param("id") int id);
}
