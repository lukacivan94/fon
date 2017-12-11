package com.example.ivan.fon.classes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ivan.fon.R;
import com.example.ivan.fon.dataProviders.DataProvider;

import java.util.ArrayList;
import java.util.List;


public class ListDataAdapter extends ArrayAdapter{
    List list = new ArrayList();

    public ListDataAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    static class  LayoutHandler{

        TextView NAZIV, OCENA;
    }

    @Override
    public void add(@Nullable Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
        LayoutHandler layoutHandler;
        if(row == null){

            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout, parent, false);
            layoutHandler = new LayoutHandler();
            layoutHandler.NAZIV = (TextView)row.findViewById(R.id.naziv_ispita);
            layoutHandler.OCENA = (TextView)row.findViewById(R.id.ocena_ispita);
            row.setTag(layoutHandler);
        } else {

            layoutHandler = (LayoutHandler)row.getTag();

        }
        DataProvider dataProvider = (DataProvider)this.getItem(position);
        layoutHandler.NAZIV.setText(dataProvider.getNaziv());
        layoutHandler.OCENA.setText(dataProvider.getOcena());

        return row;
    }
}
