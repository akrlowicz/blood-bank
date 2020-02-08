package edu.ib;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * A simple class representing MySQL view
 * showing blood resources for locations
 *
 */
public class Zasoby2 {

    /**
     * Blood type
     */
    private StringProperty grupaKrwi;
    /**
     * Blood resources state
     */
    private StringProperty zapasy;
    /**
     * Name of blood donation center
     */
    private StringProperty lokalizacja;

    /**
     * Constructor of Zasoby Object
     */
    public Zasoby2() {
        grupaKrwi = new SimpleStringProperty();
        zapasy = new SimpleStringProperty();
        lokalizacja = new SimpleStringProperty();

    }

    public String getGrupaKrwi() {
        return grupaKrwi.get();
    }

    public StringProperty grupaKrwiProperty() {
        return grupaKrwi;
    }

    public void setGrupaKrwi(String grupaKrwi) {
        this.grupaKrwi.set(grupaKrwi);
    }

    public String getZapasy() {
        return zapasy.get();
    }

    public StringProperty zapasyProperty() {
        return zapasy;
    }

    public void setZapasy(String zapasy) {
        this.zapasy.set(zapasy);
    }

    public String getLokalizacja() {
        return lokalizacja.get();
    }

    public StringProperty lokalizacjaProperty() {
        return lokalizacja;
    }

    public void setLokalizacja(String lokalizacja) {
        this.lokalizacja.set(lokalizacja);
    }
}
