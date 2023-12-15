package com.example.the_bloom_room;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UserViewProduct extends AppCompatActivity {

    TextView userProID, userProName, userProPrice, userProCat;

    ImageView imageView2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_product);

        int productID = getIntent().getIntExtra("productID", 0);




        userProID = findViewById(R.id.txtProID3);
        userProName = findViewById(R.id.txtProName3);
        userProPrice = findViewById(R.id.txtProPrice3);
        userProCat = findViewById(R.id.txtProCat3);
        imageView2 = findViewById(R.id.imgProduct3);

        if(productID == 0){
            Toast.makeText(this, "Product id invalid", Toast.LENGTH_SHORT).show();
        }

        DB_Operation db = new DB_Operation(this);

        Product pro = db.getProductByID(productID);

        if(pro == null){
            Toast.makeText(this, "Product not Found!!", Toast.LENGTH_SHORT).show();
            return;
        }

        userProID.setText(String.valueOf(pro.getId()));
        userProName.setText(pro.getName());
        userProPrice.setText(String.valueOf(pro.getPrice()));
        userProCat.setText(pro.getCategory());

        Bitmap bitmap = BitmapFactory.decodeByteArray(pro.getImage(),0,pro.getImage().length);
        imageView2.setImageBitmap(bitmap);




    }


}