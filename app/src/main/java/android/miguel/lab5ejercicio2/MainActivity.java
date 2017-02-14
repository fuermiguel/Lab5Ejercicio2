package android.miguel.lab5ejercicio2;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity implements LocationListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LocationManager gestorLoc = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        gestorLoc.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 1, this);
    }
    @Override
    public void onLocationChanged(Location location) {
        String text ="Posición actual:\n" +
                "Latitud = " + new BigDecimal(location.getLatitude()).setScale(4, RoundingMode.HALF_UP) + "\n" +
                "Longitud = " + new BigDecimal(location.getLongitude()).setScale(4, RoundingMode.HALF_UP) ;
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        String miestado =  "";
        switch (status){
            case LocationProvider.OUT_OF_SERVICE:
                miestado  ="Estado del GPS: Fuera de servicio";
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                miestado ="Estado del GPS: Temporalmente no disponible";
                break;
            case LocationProvider.AVAILABLE:
                miestado = "Estado del GPS: Disponible";
                break;
        }
        Toast.makeText(getApplicationContext(),miestado,Toast.LENGTH_LONG).show();
    }
    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(getApplicationContext(), "GPS activado por el usuario",
                Toast.LENGTH_LONG).show();
    }
    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(getApplicationContext(), "GPS desactivado por el usuario",
                Toast.LENGTH_LONG).show();
    }
}