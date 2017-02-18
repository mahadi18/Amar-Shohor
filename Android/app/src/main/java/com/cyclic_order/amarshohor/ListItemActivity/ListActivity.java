package com.cyclic_order.amarshohor.ListItemActivity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cyclic_order.amarshohor.R;

import java.util.ArrayList;

/**
 * Created by cyclic_order on 1/31/2016.
 */
public class ListActivity extends ActionBarActivity {
    RecyclerView recyclerView;
    ArrayList<RowItems> itemsList = new ArrayList<>();
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(ListActivity.this, getData());
        recyclerView.setAdapter(adapter);

    }

    public ArrayList<RowItems> getData()
    {
        ArrayList<RowItems> it = new ArrayList<RowItems>();
        RowItems items1 = new RowItems();
        items1.setTitle("PothHoles");
        items1.setImgIcon(R.drawable.pothholes);
        it.add(items1);
        RowItems items2 = new RowItems();
        items2.setTitle("Uprooted tree");
        items2.setImgIcon(R.drawable.tree);
        it.add(items2);
        RowItems items3 = new RowItems();
        items3.setTitle("Faulty Street light");
        items3.setImgIcon(R.drawable.light);
        it.add(items3);
        RowItems items4 = new RowItems();
        items4.setTitle("Trash Dumping");
        items4.setImgIcon(R.drawable.trash);
        it.add(items4);
        RowItems items5 = new RowItems();
        items5.setTitle("Illegal Advertising");
        items5.setImgIcon(R.drawable.advertise);
        it.add(items5);
        RowItems items6 = new RowItems();
        items6.setTitle("Eve Teasing");
        items6.setImgIcon(R.drawable.eveteasing);
        it.add(items6);
        RowItems items7 = new RowItems();
        items7.setTitle("Road Accident");
        items7.setImgIcon(R.drawable.road_accident);
        it.add(items7);
        RowItems items8 = new RowItems();
        items8.setTitle("other");
        items8.setImgIcon(R.drawable.others);
        it.add(items8);
        return it;
    }
}