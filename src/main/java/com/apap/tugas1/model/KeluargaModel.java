package com.apap.tugas1.model;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class KeluargaModel {
    private String nomor_kk;
    private String alamat;
    private String rt;
    private String rw;
    private String idKelurahan;
    private int isTidakBerlaku;

}
