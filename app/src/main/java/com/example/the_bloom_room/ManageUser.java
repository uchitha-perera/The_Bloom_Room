package com.example.the_bloom_room;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ManageUser extends AppCompatActivity {

    EditText txtUser, txtPass, txtFName, txtLName;

    DB_Operation dbOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_user);

        dbOperation = new DB_Operation(this);

        txtUser = findViewById(R.id.txtUsername2);
        txtPass = findViewById(R.id.txtPass2);
        txtFName = findViewById(R.id.txtFName2);
        txtLName = findViewById(R.id.txtLName2);
    }

    public void find(View view){
        String username = txtUser.getText().toString();
        if (username.isEmpty()) {
            Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = dbOperation.findUser(username);
        if (user!=null){
            txtPass.setText(""+user.getPass());
            txtFName.setText(user.getFname());
            txtLName.setText(user.getlName());
            
        }else{
            Toast.makeText(this,"user not found", Toast.LENGTH_SHORT).show();
        }

    }

    public void update(View view){

        String username = txtUser.getText().toString().trim();
        String password = txtPass.getText().toString().trim();
        String fName = txtFName.getText().toString().trim();
        String lName = txtLName.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty() || fName.isEmpty() || lName.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }


        User user = new User();

        user.setUsername(txtUser.getText().toString());
        user.setPass(Integer.parseInt(txtPass.getText().toString()));
        user.setFname(txtFName.getText().toString());
        user.setlName(txtLName.getText().toString());

        if (dbOperation.updateUser(user)>0){
            Toast.makeText(this, "user record updated",Toast.LENGTH_SHORT).show();
        }

    }

    public void delete(View view){
        String username = txtUser.getText().toString();

        if (username.isEmpty()) {
            Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show();
            return;
        }

        if (dbOperation.deleteUser(username)>0){
            Toast.makeText(this, "user record delete",Toast.LENGTH_SHORT).show();
        }

    }

    public void clear(View view){
        txtUser.setText(null);
        txtPass.setText(null);
        txtFName.setText(null);
        txtLName.setText(null);
        txtUser.requestFocus();

    }
}