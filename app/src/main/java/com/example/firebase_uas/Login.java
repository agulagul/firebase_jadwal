package com.example.firebase_uas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    private TextView text_Title;
    private EditText etusername, etpassword;
    private ProgressBar progressBar;
    private Button btnLogin;
    String password, username;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Typeface Mlight = Typeface.createFromAsset(getAssets(), "fonts/mplus-1mn-light.ttf");
        Typeface Mregular = Typeface.createFromAsset(getAssets(), "fonts/mplus-1mn-regular.ttf");
        Typeface Mmedium = Typeface.createFromAsset(getAssets(), "fonts/mplus-1mn-medium.ttf");

        text_Title = findViewById(R.id.text_Title);
        text_Title.setTypeface(Mregular);
        etusername = findViewById(R.id.edit_text_username_login);
        etusername.setTypeface(Mlight);
        etpassword = findViewById(R.id.edit_text_password_login);
        etpassword.setTypeface(Mlight);
        btnLogin = findViewById(R.id.button_login);
        btnLogin.setTypeface(Mmedium);
        progressBar = findViewById(R.id.progressbar_login);
        progressBar.setVisibility(View.GONE);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("users");
    }

    public void button_login_Click(View view) {
        username = etusername.getText().toString();
        password = etpassword.getText().toString();


        if (username.isEmpty()) {
            etusername.setError(getString(R.string.input_error_name));
            etusername.requestFocus();
            return;
        }


        if (password.isEmpty()) {
            etpassword.setError(getString(R.string.input_error_password));
            etpassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            etpassword.setError(getString(R.string.input_error_password_length));
            etpassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);


        databaseReference.child(username).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if(dataSnapshot.exists()){
                    if(password.equals(user.getPassword())){
                        Bundle bundle = new Bundle();
                        bundle.putString("username", user.getUsername());
                        bundle.putString("fullname", user.getName());
                        bundle.putString("email", user.getEmail());
                        bundle.putString("nim", user.getNim());
                        bundle.putString("password", user.getPassword());
                        bundle.putString("role", user.getRole());
                        Toast.makeText(Login.this, "Login Success", Toast.LENGTH_SHORT).show();
                        if(user.getRole().equals("admin")){
                            Intent intent = new Intent(Login.this, Home.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                        if(user.getRole().equals("user")){
                            Intent intent = new Intent(Login.this, HomeUser.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                        progressBar.setVisibility(View.GONE);

                    }else{
                        Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }else{
                    Toast.makeText(Login.this, "User not exist", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Login.this, "User not exist", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);

            }
        });
    }
}
