package com.example.dannadai.uparking;

import android.Manifest;
import android.app.TabActivity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
<<<<<<< Updated upstream
import android.location.Address;
import android.location.Geocoder;
=======
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
>>>>>>> Stashed changes
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
<<<<<<< Updated upstream
import android.widget.EditText;
=======
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
>>>>>>> Stashed changes

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
<<<<<<< Updated upstream
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
=======
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
>>>>>>> Stashed changes

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    SQLiteOpenHelper dbhelper2;
    Cursor cursor;
    private static final LatLng BENTLEY = new LatLng(42.3889167, -71.2208033);
    private static final LatLng p = new LatLng(42.384802, -71.218219);
    private static final LatLng p1 = new LatLng(42.386621, -71.225729);
    private static final LatLng p2 = new LatLng(42.366146, -71.228616);
    public String address;
    Button search = (Button) findViewById(R.id.searchButton);
    EditText searchET = (EditText) findViewById(R.id.searchET);
    private static final float zoom = 14.0f;
    public static Context context;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    private List<Marker> m;
    float d = 10000;
    String minAddress;
    String stringLatitude;//current
    String stringLongitude;//current
    String strl1;//dest
    String strl2;//dest

    public MapsActivity() {
        if (m == null) {
            m = new ArrayList<Marker>();
        }
    }
    //get location from address
    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

<<<<<<< Updated upstream
    //set onclick to search button
    public void onSearch(View view) {

        EditText location_tf = (EditText) findViewById(R.id.searchET);
        String location = location_tf.getText().toString();
        List<Address> addressList = null;
        if (location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);

            } catch (IOException e) {
                e.printStackTrace();
            }

            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            //mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

        }
=======
            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (IOException ex) {

            ex.printStackTrace();
        }

        return p1;
>>>>>>> Stashed changes
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.addMarker(new MarkerOptions().position(BENTLEY).title("Bentley University").snippet("175 Forest Street"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(BENTLEY, zoom));

        mMap.addMarker(new MarkerOptions().position(p).title(" ").snippet(" "));

        mMap.addMarker(new MarkerOptions().position(p1));

        mMap.addMarker(new MarkerOptions().position(p2));
    }
    //Add option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Uri uri;
        // Handle item selection
        switch (item.getItemId()) {

            case R.id.profile:
                Intent i1 = new Intent(this, profile.class);
                startActivity(i1);
                return true;

            case R.id.feedback:
                Uri uri2 = Uri.parse("mailto:customerservice@gmail.com");
                Intent i2 = new Intent(Intent.ACTION_SENDTO, uri2);
                startActivity(i2);

                return true;

            case R.id.close:

                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }


    ///get address from LatLng
    private String GetAddress(Double lat, Double lon) {
        Geocoder geocoder = new Geocoder(context, Locale.ENGLISH);
        String ret = "";
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(lat, lon, 1);
            if (!addresses.equals(null)) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("\n");
                for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress
                            .append(returnedAddress.getAddressLine(i)).append(
                            "\n");
                }
                ret = "Around: " + strReturnedAddress.toString();
            } else {
                ret = "No Address returned!";
            }
        } catch (IOException e) {
            e.printStackTrace();
            ret = "Location: https://maps.google.co.in/maps?hl=en&q=" + lat
                    + "," + lon;
        } catch (NullPointerException e) {
            e.printStackTrace();
            ret = lat + "," + lon;
        }
        return ret;
    }
    //finding distance between two geo points.
    public static float distFrom (float lat1, float lng1, float lat2, float lng2 )
    {
        double earthRadius = 3958.75;
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = earthRadius * c;

        int meterConversion = 1609;

        return new Float(dist * meterConversion).floatValue();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map1);
        mapFragment.getMapAsync(this);

        TabHost tabs = (TabHost) findViewById(R.id.tabhost);
        tabs.setup();
        TabHost.TabSpec spec;
//Insert some base data
        db = dbhelper2.getWritableDatabase();

        //Calling InsertData Method - Defined below
        InsertData("175 Forest St, Waltham, MA 02452", "Henry bentley", "781- 891-2000");
        InsertData("200 Trapelo Rd, Waltham, MA 02452", "Jane Smith", "617-982-4253");
        InsertData("20 Middlesex Cir, Waltham, MA 02452", "Mike Jones", "781-894-4230");
        InsertData("5 Upton Road, Waltham, MA", "Mary Jane", "617-992-4433");
        //**search tab activity
        // Initialize a TabSpec for tab1 and add it to the TabHost
        spec = tabs.newTabSpec("tag1");    //create new tab specification
        spec.setContent(R.id.SearchHere);    //add tab view content
        spec.setIndicator("Search Here");    //put text on tab
        tabs.addTab(spec);             //put tab in TabHost container
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        //TODO: show the spot with marker on map

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mMap.setMyLocationEnabled(true);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onStatusChanged(String provider, int status,
                                        Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }

            @Override
            public void onLocationChanged(Location location) {
                Double l1 = location.getLatitude();
                Double l2 = location.getLongitude();
                address = GetAddress(l1, l2);
            }
        };
        GPSTracker gpsTracker = new GPSTracker(this);

        if (gpsTracker.getIsGPSTrackingEnabled()) {
            stringLatitude = String.valueOf(gpsTracker.latitude);
            stringLongitude = String.valueOf(gpsTracker.longitude);
        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            return;
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

//When click search, Google Maps will show the nearest parking spot to the user.
        search.setText(address);
        final String stradd = searchET.getText().toString();
        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LatLng latLng = getLocationFromAddress(context, stradd);
                float lat = (float) latLng.latitude;
                float lng = (float) latLng.longitude;
                //TODO
                cursor = db.rawQuery("SELECT *FROM " + SQLiteDBHelper2.TABLE_NAME + " WHERE " + SQLiteDBHelper2.COLUMN_SID + "!= null", null);
                if (cursor != null) {
                    while (cursor.getCount() > 0) {
                        //Retrieving User FullName and Email after successfull login and passing to UserArea
                        String add = cursor.getString(cursor.getColumnIndex(SQLiteDBHelper2.COLUMN_ADD));
                        LatLng latLng1 = getLocationFromAddress(context, add);
                        float lat1 = (float) latLng1.latitude;
                        float lng1 = (float) latLng1.longitude;
                        if (d > distFrom(lat, lng, lat1, lng1)) {
                            d = distFrom(lat, lng, lat1, lng1);
                            strl1 = String.valueOf(lat1);
                            strl2 = String.valueOf(lng1);
                            minAddress = add;
                        }
                        cursor.moveToNext();
                    }
                }
                LatLng minaddress = getLocationFromAddress(context, minAddress);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(minaddress, zoom));
            }
        });

