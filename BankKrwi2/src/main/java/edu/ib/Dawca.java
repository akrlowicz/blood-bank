package edu.ib;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Simple class representing MySQL view
 * Shwowing donor's data
 */
public class Dawca {

    private StringProperty imieNazwisko;
    private IntegerProperty numerTelefonu;
    private StringProperty dataUrodzenia;
    private IntegerProperty waga;
    private IntegerProperty wzrost;
    private StringProperty plec;

    public Dawca() {
        imieNazwisko = new SimpleStringProperty();
        numerTelefonu = new SimpleIntegerProperty();
        dataUrodzenia = new SimpleStringProperty();
        waga = new SimpleIntegerProperty();
        wzrost = new SimpleIntegerProperty();
        plec = new SimpleStringProperty();

    }



    public String getImieNazwisko() {
        return imieNazwisko.get();
    }

    public StringProperty imieNazwiskoProperty() {
        return imieNazwisko;
    }

    public void setImieNazwisko(String imieNazwisko) {
        this.imieNazwisko.set(imieNazwisko);
    }

    public int getNumerTelefonu() {
        return numerTelefonu.get();
    }

    public IntegerProperty numerTelefonuProperty() {
        return numerTelefonu;
    }

    public void setNumerTelefonu(int numerTelefonu) {
        this.numerTelefonu.set(numerTelefonu);
    }


    public String getDataUrodzenia() {
        return dataUrodzenia.get();
    }

    public StringProperty dataUrodzeniaProperty() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(String dataUrodzenia) {
        this.dataUrodzenia.set(dataUrodzenia);
    }

    public int getWaga() {
        return waga.get();
    }

    public IntegerProperty wagaProperty() {
        return waga;
    }

    public void setWaga(int waga) {
        this.waga.set(waga);
    }

    public int getWzrost() {
        return wzrost.get();
    }

    public IntegerProperty wzrostProperty() {
        return wzrost;
    }

    public void setWzrost(int wzrost) {
        this.wzrost.set(wzrost);
    }

    public String getPlec() {
        return plec.get();
    }

    public StringProperty plecProperty() {
        return plec;
    }

    public void setPlec(String plec) {
        this.plec.set(plec);
    }
}
