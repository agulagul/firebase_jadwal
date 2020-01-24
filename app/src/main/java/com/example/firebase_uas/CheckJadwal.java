package com.example.firebase_uas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CheckJadwal extends AppCompatActivity implements RecyclerViewClickListener{

    private DatabaseReference databaseReference;
    List<JadwalData> listJadwal = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_jadwal);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        recyclerView = findViewById(R.id.recycler_jadwal);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(CheckJadwal.this));

        databaseReference = FirebaseDatabase.getInstance().getReference().child("jadwal");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    JadwalData jadwalData = snapshot.getValue(JadwalData.class);
                    listJadwal.add(jadwalData);
                }

                adapter = new DataAdapter(CheckJadwal.this, listJadwal, CheckJadwal.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(int position) {
        String tanggal = listJadwal.get(position).getTanggal();
        String nama_mk = listJadwal.get(position).getNamamk();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date date = null;
        int day = 0;
        int hour = 0;
        int minutes = 0;
        String alarm = null;
        try {
            date = dateFormat.parse(tanggal);
            SimpleDateFormat dateFormatDay = new SimpleDateFormat("u");
            day = Integer.parseInt(dateFormatDay.format(date));
            SimpleDateFormat dateFormatHour = new SimpleDateFormat("HH");
            hour = Integer.parseInt(dateFormatHour.format(date));
            SimpleDateFormat dateFormatMinutes = new SimpleDateFormat("mm");
            minutes = Integer.parseInt(dateFormatMinutes.format(date));
            SimpleDateFormat dateFormatAlarm = new SimpleDateFormat("EEEE HH:mm");
            alarm = dateFormatAlarm.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
        intent.putExtra(AlarmClock.EXTRA_DAYS, day);
        intent.putExtra(AlarmClock.EXTRA_HOUR, hour);
        intent.putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        intent.putExtra(AlarmClock.EXTRA_MESSAGE, nama_mk);
        Toast.makeText(this, "Alarm set on " + alarm, Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}
