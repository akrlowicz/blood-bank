package edu.ib;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * A simple class representing MySQL view
 * showing all blood resources for all locations
 *
 */
public class Zasoby {

    /**
     * Blood type
     */
    private StringProperty grupaKrwi;
    /**
     * Number of blood units
     */
    private IntegerProperty liczbaJednostek;
    /**
     * Blood resources state
     */
    private StringProperty zapasy;

/**
 * Constructor of Zasoby Object
 */

    public Zasoby() {
        grupaKrwi = new SimpleStringProperty();
        liczbaJednostek = new SimpleIntegerProperty();
        zapasy = new SimpleStringProperty();


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

    public int getLiczbaJednostek() {
        return liczbaJednostek.get();
    }

    public IntegerProperty liczbaJednostekProperty() {
        return liczbaJednostek;
    }

    public void setLiczbaJednostek(int liczbaJednostek) {
        this.liczbaJednostek.set(liczbaJednostek);
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

}
