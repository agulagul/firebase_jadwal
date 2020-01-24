package com.example.firebase_uas;

import java.util.HashMap;
import java.util.Map;

public class JadwalData {
    public String tanggal, kode_mk, nama_mk, kode_ruang, status;

    public  JadwalData(){

    }

    public JadwalData(String tanggal, String kodemk, String namamk, String koderuang, String status) {
        this.tanggal = tanggal;
        this.kode_mk = kodemk;
        this.nama_mk = namamk;
        this.kode_ruang = koderuang;
        this.status = status;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getKodemk() {
        return kode_mk;
    }

    public void setKodemk(String kodemk) {
        this.kode_mk = kodemk;
    }

    public String getNamamk() {
        return nama_mk;
    }

    public void setNamamk(String namamk) {
        this.nama_mk = namamk;
    }

    public String getKoderuang() {
        return kode_ruang;
    }

    public void setKoderuang(String koderuang) {
        this.kode_ruang = koderuang;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("tanggal", tanggal);
        result.put("nama_mk", nama_mk);
        result.put("kode_mk", kode_mk);
        result.put("kode_ruang", kode_ruang);
        result.put("status", status);
        return result;
    }
}
