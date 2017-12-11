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
import android.widget.TextView;
import android.widget.Toast;

import com.example.ivan.fon.classes.IspitDbHelper;
import com.example.ivan.fon.R;


public class FormaFragment extends Fragment {


    EditText Naziv, Ocena, unesenNaziv;
    Button Sacuvaj, Pronadji, Obrisi;
    Context context;
    IspitDbHelper ispitDbHelper;
    SQLiteDatabase sqLiteDatabase;
    TextView prikazanaOcena;
    String naziv_ispita;

    public FormaFragment() {
        // Required empty public constructor
    }

    public static FormaFragment newInstance() {
        FormaFragment fragment = new FormaFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_forma, container, false);

        context = getActivity();
        // !!! MNOGO VAZNO !!!
        // da bi mogao da koristis context bilo gde u fragmentu moras da ga inicijalizujes u
        // onCreateView i to da ga preuzmes od aktivnosti sa getActivity()
        // !!! !!! !!! !!! !!!

        Naziv = (EditText)view.findViewById(R.id.editTextNazivPredmeta);
        Ocena = (EditText)view.findViewById(R.id.editTextOcena);
        Sacuvaj = (Button)view.findViewById(R.id.buttonSacuvaj);
        Sacuvaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sacuvaj(view);
            }
        });

        unesenNaziv = (EditText)view.findViewById(R.id.nazivET);
        prikazanaOcena = (TextView)view.findViewById(R.id.ocenaTV);
        Pronadji = (Button)view.findViewById(R.id.buttonPronadji);
        Pronadji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pronadji(view);
            }
        });
        Obrisi = (Button)view.findViewById(R.id.buttonObrisi);
        Obrisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obrisiIspit(view);
            }
        });

        return view;
    }

    public void sacuvaj(View view){

        String naziv = Naziv.getText().toString();
        String ocena = Ocena.getText().toString();

        ispitDbHelper = new IspitDbHelper(context);
        sqLiteDatabase = ispitDbHelper.getWritableDatabase();
        ispitDbHelper.addInformation(naziv,ocena,sqLiteDatabase);
        Toast.makeText(getContext(), "DataSaved", Toast.LENGTH_LONG).show();
        ispitDbHelper.close();
    }

    public void pronadji(View view){

        naziv_ispita = unesenNaziv.getText().toString();
        ispitDbHelper = new IspitDbHelper(context); //ovde moze da stoji i getApplicationContext()
        sqLiteDatabase = ispitDbHelper.getReadableDatabase();
        Cursor cursor = ispitDbHelper.getIspit(naziv_ispita, sqLiteDatabase);
        if(cursor.moveToFirst()){
            String OCENA_ = cursor.getString(0);
            prikazanaOcena.setText(OCENA_);
            prikazanaOcena.setVisibility(View.VISIBLE);

        }
    }

    public void obrisiIspit (View view){

        ispitDbHelper = new IspitDbHelper(context);
        sqLiteDatabase = ispitDbHelper.getReadableDatabase();
        ispitDbHelper.deleteInformation(naziv_ispita, sqLiteDatabase);
        Toast.makeText(context, "Ispit je izbrisan", Toast.LENGTH_LONG).show();


    }



}
