package com.example.laharish;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class sign_Up extends AppCompatActivity
{

    private EditText  newUserName,newpassword,newEmail;
    private Button    register;
    private TextView  oldUser;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("User");
    private String name="\0",password="\0",userEmail="\0", uid;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);
        firebaseAuth = FirebaseAuth.getInstance();//getting an instance from firebase
        UIvariables();//calling function to reference the variables

        //if user has already registered
        oldUser.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Toast.makeText(sign_Up.this, "works", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(sign_Up.this, Login.class));
                finish();
            }
        });

        //on clicking register button
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                enterData();
                if(Validate_Login_Credentials())
                {
                    firebaseAuth.createUserWithEmailAndPassword(userEmail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (task.isSuccessful())
                            {
                                user=firebaseAuth.getCurrentUser();
                                uid=user.getUid();
                                ref.child(uid).child("Name").setValue(name);

                                Toast.makeText(sign_Up.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(sign_Up.this, Login.class));
                            }
                            else
                                {
                                    Toast.makeText(sign_Up.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                }
                        }
                    }
                    );
                }
            }
        });




    }
    private void UIvariables()//            to reference xml id's to java variables
    {


        //signUp=(TextView)findViewById(R.id.signUp);
        newUserName=(EditText)findViewById(R.id.newUserName);
        newpassword=(EditText)findViewById(R.id.newpassword);
        newEmail=(EditText)findViewById(R.id.newEmail);
        register=(Button) findViewById(R.id.register);
        oldUser=(TextView)findViewById(R.id.oldUser);
    newUserName.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            newUserName.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_action_name,0,0,0);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
           newUserName.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,0,0);
        }

        @Override
        public void afterTextChanged(Editable s) {
            newUserName.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,0,0);

        }
    });

    newEmail.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            newEmail.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_action_name,0,0,0);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            newEmail.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,0,0);

        }

        @Override
        public void afterTextChanged(Editable s) {
            newEmail.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,0,0);

        }
    });
    newpassword.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            newpassword.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.password_key,0,0,0);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            newpassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,0,0);

        }

        @Override
        public void afterTextChanged(Editable s) {
            newpassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,0,0);

        }
    });
    }
    private void enterData()
    {
        name = newUserName.getText().toString().trim();
        password = newpassword.getText().toString().trim();
        userEmail = newEmail.getText().toString().trim();
    }

    private boolean Validate_Login_Credentials()//to see to that the user fills in all the fields
    {
        boolean field_check=false;
        if(userEmail.isEmpty() || password.isEmpty() || name.isEmpty() )
        {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        }
        else
        {
            field_check=true;
        }
        return field_check;

    }
}