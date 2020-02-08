package edu.ib;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple class for Data Access Object on blood bankk database
 * Searching, executing procedures, showing tables
 */
public class BankKrwiDAO {

    /**
     * Tool for connecting with database
     * And exwcuting queries
     */
    private DBUtil dbUtil;
    /**
     * Alert dialog for informing the user about the results
     */
    private Alert alert;

    /**
     * Constructor
     * @param dbUtil
     */
    public BankKrwiDAO(DBUtil dbUtil) {
        this.dbUtil = dbUtil;

    }

    /**
     * Gathering all ids of outdated blood for given blood donation center
     * By its unique id number
     * Used in utilization process
     * @param idCentrum blood donation center id
     * @return  List of ids of outdated blood units
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<Integer> showAllPrzeterminowaneID(Integer idCentrum) throws SQLException, ClassNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT id FROM krew WHERE tygodnie(id) >= 6 AND centrum_krwiodawstwa_id=");
        sb.append(idCentrum);

        String stmt = sb.toString();
        List<Integer> idPrzeterminowane = new ArrayList<>();

        try{
            ResultSet resultSet = dbUtil.dbExecuteQuery(stmt);

            while (resultSet.next()) {
                 idPrzeterminowane.add(resultSet.getInt("id"));

            }
        }catch (SQLException e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Wystąpił błąd podczas ustalania przeterminowanej krwi.");
            alert.show();
            throw e;
        }
        return idPrzeterminowane;

    }


    /**
     * Gathering all blood resources from view
     * Using Zaoby Class
     * @return ObservableList of Zasoby objects
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ObservableList<Zasoby> showAllZasoby() throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM zasoby_v2;";

        try {

            ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);

            ObservableList<Zasoby> zasobyList = getZasobyList(resultSet);

            return zasobyList;

        }catch (SQLException e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Wystąpił błąd podczas przeszukiwania zasobów.");
            alert.show();
            throw e;
        }

    }

    /**
     * Gathering all blood resources from view with locations
     * Using Zaoby2 Class
     * @return ObservableList of Zasoby2 objects
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ObservableList<Zasoby2> showAllZasoby2() throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM zasoby_v;";

        try {

            ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);

            ObservableList<Zasoby2> zasobyList = getZasoby2List(resultSet);

            return zasobyList;

        }catch (SQLException e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Wystąpił błąd podczas przeszukiwania zasobów.");
            alert.show();
            throw e;
        }

    }

    /**
     * Gathering all blood resources for given blood type
     * @param grupa blood type string
     * @return ObservableList of Zasoby objects
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ObservableList<Zasoby> showZasobyForGrupa(String grupa) throws SQLException, ClassNotFoundException {
        StringBuilder  sb = new StringBuilder();
        sb.append("SELECT * FROM zasoby_v2 WHERE grupa_krwi='");
        sb.append(grupa); sb.append("'");

        String selectStmt = sb.toString();
        try {

            ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);

            ObservableList<Zasoby> zasobyList = getZasobyList(resultSet);

            return zasobyList;

        }catch (SQLException e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Wystąpił błąd podczas szukania zasobów dla " + grupa + ".");
            alert.show();
            throw e;
        }

    }

    /**
     * Gathering all blood resources for given blood type
     * @param centrum blood donation center name string
     * @return ObservableList of Zasoby2 objects
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ObservableList<Zasoby2> showZasoby2ForCentrum(String centrum) throws SQLException, ClassNotFoundException {
        StringBuilder  sb = new StringBuilder();
        sb.append("SELECT * FROM zasoby_v WHERE lokalizacja='Centrum Krwiodawstwa ");
        sb.append(centrum); sb.append("'");

        String selectStmt = sb.toString();
        try {

            ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);

            ObservableList<Zasoby2> zasobyList = getZasoby2List(resultSet);

            return zasobyList;

        }catch (SQLException e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Wystąpił błąd podczas szukania zasobów dla " + centrum + ".");
            alert.show();
            throw e;
        }

    }

    /**
     * Gathering all donors data
     * @return ObservableList of Dawca objects
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ObservableList<Dawca> showAllDawcy() throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM dawcy_v";

        try {

            ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);

            ObservableList<Dawca> dawcyList = getDawcaList(resultSet);

            return dawcyList;


        } catch (SQLException e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Wystąpił błąd podczas przeszukiwania dawców." +e.getMessage());
            alert.show();
            throw e;
        }

    }

    /**
     * Gathering donors data for given name
     * @param imieNazwisko name of donor
     * @return ObservanbleList of Dawca objects
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ObservableList<Dawca> showDawcyForImieNazwisko(String imieNazwisko) throws SQLException, ClassNotFoundException {

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM dawcy_v WHERE imie_nazwisko='");
        sb.append(imieNazwisko); sb.append("'");

        String selectStmt = sb.toString();

        try {

            ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);

            ObservableList<Dawca> dawcyList = getDawcaList(resultSet);

            return dawcyList;


        } catch (SQLException e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Wystąpił błąd podczas szukania dawcy " + imieNazwisko + ".");
            alert.show();
            throw e;
        }

    }


    /**
     * Converting data from ResultSet to ObservableList
     * of Zasoby objects
     * @param rs ResultSet from query
     * @return ObservableList of Zasoby objects
     * @throws SQLException
     */
    private ObservableList<Zasoby> getZasobyList(ResultSet rs) throws SQLException {

        ObservableList<Zasoby> zasobyList = FXCollections.observableArrayList();

        while (rs.next()) {

            Zasoby z = new Zasoby();
            z.setGrupaKrwi(rs.getString("grupa_krwi"));
            z.setLiczbaJednostek(rs.getInt("liczba_jednostek"));
            z.setZapasy(rs.getString("zapasy"));
            zasobyList.add(z);
        }

        return zasobyList;
    }

