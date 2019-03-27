package com.example.user_registeration;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;
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
      /*  listView = findViewById(R.id.list_view);
        listView.setAdapter(new ArrayAdapter <>(this, android.R.layout.simple_list_item_1, items));
        listView.setOnItemClickListener((AdapterView.OnItemClickListener) this);
*/

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




