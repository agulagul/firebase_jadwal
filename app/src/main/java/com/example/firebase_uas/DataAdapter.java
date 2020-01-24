package com.example.firebase_uas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    Context context;
    List<JadwalData> listJadwal;
    private RecyclerViewClickListener mListener;


    public DataAdapter(Context context, List<JadwalData> listJadwal, RecyclerViewClickListener listener) {
        this.context = context;
        this.listJadwal = listJadwal;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final JadwalData jadwal = listJadwal.get(position);
        holder.tvNamaMK.setText(jadwal.getNamamk());
        holder.tvKodeRuang.setText(jadwal.getKoderuang());
        holder.tvTanggal.setText(jadwal.getTanggal());
        if(jadwal.getStatus().equals("SUDAH")){
            holder.iconStatus.setImageResource(R.drawable.check);
        }
        if(jadwal.getStatus().equals("BELUM")){
            holder.iconStatus.setImageResource(R.drawable.cross);
        }

    }

    @Override
    public int getItemCount() {
        return listJadwal.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNamaMK, tvKodeRuang, tvTanggal;
        public ImageView iconStatus;

        public ViewHolder(View itemView){
            super(itemView);
            tvNamaMK = itemView.findViewById(R.id.nama_mk);
            tvTanggal = itemView.findViewById(R.id.tanggal);
            tvKodeRuang = itemView.findViewById(R.id.kode_ruang);
            iconStatus = itemView.findViewById(R.id.icon_status);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onClick(getAdapterPosition());
                }
            });
        }
    }
}
