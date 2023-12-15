package com.example.the_bloom_room;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DB_Operation extends SQLiteOpenHelper {

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE tblUser(Username VARCHAR(15) PRIMARY KEY, pass INTEGER(10), FName VARCHAR(15), LName VARCHAR(15))";
        sqLiteDatabase.execSQL(sql);

        sql = "CREATE TABLE tblProduct(ID INTEGER(10) PRIMARY KEY, P_NAME VARCHAR(25), PRICE DOUBLE, CATEGORY VARCHAR(25), IMG BLOG)";
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS tblUser";
        sqLiteDatabase.execSQL(sql);

        sql = "DROP TABLE IF EXISTS tblProduct";
        sqLiteDatabase.execSQL(sql);

    }

    public DB_Operation(Context context){
        super(context, "Bloom",null,1);
    }

    public long createUser(User user){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        /*
        String sql = "INSERT INTO tblUser VALUES('"+user.getUsername()+"',"+user.getPass()+","+
                "'"+user.getFname()+"','"+user.getlName()+"')";
        database.execSQL(sql);

         */

        contentValues.put("Username", user.getUsername());
        contentValues.put("Pass",user.getPass());
        contentValues.put("FName",user.getFname());
        contentValues.put("LName",user.getlName());

        return database.insert("tblUser", null, contentValues);

    }

    public boolean login(String user, int pass){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "SELECT * FROM tblUser WHERE Username='"+ user +"' AND Pass="+pass;
        Cursor cursor = database.rawQuery(sql,null);
        if (cursor.getCount()==0){
            return false;
        }else {
            return true;
        }
    }

    public User findUser (String user){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "SELECT * FROM tblUser WHERE Username='"+ user +"'";

        Cursor cursor = database.rawQuery(sql, null);
        User user1 = new User();

        if (cursor.getCount() !=0){
            cursor.moveToFirst();
            user1.setUsername(cursor.getString(0));
            user1.setPass(cursor.getInt(1));
            user1.setFname(cursor.getString(2));
            user1.setlName(cursor.getString(3));
        }else {
            user1 = null;
        }
        return user1;
    }

    public int updateUser(User user){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Pass",user.getPass());
        contentValues.put("FName",user.getFname());
        contentValues.put("LName",user.getlName());

        return database.update("tblUser",contentValues,"Username='"+user.getUsername()+"'",null);


    }

    public int deleteUser(String username){
        SQLiteDatabase database = getWritableDatabase();

        return  database.delete("tblUser", "Username='"+username+"'",null);
    }

    public ArrayList<User> ViewAllUsers(){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "SELECT * FROM tblUser";

        ArrayList<User> users = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, null);

        if (cursor.getCount()>0){
            while (cursor.moveToNext()){

                User user = new User();

                user.setUsername(cursor.getString(0));
                user.setPass(cursor.getInt(1));
                user.setFname(cursor.getString(2));
                user.setlName(cursor.getString(3));

                users.add(user);
            }
        }else {
            users = null;
        }
        return users;
    }

    public long createProduct(Product product){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",product.getId());
        contentValues.put("P_NAME",product.getName());
        contentValues.put("PRICE",product.getPrice());
        contentValues.put("CATEGORY",product.getCategory());
        contentValues.put("IMG",product.getImage());

        return database.insert("tblProduct",null, contentValues);
    }

    public Product getProductByID(int id) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "SELECT * FROM tblProduct WHERE ID = " + id;
        Cursor cursor = database.rawQuery(sql, null);

        Product product = null;

        if (cursor != null && cursor.moveToFirst()) {
            product = new Product(); // Instantiate the Product object before setting its properties
            product.setId(cursor.getInt(0));
            product.setName(cursor.getString(1));
            product.setPrice(cursor.getDouble(2));
            product.setCategory(cursor.getString(3));
            product.setImage(cursor.getBlob(4));
        }

        if (cursor != null) {
            cursor.close();
        }

        return product;
    }




    public ArrayList<Product> ViewAllProduct(){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "SELECT * FROM tblProduct";

        ArrayList<Product> products = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, null);

        if (cursor.getCount()>0){
            while (cursor.moveToNext()){

                Product product = new Product();

                product.setId(cursor.getInt(0));
                product.setName(cursor.getString(1));
                product.setPrice(cursor.getDouble(2));
                product.setCategory(cursor.getString(3));
                product.setImage(cursor.getBlob(4));
                products.add(product);
            }
        }else {
            products = null;
        }
        return products;
    }

}
