package com.example.the_bloom_room;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewAllUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_user);

        ListView listView = findViewById(R.id.listUser);
        DB_Operation db = new DB_Operation(this);

        ArrayList<User> userArrayList = db.ViewAllUsers();

        if (userArrayList != null){
           ArrayList<String> stringArrayList = new ArrayList<>();
           String data = "";

            for (int i = 0; i< userArrayList.size(); i++){
                User user = userArrayList.get(i);
                data = user.getUsername() +"\n" + user.getFname() + "\n"+ user.getlName();
                stringArrayList.add(data);
        }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stringArrayList);

            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    User user = userArrayList.get(i);
                    Toast.makeText(getApplicationContext(), user.getFname() +" "+ user.getlName(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}