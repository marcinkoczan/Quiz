package net.marcinkoczan.bazatutorial;

import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.osmdroid.api.IGeoPoint;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.slf4j.LoggerFactory;

import java.util.logging.Logger;


public class wyswietlenieWyniku extends ActionBarActivity {

    TextView tv;
    String wynik;
    zarzadzanieBaza zb;
    wyniki Wynik = new wyniki();
    LocationManager lm;
    Criteria kr;
    Location loc;
    String najlepszyDostawca;
    MapView mapView;
    MapController mapController;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(wyswietlenieWyniku.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wyswietlenie_wyniku);
        tv = (TextView) findViewById(R.id.textView3);
        Intent intent = getIntent();
        wynik = intent.getStringExtra(Start.EXTRA_MESSAGE);
        tv.setText("Twój wynik to: "+wynik+" na 20 możliwych punktów!");
        final Intent powrot = new Intent(this, menu.class);
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        mapController = new MapController(mapView);


        lm=(LocationManager) getSystemService(LOCATION_SERVICE);
        kr=new Criteria();
        najlepszyDostawca=lm.getBestProvider(kr, true);
        loc=lm.getLastKnownLocation(najlepszyDostawca);
        Log.d("dlugosc i szerokosc to", loc.getLongitude() + " i " + loc.getLatitude() + " i " + najlepszyDostawca);
        double l1 = loc.getLatitude();
        double l2 = loc.getLongitude();
        GeoPoint punkt = new GeoPoint(loc.getLatitude(),loc.getLongitude());
        mapView.refreshDrawableState();
        mapView.getController().animateTo(punkt);
        mapView.getController().setZoom(10);
        mapView.invalidate();

//        String Lo = Double.toString(loc.getLongitude());
//        String La = Double.toString(loc.getLatitude());

//        Wynik.setWynik(wynik);
//        Wynik.setLoc1(Lo); Log.d("LO=", Wynik.getLoc1());
//        Wynik.setLoc2(La); Log.d("LA=", Wynik.getLoc2());
//        zb.dodajWynik(Wynik);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() { @Override public void run(){

            powrot.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(powrot);
        }},15000);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_wyswietlenie_wyniku, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
