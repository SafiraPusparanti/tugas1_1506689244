package com.apap.tugas1.model;

public class KotaModel {
    private int id;
    private String kodeKota;
    private String namaKota;

    public KotaModel(int id, String kodeKota, String namaKota) {
        this.id = id;
        this.kodeKota = kodeKota;
        this.namaKota = namaKota;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKodeKota() {
        return kodeKota;
    }

    public void setKodeKota(String kodeKota) {
        this.kodeKota = kodeKota;
    }

    public String getNamaKota() {
        return namaKota;
    }

    public void setNamaKota(String namaKota) {
        this.namaKota = namaKota;
    }
}
