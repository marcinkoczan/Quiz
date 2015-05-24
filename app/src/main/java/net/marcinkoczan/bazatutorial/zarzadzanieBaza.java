package net.marcinkoczan.bazatutorial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zwyklyuser on 2015-04-18.
 */
public class zarzadzanieBaza extends SQLiteOpenHelper{

    public zarzadzanieBaza (Context context)
    {
        super(context, "pytania.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL( "create table pytania(" + "id integer primary key autoincrement," + "pytanie text," + "odpowiedz1 text," + "odpowiedz2 text," + "odpowiedz3 text," + "odpowiedz4 text," + "dobra_odpowiedz text);" + "");
    }

    public void utworz()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL( "create table pytania(" + "id integer primary key autoincrement," + "pytanie text," + "odpowiedz1 text," + "odpowiedz2 text," + "odpowiedz3 text," + "odpowiedz4 text," + "dobra_odpowiedz text);" + "");
    }

    public void utworzTabWyniki()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL( "create table wyniki(" + "id integer primary key autoincrement," + "wynik text," + "loc1 text," + "loc2 text);" + "");
        Log.d("Utworzono tabelę","wyniki");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public void dodajPytanie(pytania pytania)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues wartosci = new ContentValues();
        wartosci.put("pytanie", pytania.getPytanie());
        wartosci.put("odpowiedz1", pytania.getOdpowiedz1());
        wartosci.put("odpowiedz2", pytania.getOdpowiedz2());
        wartosci.put("odpowiedz3", pytania.getOdpowiedz3());
        wartosci.put("odpowiedz4", pytania.getOdpowiedz4());
        wartosci.put("dobra_odpowiedz", pytania.getDobra_odpowiedz());
        db.insertOrThrow("pytania", null, wartosci);
    }

    public void dodajWynik(wyniki wyniki)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues wartosci = new ContentValues();
        wartosci.put("wynik", wyniki.getWynik());
        wartosci.put("loc1", wyniki.getLoc1());
        wartosci.put("loc2", wyniki.getLoc2());
        db.insertOrThrow("wyniki", null, wartosci);
    }

    public List<pytania> dajWszystkie()
    {
        List<pytania> pytaniaList = new LinkedList<pytania>();
        String[] kolumny={"id","pytanie","odpowiedz1","odpowiedz2", "odpowiedz3","odpowiedz4", "dobra_odpowiedz"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor kursor = db.query("pytania",kolumny,null,null,null,null,null);
        while(kursor.moveToNext())
        {
            pytania pyt = new pytania();
            pyt.setId(kursor.getLong(0));
            pyt.setPytanie(kursor.getString(1));
            pyt.setOdpowiedz1(kursor.getString(2));
            pyt.setOdpowiedz2(kursor.getString(3));
            pyt.setOdpowiedz3(kursor.getString(4));
            pyt.setOdpowiedz4(kursor.getString(5));
            pyt.setDobra_odpowiedz(kursor.getString(6));
            pytaniaList.add(pyt);
        }
        return pytaniaList;
    }

    public List<pytania> losujPytania()
    {
        List<pytania> pytaniaList = new LinkedList<pytania>();
        List<pytania> pytanka = new LinkedList<pytania>();
        String[] kolumny={"id","pytanie","odpowiedz1","odpowiedz2", "odpowiedz3","odpowiedz4", "dobra_odpowiedz"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor kursor = db.query("pytania",kolumny,null,null,null,null,null);
        while(kursor.moveToNext())
        {
            pytania pyt = new pytania();
            pyt.setId(kursor.getLong(0));
            pyt.setPytanie(kursor.getString(1));
            pyt.setOdpowiedz1(kursor.getString(2));
            pyt.setOdpowiedz2(kursor.getString(3));
            pyt.setOdpowiedz3(kursor.getString(4));
            pyt.setOdpowiedz4(kursor.getString(5));
            pyt.setDobra_odpowiedz(kursor.getString(6));
            pytaniaList.add(pyt);
        }
        Log.d("",Integer.toString(pytaniaList.size()));
        Collections.shuffle(pytaniaList);
        Collections.shuffle(pytaniaList);
        Log.d("",Integer.toString(pytaniaList.size()));
        for(int i=0;i<=4;i++)
        {
            pytanka.add(pytaniaList.get(i));
        }

        return pytanka;
    }

    public pytania dajPytanie(int id)
    {
        pytania pytania = new pytania();
            SQLiteDatabase db = getReadableDatabase();
            String[] kolumny={"id","pytanie","odpowiedz1","odpowiedz2","odpowiedz3","odpowiedz4","dobra_odpowiedz"};
            String args[]={id+""};
            Cursor kursor=db.query("pytania",kolumny,"id=?",args,null,null,null,null);
            if(kursor!=null)
            {
                kursor.moveToFirst();
                pytania.setId(kursor.getLong(0));
                pytania.setPytanie(kursor.getString(1));
                pytania.setOdpowiedz1(kursor.getString(2));
                pytania.setOdpowiedz2(kursor.getString(3));
                pytania.setOdpowiedz3(kursor.getString(4));
                pytania.setOdpowiedz4(kursor.getString(5));
                pytania.setDobra_odpowiedz(kursor.getString(6));
            }
        return pytania;
    }

    public void usunBaze()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("drop table pytania");
    }


}
