package com.cyclic_order.amarshohor.ImageActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.cyclic_order.amarshohor.MapsActivity.MapsActivity;
import com.cyclic_order.amarshohor.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by cyclic_order on 2/11/2016.
 */
public class upload_text extends AppCompatActivity implements View.OnClickListener {

    private Button buttonChoose;
    private Button buttonUpload;
    public static final String MyPREFERENCES = "MyPrefs";

    private EditText editText;
    private ImageView imageView;
    String locationC=null;
    public static final String KEY_IMAGE = "image";
    public static final String KEY_TEXT = "name";
    public static final String KEY_LAT="latitude";
    public static final String KEY_LONG="longitude";
    public static final String KEY_POST="posted_time";
    public static final String KEY_ID="submitted_by";
    public  static  final  String KEY_LOCATION="locationname";
    public static double latitude,longitude;
    LocationManager locationManager;
    Location mCurrentLocation;
    String locationProvider;

   public static final String UPLOAD_URL = "http://192.168.137.1:8080/PhotoUpload/uploadImage.php";
   //public static final String UPLOAD_URL = "http://192.168.43.74:8080/PhotoUpload/uploadImage.php";

    private int PICK_IMAGE_REQUEST = 1;

    private Bitmap bitmap;
     double latS,longS;
public int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        buttonUpload = (Button) findViewById(R.id.buttonUpload);
        buttonChoose = (Button) findViewById(R.id.buttonChooseImage);

        editText = (EditText) findViewById(R.id.editText);
        imageView = (ImageView) findViewById(R.id.imageView);

        buttonChoose.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    public void uploadImage() {
        final String text = editText.getText().toString().trim();
        final String image = getStringImage(bitmap);
//        if(image.isEmpty())
//        {
//            Toast.makeText(upload_text.this,"Image is not selected",Toast.LENGTH_LONG).show();
//        }
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        locationProvider = locationManager.getBestProvider(criteria, true);
        mCurrentLocation = locationManager.getLastKnownLocation(locationProvider);
        latS= mCurrentLocation.getLatitude();
        longS=mCurrentLocation.getLongitude();
        pos = getIntent().getIntExtra("position", 0);
        SharedPreferences settings = getSharedPreferences(MyPREFERENCES, 0);
        final String uname=settings.getString("username", "");
        //final SharedPreferences locname = getSharedPreferences("Address", 0);
        //final String address=locname.getString("location","");

        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latS, longS, 1);

            addresses.get(0);
            locationC = addresses.get(0).getFeatureName() +", "+addresses.get(0).getLocality() +", "+ addresses.get(0).getCountryName();
            //SharedPreferences.Editor editor = sharedPreferences.edit();
            //editor.putString("location", location);
            Toast.makeText(this, "Address -> " + locationC, Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
//        Toast.makeText(upload_text.this,uname,Toast.LENGTH_LONG).show();


        class UploadImage extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(upload_text.this, "Please wait...", "uploading", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(upload_text.this, s, Toast.LENGTH_LONG).show();
                startActivity(new Intent(upload_text.this,MapsActivity.class));
                finish();
            }

            @Override
            protected String doInBackground(Void... params) {
                String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());

                RequestHandler rh = new RequestHandler();
                HashMap<String, String> param = new HashMap<String, String>();
                switch(pos)
                {
                    case 0:
                        param.put("problem_title","PothHoles");
                        break;
                    case 1:
                        param.put("problem_title","uprooted treee");
                        break;
                    case 2:
                        param.put("problem_title","faulty street light");
                        break;
                    case 3:
                        param.put("problem_title","trash dumping");
                        break;
                    case 4:
                        param.put("problem_title","illegal advertising");
                        break;
                    case 5:
                        param.put("problem_title","eve teasing");
                        break;
                    case 6:
                        param.put("problem_title","Road Accident");
                        break;
                    case 7:
                        param.put("problem_title","other");
                        break;
                }
                param.put(KEY_LAT,String.valueOf(latS));
                param.put(KEY_LONG,String.valueOf(longS));
                param.put(KEY_IMAGE, image);
                param.put(KEY_TEXT, text);
                param.put(KEY_POST,timeStamp);
                param.put(KEY_ID,uname);
                param.put(KEY_LOCATION,locationC);
                String result = rh.sendPostRequest(UPLOAD_URL, param);
                return result;
            }
        }
        UploadImage u = new UploadImage();
        u.execute();
    }


    @Override
    public void onClick(View v) {
        if (v == buttonChoose) {
            showFileChooser();
        }
        if (v == buttonUpload) {
            uploadImage();

        }
    }
}
