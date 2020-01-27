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

    public void iconLogout_click(View view) {
        Toast.makeText(HomeUser.this, getString(R.string.bye, fullname), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(HomeUser.this, Login.class);
        startActivity(intent);
    }

    public void iconCheckJadwal_click(View view) {
        Intent intent = new Intent(HomeUser.this, CheckJadwal.class);
        startActivity(intent);
    }
}
