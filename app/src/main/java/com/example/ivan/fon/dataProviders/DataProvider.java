package com.example.ivan.fon.dataProviders;

/**
 * Created by ivan on 12/7/17.
 */

public class DataProvider {

    private String naziv;
    private String ocena;

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOcena() {
        return ocena;
    }

    public void setOcena(String ocena) {
        this.ocena = ocena;
    }

    public DataProvider(String naziv, String ocena){

        this.naziv = naziv;
        this.ocena = ocena;

    }
}
