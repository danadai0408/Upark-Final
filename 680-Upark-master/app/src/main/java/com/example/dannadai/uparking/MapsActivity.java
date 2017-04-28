package com.example.dannadai.uparking;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final LatLng BENTLEY = new LatLng(42.3889167, -71.2208033);
    private static final LatLng p = new LatLng(42.384802, -71.218219);
    private static final LatLng p1 = new LatLng(42.386621, -71.225729);
    private static final LatLng p2 = new LatLng(42.366146, -71.228616);

    private static final float zoom = 14.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p, zoom));

        mMap.addMarker(new MarkerOptions().position(p1));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p1, zoom));

        mMap.addMarker(new MarkerOptions().position(p2));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p2, zoom));
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


}
