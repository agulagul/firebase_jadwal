package com.example.firebase_uas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class HomeUser extends AppCompatActivity {

    TextView textName, textEmail, textCheckJadwal, textLogout, textMainMenu;
    private String username, fullname, email, nim, password, role;
    private User user;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user);

        textName = findViewById(R.id.textName);
        textEmail = findViewById(R.id.textEmail);
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

    public void iconLogout_click(View view) {
        progressBar.setVisibility(View.VISIBLE);
        Toast.makeText(HomeUser.this, getString(R.string.bye, fullname), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(HomeUser.this, Login.class);
        progressBar.setVisibility(View.GONE);
        startActivity(intent);
    }

    public void iconCheckJadwal_click(View view) {
        progressBar.setVisibility(View.VISIBLE);
        Intent intent = new Intent(HomeUser.this, CheckJadwal.class);
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
        bundle.putString("role", user.getRole());
        Intent intent = new Intent(HomeUser.this, Profile.class);
        intent.putExtras(bundle);
        progressBar.setVisibility(View.GONE);
        startActivity(intent);
    }
}
