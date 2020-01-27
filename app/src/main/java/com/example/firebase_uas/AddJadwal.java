package com.example.firebase_uas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddJadwal extends AppCompatActivity {
    private TextView textTitle;
    private Button buttonAddjAdwal;
    private EditText etkodemk, etnamamk, etkoderuang, ettanggal;
    private String datetime = "";
    private ProgressBar progressBar;
    private JadwalData jadwalData;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_jadwal);

        etkodemk = findViewById(R.id.edit_text_kodemk);
        etnamamk = findViewById(R.id.edit_text_namamk);
        etkoderuang = findViewById(R.id.edit_text_koderuang);
        ettanggal = findViewById(R.id.edit_text_tanggal);
        progressBar = findViewById(R.id.progressbarAddJadwal);
        progressBar.setVisibility(View.GONE);
        textTitle = findViewById(R.id.text_Title);
        buttonAddjAdwal = findViewById(R.id.button_add_jadwal);

        Typeface Mlight = Typeface.createFromAsset(getAssets(), "fonts/mplus-1mn-light.ttf");
        Typeface Mregular = Typeface.createFromAsset(getAssets(), "fonts/mplus-1mn-regular.ttf");
        Typeface Mmedium = Typeface.createFromAsset(getAssets(), "fonts/mplus-1mn-medium.ttf");

        textTitle.setTypeface(Mregular);
        ettanggal.setTypeface(Mlight);
        etkoderuang.setTypeface(Mlight);
        etnamamk.setTypeface(Mlight);
        etkodemk.setTypeface(Mlight);
        buttonAddjAdwal.setTypeface(Mmedium);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("jadwal");
    }

    public void button_add_jadwal_Click(View view) {

        System.out.println(etkodemk.getText().toString());
        jadwalData.setKodemk(etkodemk.getText().toString());
        jadwalData.setKoderuang(etkoderuang.getText().toString());
        jadwalData.setNamamk(etnamamk.getText().toString());
        datetime = ettanggal.getText().toString();
        jadwalData.setTanggal(datetime);
        jadwalData.setStatus("BELUM");

        if (jadwalData.getKodemk().isEmpty()) {
            etkodemk.setError(getString(R.string.input_error_name));
            etkodemk.requestFocus();
            return;
        }

        if (jadwalData.getNamamk().isEmpty()) {
            etnamamk.setError(getString(R.string.input_error_name));
            etnamamk.requestFocus();
            return;
        }

        if (jadwalData.getKoderuang().isEmpty()) {
            etkoderuang.setError(getString(R.string.input_error_name));
            etkoderuang.requestFocus();
            return;
        }


        if (jadwalData.getTanggal().isEmpty()) {
            ettanggal.setError(getString(R.string.input_error_email));
            ettanggal.requestFocus();
            return;
        }


        progressBar.setVisibility(View.VISIBLE);

        databaseReference.child(jadwalData.getKodemk()).setValue(jadwalData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(AddJadwal.this, R.string.registration_success, Toast.LENGTH_SHORT).show();
                    etkodemk.setText("");
                    etkoderuang.setText("");
                    etnamamk.setText("");
                    ettanggal.setText("");
                    progressBar.setVisibility(View.GONE);
                }else{
                    Toast.makeText(AddJadwal.this, R.string.registration_filed, Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}
