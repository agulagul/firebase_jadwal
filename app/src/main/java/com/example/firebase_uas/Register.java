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

public class Register extends AppCompatActivity {
    private TextView textTitle;
    private Button buttonRegister;
    private EditText fullname, username, nim, email, password;
    private RadioButton rbAdmin, rbUser;
    private ProgressBar progressBar;
    private User user;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullname = findViewById(R.id.edit_text_name);
        username = findViewById(R.id.edit_text_username);
        nim = findViewById(R.id.edit_text_nim);
        email = findViewById(R.id.edit_text_email);
        password = findViewById(R.id.edit_text_password);
        rbAdmin = findViewById(R.id.radioAdmin);
        rbUser = findViewById(R.id.radioUser);
        textTitle = findViewById(R.id.text_Title);
        buttonRegister = findViewById(R.id.button_register);
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
        password.setTypeface(Mlight);
        rbUser.setTypeface(Mlight);
        rbAdmin.setTypeface(Mlight);
        buttonRegister.setTypeface(Mmedium);
        textTitle.setTypeface(Mregular);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("users");

    }

    public void button_register_Click(View view) {
        user.setName(fullname.getText().toString());
        user.setUsername(username.getText().toString());
        user.setNim(nim.getText().toString());
        user.setEmail(email.getText().toString());
        user.setPassword(password.getText().toString());
        if(rbAdmin.isChecked()){
            user.setRole("admin");
        }
        if(rbUser.isChecked()){
            user.setRole("user");
        }

        if (user.getName().isEmpty()) {
            fullname.setError(getString(R.string.input_error_name));
            fullname.requestFocus();
            return;
        }

        if (user.getUsername().isEmpty()) {
            username.setError(getString(R.string.input_error_username));
            username.requestFocus();
            return;
        }

        if (user.getNim().isEmpty()) {
            nim.setError(getString(R.string.input_error_nim));
            nim.requestFocus();
            return;
        }


        if (user.getEmail().isEmpty()) {
            email.setError(getString(R.string.input_error_email));
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(user.getEmail()).matches()) {
            email.setError(getString(R.string.input_error_email_invalid));
            email.requestFocus();
            return;
        }

        if (user.getPassword().isEmpty()) {
            password.setError(getString(R.string.input_error_password));
            password.requestFocus();
            return;
        }

        if (password.length() < 6) {
            password.setError(getString(R.string.input_error_password_length));
            password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        databaseReference.child(user.getUsername()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Register.this, R.string.registration_success, Toast.LENGTH_SHORT).show();
                    fullname.setText("");
                    username.setText("");
                    nim.setText("");
                    email.setText("");
                    password.setText("");
                    progressBar.setVisibility(View.GONE);
                }else{
                    Toast.makeText(Register.this, R.string.registration_filed, Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
