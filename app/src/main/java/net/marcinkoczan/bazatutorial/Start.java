package net.marcinkoczan.bazatutorial;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class Start extends ActionBarActivity {

    String pytanie;
    String odpowiedz1;
    String odpowiedz2;
    String odpowiedz3;
    String odpowiedz4;
    String dobra_odpowiedz;
    TextView tv;
    Button odp1;
    Button odp2;
    Button odp3;
    Button odp4;
    TextView tv2;
    int index;
    zarzadzanieBaza zb;
    int wynik;
    int wynikZaPytanie;
    public final static String EXTRA_MESSAGE = "net.koczan.bazatutorial.MESSAGE";
    Random generator = new Random();
    List<pytania> lista = new LinkedList<pytania>();
    ProgressBar pd;
    View view;
    int procent;
    CountDownTimer cdt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        tv = (TextView) findViewById(R.id.textView);
        odp1 = (Button) findViewById(R.id.button);
        odp2 = (Button) findViewById(R.id.button2);
        odp3 = (Button) findViewById(R.id.button3);
        odp4 = (Button) findViewById(R.id.button4);
        tv2 = (TextView) findViewById(R.id.textView2);
        zb = new zarzadzanieBaza(this);
        index = 0;
        wynik = 0;
        wynikZaPytanie = 0;
        lista = zb.losujPytania();
        Collections.shuffle(lista);
        pd =(ProgressBar) findViewById(R.id.progressBar) ;
//        zb.utworz();
        gra();

    }

    public void gra()
    {
//        pytania nowePytanie = new pytania();
//        nowePytanie.setPytanie("W którym roku miała miejsce Bitwa pod Grunwaldem?");
//        nowePytanie.setOdpowiedz1("2010");
//        nowePytanie.setOdpowiedz2("2012");
//        nowePytanie.setOdpowiedz3("1430");
//        nowePytanie.setOdpowiedz4("1410");
//        nowePytanie.setDobra_odpowiedz("1410");
//        zb.dodajPytanie(nowePytanie);
//        nowePytanie.setPytanie("Adam małysz to najlepszy polski:");
//        nowePytanie.setOdpowiedz1("skoczek narciarski");
//        nowePytanie.setOdpowiedz2("pływak");
//        nowePytanie.setOdpowiedz3("biegacz");
//        nowePytanie.setOdpowiedz4("śpiewak");
//        nowePytanie.setDobra_odpowiedz("skoczek narciarski");
//        zb.dodajPytanie(nowePytanie);
//        nowePytanie.setPytanie("Co dziadkowie dostają od wnuków?");
//        nowePytanie.setOdpowiedz1("launkę");
//        nowePytanie.setOdpowiedz2("kopniaka");
//        nowePytanie.setOdpowiedz3("laurkę");
//        nowePytanie.setOdpowiedz4("lanurkę");
//        nowePytanie.setDobra_odpowiedz("laurkę");
//        zb.dodajPytanie(nowePytanie);
//        nowePytanie.setPytanie("Rumak to:");
//        nowePytanie.setOdpowiedz1("pies");
//        nowePytanie.setOdpowiedz2("kot");
//        nowePytanie.setOdpowiedz3("koń");
//        nowePytanie.setOdpowiedz4("jeż");
//        nowePytanie.setDobra_odpowiedz("koń");
//        zb.dodajPytanie(nowePytanie);
//        nowePytanie.setPytanie("Jak nazywała się kreskówkowa pszczółka?");
//        nowePytanie.setOdpowiedz1("Marzenka");
//        nowePytanie.setOdpowiedz2("Asia");
//        nowePytanie.setOdpowiedz3("Anetka");
//        nowePytanie.setOdpowiedz4("Maja");
//        nowePytanie.setDobra_odpowiedz("Maja");
//        zb.dodajPytanie(nowePytanie);

//        zb.dodajPytanie("W którym roku miała miejsce Bitwa pod Grunwaldem?", "2010", "2012","1430","1410","1410");
//        zb.dodajPytanie("Adam małysz to najlepszy polski:", "skoczek narciarski", "pływak","biegacz","śpiewak","skoczek narciarski");
//        zb.dodajPytanie("Co dziadkowie dostają od wnuków?", "launkę", "kopniaka","laurkę","lanurkę","laurkę");
//        zb.dodajPytanie("Rumak to:", "pies", "kot","koń","jeż","koń");
//        zb.dodajPytanie("Jak nazywała się kreskówkowa pszczółka?", "Marzenka", "Asia","Anetka","Maja","Maja");

        //Iterator<pytania> iterator = lista.iterator();
//        pd = new ProgressDialog(view.getContext());
//        pd.setMax(100);
//        pd.setProgress(100);
//        pd.show();

        //pd = new ProgressDialog();

        pd.setMax(100);
        pd.setProgress(100);
        procent=100;

        if (index <= 4)

        {
            pytania p = lista.get(index);
            wynikZaPytanie = 4;
            tv.setText(p.getPytanie());
            odp1.setText(p.getOdpowiedz1());
            odp2.setText(p.getOdpowiedz2());
            odp3.setText(p.getOdpowiedz3());
            odp4.setText(p.getOdpowiedz4());
            dobra_odpowiedz = p.getDobra_odpowiedz();
            odp1.setBackgroundColor(Color.LTGRAY);
            odp2.setBackgroundColor(Color.LTGRAY);
            odp3.setBackgroundColor(Color.LTGRAY);
            odp4.setBackgroundColor(Color.LTGRAY);
        } else

        {
            cdt.cancel();
            Intent intent = new Intent(this, wyswietlenieWyniku.class);
            intent.putExtra(EXTRA_MESSAGE, Integer.toString(wynik));
            startActivity(intent);
        }
        cdt = new CountDownTimer(15000,1000) {
            public void onTick(long milisunitilfinished) {
                procent=procent-6; pd.setProgress(procent);
            } public void onFinish(){ if(index<=4) {index=index+1; gra();} else cancel();  };
        }.start();



    }



    public void odpowiedz (String text, Button przycisk)
    {
        if (text.equals(dobra_odpowiedz))
        {
            Toast.makeText(getApplicationContext(),"Brawo! Poprawna odpowiedź!", Toast.LENGTH_SHORT).show();
            przycisk.setBackgroundColor(Color.GREEN);
            wynik=wynik+wynikZaPytanie;
            index=index+1;
            cdt.cancel();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() { @Override public void run(){gra();}},3000);

        }
        else
        {
            Toast.makeText(getApplicationContext(), "Błąd! Spróbuj ponownie!", Toast.LENGTH_SHORT).show();
            przycisk.setBackgroundColor(Color.RED);
            if (wynikZaPytanie>0)
            {
                wynikZaPytanie=wynikZaPytanie-1;
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
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

    public void onClick1(View view)
    {
        odpowiedz((String)odp1.getText(),odp1);
    }
    public void onClick2(View view)
    {
        odpowiedz((String)odp2.getText(),odp2);
    }
    public void onClick3(View view)
    {
        odpowiedz((String)odp3.getText(),odp3);
    }
    public void onClick4(View view)
    {
        odpowiedz((String)odp4.getText(),odp4);
    }




}
