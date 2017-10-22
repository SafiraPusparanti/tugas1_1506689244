package com.apap.tugas1.model;

import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class KeluargaModel {
    private int id;
    private String nomor_kk;
    private String alamat;
    private String rt;
    private String rw;
    private String id_kelurahan;
    private String kelurahan;
    private String kecamatan;
    private String kota;
    private String id_kota;
    private int is_tidak_berlaku;
    private List<PendudukModel> penduduks;
}
