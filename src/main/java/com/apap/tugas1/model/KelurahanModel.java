package com.apap.tugas1.model;

public class KelurahanModel {
    private int id;
    private String kodeKelurahan;
    private String idKecamatan;
    private String namaKecamatan;
    private String kodePos;

    public KelurahanModel(int id, String kodeKelurahan, String idKecamatan, String namaKecamatan, String kodePos) {
        this.id = id;
        this.kodeKelurahan = kodeKelurahan;
        this.idKecamatan = idKecamatan;
        this.namaKecamatan = namaKecamatan;
        this.kodePos = kodePos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKodeKelurahan() {
        return kodeKelurahan;
    }

    public void setKodeKelurahan(String kodeKelurahan) {
        this.kodeKelurahan = kodeKelurahan;
    }

    public String getIdKecamatan() {
        return idKecamatan;
    }

    public void setIdKecamatan(String idKecamatan) {
        this.idKecamatan = idKecamatan;
    }

    public String getNamaKecamatan() {
        return namaKecamatan;
    }

    public void setNamaKecamatan(String namaKecamatan) {
        this.namaKecamatan = namaKecamatan;
    }

    public String getKodePos() {
        return kodePos;
    }

    public void setKodePos(String kodePos) {
        this.kodePos = kodePos;
    }
}
