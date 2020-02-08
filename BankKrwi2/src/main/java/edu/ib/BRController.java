package edu.ib;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 *
 * Simple Controller class for opearting on window
 * For Regional Blood Bank app window
 *
 */
public class BRController {

    /**
     * Tool to operate on database
     */
    private BankKrwiDAO bankKrwiDAO;
    /**
     * Tool for getting queries and proceudres
     */
    private DBUtil dbUtil;
    /**
     * Password of logged user
     */
    private String name = "";
    /**
     * id for user
     */
    private String password = "";

    /**
     * Dtermines if a mouse cursor entered for the first time on widnow
     */
    private boolean firstEnter=true;

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

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button pokazZasobyBtn;

    @FXML
    private TextField grupaWyszukajTxt;

    @FXML
    private TextField centrumWyszukajTxt;

    @FXML
    private ChoiceBox<String> grupaTransfuzjaChoiceBox;

    @FXML
    private TextField liczbaJednostekTxt;

    @FXML
    private Button transfuzjaBtn;

    @FXML
    private Button wylogujBtn;

    @FXML
    private TableView tabela;

    @FXML
    private TableColumn<Zasoby, String> grupaKrwiCol;

    @FXML
    private TableColumn<Zasoby, String> jednostkiCol;

    @FXML
    private TableColumn<Zasoby, String> zasobyCol;

    @FXML
    private TableView tabela2;

    @FXML
    private TableColumn<Zasoby2, String> grupaKriw2Col;

    @FXML
    private TableColumn<Zasoby2, String> stan2Col;

    @FXML
    private TableColumn<Zasoby2, String> lokalizacja2Col;

    /**
     * Log out of Regional Blood Bank window
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
     * Show all blood resources in table
     * @param event
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @FXML
    void pokazZasobyBtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {



        try {

            tabela.getItems().clear();
            tabela2.getItems().clear();

            ObservableList<Zasoby> data = bankKrwiDAO.showAllZasoby();
            ObservableList<Zasoby2> data2 = bankKrwiDAO.showAllZasoby2();
            populateZasoby(data);
            populateZasoby2(data2);


        } catch (SQLException e) {

            throw e;
        }
    }

    /**
     * Populate table with resources in ObservaebleList
     * @param zasobyData list of Zasoby objects
     */
    private void populateZasoby(ObservableList<Zasoby> zasobyData) {

        tabela.setItems(zasobyData);
    }

    /**
     * Populate table with resources with location in ObservaebleList
     * @param zasobyData list of Zasoby2 objects
     */
    private void populateZasoby2(ObservableList<Zasoby2> zasobyData) {

        tabela2.setItems(zasobyData);
    }

