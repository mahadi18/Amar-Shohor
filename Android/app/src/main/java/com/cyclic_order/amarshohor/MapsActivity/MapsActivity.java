package com.cyclic_order.amarshohor.MapsActivity;

import com.cyclic_order.amarshohor.SlidingActivity.About;
import com.cyclic_order.amarshohor.Data_Retrieving.Demo;
import com.cyclic_order.amarshohor.model.ItemSlideMenu;
import com.cyclic_order.amarshohor.ListItemActivity.ListActivity;
import com.cyclic_order.amarshohor.R;
import com.cyclic_order.amarshohor.RegistrationActivity.Activity_login;
import com.cyclic_order.amarshohor.SlidingMenu.adapter.SlidingMenuAdapter;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
        import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
        import com.google.android.gms.maps.GoogleMap;
        import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends ActionBarActivity implements GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    public static double latitude,longitude;
    LocationManager locationManager;
    Location mCurrentLocation;
    String locationProvider;
    public Marker marker;
    GetMyLocation myLocation;

    //Varialbe declaration for Sliding Menu
    private List<ItemSlideMenu> listSliding;
    private SlidingMenuAdapter adapter;
    private ListView listViewSliding;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
   public   static double latC;
    public   static double longC;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        myLocation=new GetMyLocation(this);
        setUpMapIfNeeded();
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Button zoomInButton = (Button) findViewById(R.id.zoomIn);
                Button zoomOutButton = (Button) findViewById(R.id.zoomOut);

                if (zoomInButton.getVisibility() == View.VISIBLE) {
                    zoomInButton.setVisibility(View.GONE);
                    zoomOutButton.setVisibility(View.GONE);
                } else {
                    zoomInButton.setVisibility(View.VISIBLE);
                    zoomOutButton.setVisibility(View.VISIBLE);
                }


            }
        });


        //Action for Sliding Menu

        listViewSliding = (ListView) findViewById(R.id.list_slidermenu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        listSliding = new ArrayList<>();

        listSliding.add(new ItemSlideMenu(R.drawable.ic_home, "Home"));
//       listSliding.add(new ItemSlideMenu(R.drawable.satellite, "Satellite View"));
//        listSliding.add(new ItemSlideMenu(R.drawable.terrain, "Terrain View"));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_settings, "List"));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_about, "About"));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_logout, "Logout"));
        adapter = new SlidingMenuAdapter(this,listSliding);

        listViewSliding.setAdapter(adapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*setTitle(listSliding.get(0).getTitle());
        listViewSliding.setItemChecked(0, true);
        drawerLayout.closeDrawer(listViewSliding);
        replaceFragment(0);
        message("Selected " + 0);*/
        setTitle(listSliding.get(0).getTitle());
        listViewSliding.setItemChecked(0, true);
        drawerLayout.closeDrawer(listViewSliding);
        replaceFragment(0);
        
        listViewSliding.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //message("Selected " + i);
//                setTitle(listSliding.get(i).getTitle());
//                listViewSliding.setItemChecked(i, true);
                setTitle(listSliding.get(i).getTitle());
               listViewSliding.setItemChecked(i, true);


                replaceFragment(i);

                //setContentView(findViewById(i));

                drawerLayout.closeDrawer(listViewSliding);


            }
        });

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.drawer_open,R.string.drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);

    }

    public void onSearch(View view)
    {
        EditText editText = (EditText) findViewById(R.id.search_box);
        String location = editText.getText().toString();

        if (location!=null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);

            List<Address> addressList = null;

            try {
                addressList = geocoder.getFromLocationName(location, 1);
            } catch (IOException e) {
                e.printStackTrace();


            }
            if (addressList == null || addressList.size() == 0) {
                Toast.makeText(this,"Sorry,the requested location is not available",Toast.LENGTH_LONG).show();
            }
            else {
                Address address = addressList.get(0);
                latitude = address.getLatitude();
                longitude = address.getLongitude();
                LatLng latLng = new LatLng(latitude, longitude);
                mMap.addMarker(new MarkerOptions().position(latLng));
                mMap.setMyLocationEnabled(true);
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            }
        }
        else  {
            Toast.makeText(MapsActivity.this,"Insert a place please",Toast.LENGTH_LONG).show();

        }

        // mMap.getMaxZoomLevel();


    }

    public static double getLatitude()
    {
        return latitude;
    }

    public static double getLongitude()
    {
        return longitude;
    }

    //For changing map type.Custom Changing//

//    public void changeMapType(int type) {
//        switch (type) {
//
//            case 1:
//                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//                break;
//            case 2:
//                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
//                break;
//
//        }
//    }

    public void ZoomMap(View view)
    {
        if(view.getId() == R.id.zoomIn)
        {
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
        }
        if (view.getId() == R.id.zoomOut)
            mMap.animateCamera(CameraUpdateFactory.zoomOut());

    }
    public void onChangeType(View view) {
        if (mMap.getMapType() == GoogleMap.MAP_TYPE_TERRAIN) {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        } else
            mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
    }


    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
//        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//        Criteria criteria = new Criteria();
//        locationProvider = locationManager.getBestProvider(criteria, true);
//        Toast.makeText(this,locationProvider+"",Toast.LENGTH_LONG).show();
//
//        mCurrentLocation = locationManager.getLastKnownLocation(locationProvider);
        if (myLocation.canGetLocation()) {
            mCurrentLocation = myLocation.getLocationValue();
        }
        mMap.setOnMarkerClickListener(this);
        if(marker!=null)
            marker.remove();
        latC=mCurrentLocation.getLatitude();
        longC=mCurrentLocation.getLongitude();

        marker = mMap.addMarker(new MarkerOptions().position(new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude())).draggable(true).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude())), 3000,null);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude()), 14f));
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setAllGesturesEnabled(true);
        mMap.setMyLocationEnabled(true);




    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("LOCATION_KEY",mCurrentLocation);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mCurrentLocation=savedInstanceState.getParcelable("LOCATION_KEY");
    }

    /*
            *   adding of Sliding Menu in Home page
            */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    //show some thing as message
    private void message(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    private void replaceFragment(int pos)
    {
            Fragment fragment=null;
        switch (pos){
            case 0:
               // startActivity(new Intent(MapsActivity.this,MapsActivity.class));
                break;
            case 1:
               //changeMapType(0);
                //func(view);
                startActivity(new Intent(MapsActivity.this,Demo.class));

                break;
            case 2:
                //changeMapType(1);
                startActivity(new Intent(MapsActivity.this,About.class));
                break;

//            case 3:
//                //fragment=new AboutFragment();
//
//            case 4:
//
//                break;
            case 4:
                //logout();
                SharedPreferences sharedpreferences = getSharedPreferences("Activity_login.MyPREFERENCES", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.commit();
               startActivity(new Intent(MapsActivity.this, Activity_login.class));
                finish();
                break;

            default:
                //fragment = new Fragment();
                break;
        }


        if(null!=fragment)
        {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.map, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
//    public void logout(View v)
//    {
//        SharedPreferences sharedpreferences = getSharedPreferences("MapsActivity.MYPREFERENCES", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedpreferences.edit();
//        editor.clear();
//        editor.commit();    }
    public boolean onMarkerClick(final Marker marker) {

        //Toast.makeText(this,marker.getTitle(), Toast.LENGTH_LONG).show();
        if (marker.equals(marker)) {
            startActivity(new Intent(MapsActivity.this, ListActivity.class));
        }
        return true;
    }





}




