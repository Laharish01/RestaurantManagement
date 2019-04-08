package com.example.user_registeration;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user_registeration.Database.Database;
import com.example.user_registeration.Model.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Bill extends AppCompatActivity {

    Button confirm;
    TextView tableNameOrAddress;
    ListView listView;
    TextView totalAmnt;
    ArrayList <String> list;
    ArrayAdapter arrayAdapter;
    String total;
    public static List<Order> globalcart;
    Database myDb;


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<Order> cart = new ArrayList<>();
    CartAdapter adapter;


    private static final String TAG = "Bill";
    String tableNameOrAddressText ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        recyclerView=findViewById(R.id.listBill);
        recyclerView.setHasFixedSize(true);



        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Variables();
        list = new ArrayList <>();
        arrayAdapter = new ArrayAdapter <>(this, android.R.layout.simple_list_item_1, list);

        String temp=NaviDraw.globalDineorHD;
        if(temp=="HomeDelivery") {

            tableNameOrAddressText=CartHD.globaladdress;
        }
        else
        {
            tableNameOrAddressText=TwoSeaterTimings.globalTableName;

        }
        Log.d(TAG, "onCreate: "+tableNameOrAddressText);
        tableNameOrAddress=findViewById(R.id.TableName);
        tableNameOrAddress.setText(tableNameOrAddressText);//settinf tname or address


         Log.d(TAG, "onCreateADD/table: "+total);
        totalAmnt=findViewById(R.id.TotalAmtText);
        totalAmnt.setText(""+CartHD.globaltotal);//setting amount






        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        loadListFood();
    }


    private void Variables() {
        confirm = findViewById(R.id.ConfirmBill);
        //listView = findViewById(R.id.FoodItemsList);
        totalAmnt = findViewById(R.id.TotalPrice);
    }
    private void loadListFood() {
        globalcart = new Database(this).getCarts();
        adapter = new CartAdapter(cart, this);
        recyclerView.setAdapter(adapter);



    }

}


