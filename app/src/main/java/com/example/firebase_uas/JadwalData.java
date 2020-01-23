package com.example.firebase_uas;

import java.util.HashMap;
import java.util.Map;

public class JadwalData {
    public String tanggal, kodemk, namamk, koderuang, status;

    public  JadwalData(){

    }

    public JadwalData(String tanggal, String kodemk, String namamk, String koderuang, String status) {
        this.tanggal = tanggal;
        this.kodemk = kodemk;
        this.namamk = namamk;
        this.koderuang = koderuang;
        this.status = status;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getKodemk() {
        return kodemk;
    }

    public void setKodemk(String kodemk) {
        this.kodemk = kodemk;
    }

    public String getNamamk() {
        return namamk;
    }

    public void setNamamk(String namamk) {
        this.namamk = namamk;
    }

    public String getKoderuang() {
        return koderuang;
    }

    public void setKoderuang(String koderuang) {
        this.koderuang = koderuang;
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
        result.put("nama_mk", namamk);
        result.put("kode_mk", kodemk);
        result.put("kode_ruang", koderuang);
        result.put("status", status);
        return result;
    }
}
