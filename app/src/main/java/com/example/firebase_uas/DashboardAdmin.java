package com.example.firebase_uas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class DashboardAdmin extends AppCompatActivity {

    private TextView textWelcomeAdmin, textWelcomeRoleAdmin;
    private String username, fullname, role;
    private Button btnAddUser, btnAddJadwal, btnCheck, btnLogout;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_admin);

        textWelcomeAdmin = findViewById(R.id.text_welcome_admin);
        textWelcomeRoleAdmin = findViewById(R.id.text_welcome_role_admin);
        btnAddUser = findViewById(R.id.button_add_user);
        btnAddJadwal = findViewById(R.id.button_add_jadwal);
        btnCheck = findViewById(R.id.button_check_jadwal_admin);
        btnLogout = findViewById(R.id.button_logout_admin);
        progressBar = findViewById(R.id.progressbar_dashboard);
        progressBar.setVisibility(View.GONE);

        if(getIntent().getExtras()!=null){
            Bundle bundle = getIntent().getExtras();
            username = bundle.getString("username");
            fullname = bundle.getString("fullname");
            role = bundle.getString("role");

            textWelcomeAdmin.setText(getString(R.string.welcome, fullname));
            textWelcomeRoleAdmin.setText(getString(R.string.welcome_role, role));
        }
    }

    public void button_check_jadwal_Click(View view) {
        Intent intent = new Intent(DashboardAdmin.this, CheckJadwal.class);
        startActivity(intent);
    }

    public void button_logout_Click(View view) {
        Toast.makeText(DashboardAdmin.this, getString(R.string.bye, fullname), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(DashboardAdmin.this, Login.class);
        startActivity(intent);
    }

    public void button_add_user_Click(View view) {
        Intent intent = new Intent(DashboardAdmin.this, Register.class);
        startActivity(intent);
    }

    public void button_add_jadwal_Click(View view) {
        Intent intent = new Intent(DashboardAdmin.this, AddJadwal.class);
        startActivity(intent);
    }
}
