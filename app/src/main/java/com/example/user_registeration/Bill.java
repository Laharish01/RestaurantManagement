package com.example.user_registeration;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class Bill extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference databaseRef;
    Button confirm;
    EditText address;
    ListView listView;
    TextView totalAmnt;
    ArrayList <String> list;
    ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        database = FirebaseDatabase.getInstance();
        databaseRef = database.getReference().child("RequestHD");
        Variables();
        list = new ArrayList <>();
        arrayAdapter = new ArrayAdapter <>(this, android.R.layout.simple_list_item_1, list);
        databaseRef.child("########").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                address.setText(Objects.requireNonNull(dataSnapshot.child("address").getValue()).toString());
                totalAmnt.setText(Objects.requireNonNull(dataSnapshot.child("total").getValue()).toString());

                for (DataSnapshot ds : dataSnapshot.child("foods").getChildren()) {
                    list.add(ds.toString());
                }

                listView.setAdapter(arrayAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    private void Variables() {
        confirm = findViewById(R.id.ConfirmBill);
        address = findViewById(R.id.Address);
        listView = findViewById(R.id.FoodItemsList);
        totalAmnt = findViewById(R.id.TotalPrice);
    }

}


