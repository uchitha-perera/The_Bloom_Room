package com.example.the_bloom_room;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewAllProduct extends AppCompatActivity {

    ListView listProducts;

    ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_product);

        DB_Operation db = new DB_Operation(this);
        listProducts = findViewById(R.id.listProducts);

        products = db.ViewAllProduct();

        if (products !=null){
            ProductAdapter productAdapter = new ProductAdapter(this, products);
            listProducts.setAdapter(productAdapter);
        }
    }

    public void getProduct(View view){
        int index = (int)view.getTag();
        String name = products.get(index).getName();
        Toast.makeText(this, "name", Toast.LENGTH_SHORT).show();
    }
}