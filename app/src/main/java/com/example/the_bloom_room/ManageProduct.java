package com.example.the_bloom_room;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ManageProduct extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner txtCategory;

    EditText txtID, txtName, txtPrice;
    ImageView imageView;
    byte[] imageByte;
    DB_Operation db;

    String selectedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_product);

        txtID = findViewById(R.id.txtProID1);
        txtName = findViewById(R.id.txtProName1);
        txtPrice = findViewById(R.id.txtProPrice1);
        txtCategory = findViewById(R.id.txtSpinner);
        imageView = findViewById(R.id.imgProduct1);

        txtCategory = findViewById(R.id.txtSpinner);

        String[] cate = getResources().getStringArray(R.array.Cat);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, cate);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        txtCategory.setAdapter(arrayAdapter);

        txtCategory.setOnItemSelectedListener(this);

         db = new DB_Operation(this);
    }

    public void selectImage(View view){

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("crop","true");
        intent.putExtra("aspectX","0");
        intent.putExtra("aspectY","0");
        intent.putExtra("outputX","150");
        intent.putExtra("outputY","150");
        intent.putExtra("return-data","true");
        startActivityForResult(Intent.createChooser(intent,"Select Product Image"),111);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==111){
            if (data !=null){
                Uri uri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG,0, arrayOutputStream);
                    imageByte = arrayOutputStream.toByteArray();
                    imageView.setImageBitmap(bitmap);
                }catch (IOException ex){
                    Log.e("Error" ,""+ex.getMessage());
                }
            }
        }
    }

    public void addProduct(View view){

        String idString = txtID.getText().toString().trim();
        String name = txtName.getText().toString().trim();
        String priceString = txtPrice.getText().toString().trim();

        // Validate ID
        if (idString.isEmpty()) {
            showError(txtID, "ID is required");
            return;
        }

        // Validate Name
        if (name.isEmpty()) {
            showError(txtName, "Name is required");
            return;
        }

        // Validate Price
        if (priceString.isEmpty()) {
            showError(txtPrice, "Price is required");
            return;
        }

        // Parse input values
        int id;
        double price;

        try {
            id = Integer.parseInt(idString);
        } catch (NumberFormatException e) {
            showError(txtID, "Invalid ID");
            return;
        }

        try {
            price = Double.parseDouble(priceString);
        } catch (NumberFormatException e) {
            showError(txtPrice, "Invalid price");
            return;
        }


        Product product = new Product();

        product.setId(Integer.parseInt(txtID.getText().toString()));
        product.setName(txtName.getText().toString());
        product.setPrice(Double.parseDouble(txtPrice.getText().toString()));
        product.setCategory(selectedCategory);
        product.setImage(imageByte);

        if (db.createProduct(product)>0){
            Toast.makeText(this, "Product Inserted", Toast.LENGTH_SHORT).show();
        }

    }

    private void showError(EditText editText, String error) {
        editText.setError(error);
        editText.requestFocus();
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(adapterView.getId() == R.id.txtSpinner){

            selectedCategory = adapterView.getItemAtPosition(i).toString();
            Toast.makeText(this, "Selected Category  : " + selectedCategory, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}