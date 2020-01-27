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
    private User user;
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

        user = new User();

        if(getIntent().getExtras()!=null){
            Bundle bundle = getIntent().getExtras();
            user.setUsername(bundle.getString("username"));
            user.setName(bundle.getString("fullname"));
            user.setEmail(bundle.getString("email"));
            user.setNim(bundle.getString("nim"));
            user.setPassword(bundle.getString("password"));
            user.setRole(bundle.getString("role"));

            textName.setText(user.getName());
            textEmail.setText(user.getEmail());
        }
    }

    public void iconAddJadwal_click(View view) {
        progressBar.setVisibility(View.VISIBLE);
        Intent intent = new Intent(Home.this, AddJadwal.class);
        progressBar.setVisibility(View.GONE);
        startActivity(intent);
    }

    public void iconAddUser_click(View view) {
        progressBar.setVisibility(View.VISIBLE);
        Intent intent = new Intent(Home.this, Register.class);
        progressBar.setVisibility(View.GONE);
        startActivity(intent);
    }

    public void iconCheckJadwal_click(View view) {
        progressBar.setVisibility(View.VISIBLE);
        Intent intent = new Intent(Home.this, CheckJadwalAdmin.class);
        progressBar.setVisibility(View.GONE);
        startActivity(intent);
    }

    public void iconLogout_click(View view) {
        progressBar.setVisibility(View.VISIBLE);
        Toast.makeText(Home.this, getString(R.string.bye, user.getName()), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Home.this, Login.class);
        progressBar.setVisibility(View.GONE);
        startActivity(intent);
    }

    public void profileIcon_click(View view) {
        progressBar.setVisibility(View.VISIBLE);
        Bundle bundle = new Bundle();
        bundle.putString("username", user.getUsername());
        bundle.putString("fullname", user.getName());
        bundle.putString("email", user.getEmail());
        bundle.putString("nim", user.getNim());
        bundle.putString("password", user.getPassword());
        bundle.putString("role", user.getPassword());
        Toast.makeText(Home.this, "Login Success", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Home.this, Profile.class);
        intent.putExtras(bundle);
        progressBar.setVisibility(View.GONE);
        startActivity(intent);
    }
}
