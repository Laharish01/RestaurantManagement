package com.example.user_registeration;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class NaviDraw extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "NaviDraw";

    Button homeDel, finedine;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser currentuser = firebaseAuth.getCurrentUser();

    FloatingActionButton fab;
    TextView username, useremail;

    public static String globalDineorHD="";
    String HDorDine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi_draw);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        username = findViewById(R.id.current_user);
        useremail = findViewById(R.id.current_useremail);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //load menu


        homeDel=findViewById(R.id.homeDel);
        homeDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalDineorHD="HomeDelivery";
                startActivity(new Intent(NaviDraw.this, MainMenu.class));

            }
        });

        finedine = findViewById(R.id.finedine);
        finedine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent=new Intent(NaviDraw.this,Bill.class);
                //intent.putExtra("DineorHD","Dine");
                globalDineorHD="Dine";

                startActivity(new Intent(NaviDraw.this, TableType.class));

            }
        });


       // useremail.setText(currentuser.getEmail());
        Log.d(TAG, "onCreate: " + currentuser.getEmail());
        //To take the user to the cart
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent putHDorDine = new Intent(NaviDraw.this, CartHD.class);
                putHDorDine.putExtra("HDorDine",HDorDine);
                startActivity(putHDorDine);
            }
        });



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navi_draw, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

         if (id == R.id.parking_select) {
            startActivity(new Intent(getApplicationContext(),Parking.class));


        } else if (id == R.id.feedbackDine) {
            startActivity(new Intent(getApplicationContext(),Feedback.class));


        } else if (id == R.id.feedbackHd) {
            startActivity(new Intent(getApplicationContext(),Feedback_home_delivery.class));


        } else if (id == R.id.FeedbackManager) {
            startActivity(new Intent(getApplicationContext(), Manager_Feedback.class));
        }
        else if (id == R.id.logout) {
            logout();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //the logout option
    public void logout() {
        firebaseAuth.signOut();
        Toast.makeText(NaviDraw.this, "Logout Successful!!!", Toast.LENGTH_SHORT).show();
        finish();
        startActivity(new Intent(NaviDraw.this, MainActivity.class));
    }

}
