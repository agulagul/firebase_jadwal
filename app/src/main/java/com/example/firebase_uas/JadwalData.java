package com.example.firebase_uas;

import com.google.firebase.database.PropertyName;

import java.util.HashMap;
import java.util.Map;

public class JadwalData {
    public String tanggal, kode_mk, nama_mk, kode_ruang, status;

    public  JadwalData(){

    }

    public JadwalData(String tanggal, String kode_mk, String nama_mk, String kode_ruang, String status) {
        this.tanggal = tanggal;
        this.kode_mk = kode_mk;
        this.nama_mk = nama_mk;
        this.kode_ruang = kode_ruang;
        this.status = status;
    }

    @PropertyName("tanggal")
    public String getTanggal() {
        return tanggal;
    }

    @PropertyName("tanggal")
    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    @PropertyName("kode_mk")
    public String getKodemk() {
        return kode_mk;
    }

    @PropertyName("kode_mk")
    public void setKodemk(String kode_mk) {
        this.kode_mk = kode_mk;
    }

    @PropertyName("nama_mk")
    public String getNamamk() {
        return nama_mk;
    }

    @PropertyName("nama_mk")
    public void setNamamk(String nama_mk) {
        this.nama_mk = nama_mk;
    }

    @PropertyName("kode_ruang")
    public String getKoderuang() {
        return kode_ruang;
    }

    @PropertyName("kode_ruang")
    public void setKoderuang(String kode_ruang) {
        this.kode_ruang = kode_ruang;
    }

    @PropertyName("status")
    public String getStatus() {
        return status;
    }

    @PropertyName("status")
    public void setStatus(String status) {
        this.status = status;
    }

//    public Map<String, Object> toMap(){
//        HashMap<String, Object> result = new HashMap<>();
//        result.put("tanggal", tanggal);
//        result.put("nama_mk", nama_mk);
//        result.put("kode_mk", kode_mk);
//        result.put("kode_ruang", kode_ruang);
//        result.put("status", status);
//        return result;
//    }
}
