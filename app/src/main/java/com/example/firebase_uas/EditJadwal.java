package com.example.firebase_uas;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditJadwal extends AppCompatActivity {
    private TextView textTitle;
    private Button buttonEdit, buttonHapus;
    private RadioButton rbSudah, rbBelum;
    private RadioGroup groupStatus;
    private EditText etkodemk, etnamamk, etkoderuang, ettanggal, etwaktu;
    private String tanggal, waktu;
    private ProgressBar progressBar;
    private JadwalData jadwalData;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_jadwal);

        etkodemk = findViewById(R.id.edit_text_kodemk);
        etnamamk = findViewById(R.id.edit_text_namamk);
        etkoderuang = findViewById(R.id.edit_text_koderuang);
        ettanggal = findViewById(R.id.edit_text_tanggal);
        etwaktu = findViewById(R.id.edit_text_waktu);
        progressBar = findViewById(R.id.progressbarAddJadwal);
        progressBar.setVisibility(View.GONE);
        textTitle = findViewById(R.id.text_Title);
        rbSudah = findViewById(R.id.rbSudah);
        rbBelum = findViewById(R.id.rbBelum);
        buttonEdit = findViewById(R.id.button_edit_jadwal);
        buttonHapus = findViewById(R.id.button_hapus_jadwal);
        groupStatus = findViewById(R.id.groupStatus);

        Typeface Mlight = Typeface.createFromAsset(getAssets(), "fonts/mplus-1mn-light.ttf");
        Typeface Mregular = Typeface.createFromAsset(getAssets(), "fonts/mplus-1mn-regular.ttf");
        Typeface Mmedium = Typeface.createFromAsset(getAssets(), "fonts/mplus-1mn-medium.ttf");

        textTitle.setTypeface(Mregular);
        etwaktu.setTypeface(Mlight);
        ettanggal.setTypeface(Mlight);
        etkoderuang.setTypeface(Mlight);
        etnamamk.setTypeface(Mlight);
        etkodemk.setTypeface(Mlight);
        buttonEdit.setTypeface(Mmedium);
        buttonHapus.setTypeface(Mmedium);

        jadwalData = new JadwalData();

        tanggal = "";
        waktu = "";

        if(getIntent().getExtras()!=null){
            Bundle bundle = getIntent().getExtras();
            jadwalData.setKodemk(bundle.getString("kode_mk"));
            jadwalData.setNamamk(bundle.getString("nama_mk"));
            jadwalData.setKoderuang(bundle.getString("kode_ruang"));
            jadwalData.setTanggal(bundle.getString("tanggal"));
            jadwalData.setStatus(bundle.getString("status"));
        }

        tanggal = jadwalData.getTanggal().split(" ")[0];
        waktu = jadwalData.getTanggal().split(" ")[1];
        etkodemk.setText(jadwalData.getKodemk());
        etnamamk.setText(jadwalData.getNamamk());
        etkoderuang.setText(jadwalData.getKoderuang());
        ettanggal.setText(tanggal);
        etwaktu.setText(waktu);

        if(jadwalData.getStatus().equals("SUDAH")){
            rbSudah.setChecked(true);
        }else{
            rbBelum.setChecked(true);
        }

        groupStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbSudah){
                    jadwalData.setStatus("SUDAH");
                    rbSudah.setTextColor(getResources().getColor(R.color.colorText));
                    rbBelum.setTextColor(getResources().getColor(R.color.colorHint));
                }else{
                    jadwalData.setStatus("BELUM");
                    rbSudah.setTextColor(getResources().getColor(R.color.colorHint));
                    rbBelum.setTextColor(getResources().getColor(R.color.colorText));
                }
            }
        });

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("jadwal");
    }

    public void button_edit_jadwal_Click(View view) {
                databaseReference.child(jadwalData.getKodemk()).setValue(jadwalData);
        Toast.makeText(EditJadwal.this, R.string.editJadwal_success, Toast.LENGTH_SHORT).show();
    }

    public void button_edit_hapus_Click(View view) {
        databaseReference.child(jadwalData.getKodemk()).removeValue();
        Toast.makeText(EditJadwal.this, R.string.editJadwal_success, Toast.LENGTH_SHORT).show();
    }
}
