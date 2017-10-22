package com.apap.tugas1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PendudukModel {
    private int id, jenis_kelamin, is_wni, id_keluarga, is_wafat;
    private String nik, nama, tempat_lahir, tanggal_lahir, agama, pekerjaan, status_perkawinan,
            status_dalam_keluarga, golongan_darah;
    private KeluargaModel keluarga;
}
