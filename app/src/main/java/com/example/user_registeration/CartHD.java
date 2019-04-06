package com.example.user_registeration;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user_registeration.Database.*;
import com.example.user_registeration.Model.Order;
import com.example.user_registeration.Model.RequestHD;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CartHD extends AppCompatActivity {
    private static final String TAG = "CartHD";

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference ref=database.getReference();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    TextView xtotal;
    Button btnPlace;

    String name;
    DatabaseReference nameref;
     String HDorDine;

    List<Order> cart = new ArrayList<>();
    CartAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ref=database.getReference("Requests");
        nameref= ref.child("User").child(user.getUid()).child("Name");
        recyclerView=findViewById(R.id.listCart);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final Intent but = getIntent();
        HDorDine = but.getStringExtra("HDorDine");
        Toast.makeText(this, ""+HDorDine, Toast.LENGTH_SHORT).show();

        xtotal = findViewById(R.id.total);
        btnPlace=findViewById(R.id.btnPlaceOrder);
        btnPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(HDorDine.equals("homedel"))
                showAlertDialog();
                else Toast.makeText(CartHD.this, "Wait not yet done!", Toast.LENGTH_SHORT).show();
            }
        });
        loadListFood();

    }

    private void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CartHD.this);
        alertDialog.setTitle("One more step!");
        alertDialog.setMessage("Enter your address");

        final EditText edtAddress = new EditText(CartHD.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        edtAddress.setLayoutParams(lp);

        alertDialog.setView(edtAddress);
        alertDialog.setIcon(R.drawable.ic_add_shopping_cart_black_24dp);

        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                RequestHD requestHD = new RequestHD(
                        name,
                        edtAddress.getText().toString(),
                        xtotal.getText().toString(),
                        cart

                );
                Log.d(TAG, "onClick: "+ user.getDisplayName());

                ref.child(user.getUid())
                        .setValue(requestHD);

                new Database(getBaseContext()).cleanCart();
                Toast.makeText(CartHD.this, "Thank you, Order placed", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.show();

    }

    private void loadListFood() {
        cart = new Database(this).getCarts();
        adapter = new CartAdapter(cart, this);
        recyclerView.setAdapter(adapter);

        int total = 0;
        for(Order order : cart){

            total+=(Integer.parseInt(order.getPrice()))*(Integer.parseInt(order.getQuantity()));

        }
        Locale locale = new Locale("en", "US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

        xtotal.setText(fmt.format(total));

    }
}
