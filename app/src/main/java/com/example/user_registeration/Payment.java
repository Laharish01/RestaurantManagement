package com.example.user_registeration;

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