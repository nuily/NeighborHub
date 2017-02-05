package nyc.c4q.huilin.neighborhoodhub;

import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import java.util.concurrent.TimeUnit;

import nyc.c4q.huilin.neighborhoodhub.utils.Constants;

public class LocationFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener,
        ResultCallback<LocationSettingsResult> {
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 204;
    private final String TAG = getClass().getSimpleName();

    private GoogleApiClient googleApiClient;
    private Location lastLocation;
    private LocationRequest locationRequest;
    private LocationSettingsRequest locationSettingsRequest;
    private TextView latitudeTV;
    private View view;
    private TextView longitudeTV;

    public LocationFragment() {
    }

    public static LocationFragment newInstance() {
        LocationFragment fragment = new LocationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createGoogleAPIClient();
        createLocationRequest();
        createLocationSettingsRequest();
    }

    private void checkLocationSettings() {
        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(
                        googleApiClient,
                        locationSettingsRequest
                );
        result.setResultCallback(this);
    }

    private void createLocationRequest() {
        locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
                .setInterval(TimeUnit.MINUTES.toMinutes(1))
                .setFastestInterval(TimeUnit.MINUTES.toMinutes(60));
    }


    private void createLocationSettingsRequest() {
        locationSettingsRequest = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)
                .setAlwaysShow(true)
                .build();
    }

    private void createGoogleAPIClient() {
        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(getContext())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_location, container, false);
        latitudeTV = (TextView) view.findViewById(R.id.tv_latitude);
        longitudeTV = (TextView) view.findViewById(R.id.tv_longitude);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        googleApiClient.connect();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (googleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
            googleApiClient.disconnect();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle connectionHint) {
        checkLocationSettings();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        googleApiClient.connect();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Connection suspended");
        googleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i(TAG, "Connection failed");
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged: " + location.toString());
    }

    @Override
    public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
        final Status status = locationSettingsResult.getStatus();
        switch (status.getStatusCode()) {
            case LocationSettingsStatusCodes.SUCCESS:
                if (ContextCompat.checkSelfPermission(getContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(getActivity(), new String[]{
                            android.Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
                }
                LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
                lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

                if (lastLocation != null) {
                    latitudeTV.setText("Latitude: " + lastLocation.getLatitude());
                    longitudeTV.setText("Longitude: " + lastLocation.getLongitude());


                } else {
                    Log.d(TAG, "onResult: null object");
                }
                break;
            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                try {
                    status.startResolutionForResult(getActivity(), Constants.REQUEST_CHECK_SETTINGS);
                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                }
                break;
            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        checkLocationSettings();
    }
}
