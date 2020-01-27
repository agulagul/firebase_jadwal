package com.example.firebase_uas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Type;

public class Home extends AppCompatActivity {

    TextView textName, textEmail, textAddJadwal, textAddUser, textCheckJadwal, textLogout, textMainMenu;
    private String username, fullname, email, nim, password, role;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textName = findViewById(R.id.textName);
        textEmail = findViewById(R.id.textEmail);
        textAddJadwal = findViewById(R.id.textAddJadwal);
        textAddUser = findViewById(R.id.textAddUser);
        textCheckJadwal = findViewById(R.id.textCheckJadwal);
        textMainMenu = findViewById(R.id.textMainMenu);
        textLogout = findViewById(R.id.textLogout);
        progressBar = findViewById(R.id.progressbar_home);
        progressBar.setVisibility(View.GONE);

        Typeface Mlight = Typeface.createFromAsset(getAssets(), "fonts/mplus-1mn-light.ttf");
        Typeface Mregular = Typeface.createFromAsset(getAssets(), "fonts/mplus-1mn-regular.ttf");
        Typeface Mmedium = Typeface.createFromAsset(getAssets(), "fonts/mplus-1mn-medium.ttf");

        textName.setTypeface(Mmedium);
        textEmail.setTypeface(Mlight);
        textAddUser.setTypeface(Mlight);
        textAddJadwal.setTypeface(Mlight);
        textLogout.setTypeface(Mlight);
        textCheckJadwal.setTypeface(Mlight);
        textMainMenu.setTypeface(Mmedium);

        if(getIntent().getExtras()!=null){
            Bundle bundle = getIntent().getExtras();
            username = bundle.getString("username");
            fullname = bundle.getString("fullname");
            email = bundle.getString("email");
            nim = bundle.getString("nim");
            password = bundle.getString("password");
            role = bundle.getString("role");

            textName.setText(fullname);
            textEmail.setText(email);
        }
    }

    public void iconAddJadwal_click(View view) {
        Intent intent = new Intent(Home.this, AddJadwal.class);
        startActivity(intent);
    }

    public void iconAddUser_click(View view) {
        Intent intent = new Intent(Home.this, Register.class);
        startActivity(intent);
    }

    public void iconCheckJadwal_click(View view) {
        Intent intent = new Intent(Home.this, CheckJadwal.class);
        startActivity(intent);
    }

    public void iconLogout_click(View view) {
        Toast.makeText(Home.this, getString(R.string.bye, fullname), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Home.this, Login.class);
        startActivity(intent);
    }
}
