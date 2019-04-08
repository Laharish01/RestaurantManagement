package com.example.user_registeration;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user_registeration.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;

public class ListOrders extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView ListOrder ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_orders);
        Intent intent=getIntent();
        String type=intent.getStringExtra("Dine/Delivery");
        ListOrder = findViewById(R.id.ListOrder);
        ListOrder.setAdapter(new ChefAdapter(this,type));
        ListOrder.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView <?> parent, View view, int position, long id) {
        Intent intent =new Intent(view.getContext(),ItemsToBeCooked.class);
        ViewHolderOrder holder= (ViewHolderOrder) view.getTag();
        OrderInfo temp= (OrderInfo) holder.tableName.getTag();
        intent.putExtra("Table_NameORAddress",temp.tableName);
        startActivity(intent);

    }
}
class OrderInfo implements  Comparable<OrderInfo>
{
    String tableName,startTime;

    public OrderInfo(String tableName, String startTime) {
        this.tableName = tableName;
        this.startTime = startTime;
    }


    @Override
    public int compareTo(OrderInfo o) {
        int compare=startTime.compareTo(o.startTime);
        if(compare==0)
        {
            compare=tableName.compareTo(o.tableName);
        }
        return compare;
    }
}
class ViewHolderOrder
{
    TextView tableName,startTime;
    ViewHolderOrder(View v)
    {
        tableName=v.findViewById(R.id.TableName) ;
        startTime=v.findViewById(R.id.StTime);
    }
}
class ChefAdapter extends BaseAdapter
{
    FirebaseDatabase database;
    DatabaseReference databaseref;

    ArrayList<OrderInfo> list;
    Context context;
    ChefAdapter(Context context, final String type)
    {
        this.context=context;
        list=new ArrayList <>();
        database=FirebaseDatabase.getInstance();
        databaseref=database.getReference().child("ConfirmedOrders").child(type);
        databaseref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              if(type=="Dine") {
                  for (DataSnapshot ds : dataSnapshot.getChildren()) {

                      OrderInfo tempOrderObject = new OrderInfo(ds.child("name").toString(), ds.child("startTime").toString().substring(0,4));
                      list.add(tempOrderObject);
                  }
                  Collections.sort(list);
              }
              if(type=="HomeDelivery")
              {

                  for (DataSnapshot ds : dataSnapshot.getChildren()) {

                      OrderInfo tempOrderObject = new OrderInfo(ds.child("address").toString(),ds.child("currentTime").toString());
                      list.add(tempOrderObject);
                  }
              }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View order=convertView;
        ViewHolderOrder holder;
        if(order==null)
        {
            LayoutInflater inflator= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            order=inflator.inflate(R.layout.order_list_comps,parent,false);
            holder=new ViewHolderOrder(order);

        }
        else
        {
            holder= (ViewHolderOrder) order.getTag();
        }
        OrderInfo temp=list.get(position);
        holder.tableName.setText(temp.tableName);
        holder.startTime.setText(temp.startTime);
        holder.tableName.setTag(temp);

        return order;
    }




}
