package com.example.user_registeration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ItemsToBeCooked extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference databaseRef;
    //Adapter and list
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> list;
    //Widgets
    Button close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      /*  setContentView(R.layout.activity_items_to_be_cooked);

        listView=findViewById(R.id.OrderedItems);
        Intent intent =new Intent()*/
    }
}
