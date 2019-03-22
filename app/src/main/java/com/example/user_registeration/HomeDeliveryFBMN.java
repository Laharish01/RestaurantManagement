package com.example.user_registeration;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeDeliveryFBMN extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference databaseRef;
    ListView ListViewMN_HDFB;
    ArrayAdapter <String> arrayAdapter;
    ArrayList   <String> List;

    float quickness, cordialtyHD, qualityHD, packing, tasteHD,countHD;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_delivery_fbmn);

        database=FirebaseDatabase.getInstance();
        databaseRef=database.getReference().child("Ratings_HD");
        ListViewMN_HDFB=findViewById(R.id.ListViewMN_DineFB);
        
        List=new ArrayList<>();
        arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,List);
        
        
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                countHD= Float.parseFloat(dataSnapshot.child("countHD").getValue().toString());
                quickness= Float.parseFloat(dataSnapshot.child("quickness").getValue().toString());
                packing= Float.parseFloat(dataSnapshot.child("packing").getValue().toString());
                cordialtyHD= Float.parseFloat(dataSnapshot.child("cordialtyHD").getValue().toString());
                qualityHD= Float.parseFloat(dataSnapshot.child("qualityHD").getValue().toString());
                tasteHD= Float.parseFloat(dataSnapshot.child("tasteHD").getValue().toString());

                List.add("Count:"+countHD);
                List.add("Quickness:"+quickness);
                List.add("Packing:"+packing);
                List.add("Cordiality:"+cordialtyHD);
                List.add("Quality:"+qualityHD);
                List.add("Taste:"+tasteHD);

                ListViewMN_HDFB.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
            }
}
