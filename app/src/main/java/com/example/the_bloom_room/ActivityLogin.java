package com.example.the_bloom_room;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityLogin extends AppCompatActivity {

    EditText txtUser, txtPass;
    TextView lblNumAtts;
    Button btnLogin;

    int count = 3;

    DB_Operation dbOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbOperation = new DB_Operation(this);

        txtUser = findViewById(R.id.txtUser);
        txtPass = findViewById(R.id.txtPass);
        lblNumAtts = findViewById(R.id.lblNumAttempts);
        btnLogin = findViewById(R.id.btnLogin);

        txtUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!txtUser.getText().toString().isEmpty() && !txtPass.getText().toString().isEmpty()){
                    btnLogin.setEnabled(true);
                }else{
                    btnLogin.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        txtPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!txtUser.getText().toString().isEmpty()){
                    btnLogin.setEnabled(true);
                }else{
                    btnLogin.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void loginClick(View view){

        ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Authenticating...");
        pd.show();
        Intent intent = new Intent(this, ActivityWelcome.class);

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String user = txtUser.getText().toString();
                int pass = Integer.parseInt(txtPass.getText().toString());


                if(user.equals("Admin1995") && pass == 1995) {
                    intent.putExtra("username", user);
                    startActivity(intent);

                    Toast.makeText(getApplicationContext(), "Login Successful!", Toast.LENGTH_SHORT).show();


                }else if (dbOperation.login(user, pass)){
                    Intent i = new Intent(getApplicationContext(), UserDashboard.class);
                    intent.putExtra("username", user);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(),"Login Successful!", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getApplicationContext(),"Wrong username or password", Toast.LENGTH_SHORT).show();

                    count--;
                    lblNumAtts.setText("Attempts Left: " + count);
                    if(count==0){
                        btnLogin.setEnabled(false);
                    }
                }
                pd.dismiss();

            }
        }, 2000);

    }
    public void signup(View view){
        Intent intent = new Intent(this, ActivitySignUp.class);
        startActivity(intent);
    }
}