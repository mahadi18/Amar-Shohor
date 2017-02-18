package com.cyclic_order.amarshohor.model;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cyclic_order.amarshohor.ImageActivity.UploadToServer;
import com.cyclic_order.amarshohor.MapsActivity.MapsActivity;
import com.cyclic_order.amarshohor.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Cyclic_Order on 12/14/2015.
 */
public class Activity_list extends android.app.ListActivity {
    String[]items;
    ListView listView;
    public GoogleMap gmap;
    public Marker[] usermarker;
    public MarkerOptions[] placesresult;




    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);
        listView = getListView();
        setListAdapter(new MyAdapter(this, android.R.layout.simple_list_item_activated_1, R.id.list_textView, getResources().getStringArray(R.array.ProblemList)));
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position)
                        {
                            case 0:
                                startActivity(new Intent(Activity_list.this, MapsActivity.class));
                                break;
                            case 1:
                                startActivity(new Intent(Activity_list.this,UploadToServer.class));
                                break;
                            case 2:
                                startActivity(new Intent(Activity_list.this,UploadToServer.class));
                                break;
                            case 3:
                                startActivity(new Intent(Activity_list.this,UploadToServer.class));
                                break;
                            case 4:
                                startActivity(new Intent(Activity_list.this,UploadToServer.class));
                                break;
                            case 5:
                                startActivity(new Intent(Activity_list.this,UploadToServer.class));
                                break;

                        }
                    }
                }
        );
    }





  protected  class MyAdapter extends ArrayAdapter <String>{
        public MyAdapter(Context context, int resource, int textViewResourceId, String[] strings) {
            super(context, resource, textViewResourceId, strings);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater= (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=inflater.inflate(R.layout.list_display, parent, false);
            TextView tv= (TextView)row.findViewById(R.id.list_textView);
            ImageView iv= (ImageView) row.findViewById(R.id.list_imageView);
            String []items=getApplicationContext().getResources().getStringArray(R.array.ProblemList);
            tv.setText(items[position]);
            if(items[position].equals("PothHoles"))
            {

                iv.setImageResource(R.drawable.pothholes);
            }
            else if(items[position].equals("Trash Dumping"))
            {
                iv.setImageResource(R.drawable.trash);
            }
            else if(items[position].equals("Faulty Street Lights"))
            {
                iv.setImageResource(R.drawable.light);
            }
            else if(items[position].equals("Uprooted Tree"))
            {
                iv.setImageResource(R.drawable.tree);
            }
            else if(items[position].equals("Illegal Advertising Boards"))
            {
                iv.setImageResource(R.drawable.advertise);
            }
            else if(items[position].equals("Eve Teasing"))
            {
                iv.setImageResource(R.drawable.eveteasing);
            }
            else if(items[position].equals("Others"))
            {
                iv.setImageResource(R.drawable.other);
            }

            return row;
        }


    }
    private class MarkerAsynctask extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected Void doInBackground(Void... params) {

            return null;
        }
    }





}
