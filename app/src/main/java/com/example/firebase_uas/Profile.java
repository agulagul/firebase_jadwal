package com.example.firebase_uas;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profile extends AppCompatActivity {

    private TextView textTitle;
    private ImageView profileIcon;
    private EditText fullname, username, nim, email, role;
    private ProgressBar progressBar;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fullname = findViewById(R.id.edit_text_name);
        username = findViewById(R.id.edit_text_username);
        nim = findViewById(R.id.edit_text_nim);
        email = findViewById(R.id.edit_text_email);
        role = findViewById(R.id.edit_text_role);
        textTitle = findViewById(R.id.text_Title);
        profileIcon = findViewById(R.id.profileIcon);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        user = new User();

        Typeface Mlight = Typeface.createFromAsset(getAssets(), "fonts/mplus-1mn-light.ttf");
        Typeface Mregular = Typeface.createFromAsset(getAssets(), "fonts/mplus-1mn-regular.ttf");
        Typeface Mmedium = Typeface.createFromAsset(getAssets(), "fonts/mplus-1mn-medium.ttf");

        fullname.setTypeface(Mlight);
        username.setTypeface(Mlight);
        nim.setTypeface(Mlight);
        email.setTypeface(Mlight);
        textTitle.setTypeface(Mregular);

        if(getIntent().getExtras()!=null){
            Bundle bundle = getIntent().getExtras();
            user.setUsername(bundle.getString("username"));
            user.setName(bundle.getString("fullname"));
            user.setEmail(bundle.getString("email"));
            user.setNim(bundle.getString("nim"));
            user.setPassword(bundle.getString("password"));
            user.setRole(bundle.getString("role"));

            username.setText(user.getUsername());
            fullname.setText(user.getName());
            email.setText(user.getEmail());
            nim.setText(user.getNim());
            role.setText(user.getRole());

            if(user.getRole().equals("admin")){
                profileIcon.setImageResource(R.drawable.chimpanzee);
            }else{
                profileIcon.setImageResource(R.drawable.cat);
            }
        }
    }
}
