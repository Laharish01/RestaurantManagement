/**package com.example.user_registeration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.user_registeration.Model.RequestDine;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Payment extends AppCompatActivity {

    private static final String TAG = "Payment";
    TextView amt;
    Button confirm;
    String HDorDine;

    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference ref=database.getReference();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        amt=findViewById(R.id.money);
        confirm = findViewById(R.id.confirm);

        Intent amount = getIntent();
        String totprice =amount.getStringExtra("amount");

        amt.setText(totprice);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(HDorDine.equals("homedel"))
                    showAlertDialog();
                else {
                    placeOrder();
                }
            }
        });

    }

    private void placeOrder() {
        RequestDine requestDine = new RequestDine(name, xtotal.getText().toString(), cart, tiem, "table1");
        ref.child(user.getUid())
                .setValue(requestDine);

        new Database(getBaseContext()).cleanCart();
        Toast.makeText(CartHD.this, "Order placed, Thank you!", Toast.LENGTH_SHORT).show();

        finish();
    }

    private void showAlertDialog() {

        Intent intent = getIntent();
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
                Intent money = new Intent(CartHD.this, Payment.class);
                money.putExtra("amount", xtotal.getText().toString());
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

}
*/
package com.example.user_registeration;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Conference;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user_registeration.Database.Database;
import com.example.user_registeration.Model.Order;
import com.example.user_registeration.Model.RequestDine;
import com.example.user_registeration.Model.RequestHD;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.user_registeration.CartHD.globaladdress;

public class Payment extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference databaseReference;

    private static final String TAG = "Payment";
    TextView amt;
    Button confirm;
    String dineOrHD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference().child("ConfirmedOrders");
        confirm=findViewById(R.id.confirm);
        amt = findViewById(R.id.money);
        amt.setText(""+CartHD.globaltotal);
        dineOrHD=NaviDraw.globalDineorHD;
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dineOrHD=="HomeDelivery")
                {
                    //databaseReference=databaseReference.child("HomeDelivery");
                    sendToDatabaseHomeDelivery();
                    startActivity(new Intent(Payment.this, NaviDraw.class));
                    Toast.makeText(Payment.this, "Payment Successful!!!!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onClickPayment: ");
                }
                else
                {
                    //databaseReference=databaseReference.child("Dine");
                    sendToDatabaseDine();
                    startActivity(new Intent(Payment.this, NaviDraw.class));
                    Toast.makeText(Payment.this, "Payment Successful!!!!", Toast.LENGTH_SHORT).show();

                }
                new Database(getBaseContext()).cleanCart();
            }
        });
    }

    private void sendToDatabaseDine()
    {
        RequestDine obj=new RequestDine(TwoSeaterTimings.globalTableName,Bill.globalcart,Set_Time.globalTableTiming);
        databaseReference.child("Dine").push().setValue(obj);
    }

    private void sendToDatabaseHomeDelivery()
    {
        Calendar calendar = Calendar.getInstance();
        int  hour = calendar.get(Calendar.HOUR_OF_DAY);
        int  minute = calendar.get(Calendar.MINUTE);
        String currentTime=hour+":"+minute;
        RequestHD obj=new RequestHD(CartHD.globaladdress,Bill.globalcart,currentTime);
        databaseReference.child("HomeDelivery").push().setValue(obj);

    }
}

