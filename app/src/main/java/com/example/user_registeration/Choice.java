package com.example.user_registeration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Set;

public class Choice extends AppCompatActivity {

    //Firebase authentication and widgets
    FirebaseAuth firebaseAuth;
    Button Feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        firebaseAuth = FirebaseAuth.getInstance();

        Feedback=findViewById(R.id.Feedback);
        Feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Choice.this,Parking.class));
            }
        });

    }

    //menu for logout etc
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.logout:
                logout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void logout()
    {
        firebaseAuth.signOut();
        Toast.makeText(Choice.this,"Logged out Successfully!!!",Toast.LENGTH_SHORT).show();
        finish();
        startActivity(new Intent(Choice.this,MainActivity.class));
    }
}

