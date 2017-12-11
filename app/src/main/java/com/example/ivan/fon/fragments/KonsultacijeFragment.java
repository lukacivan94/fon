package com.example.ivan.fon.fragments;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ivan.fon.classes.ListKonsDataAdapter;
import com.example.ivan.fon.dataProviders.DataProvider;
import com.example.ivan.fon.classes.KonsDBHelper;
import com.example.ivan.fon.R;
import com.example.ivan.fon.dataProviders.DataProviderKons;

public class KonsultacijeFragment extends Fragment {

    EditText Ime, Termin, Kabinet;
    Button SacuvajKons, PrikaziKons;
    Context context;
    Cursor cursor;
    KonsDBHelper konsDBHelper;
    SQLiteDatabase sqLiteDatabase;
    ListKonsDataAdapter listKonsDataAdapter;
    ListView listView;

    public KonsultacijeFragment() {
        // Required empty public constructor
    }

    public static KonsultacijeFragment newInstance() {
        KonsultacijeFragment fragment = new KonsultacijeFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_konsultacije, container, false);
        context = getActivity();

        Ime = (EditText)view.findViewById(R.id.ime_prezimeET);
        Termin = (EditText)view.findViewById(R.id.terminET);
        Kabinet = (EditText)view.findViewById(R.id.kabinetET);
        SacuvajKons = (Button) view.findViewById(R.id.buttonSacuvajKons);
        SacuvajKons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sacuvajKonsultaciju(view);
            }
        });

        PrikaziKons = (Button) view.findViewById(R.id.buttonPrikaziKons);
        listView = (ListView)view.findViewById(R.id.list_viewKons);
        listKonsDataAdapter = new ListKonsDataAdapter(context, R.layout.kons_row_layout);
        listView.setAdapter(listKonsDataAdapter);
        konsDBHelper = new KonsDBHelper(context);
        sqLiteDatabase = konsDBHelper.getReadableDatabase();
        PrikaziKons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prikaziKonsultacije(view);
            }
        });

        return view;
    }

    public void sacuvajKonsultaciju(View view){

        String ime = Ime.getText().toString();
        String termin = Termin.getText().toString();
        String kabinet = Kabinet.getText().toString();

        konsDBHelper = new KonsDBHelper(context);
        sqLiteDatabase = konsDBHelper.getWritableDatabase();
        konsDBHelper.addInformation(ime,termin, kabinet,sqLiteDatabase);
        Toast.makeText(getContext(), "DataSaved", Toast.LENGTH_LONG).show();
        konsDBHelper.close();
    }

    public void prikaziKonsultacije(View view){

        cursor = konsDBHelper.getInformation(sqLiteDatabase);
        if(cursor.moveToFirst()){

            do {

                String ime, termin, kabinet;
                ime = cursor.getString(0);
                termin = cursor.getString(1);
                kabinet = cursor.getString(2);

                DataProviderKons dataProviderKons = new DataProviderKons(ime, termin, kabinet);

                listKonsDataAdapter.add(dataProviderKons);

            } while (cursor.moveToNext());
        }
    }
}
