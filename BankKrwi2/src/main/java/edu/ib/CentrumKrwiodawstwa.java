package edu.ib;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * A simple Class representing MySQL table
 * Containing blood donations centers in region
 */
public class CentrumKrwiodawstwa {

    /**
     * Id for blood donation center
     */
    private IntegerProperty id;
    /**
     * Name of blood donation center
     */
    private StringProperty nazwa;
    /**
     * Address of blood donation center
     */
    private StringProperty adres;
    /**
     * Telephone number
     */
    private IntegerProperty numerTelefonu;
    /**
     * Id of blood donation center
     */
    private IntegerProperty regionalnyBankID;

    public CentrumKrwiodawstwa() {
        id = new SimpleIntegerProperty();
        nazwa = new SimpleStringProperty();
        adres = new SimpleStringProperty();
        numerTelefonu = new SimpleIntegerProperty();
        regionalnyBankID = new SimpleIntegerProperty();
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNazwa() {
        return nazwa.get();
    }

    public StringProperty nazwaProperty() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa.set(nazwa);
    }

    public String getAdres() {
        return adres.get();
    }

    public StringProperty adresProperty() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres.set(adres);
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

    public int getRegionalnyBankID() {
        return regionalnyBankID.get();
    }

    public IntegerProperty regionalnyBankIDProperty() {
        return regionalnyBankID;
    }

    public void setRegionalnyBankID(int regionalnyBankID) {
        this.regionalnyBankID.set(regionalnyBankID);
    }
}