    /**
     * Converting data from ResultSet to ObservableList
     * of Zasoby2 objects
     * @param rs ResultSet from query
     * @return ObservableList of Zasoby2 objects
     * @throws SQLException
     */
    private ObservableList<Zasoby2> getZasoby2List(ResultSet rs) throws SQLException {

        ObservableList<Zasoby2> zasobyList = FXCollections.observableArrayList();

        while (rs.next()) {

            Zasoby2 z = new Zasoby2();
            z.setGrupaKrwi(rs.getString("grupa_krwi"));
            z.setZapasy(rs.getString("zapasy"));
            z.setLokalizacja(rs.getString("lokalizacja"));
            zasobyList.add(z);
        }

        return zasobyList;
    }

    /**
     * Converting data from ResultSet to ObservableList
     * of Dawca objects
     * @param rs ResultSet from query
     * @return ObservableList of Dawca objects
     * @throws SQLException
     */
    private ObservableList<Dawca> getDawcaList(ResultSet rs) throws SQLException {

        ObservableList<Dawca> dawcyList = FXCollections.observableArrayList();

        while (rs.next()) {

           Dawca d = new Dawca();

            d.setImieNazwisko(rs.getString("imie_nazwisko"));
            d.setDataUrodzenia(rs.getString("data_urodzenia"));
            d.setNumerTelefonu(rs.getInt("numer_telefonu"));
            d.setWaga(rs.getInt("waga"));
            d.setWzrost(rs.getInt("wzrost"));
            d.setPlec(rs.getString("plec"));
            dawcyList.add(d);
        }

        return dawcyList;
    }


    /**
     * Transfering procedure call of blood utilization
     * Using showAllPrzeterminowaneID(String idCentrum) method
     * @param idCentrum id of blood donation center
     * @throws SQLException
     * @throws ClassNotFoundException
     */
   public void przeprowadzUtylizacje(Integer idCentrum) throws SQLException, ClassNotFoundException {

        String procedureStmt = "{CALL przeterminowana_usun(?)}";
        List<Integer> idList = showAllPrzeterminowaneID(idCentrum);
        for(Integer i: idList)
        dbUtil.procedurePrzeterminowana(procedureStmt,i);
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Pomyślnie przeprowadzono utylizację przeterminowanej krwi.");
        alert.show();

   }

    /**
     * Transfering procedure call of blood transfusion
     * @param grupaKrwi blood type for transfusion
     * @param ilosc number of units for transfusion
     * @throws SQLException
     * @throws ClassNotFoundException
     */
       public void wykonajTransfuzje(String grupaKrwi, Integer ilosc) throws SQLException, ClassNotFoundException{

        String komunikat;
        String procedureStmt = "{CALL transfuzja(?,?,?)}";
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);

        komunikat = dbUtil.procedureTransfuzja(procedureStmt,grupaKrwi,ilosc);

        alert.setContentText(komunikat);
        alert.show();
    }

    /**
     * Transfering procedure call of blood dontation
     * @param czyOddawal detrmine if donor donated before y/n
     * @param imieNazwisko name of donor
     * @param idCentrum id of blood donation center in which the donor is donating
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void oddajKrew(String czyOddawal,String imieNazwisko, Integer idCentrum) throws SQLException, ClassNotFoundException{
        String komunikat;
        String procedureStmt = "{CALL oddanie_krwi(?,?,?,?)}";
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);

        komunikat = dbUtil.procedureOddanieKrwi(procedureStmt,czyOddawal,imieNazwisko,idCentrum);


        alert.setContentText(komunikat);
        alert.show();

    }

    /**
     * Transfering procedure call of registering new donor
     * @param imieNazwisko name of potential donor
     * @param numerTelefonu telephone number of potential donor
     * @param dataUrodzenia birth date of potential donor
     * @param waga weight of potential donor
     * @param wzrost height of potential donor
     * @param plec sex of potential donor
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void zarejestrujDawce(String imieNazwisko, Integer numerTelefonu,String grupa, String rh, String dataUrodzenia,Integer waga, Integer wzrost,String plec) throws SQLException, ClassNotFoundException {

          int komunikat;
          String procedureStmt = "{CALL zarejestruj_dawce(?,?,?,?,?,?,?,?,?)}";
          alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setHeaderText(null);


           komunikat = dbUtil.procedureZarejestrujDawce(procedureStmt,imieNazwisko,numerTelefonu,grupa,rh,dataUrodzenia,waga,wzrost,plec);


       if (komunikat==-1)
           alert.setContentText("Dawca nie spełnia wymogów.");
       else if(komunikat==1)
           alert.setContentText("Dawca " + imieNazwisko + " dodany.");

       alert.show();
    }


}
