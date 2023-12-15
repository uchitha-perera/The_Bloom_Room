package com.example.the_bloom_room;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public Spinner userCategory;

    ListView listProducts2;
    ArrayList<Product> products;

    String selecteCat = "All";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        userCategory = findViewById(R.id.txtSpinner2);

        String[] usercat = getResources().getStringArray(R.array.Cat);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, usercat);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userCategory.setAdapter(arrayAdapter);

        DB_Operation db = new DB_Operation(this);
        listProducts2 = findViewById(R.id.listProducts2);

        products = db.ViewAllProduct();

        userCategory.setOnItemSelectedListener(this);

        showProducts();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(adapterView.getId() == R.id.txtSpinner2){

            selecteCat = adapterView.getItemAtPosition(i).toString();
            Toast.makeText(this, "Selected Category  : " + selecteCat, Toast.LENGTH_SHORT).show();
            this.showProducts();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void getProduct(View view){
        int index = (int)view.getTag();

        Product pro = products.get(index);


        Intent i = new Intent(getApplicationContext(), UserViewProduct.class);
        i.putExtra("productID", pro.getId());
        startActivity(i);

    }

    public void showProducts(){

        ArrayList<Product> filtered = new ArrayList<>();

        if(selecteCat.equals("All")){
            filtered = products;
        }
        else {
            for (Product product : products) {
                if (product.getCategory().equals(selecteCat)) {
                    filtered.add(product);
                }
            }
        }

        if (filtered !=null){
            ProductAdapter22 productAdapter = new ProductAdapter22(this, filtered);
            listProducts2.setAdapter(productAdapter);
        }
    }

}