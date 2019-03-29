package com.example.laharish.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.widget.ListView;

import com.example.laharish.Model.Order;
import com.example.laharish.R;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper {
    private static final String DB_NAME = "RMA.db";
    private static  final int DB_VER = 1;

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    public List<Order> getCarts()
    {
        SQLiteDatabase db= getReadableDatabase();
        SQLiteQueryBuilder qb= new SQLiteQueryBuilder();

        String[] sqlSelect = {"ProductName","ProductId","Quantity","Price"};
        String sqlTable = "RMA";
        qb.setTables(sqlTable);

        Cursor c = qb.query(db, sqlSelect, null,null,null,null,null);

        final List<Order> result = new ArrayList<>();
        if(c.moveToFirst())
        {
            do{
                result.add(new Order(c.getString(c.getColumnIndex("ProductName")),
                        c.getString(c.getColumnIndex("ProductId")) ,
                        c.getString(c.getColumnIndex("Quantity")),
                        c.getString(c.getColumnIndex("Price"))
                        ));
            }while(c.moveToNext());
        }
        return result;
    }

    public void addToCart(Order order)
    {
        SQLiteDatabase db;
        SQLiteOpenHelper ohg;

        String query = String.format("INSERT INTO RMA (ProductName,ProductId,Quantity,Price) VALUES('%s', '%s','%s','%s' )",
                order.getProductId(),
                order.getProductName(),
                order.getPrice(),
                order.getQuantity());
        db.execSQL(query);
    }

    public void cleanCart()
    {
        SQLiteDatabase db= getReadableDatabase();
        String query = "DELETE FROM RMA";
        db.execSQL(query);
    }
}
