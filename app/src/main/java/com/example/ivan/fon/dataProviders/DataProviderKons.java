package com.example.ivan.fon.dataProviders;

/**
 * Created by ivan on 12/9/17.
 */

public class DataProviderKons {

    private String ime;
    private String termin;
    private String kabinet;

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getTermin() {
        return termin;
    }

    public void setTermin(String termin) {
        this.termin = termin;
    }

    public String getKabinet() {
        return kabinet;
    }

    public void setKabinet(String kabinet) {
        this.kabinet = kabinet;
    }

    public DataProviderKons(String ime, String termin, String kabinet) {
        this.ime = ime;
        this.termin = termin;
        this.kabinet = kabinet;
    }
}