//**SpaceInfo Tab Activity
        //-------------------------------------------------------------------------------------

        // Initialize a TabSpec for tab2 and add it to the TabHost
        spec = tabs.newTabSpec("tag2");        //create new tab specification
        spec.setContent(R.id.SpaceInfo);            //add view tab content
        spec.setIndicator("Space Info");
        tabs.addTab(spec);                    //put tab in TabHost container

        final EditText add = (EditText) findViewById(R.id.editText);
        final EditText contact = (EditText) findViewById(R.id.editText2);
        final EditText phone = (EditText) findViewById(R.id.editText3);
        Button map = (Button) findViewById(R.id.showonmap);
        Button dial = (Button) findViewById(R.id.dial);
        //Opening SQLite Pipeline
        dbhelper2 = new SQLiteDBHelper2(this);
        db = dbhelper2.getReadableDatabase();

        cursor = db.rawQuery("SELECT *FROM " + SQLiteDBHelper2.TABLE_NAME + " WHERE " + SQLiteDBHelper2.COLUMN_ADD + "=? ", new String[]{minAddress});
        if (cursor != null) {
            if (cursor.getCount() > 0) {

                cursor.moveToFirst();
                //Retrieving User FullName and Email after successfull login and passing to UserArea
                String contactname = cursor.getString(cursor.getColumnIndex(SQLiteDBHelper2.COLUMN_CONTACTNAME));
                final String contactphone = cursor.getString(cursor.getColumnIndex(SQLiteDBHelper2.COLUMN_CONTACTPHONE));
                contact.setText(contactname);
                phone.setText(contactphone);
                add.setText(minAddress);//set the nearest spot here

                map.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //TODO: goto Google Maps, set goal to the marker's location
                        String uri = "http://maps.google.com/maps?saddr=" + stringLatitude + "," + stringLongitude + "&daddr=" + strl1 + "," + strl2;
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                        intent.setPackage("com.google.android.apps.maps");
                        startActivity(intent);
                    }
                });
                dial.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:" + Uri.encode(contactphone.trim())));
                        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(callIntent);
                    }
                });
            } else {

                //I am showing Alert Dialog Box here for system errors
                final AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
                builder.setTitle("Alert");
                builder.setMessage("No such spot");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
                //-------Alert Dialog Code Snippet End Here
            }
        }

//**Post Spots Tab
        //-------------------------------------------------------------------------------------

        // Initialize a TabSpec for tab3 and add it to the TabHost
        spec = tabs.newTabSpec("tag3");        //create new tab specification
        spec.setContent(R.id.sharespace);            //add tab view content
        spec.setIndicator("Share Space");            //put text on tab
        tabs.addTab(spec);                    //put tab in TabHost container

        //TODO: add some default parking spots
        openHelper = new SQLiteDBHelper2(this);

        //Referencing EditText widgets and Button placed inside in xml layout file
        final EditText locationDisplay = (EditText) findViewById(R.id.locationDisplay);
        final EditText contactName = (EditText) findViewById(R.id.contactName);
        final EditText phoneDisplay = (EditText) findViewById(R.id.phoneDisplay);
        Button save = (Button) findViewById(R.id.button);

        //Register Button Click Event
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db = openHelper.getWritableDatabase();

                String location = locationDisplay.getText().toString();
                String contact = contactName.getText().toString();
                String phone = phoneDisplay.getText().toString();

                //Calling InsertData Method - Defined below
                InsertData(location, contact, phone);

                //Alert dialog after clicking the Register Account
                final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MapsActivity.this);
                builder.setTitle("Information");
                builder.setMessage("Your post has been saved.");
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //Finishing the dialog and removing Activity from stack.
                        dialogInterface.dismiss();
                        finish();
                    }
                });

                android.app.AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

    }

    //Inserting Data into database - Like INSERT INTO QUERY.
    public void InsertData(String location, String contact, String phone) {

        ContentValues values = new ContentValues();
        values.put(SQLiteDBHelper2.COLUMN_ADD, location);
        values.put(SQLiteDBHelper2.COLUMN_CONTACTNAME, contact);
        values.put(SQLiteDBHelper2.COLUMN_CONTACTPHONE, phone);
        LatLng ll = getLocationFromAddress(context, location);
        mMap.addMarker(new MarkerOptions().position(ll).title("Pick Me!").snippet(location));
        Log.d("Spot", location + " added");
    }
}
