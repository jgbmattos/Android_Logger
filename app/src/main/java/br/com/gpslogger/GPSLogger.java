package br.com.gpslogger;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class GPSLogger extends Service {

    public LocationManager locationManager;
    public MyLocationListener locationListener;

    public GPSLogger()
    {

    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, R.string.gps_logger_stoped, Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getApplicationContext(), "Iniciando serviço", Toast.LENGTH_SHORT ).show();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new MyLocationListener();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(getApplicationContext(), "Não tem permissão", Toast.LENGTH_SHORT ).show();
            return Service.START_STICKY;
        }
        //Vai buscar de 10 em 10 segundos se a diferença for de 10 metros
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 50, locationListener);
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    public class MyLocationListener implements LocationListener
    {

        public void onLocationChanged(final Location loc)
        {
                new UpdateThingSpeakTask(Double.toString(loc.getLatitude()), Double.toString(loc.getLongitude())).execute();
        }


        public void onProviderDisabled(String provider)
        {
            Toast.makeText( getApplicationContext(), "Gps Disabled", Toast.LENGTH_SHORT ).show();
        }


        public void onProviderEnabled(String provider)
        {
            Toast.makeText( getApplicationContext(), "Gps Enabled", Toast.LENGTH_SHORT).show();
        }


        public void onStatusChanged(String provider, int status, Bundle extras)
        {

        }

    }
}
