package com.example.user_registeration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Chef extends AppCompatActivity {

    Button Dine,Delivery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef);
        Dine=findViewById(R.id.Dine);
        Delivery=findViewById(R.id.Delivery);
        final Intent intent=new Intent(Chef.this,ListOrders.class);
        Dine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("Dine/Delivery","Dine");
                startActivity(intent);
            }
        });
        Delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("Dine/Delivery","Delivery");
                startActivity(intent);
            }
        });
    }

}
