package com.example.user_registeration;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DineFBMN extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference databaseRef;
    ListView LvMN_DFB;
    ArrayList<String> Dine_FB;
    ArrayAdapter<String> arrayAdapter;

    //Data_From_Database ratings;
    float parking,cordialty,quality,appeal,taste,ambience,comfort,hygiene,count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dine_fbmn);


        Dine_FB=new ArrayList<>();
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Dine_FB);

        database = FirebaseDatabase.getInstance();
        databaseRef = database.getReference().child("Ratings");
        LvMN_DFB=findViewById(R.id.ListViewMN_DineFB);



                databaseRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {

                        count= Float.parseFloat(dataSnapshot.child("count").getValue().toString());
                        ambience= Float.parseFloat(dataSnapshot.child("ambience").getValue().toString());
                        appeal= Float.parseFloat(dataSnapshot.child("appeal").getValue().toString());
                        comfort= Float.parseFloat(dataSnapshot.child("comfort").getValue().toString());
                        cordialty= Float.parseFloat(dataSnapshot.child("cordialty").getValue().toString());
                        hygiene= Float.parseFloat(dataSnapshot.child("hygiene").getValue().toString());
                        parking= Float.parseFloat(dataSnapshot.child("parking").getValue().toString());
                        quality= Float.parseFloat(dataSnapshot.child("quality").getValue().toString());
                        taste= Float.parseFloat(dataSnapshot.child("taste").getValue().toString());

                        Dine_FB.add("Count:"+count);
                        Dine_FB.add("Ambience:"+ambience);
                        Dine_FB.add("Appeal:"+appeal);
                        Dine_FB.add("Comfort:"+comfort);
                        Dine_FB.add("Cordiality:"+cordialty);
                        Dine_FB.add("Hygiene:"+hygiene);
                        Dine_FB.add("Parking:"+parking);
                        Dine_FB.add("Quality:"+quality);
                        Dine_FB.add("Taste:"+taste);

                        LvMN_DFB.setAdapter(arrayAdapter);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

}
