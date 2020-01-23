package com.example.firebase_uas;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder{

    public TextView tvTanggal, tvNamaMK, tvKodeRuang;
    public ImageView iconStatus;

    public ViewHolder(View itemView){
        super(itemView);
        tvTanggal = itemView.findViewById(R.id.tanggal);
        tvNamaMK = itemView.findViewById(R.id.nama_mk);
        tvKodeRuang = itemView.findViewById(R.id.kode_ruang);
        iconStatus = itemView.findViewById(R.id.icon_status);
    }

    public void bindToData(JadwalData jadwalData, View.OnClickListener onClickListener){
        tvTanggal.setText(jadwalData.getTanggal());
        tvNamaMK.setText(jadwalData.getNamamk());
        tvKodeRuang.setText(jadwalData.getKoderuang());
        if(jadwalData.getStatus().equals("SUDAH")){
            iconStatus.setImageResource(R.drawable.check);
        }
        if(jadwalData.getStatus().equals("BELUM")){
            iconStatus.setImageResource(R.drawable.cross);
        }
    }
}