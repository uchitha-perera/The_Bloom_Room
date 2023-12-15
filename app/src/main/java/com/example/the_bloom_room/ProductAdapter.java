package com.example.the_bloom_room;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {

    Context context;
    ArrayList<Product> products;

    public ProductAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        TextView txtProID, txtProName, txtProPrice, txtProCategory;
        ImageView imgProduct;
        Button btnView;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = inflater.inflate(R.layout.custom_product_layout,viewGroup, false);

        txtProID = view1.findViewById(R.id.txtProID2);
        txtProName = view1.findViewById(R.id.txtProName2);
        txtProPrice = view1.findViewById(R.id.txtProPrice2);
        txtProCategory = view1.findViewById(R.id.txtProCat2);
        imgProduct = view1.findViewById(R.id.imgProduct2);
        btnView = view1.findViewById(R.id.btnView);

        btnView.setTag(i);

        Product product = products.get(i);
        txtProID.setText(""+ product.getId());
        txtProName.setText(product.getName());
        txtProPrice.setText(""+product.getPrice());
        txtProCategory.setText(product.getCategory());
        Bitmap bitmap = BitmapFactory.decodeByteArray(product.getImage(),0,product.getImage().length);
        imgProduct.setImageBitmap(bitmap);

        return view1;
    }
}
