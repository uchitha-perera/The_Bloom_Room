package com.example.the_bloom_room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActivitySignUp extends AppCompatActivity {

    DB_Operation dbOperation;
    EditText txtUser, txtPass, txtFName, txtLName, txtPass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        dbOperation = new DB_Operation(this);

        txtUser = findViewById(R.id.txtSignUpUsername);
        txtPass = findViewById(R.id.txtSignUpPass);
        txtFName = findViewById(R.id.txtSignUpFName);
        txtLName = findViewById(R.id.txtSignUpLName);
        txtPass2 = findViewById(R.id.txtSignUpPass2);
    }
    public void create(View view){

        String username = txtUser.getText().toString();
        String password = txtPass.getText().toString();
        String firstname = txtFName.getText().toString();
        String lastname = txtLName.getText().toString();
        String password2 = txtPass2.getText().toString();

        if(username.isEmpty() || password.isEmpty() || firstname.isEmpty() || lastname.isEmpty()){
            Toast.makeText(this, "All Fields Are Required", Toast.LENGTH_SHORT).show();
        }
        else if(!password.equals(password2)){
            Toast.makeText(this, "Password Not Matched", Toast.LENGTH_SHORT).show();
        }
        else {
            try {
                User user = new User();
                user.setUsername(txtUser.getText().toString());
                user.setPass(Integer.parseInt(txtPass.getText().toString()));
                user.setFname(txtFName.getText().toString());
                user.setlName(txtLName.getText().toString());

                dbOperation.createUser(user);
                Toast.makeText(this, "Account Created", Toast.LENGTH_SHORT).show();

            }catch (Exception ex){
                Log.e("Error", ""+ex.getMessage() );
            }
        }

    }
}