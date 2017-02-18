package com.cyclic_order.amarshohor.Data_Retrieving;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cyclic_order.amarshohor.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cyclic_order on 2/5/2016.
 */
public class Issue_details extends Activity {
    TextView tv;
    Button check;
    StringBuffer sb;
    HttpURLConnection connection = null;
    BufferedReader reader = null;
    private ListView lvmovies;
    private ProgressDialog progressdialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);
        progressdialog=new ProgressDialog(this);
        progressdialog.setMessage("Loading");
        progressdialog.setIndeterminate(true);
        progressdialog.setCancelable(false);
       // new AsyncShow().execute("http://cyclicorder.site88.net/JSONDATA/Jsondata.php");


        //Create default options which will be used for every
//  displayImage(...) call if no options will be passed to this method
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()

                .cacheInMemory(true)
                .cacheOnDisk(true)

                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())

                .defaultDisplayImageOptions(defaultOptions)

                .build();
        ImageLoader.getInstance().init(config); // Do it on Application start
        lvmovies = (ListView) findViewById(R.id.listView);
//        check.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new AsyncShow().execute("http://jsonparsing.parseapp.com/jsonData/moviesDemoList.txt");
//            }
//        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_1) {
            new AsyncShow().execute("http://192.168.137.1:8080/JSONDATA/Jsondata.php");

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class AsyncShow extends AsyncTask<String, String, List<IssueModel>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressdialog.show();
        }


        @Override
        protected List<IssueModel> doInBackground(String... params) {
            StringBuffer finalBuffer;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(inputStream));
                sb = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                String finalJson = sb.toString();
                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray parentArray = parentObject.getJSONArray("Issues");
                //finalBuffer=new StringBuffer();
                List<IssueModel> movieModelList = new ArrayList<>();
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    IssueModel movieModel = new IssueModel();
                    movieModel.setProblem_title(finalObject.getString("problem_title"));
                    //int year = finalObject.getInt("year");
                    movieModel.setId(finalObject.getString("id"));
                    movieModel.setLatitude(finalObject.getString("latitude"));
                    movieModel.setLongitude(finalObject.getString("longitude"));
                    movieModel.setPosted_time(finalObject.getString("posted_time"));
                    movieModel.setImage(finalObject.getString("image"));
                    movieModel.setName(finalObject.getString("name"));
                    movieModel.setSubmitted_by(finalObject.getString("submitted_by"));
                    movieModelList.add(movieModel);


                }
                //return finalBuffer.toString();
                return movieModelList;




            } catch (java.io.IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null)
                    connection.disconnect();
                try {
                    if (reader != null)
                        reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }


        @Override
        protected void onPostExecute(List<IssueModel> s) {
            super.onPostExecute(s);
            progressdialog.dismiss();
            // tv.setText(s);


            MyAdapter myadapter=new MyAdapter(getApplicationContext(),R.layout.singlerow,s);
            lvmovies.setAdapter(myadapter);
        }
    }

    protected class MyAdapter extends ArrayAdapter {

        private List<IssueModel>modellist;
        private  int resource;
        private LayoutInflater inflater;
        ProgressBar progressbar;
        public MyAdapter(Context context, int resource, List objects) {
            super(context, resource, objects);
            inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.resource=resource;
            modellist=objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Viewholder holder=null;
            if(convertView==null)
            {
                holder=new Viewholder();
                convertView=inflater.inflate(R.layout.singlerow,null);
                //holder.ivMovie=(ImageView)convertView.findViewById(R.id.img_icon);
                holder.tvTitle=(TextView)convertView.findViewById(R.id.tv_name);
                holder.tvId=(TextView)convertView.findViewById(R.id.tv_id);
                holder.tvLat=(TextView)convertView.findViewById(R.id.tv_latitude);
                holder.tvLong=(TextView)convertView.findViewById(R.id.tv_longitude);
                holder.tvTime=(TextView)convertView.findViewById(R.id.tv_datetime);
                holder.tvDetails=(TextView)convertView.findViewById(R.id.tv_description);
                holder.tvUserid=(TextView)convertView.findViewById(R.id.tv_submit);
                convertView.setTag(holder);
            }
            else
            {
                holder=(Viewholder)convertView.getTag();
            }

            progressbar=(ProgressBar)convertView.findViewById(R.id.progress);
            // Then later, when you want to display image
            //Universal image loader can load,download image if it doesnt found any image//
            ImageLoader.getInstance().displayImage(modellist.get(position).getImage(), holder.ivMovie, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {
                    progressbar.setVisibility(View.VISIBLE);
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    progressbar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    progressbar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {
                    progressbar.setVisibility(View.GONE);
                }
            }); // Default options will be used
            holder.tvTitle.setText("Name:" + modellist.get(position).getProblem_title());
            holder.tvId.setText("id:"+modellist.get(position).getId());
            holder.tvLat.setText("location(latitude):"+modellist.get(position).getLatitude());
            holder.tvLong.setText("location(longitude):"+modellist.get(position).getLongitude());
            holder.tvTime.setText("Submitted at:" + modellist.get(position).getPosted_time());
            holder.tvDetails.setText("Short description:"+modellist.get(position).getName());
            holder.tvUserid.setText("Submitted_by:"+modellist.get(position).getSubmitted_by());

            return  convertView;


        }
        class  Viewholder{
            private ImageView ivMovie;
            private TextView tvTitle;
            private TextView tvId;
            private TextView tvLat;
            private TextView tvLong;
            private TextView tvTime;
            private TextView tvDetails;
            private  TextView tvUserid;


        }

    }
}