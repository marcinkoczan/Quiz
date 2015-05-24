package net.marcinkoczan.bazatutorial;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class dodawanie_pytan extends ActionBarActivity {

    pytania NowePytanko;
    EditText odp1;
    EditText odp2;
    EditText odp3;
    EditText odp4;
    EditText pytanie;
    zarzadzanieBaza zb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodawanie_pytan);
        odp1 = (EditText) findViewById(R.id.editText3);
        odp2 = (EditText) findViewById(R.id.editText);
        odp3 = (EditText) findViewById(R.id.editText4);
        odp4 = (EditText) findViewById(R.id.editText5);
        pytanie = (EditText) findViewById(R.id.editText2);
        zb = new zarzadzanieBaza(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dodawanie_pytan, menu);
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

    public void dodaniepytania(View view)
    {
        NowePytanko = new pytania();
        NowePytanko.setPytanie(pytanie.getText().toString());
        NowePytanko.setOdpowiedz1(odp1.getText().toString());
        NowePytanko.setOdpowiedz2(odp2.getText().toString());
        NowePytanko.setOdpowiedz3(odp3.getText().toString());
        NowePytanko.setOdpowiedz4(odp4.getText().toString());
        NowePytanko.setDobra_odpowiedz(odp4.getText().toString());
        Log.d(NowePytanko.getPytanie(),NowePytanko.getOdpowiedz1()+NowePytanko.getOdpowiedz2()+ NowePytanko.getOdpowiedz3()+ NowePytanko.getOdpowiedz4()+ NowePytanko.getDobra_odpowiedz());
        zb.dodajPytanie(NowePytanko);
        Log.d("dodano nowe", " pytanie!");


//        final Intent powrot = new Intent(this, menu.class);
//        powrot.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(powrot);


    }
}
