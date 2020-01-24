package com.example.firebase_uas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {

    private TextView textWelcome, textWelcomeRole;
    private String username, fullname, role;
    private Button btnCheck, btnLogout;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        textWelcome = findViewById(R.id.text_welcome);
        textWelcomeRole = findViewById(R.id.text_welcome_role);
        btnCheck = findViewById(R.id.button_check_jadwal);
        btnLogout = findViewById(R.id.button_logout);
        progressBar = findViewById(R.id.progressbar_dashboard);
        progressBar.setVisibility(View.GONE);

        if(getIntent().getExtras()!=null){
            Bundle bundle = getIntent().getExtras();
            username = bundle.getString("username");
            fullname = bundle.getString("fullname");
            role = bundle.getString("role");

            textWelcome.setText(getString(R.string.welcome, fullname));
            textWelcomeRole.setText(getString(R.string.welcome_role, role));
        }
    }

    public void button_check_jadwal_Click(View view) {
        Intent intent = new Intent(Dashboard.this, CheckJadwal.class);
        startActivity(intent);
    }

    public void button_logout_Click(View view) {
        Toast.makeText(Dashboard.this, getString(R.string.bye, fullname), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Dashboard.this, Login.class);
        startActivity(intent);
    }
}