    /**
     * Search in table of blood resources by blood type
     * @param event
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    @FXML
    void wyszukajPoGrupieOnKeyPressed(KeyEvent event) throws SQLException, ClassNotFoundException {

        if(event.getCode().equals(KeyCode.ENTER)) {

            if (!grupaWyszukajTxt.getText().isEmpty()) {

                try {

                    tabela.getItems().clear();

                    ObservableList<Zasoby> data = bankKrwiDAO.showZasobyForGrupa(grupaWyszukajTxt.getText());
                    populateZasoby(data);

                } catch (SQLException e) {

                    throw e;
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setContentText("Podaj grupę do wyszukania.");
                alert.show();
            }
        }
    }


    /**
     * Search in table with resaurces and location by location
     * @param event
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @FXML
    void wyszukajPoCentrumOnKeyPressed(KeyEvent event) throws ClassNotFoundException, SQLException {

        if(event.getCode().equals(KeyCode.ENTER)) {

            if (!centrumWyszukajTxt.getText().isEmpty()) {

                try {

                    tabela2.getItems().clear();

                    ObservableList<Zasoby2> data2 = bankKrwiDAO.showZasoby2ForCentrum(centrumWyszukajTxt.getText());
                    populateZasoby2(data2);

                } catch (SQLException e) {

                    throw e;
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setContentText("Podaj centrum do wyszukania.");
                alert.show();
            }
        }

    }


    /**
     * Perform a blood transfusion given blood type and number  of units
     * @param event
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @FXML
    void transfuzjaBtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException{

        if(!grupaTransfuzjaChoiceBox.getValue().equals("") && !liczbaJednostekTxt.getText().equals("")){

            bankKrwiDAO.wykonajTransfuzje(grupaTransfuzjaChoiceBox.getValue(),Integer.valueOf(liczbaJednostekTxt.getText()));
            tabela.getItems().clear();
            tabela2.getItems().clear();

            ObservableList<Zasoby> data = bankKrwiDAO.showAllZasoby();
            ObservableList<Zasoby2> data2 = bankKrwiDAO.showAllZasoby2();
            populateZasoby(data);
            populateZasoby2(data2);

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Wypełnij wszystkie niezbędne pola w celu pobrania do transfuzji.");
            alert.show();
        }

    }

    /**
     * Create DBUtil Object and Data Access Object on first mouse enter
     * Populate tables on first mouse enter
     * @param event
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    @FXML
    void onMouseEntered(MouseEvent event) throws SQLException, ClassNotFoundException {

        dbUtil = new DBUtil(name, password);
        bankKrwiDAO = new BankKrwiDAO(dbUtil);


        if(firstEnter) {
            try {


                ObservableList<Zasoby> data = bankKrwiDAO.showAllZasoby();
                ObservableList<Zasoby2> data2 = bankKrwiDAO.showAllZasoby2();
                populateZasoby(data);
                populateZasoby2(data2);

                firstEnter = false;

            } catch (SQLException e) {

                throw e;
            }

        }
    }

    void setChoiceBox() {

        ObservableList<String> availableChoicesGrupa = FXCollections.observableArrayList("ARh+","BRh+","ABRh+","0Rh+","ARh-","BRh-","ABRh-","0Rh-");

        grupaTransfuzjaChoiceBox.setItems(availableChoicesGrupa);

    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        assert pokazZasobyBtn != null : "fx:id=\"pokazZasobyBtn\" was not injected: check your FXML file 'brapp.fxml'.";
        assert grupaWyszukajTxt != null : "fx:id=\"grupaWyszukajTxt\" was not injected: check your FXML file 'brapp.fxml'.";
        assert centrumWyszukajTxt != null : "fx:id=\"centrumWyszukajTxt\" was not injected: check your FXML file 'brapp.fxml'.";
        assert grupaTransfuzjaChoiceBox != null : "fx:id=\"grupaTransfuzjaChoiceBox\" was not injected: check your FXML file 'brapp.fxml'.";
        assert liczbaJednostekTxt != null : "fx:id=\"liczbaJednostekTxt\" was not injected: check your FXML file 'brapp.fxml'.";
        assert transfuzjaBtn != null : "fx:id=\"transfuzjaBtn\" was not injected: check your FXML file 'brapp.fxml'.";
        assert wylogujBtn != null : "fx:id=\"wylogujBtn\" was not injected: check your FXML file 'brapp.fxml'.";
        assert tabela != null : "fx:id=\"tabela\" was not injected: check your FXML file 'appDB.fxml'.";
        assert grupaKrwiCol != null : "fx:id=\"grupaKrwiCol\" was not injected: check your FXML file 'appDB.fxml'.";
        assert zasobyCol != null : "fx:id=\"zasobyCol\" was not injected: check your FXML file 'appDB.fxml'.";
        assert jednostkiCol != null : "fx:id=\"jednostkiCol\" was not injected: check your FXML file 'appDB.fxml'.";
        assert tabela2 != null : "fx:id=\"tabela2\" was not injected: check your FXML file 'brapp.fxml'.";
        assert grupaKriw2Col != null : "fx:id=\"grupaKriw2Col\" was not injected: check your FXML file 'brapp.fxml'.";
        assert stan2Col != null : "fx:id=\"stan2Col\" was not injected: check your FXML file 'brapp.fxml'.";
        assert lokalizacja2Col != null : "fx:id=\"lokalizacja2Col\" was not injected: check your FXML file 'brapp.fxml'.";
        setChoiceBox();
    }
}
