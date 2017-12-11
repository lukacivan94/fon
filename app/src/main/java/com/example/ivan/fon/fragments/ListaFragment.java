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
import android.widget.ListView;

import com.example.ivan.fon.dataProviders.DataProvider;
import com.example.ivan.fon.classes.IspitDbHelper;
import com.example.ivan.fon.classes.ListDataAdapter;
import com.example.ivan.fon.R;


public class ListaFragment extends Fragment {

    Context context;
    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    IspitDbHelper ispitDbHelper;
    Cursor cursor;
    ListDataAdapter listDataAdapter;

    Button Prikazi;

    public ListaFragment() {
        // Required empty public constructor
    }

    public static ListaFragment newInstance() {
        ListaFragment fragment = new ListaFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_lista, container, false);
        context = getActivity();

        listView = (ListView)view.findViewById(R.id.list_view);
        listDataAdapter = new ListDataAdapter(context, R.layout.row_layout);
        listView.setAdapter(listDataAdapter);
        ispitDbHelper = new IspitDbHelper(context);
        sqLiteDatabase = ispitDbHelper.getReadableDatabase();

        Prikazi = (Button)view.findViewById(R.id.buttonPrikazi);
        Prikazi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prikaziIspite(view);
            }
        });

        return view;
    }

    public void prikaziIspite(View view){

        cursor = ispitDbHelper.getInformation(sqLiteDatabase);
        if(cursor.moveToFirst()){

            do {

                String naziv, ocena;
                naziv = cursor.getString(0);
                ocena = cursor.getString(1);

                DataProvider dataProvider = new DataProvider(naziv, ocena);

                listDataAdapter.add(dataProvider);


            } while (cursor.moveToNext());
        }
    }
}
