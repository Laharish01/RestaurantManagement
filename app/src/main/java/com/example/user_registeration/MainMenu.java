package com.example.user_registeration;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.user_registeration.Model.Category;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.OnClickListener;

public class MainMenu extends AppCompatActivity {

    //Getting reference from databases
    FirebaseDatabase fireData;
    DatabaseReference ref;

    //calling adapter object
    ImageAdapter mAdapter;

    RecyclerView recycler_menu;
    RecyclerView.LayoutManager layoutManager;

    private List<Category> cat;

    Toolbar toolbar;

    String key,TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Log.d(TAG, "onCreate: started");

        fireData = FirebaseDatabase.getInstance();
        ref = fireData.getReference("Menu");

        toolbar = findViewById(R.id.toolbarMain);
        toolbar.setTitle("MENU");

        recycler_menu = findViewById(R.id.recycler_menu);
        recycler_menu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_menu.setLayoutManager(layoutManager);

        //Creation of array of objects to store the data
        cat = new ArrayList<>();

        //to put the data into the above array from firebase
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    final Category cate = postSnapshot.getValue(Category.class);
                    cat.add(cate);
                }

                mAdapter = new ImageAdapter(MainMenu.this, cat);
                recycler_menu.setAdapter(mAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainMenu.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //To take the user to the cart
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenu.this, CartHD.class));
            }
        });


    }

}
/*
// To display the data onto the screen
class ImageAdapter extends RecyclerView.Adapter<MenuViewHolder> {

    private Context mContext;
    private List<Category> menu;

    public ImageAdapter(Context context, List<Category> menus){
        mContext=context;
        menu=menus;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.menu_item, viewGroup, false);
        return new MenuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MenuViewHolder menuViewHolder, final int i) {
        final Category mmenu=menu.get(i);
        menuViewHolder.txtMenu.setText(mmenu.getName());
        Picasso.with(mContext).load(mmenu.getImage())
                .fit()
                .centerCrop()
                .into(menuViewHolder.img);

        menuViewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Toast.makeText(mContext, ""+mmenu.getName(), Toast.LENGTH_SHORT).show();
                Intent foodList = new Intent(mContext, FoodDetail.class);
                foodList.putExtra("CategoryID", ref.getRef().getKey()) ;
                view.getContext().startActivity(foodList);}

        });
    }


    @Override
    public int getItemCount() {
        return menu.size();
    }
}
}
*/






