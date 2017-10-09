package com.apap.tugas1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PendudukModel {
    private int jenisKelamin, isWni, idKeluarga, isWafat;
    private String nik, nama, tempatLahir, tanggalLahir, agama, pekerjaan, statusPerkawinan, statusDalamKeluarga, golonganDarah;

    public int getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(int jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public int getIsWni() {
        return isWni;
    }

    public void setIsWni(int isWni) {
        this.isWni = isWni;
    }

    public int getIdKeluarga() {
        return idKeluarga;
    }

    public void setIdKeluarga(int idKeluarga) {
        this.idKeluarga = idKeluarga;
    }

    public int getIsWafat() {
        return isWafat;
    }

    public void setIsWafat(int isWafat) {
        this.isWafat = isWafat;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getStatusPerkawinan() {
        return statusPerkawinan;
    }

    public void setStatusPerkawinan(String statusPerkawinan) {
        this.statusPerkawinan = statusPerkawinan;
    }

    public String getStatusDalamKeluarga() {
        return statusDalamKeluarga;
    }

    public void setStatusDalamKeluarga(String statusDalamKeluarga) {
        this.statusDalamKeluarga = statusDalamKeluarga;
    }

    public String getGolonganDarah() {
        return golonganDarah;
    }

    public void setGolonganDarah(String golonganDarah) {
        this.golonganDarah = golonganDarah;
    }
}
