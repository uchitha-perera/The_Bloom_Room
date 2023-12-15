package com.example.the_bloom_room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityWelcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        TextView lblWelcome = findViewById(R.id.lblWelcome);
        Intent intent = getIntent();
        lblWelcome.setText("Welcome " + intent.getStringExtra("username").toUpperCase());
    }

    @Override
    public void onBackPressed() {

    }

    public void logoutClick(View view){
        Intent intent = new Intent(this, ActivityLogin.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    public void exitClick(View view){

        finishAffinity();
    }

    public void ManageUser(View view){
        Intent intent = new Intent(this, ManageUser.class);
        startActivity(intent);

    }

    public void ViewAllUser(View view){
        Intent intent = new Intent(this, ViewAllUser.class);
        startActivity(intent);

    }

    public void ManageProduct(View view){
        Intent intent = new Intent(this, ManageProduct.class);
        startActivity(intent);

    }

    public void ViewAllProduct(View view){
        Intent intent = new Intent(this, ViewAllProduct.class);
        startActivity(intent);

    }

}


