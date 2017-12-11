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
import com.example.ivan.fon.dataProviders.DataProviderKons;

import java.util.ArrayList;
import java.util.List;


public class ListKonsDataAdapter extends ArrayAdapter {
    List list = new ArrayList();

    public ListKonsDataAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    static class  LayoutHandler{

        TextView IME, TERMIN, KABINET;
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
        ListKonsDataAdapter.LayoutHandler layoutHandler;
        if(row == null){

            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.kons_row_layout, parent, false);
            layoutHandler = new ListKonsDataAdapter.LayoutHandler();
            layoutHandler.IME = (TextView)row.findViewById(R.id.ime_prezime);
            layoutHandler.TERMIN = (TextView)row.findViewById(R.id.termin);
            layoutHandler.KABINET = (TextView)row.findViewById(R.id.kabinet);
            row.setTag(layoutHandler);
        } else {

            layoutHandler = (ListKonsDataAdapter.LayoutHandler)row.getTag();

        }
        DataProviderKons dataProviderKons = (DataProviderKons) this.getItem(position);
        layoutHandler.IME.setText(dataProviderKons.getIme());
        layoutHandler.TERMIN.setText(dataProviderKons.getTermin());
        layoutHandler.KABINET.setText(dataProviderKons.getKabinet());

        return row;
    }
}