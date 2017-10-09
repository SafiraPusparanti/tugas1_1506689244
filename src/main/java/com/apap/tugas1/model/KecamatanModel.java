package com.apap.tugas1.model;

public class KecamatanModel {
    private int id;
    private String kodeKecamatan;
    private String idKota;
    private String namaKecamatan;

    public KecamatanModel(int id, String kodeKecamatan, String idKota, String namaKecamatan) {
        this.id = id;
        this.kodeKecamatan = kodeKecamatan;
        this.idKota = idKota;
        this.namaKecamatan = namaKecamatan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKodeKecamatan() {
        return kodeKecamatan;
    }

    public void setKodeKecamatan(String kodeKecamatan) {
        this.kodeKecamatan = kodeKecamatan;
    }

    public String getIdKota() {
        return idKota;
    }

    public void setIdKota(String idKota) {
        this.idKota = idKota;
    }

    public String getNamaKecamatan() {
        return namaKecamatan;
    }

    public void setNamaKecamatan(String namaKecamatan) {
        this.namaKecamatan = namaKecamatan;
    }
}
