package com.cyclic_order.amarshohor;

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
import android.widget.RatingBar;
import android.widget.TextView;

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
public class List_Retrieve extends Activity {
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
            new AsyncShow().execute("http://jsonparsing.parseapp.com/jsonData/moviesData.txt");

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class AsyncShow extends AsyncTask<String, String, List<MovieModel>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressdialog.show();
        }


        @Override
        protected List<MovieModel> doInBackground(String... params) {
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
                JSONArray parentArray = parentObject.getJSONArray("movies");
                //finalBuffer=new StringBuffer();
                List<MovieModel> movieModelList = new ArrayList<>();
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    MovieModel movieModel = new MovieModel();
                    movieModel.setMoviename(finalObject.getString("movie"));
                    //int year = finalObject.getInt("year");
                    movieModel.setYear(finalObject.getInt("year"));
                    movieModel.setRating(finalObject.getInt("rating"));
                    movieModel.setDuration(finalObject.getString("duration"));
                    movieModel.setDirector(finalObject.getString("director"));
                    movieModel.setTagline(finalObject.getString("tagline"));
                    movieModel.setStory(finalObject.getString("story"));
                    movieModel.setImage(finalObject.getString("image"));
                    //array inside a jsonobject//
                    //Array inside JSonObject//
                    List<MovieModel.Cast> castlist = new ArrayList<>();
                    for (int j = 0; j < finalObject.getJSONArray("cast").length(); j++) {
                        //JSONObject castObject=finalObject.getJSONArray("cast").getJSONObject(j);
                        MovieModel.Cast cast = new MovieModel.Cast();
                        cast.setName(finalObject.getJSONArray("cast").getJSONObject(j).getString("name"));
                        castlist.add(cast);
                    }
                    //finalBuffer.append(moviename+"-"+year); //Single  jsonArray with Single JsonObject//

                    movieModel.setCast(castlist);
                    //Adding the final object of Parent Object//
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
        protected void onPostExecute(List<MovieModel> s) {
            super.onPostExecute(s);
            progressdialog.dismiss();
            // tv.setText(s);
            MyAdapter myadapter=new MyAdapter(getApplicationContext(),R.layout.row,s);
            lvmovies.setAdapter(myadapter);
        }
    }

    protected class MyAdapter extends ArrayAdapter {

            private List<MovieModel>modellist;
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
                convertView=inflater.inflate(R.layout.row,null);
                holder.ivMovie=(ImageView)convertView.findViewById(R.id.img_icon);
                holder.tvMovie=(TextView)convertView.findViewById(R.id.tv_movie);
                holder.tvyear=(TextView)convertView.findViewById(R.id.tv_year);
                holder.taglink=(TextView)convertView.findViewById(R.id.tv_taglink);;
                holder.duration=(TextView)convertView.findViewById(R.id.tv_duration);;
                holder.director=(TextView)convertView.findViewById(R.id.tv_director);;
                holder.tvCast=(TextView)convertView.findViewById(R.id.tv_cast);;
                holder.tvstory=(TextView)convertView.findViewById(R.id.tv_story);;
                holder.ratingBar=(RatingBar)convertView.findViewById(R.id.ratingBar);
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
            holder.tvMovie.setText("Movie:"+modellist.get(position).getMoviename());
            holder.tvyear.setText("Year:"+modellist.get(position).getYear());
            holder.taglink.setText("TagLink:"+modellist.get(position).getTagline());
            holder.duration.setText("Duration:"+modellist.get(position).getDuration());
            holder.director.setText("Director:"+modellist.get(position).getDirector());
            holder.ratingBar.setRating(modellist.get(position).getRating()/2);
            StringBuffer stringBuffer=new StringBuffer();
            for(MovieModel.Cast cast:modellist.get(position).getCast())
            {
                stringBuffer.append(cast.getName()+",");

            }
            holder.tvCast.setText("Cast:"+stringBuffer);
            holder.tvstory.setText("Story:"+modellist.get(position).getStory());

            return  convertView;


        }
        class  Viewholder{
            private ImageView ivMovie;
            private TextView tvMovie;
            private TextView tvyear;
            private TextView taglink;
            private TextView duration;
            private TextView director;
            private TextView tvCast;
            private TextView tvstory;
            private RatingBar ratingBar;

        }

    }
}