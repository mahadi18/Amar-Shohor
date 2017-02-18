package com.cyclic_order.amarshohor.ImageActivity;

/**
 * Created by cyclic_order on 1/29/2016.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.cyclic_order.amarshohor.ListItemActivity.RowViewHolder;
import com.cyclic_order.amarshohor.MapsActivity.MapsActivity;
import com.cyclic_order.amarshohor.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class UploadToServer extends Activity implements View.OnClickListener {
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final int MEDIA_TYPE_IMAGE = 1;
    private static final int MEDIA_TYPE_GALLERY = 2;
    int pos;
    //directory name to store Image
    private static final String Storage_name = "Project Image";
    /*file Url to store Image*/
    private Uri fileUri;
    ImageView imageView;
    Button btn_capture, btn_imageToUpload;
    EditText edt_issue_details;
    Bitmap bm;
    public String Url_To_Upload = "http://cyclicorder.site88.net/PhotoUploadWithText/upload.php";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_TEXT = "Description";
    double latS;
    double longS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        imageView = (ImageView) findViewById(R.id.imgPreview);
        btn_capture = (Button) findViewById(R.id.camerabutton);
        btn_imageToUpload = (Button) findViewById(R.id.uploadbutton);
        edt_issue_details = (EditText) findViewById(R.id.cameraedit);
        btn_capture.setOnClickListener(this);
        btn_imageToUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchUploadActivity();
                startActivity(new Intent(UploadToServer.this,MapsActivity.class));
            }
        });


    }


    private void ImageChooser() {
        final CharSequence[] items = {"Capture photo", "Select  from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(UploadToServer.this);
        builder.setTitle("Add photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (items[which].equals("Capture photo")) {

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                    //image capture intent begins
                    startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
                } else if (items[which].equals("Select  from Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent, "Select file"), MEDIA_TYPE_GALLERY);
                } else
                    dialog.dismiss();
            }
        });
        builder.show();

    }

    @Override
    public void onClick(View v) {
        ImageChooser();
    }
    /* Checking device has camera hardware or not

    private boolean isDeviceSupportCamera() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }*/

    /**
     * Dispatch incoming result to the correct fragment.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE)

            {

                //Display the captured Image
                previewCapturedImage();
            } else if (requestCode == MEDIA_TYPE_GALLERY) {
                try {
                    previewSelectedImage(data);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


        } else if (resultCode == RESULT_CANCELED) {

            Toast.makeText(this, "User Canceled the event", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "User failed to Capture the Image", Toast.LENGTH_SHORT).show();


    }

    //Display image from path to preview//
    public void previewCapturedImage() {
        imageView.setVisibility(View.VISIBLE);
        edt_issue_details.setVisibility(View.VISIBLE);
        btn_imageToUpload.setVisibility(View.VISIBLE);
        BitmapFactory.Options options = new BitmapFactory.Options();
        BitmapFactory.decodeFile(fileUri.getPath(), options);
        options.inJustDecodeBounds = true;
        final int REQUIRED_SIZE = 200;
        int scale = 1;
        while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                && options.outHeight / scale / 2 >= REQUIRED_SIZE)
            scale *= 2;
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(), options);


//        BitmapFactory.Options options=new BitmapFactory.Options();
//        //downsizing the image//
//        options.inSampleSize=8;
//        final Bitmap bitmap=BitmapFactory.decodeFile(fileUri.getPath(),options);
        imageView.setImageBitmap(bitmap);

    }

    public void previewSelectedImage(Intent data) throws IOException {
        imageView.setVisibility(View.VISIBLE);
        edt_issue_details.setVisibility(View.VISIBLE);
        btn_imageToUpload.setVisibility(View.VISIBLE);
        Uri selectedImageUri = data.getData();
        String[] projection = {MediaStore.MediaColumns.DATA};
        CursorLoader cursorLoader = new CursorLoader(this, selectedImageUri, projection, null, null,
                null);
        Cursor cursor = cursorLoader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        String selectedImagePath = cursor.getString(column_index);


        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(selectedImagePath, options);
        final int REQUIRED_SIZE = 200;
        int scale = 1;
        while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                && options.outHeight / scale / 2 >= REQUIRED_SIZE)
            scale *= 2;
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeFile(selectedImagePath, options);
        imageView.setImageBitmap(bm);

//         int rotateImage = getCameraPhotoOrientation(MainActivity.this, selectedImageUri, selectedImagePath);

    }
   /* public int getCameraPhotoOrientation(Context context, Uri imageUri, String imagePath){
        int rotate = 0;
        try {
            context.getContentResolver().notifyChange(imageUri, null);
            File imageFile = new File(imagePath);

            ExifInterface exif = new ExifInterface(imageFile.getAbsolutePath());
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
            }

            Log.i("RotateImage", "Exif orientation: " + orientation);
            Log.i("RotateImage", "Rotate value: " + rotate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rotate;
    }*/

    /*To store the file URl as it will be null after resturning from Camera app

     */
    public String getStringImage(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] bytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(bytes, Base64.DEFAULT);
        return encodedImage;

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //save file Url in the bundle as it will be null
        outState.putParcelable("file_uri", fileUri);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //get the file Url
        fileUri = savedInstanceState.getParcelable("file_uri");
    }

    /*creating file uri to store image*/
    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /*returning image
    */
    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), Storage_name);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(Storage_name, "Oops! Failed  to create "
                        + Storage_name + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd HHmmss", Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }


    public void launchUploadActivity() {
        final String text = edt_issue_details.getText().toString();
        final String image = getStringImage(bm);
        MapsActivity m=new MapsActivity();
        latS=MapsActivity.latC;
        longS=MapsActivity.longC;
        class UploadImage extends AsyncTask<Void, Void, String> {
            ProgressDialog progressDialog;
            String edit_text=edt_issue_details.getText().toString();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(UploadToServer.this, "Please Wait..", "uploading", false, false);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                pos=RowViewHolder.position;
                HashMap<String, String> hashMap = new HashMap<String, String>();
                switch(pos)
                {
                    case 0:
                        hashMap.put("position",String.valueOf(pos));
                        break;
                    case 1:
                        hashMap.put("position",String.valueOf(pos));
                        break;
                    case 2:
                        hashMap.put("position",String.valueOf(pos));
                        break;
                    case 3:
                        hashMap.put("position",String.valueOf(pos));
                        break;
                    case 4:
                        hashMap.put("position",String.valueOf(pos));
                        break;
                    case 5:
                        hashMap.put("position",String.valueOf(pos));
                        break;
                    case 6:
                        hashMap.put("position",String.valueOf(pos));
                        break;
                }
                hashMap.put("latitude",String.valueOf(latS));
                hashMap.put("longitude",String.valueOf(longS));

                hashMap.put(KEY_TEXT, edit_text);
                hashMap.put(KEY_IMAGE, image);
                String result = rh.sendPostRequest(Url_To_Upload, hashMap);

                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressDialog.dismiss();
                Log.i("positon:",String.valueOf(pos));
            }
        }
        new UploadImage().execute();

    }
}

