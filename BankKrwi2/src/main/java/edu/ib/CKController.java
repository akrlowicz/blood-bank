package edu.ib;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * Simple Controller class for opearting on window
 * For blood donation center app window
 *
 */
public class CKController {

    /**
     * Tool to operate on database
     */
    private DBUtil dbUtil;
    /**
     * Tool for getting queries and proceudres
     */
    private BankKrwiDAO bankKrwiDAO;
    /**
     * Name of logged user
     */
    private String name = "";
    /**
     * Password of logged user
     */
    private String password = "";
    /**
     * id for user
     */
    private Integer idCentrum;

    /**
     * Dtermines if a mouse cursor entered for the first time on widnow
     */
    private boolean firstEnter = true;

    /**
     * Getting name and password for Login Window
     * of logged user
     * @param name name of logged user
     * @param password password of logged user
     */
    public void setDB(String name, String password) {
        this.name = name;
        this.password = password;
    }

    /**
     * Setting id for logged user based on its name
     * given from Login window
     */

    public void setIdCentrum() {
        if(name.equals("centrumOlesnica"))
            idCentrum=1;
        else if (name.equals("centrumBrzegDolny"))
            idCentrum=2;
        else if(name.equals("centrumTrzebnica"))
            idCentrum=3;
        else if(name.equals("centrumLegnica"))
            idCentrum=4;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> grupaChoiceBox;

    @FXML
    private ChoiceBox<String> rhChoiceBox;

    @FXML
    private Button wylogujBtn;

    @FXML
    private Button utylizacjaBtn;

    @FXML
    private RadioButton takRadioBtn;

    @FXML
    private RadioButton nieRadioBtn;

    @FXML
    private TextField imieNazwiskoTxt;

    @FXML
    private TextField telefonTxt;

    @FXML
    private DatePicker dataUrodzeniaDatePicker;

    @FXML
    private TextField wagaTxt;

    @FXML
    private TextField wzrostTxt;

    @FXML
    private RadioButton kRadioBtn;

    @FXML
    private RadioButton mRadioBtn;


    @FXML
    private Button wprowadzKrewBtn;

    @FXML
    private TextField imieNazwiskoWyszukajTxt;

    @FXML
    private Button pokazWszystkichBtn;

    @FXML
    private TableView tabelaDawcy;

    @FXML
    private TableColumn<Dawca, String> imieNazwiskoCol;

    @FXML
    private TableColumn<Dawca, String> telefonCol;

    @FXML
    private TableColumn<Dawca, String> dataUrodzeniaCol;

    @FXML
    private TableColumn<Dawca, String> wagaCol;

    @FXML
    private TableColumn<Dawca, String> wzrostCol;

    @FXML
    private TableColumn<Dawca, String> plecCol;

    /**
     * Enable parameters if a blood donor is not registered
     * @param event
     */
    @FXML
    void nieRadioBtnOnAction(ActionEvent event) {

        if(nieRadioBtn.isSelected()) {

            grupaChoiceBox.setDisable(false);
            rhChoiceBox.setDisable(false);
            wagaTxt.setDisable(false);
            wzrostTxt.setDisable(false);
            dataUrodzeniaDatePicker.setDisable(false);
            telefonTxt.setDisable(false);
            kRadioBtn.setDisable(false);
            mRadioBtn.setDisable(false);
        }
    }

    /**
     * Disable parameters if a donor is registered
     * @param event
     */

    @FXML
    void takRadioBtnOnAction(ActionEvent event) {

        if(takRadioBtn.isSelected()) {

            grupaChoiceBox.setDisable(true);
            rhChoiceBox.setDisable(true);
            wagaTxt.setDisable(true);
            wzrostTxt.setDisable(true);
            dataUrodzeniaDatePicker.setDisable(true);
            telefonTxt.setDisable(true);
            kRadioBtn.setDisable(true);
            mRadioBtn.setDisable(true);
        }
    }

    /**
     * Log out of blood odnation center window
     * Get back to Login window
     * @param event
     * @throws IOException
     * @throws SQLException
     */

    @FXML
    void wylogujBtnOnAction(ActionEvent event) throws IOException, SQLException {

        dbUtil.dbDisconnect();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene s = new Scene(root);
        stage.setScene(s);
        s.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.initModality(Modality.WINDOW_MODAL);
        Stage myScene = (Stage) wylogujBtn.getScene().getWindow();
        myScene.close();
        stage.show();

    }

    /**
     * Utilization of blood if it's stored longer than 6 weeks
     *
     * @param event
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    @FXML
    void utylizacjaBtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        bankKrwiDAO.przeprowadzUtylizacje(idCentrum);
    }

    /**
     * Register blood and donor if it's not already registered
     * @param event
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    @FXML
    void wprowadzKrewBtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {



        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText("Wypełnij wszystkie niezbędne pola.");




        if(!imieNazwiskoTxt.getText().equals("")){

            if (nieRadioBtn.isSelected()) {

                if(!grupaChoiceBox.getSelectionModel().isEmpty() && !rhChoiceBox.getSelectionModel().isEmpty() && (kRadioBtn.isSelected() || mRadioBtn.isSelected()) && !telefonTxt.getText().equals("") && !dataUrodzeniaDatePicker.getValue().equals("") && !wagaTxt.getText().equals("") && !wzrostTxt.getText().equals("")) {

                    String dataUrodzeniaString = dataUrodzeniaDatePicker.getValue().toString();
                    String plec = null;

                    if (kRadioBtn.isSelected())
                        plec = "k";
                    if (mRadioBtn.isSelected())
                        plec = "m";


                    bankKrwiDAO.zarejestrujDawce(imieNazwiskoTxt.getText(), Integer.valueOf(telefonTxt.getText()), grupaChoiceBox.getValue(),rhChoiceBox.getValue(), dataUrodzeniaString, Integer.valueOf(wagaTxt.getText()), Integer.valueOf(wzrostTxt.getText()), plec);
                    bankKrwiDAO.oddajKrew("nie", imieNazwiskoTxt.getText(), idCentrum);

                } else {

                    alert.show();
                }

            } else if (takRadioBtn.isSelected()) {

                bankKrwiDAO.oddajKrew("tak", imieNazwiskoTxt.getText(), idCentrum);
            }

        } else {
            alert.show();
        }
    }

    /**
     * Set radio buttons if the donor is registered or not
     * Set radio buttons if the donor is either woman or man
     * Set ToggleGroup for them
     */

    void toggleRadioBtns() {
        ToggleGroup group = new ToggleGroup();
        takRadioBtn.setToggleGroup(group);
        nieRadioBtn.setSelected(true);
        nieRadioBtn.setToggleGroup(group);

        ToggleGroup groupPlec = new ToggleGroup();
        kRadioBtn.setToggleGroup(groupPlec);
        mRadioBtn.setToggleGroup(groupPlec);

    }

    /**
     * Restrict date in date picker to not go further than given date
     *
     * @param datePicker
     * @param minDate
     * @param maxDate
     */

    public void restrictDatePicker(DatePicker datePicker, LocalDate minDate, LocalDate maxDate) {

        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {

            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item.isBefore(minDate)) {

                            setDisable(true);
                            setStyle("-fx-background-color: #dc757a;");

                        } else if (item.isAfter(maxDate)) {

                            setDisable(true);
                            setStyle("-fx-background-color: #dc757a;");

                        }
                    }
                };

            }
        };

        datePicker.setDayCellFactory(dayCellFactory);
    }

    //TAB

    /**
     * Show all donors
     * @param event
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @FXML
    void pokazWszystkichBtn(ActionEvent event) throws SQLException, ClassNotFoundException {

        try {

            tabelaDawcy.getItems().clear();

            ObservableList<Dawca> data = bankKrwiDAO.showAllDawcy();
            populateZasoby(data);

        } catch (SQLException e) {

            throw e;
        }

    }

    /**
     * Search for donor by name in table
     * @param event
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    @FXML
    void wyszukajOnKeyPressed(KeyEvent event) throws SQLException, ClassNotFoundException {


        if(event.getCode().equals(KeyCode.ENTER)) {

            if(!imieNazwiskoWyszukajTxt.getText().isEmpty()) {
                try {

                    tabelaDawcy.getItems().clear();

                    ObservableList<Dawca> data = bankKrwiDAO.showDawcyForImieNazwisko(imieNazwiskoWyszukajTxt.getText());
                    populateZasoby(data);

                } catch (SQLException e) {

                    throw e;
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setContentText("Wpisz imię i nazwisko.");
                alert.show();
            }


        }
    }

    /**
     * Populate table with donors
     * @param dawcyData
     */
    private void populateZasoby(ObservableList<Dawca> dawcyData) {

        tabelaDawcy.setItems(dawcyData);
    }

    /**
     * Set choice box for choosing blood type
     */
    void setChoiceBox() {

        ObservableList<String> availableChoicesGrupa = FXCollections.observableArrayList("A","B","AB","0");
        ObservableList<String> availableChoicesRh = FXCollections.observableArrayList("+","-");

        grupaChoiceBox.setItems(availableChoicesGrupa);
        rhChoiceBox.setItems(availableChoicesRh);
    }

    /**
     * Populate table on first eneter of mouse on a window
     * Connect to dbUtil on first mouse enter
     * @param event
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    @FXML
    void onMouseEntered(MouseEvent event) throws SQLException, ClassNotFoundException {

        dbUtil = new DBUtil("centrum","passwordCentrum");
        bankKrwiDAO = new BankKrwiDAO(dbUtil);

        if(firstEnter) {
            try {

                ObservableList<Dawca> data = bankKrwiDAO.showAllDawcy();

                populateZasoby(data);


                firstEnter = false;

            } catch (SQLException e) {

                throw e;
            }

        }
    }

    @FXML
    void initialize()  {
        assert grupaChoiceBox != null : "fx:id=\"grupaChoiceBox\" was not injected: check your FXML file 'ckapp.fxml'.";
        assert rhChoiceBox != null : "fx:id=\"rhChoiceBox\" was not injected: check your FXML file 'ckapp.fxml'.";
        assert takRadioBtn != null : "fx:id=\"takRadioBtn\" was not injected: check your FXML file 'ckapp.fxml'.";
        assert nieRadioBtn != null : "fx:id=\"nieRadioBtn\" was not injected: check your FXML file 'ckapp.fxml'.";
        assert wylogujBtn != null : "fx:id=\"wylogujBtn\" was not injected: check your FXML file 'ckapp.fxml'.";
        assert utylizacjaBtn != null : "fx:id=\"utylizacjaBtn\" was not injected: check your FXML file 'ckapp.fxml'.";
        assert imieNazwiskoTxt != null : "fx:id=\"imieNazwiskoTxt\" was not injected: check your FXML file 'ckapp.fxml'.";
        assert telefonTxt != null : "fx:id=\"telefonTxt\" was not injected: check your FXML file 'ckapp.fxml'.";
        assert dataUrodzeniaDatePicker != null : "fx:id=\"dataUrodzeniaDatePicker\" was not injected: check your FXML file 'ckapp.fxml'.";
        assert wagaTxt != null : "fx:id=\"wagaTxt\" was not injected: check your FXML file 'ckapp.fxml'.";
        assert wzrostTxt != null : "fx:id=\"wzrostTxt\" was not injected: check your FXML file 'ckapp.fxml'.";
        assert kRadioBtn != null : "fx:id=\"kRadioBtn\" was not injected: check your FXML file 'ckapp.fxml'.";
        assert mRadioBtn != null : "fx:id=\"mRadioBtn\" was not injected: check your FXML file 'ckapp.fxml'.";
        assert wprowadzKrewBtn != null : "fx:id=\"wprowadzKrewBtn\" was not injected: check your FXML file 'ckapp.fxml'.";
        assert imieNazwiskoWyszukajTxt != null : "fx:id=\"imieNazwiskoWyszukajTxt\" was not injected: check your FXML file 'ckapp.fxml'.";
        assert pokazWszystkichBtn != null : "fx:id=\"pokazWszystkichBtn\" was not injected: check your FXML file 'ckapp.fxml'.";
        assert tabelaDawcy != null : "fx:id=\"tabelaDawcy\" was not injected: check your FXML file 'ckapp.fxml'.";
        assert imieNazwiskoCol != null : "fx:id=\"imieNazwiskoCol\" was not injected: check your FXML file 'ckapp.fxml'.";
        assert telefonCol != null : "fx:id=\"telefonCol\" was not injected: check your FXML file 'ckapp.fxml'.";
        assert dataUrodzeniaCol != null : "fx:id=\"dataUrodzeniaCol\" was not injected: check your FXML file 'ckapp.fxml'.";
        assert wagaCol != null : "fx:id=\"wagaCol\" was not injected: check your FXML file 'ckapp.fxml'.";
        assert wzrostCol != null : "fx:id=\"wzrostCol\" was not injected: check your FXML file 'ckapp.fxml'.";
        assert plecCol != null : "fx:id=\"plecCol\" was not injected: check your FXML file 'ckapp.fxml'.";

        toggleRadioBtns();
        restrictDatePicker(dataUrodzeniaDatePicker,LocalDate.of(1920,01,01),LocalDate.now());
        setChoiceBox();




    }
}
