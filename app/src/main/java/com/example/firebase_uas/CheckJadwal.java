package com.example.firebase_uas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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
    private TextView textTitle;
    private DatabaseReference databaseReference;
    List<JadwalData> listJadwal = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_jadwal);

        textTitle = findViewById(R.id.text_Title);

        Typeface Mregular = Typeface.createFromAsset(getAssets(), "fonts/mplus-1mn-regular.ttf");

        textTitle.setTypeface(Mregular);

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
    public void onLongClick(int position) {
        String tanggal = listJadwal.get(position).getTanggal();
        String nama_mk = listJadwal.get(position).getNamamk();
        String kode_mk = listJadwal.get(position).getKodemk();
        String kode_ruang = listJadwal.get(position).getKoderuang();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date datetime = null;
        int year = 0;
        int month = 0;
        int day = 0;
        int hour = 0;
        int minutes = 0;
        String date = null;
        try {
            datetime = dateFormat.parse(tanggal);
            SimpleDateFormat dateFormatDay = new SimpleDateFormat("yyyy/MM/dd");
            date = dateFormatDay.format(datetime);
            year = Integer.parseInt(date.split("/")[0]);
            month = Integer.parseInt(date.split("/")[1]);
            day = Integer.parseInt(date.split("/")[2]);
            SimpleDateFormat dateFormatHour = new SimpleDateFormat("HH");
            hour = Integer.parseInt(dateFormatHour.format(datetime));
            SimpleDateFormat dateFormatMinutes = new SimpleDateFormat("mm");
            minutes = Integer.parseInt(dateFormatMinutes.format(datetime));
//            SimpleDateFormat dateFormatAlarm = new SimpleDateFormat("EEEE HH:mm");
//            alarm = dateFormatAlarm.format(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
//        intent.putExtra(AlarmClock.EXTRA_DAYS, day);
//        intent.putExtra(AlarmClock.EXTRA_HOUR, hour);
//        intent.putExtra(AlarmClock.EXTRA_MINUTES, minutes);
//        intent.putExtra(AlarmClock.EXTRA_MESSAGE, nama_mk);
//        Toast.makeText(this, "Alarm set on " + alarm, Toast.LENGTH_SHORT).show();
//        startActivity(intent);

        Calendar beginTime = Calendar.getInstance();
        beginTime.set(year, month-1, day, hour, minutes);
        Calendar endTime = Calendar.getInstance();
        endTime.set(year, month-1, day, hour, minutes+15);
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
                .putExtra(CalendarContract.Events.TITLE, nama_mk)
                .putExtra(CalendarContract.Events.DESCRIPTION, tanggal)
                .putExtra(CalendarContract.Events.EVENT_LOCATION, kode_ruang)
                .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);
//                .putExtra(Intent.EXTRA_EMAIL, "rowan@example.com,trevor@example.com");
        startActivity(intent);
    }

    @Override
    public void onClick(int position) {
        String tanggal = listJadwal.get(position).getTanggal();
        String nama_mk = listJadwal.get(position).getNamamk();
        String kode_mk = listJadwal.get(position).getKodemk();
        String kode_ruang = listJadwal.get(position).getKoderuang();
        String status = listJadwal.get(position).getStatus();

        Bundle bundle = new Bundle();
        bundle.putString("tanggal", tanggal);
        bundle.putString("nama_mk", nama_mk);
        bundle.putString("kode_mk", kode_mk);
        bundle.putString("kode_ruang", kode_ruang);
        bundle.putString("status", status);

        Intent intent = new Intent(CheckJadwal.this, EditJadwal.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
