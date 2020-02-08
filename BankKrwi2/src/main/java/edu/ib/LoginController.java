package edu.ib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * Simple Controller class for opearting on window
 * For logging in window either as
 * Blood donation center or regional bank
 *
 */
public class LoginController {

    /**
     * Tool to connecting to database
     */
    private DBUtil dbUtil;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> kontoChoiceBox;

    @FXML
    private TextField nazwaUzytkownikaTxt;

    @FXML
    private PasswordField hasloTxt;

    @FXML
    private Button zalogujBtn;

    /**
     * Logging in button pressed
     * @param event
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @FXML
    public void zalogujBtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        zaloguj();

    }

    /**
     * Read file with accounts of blood donation centers
     * @param nazwa file Name
     * @return two dimensional String array of username and hashed password
     * @throws IOException
     */
    public static String[][] odczytPlikuTekstowego(String nazwa) throws IOException {

        BufferedReader plik = null;
        String[][] dane = new String[4][2];


        try {
            plik = new BufferedReader(new FileReader(new File(nazwa)));

            String line = plik.readLine();
            String[] daneCentrum;
            while (line != null) {
                for(int i=0;i<4;i++) {

                    daneCentrum = line.split(",");
                    dane[i][0] = daneCentrum[0];
                    dane[i][1] = daneCentrum[1];
                    line = plik.readLine();
                }
            }
        } finally {
            if (plik != null) {
                plik.close();
            }
        }

        return dane;
    }

    /**
     * Set choice box items
     */
    void setChoiceBox() {

        ObservableList<String> availableChoices = FXCollections.observableArrayList("Centrum Krwiodawstwa","Bank Regionalny");

        kontoChoiceBox.setItems(availableChoices);
    }

    /**
     * Try to log in if ENTER key is pressed on password text fiels
     * @param event
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @FXML
    void hasloOnKeyPressed(KeyEvent event) throws SQLException, ClassNotFoundException {

        if(event.getCode().equals(KeyCode.ENTER)) {

            zaloguj();
        }

    }

    /**
     * Logging in method
     * Checking if every field is not empty and if user has acces to the app
     * Redirects user to corresponding window
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void zaloguj() throws SQLException, ClassNotFoundException {

        String username = nazwaUzytkownikaTxt.getText();
        String password = hasloTxt.getText();

        String codedPassword = SimpleMD5E.hashPassword(password);



        if(!kontoChoiceBox.getSelectionModel().isEmpty() && !username.isEmpty() && !password.isEmpty()) {


            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Przyznano dostęp użytkownikowi \"" + username + "\".");

            try {

                FXMLLoader loader = null;
                Parent root = null;

                if (kontoChoiceBox.getValue().equals("Centrum Krwiodawstwa")) {

                    dbUtil = new DBUtil("centrum", "passwordCentrum");
                    dbUtil.dbConnect();

                    String[][] dane = odczytPlikuTekstowego("C:\\Users\\alicj\\LISTY\\BD\\BankKrwi2\\src\\main\\resources\\edu\\ib\\centrumPasswords.txt");


                    for(int i=0;i<4;i++){

                        if (username.equals(dane[i][0]) && codedPassword.equals(dane[i][1])) {
                            loader = new FXMLLoader(getClass().getResource("ckapp.fxml"));
                            root = loader.load();
                            CKController ckController = loader.getController();
                            ckController.setDB(username, password);
                            ckController.setIdCentrum();
                        }
                    }

                }
                else if (kontoChoiceBox.getValue().equals("Bank Regionalny")) {

                    dbUtil = new DBUtil(username,password);
                    dbUtil.dbConnect();
                    loader = new FXMLLoader(getClass().getResource("brapp.fxml"));
                    root = loader.load();
                    BRController brController = loader.getController();
                    brController.setDB(username, password);

                }

                Stage stage = new Stage();
                Scene s = new Scene(root);
                stage.setScene(s);
                stage.setResizable(false);
                s.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                stage.initModality(Modality.WINDOW_MODAL);
                stage.show();

                alert.show();

                Stage myScene = (Stage) zalogujBtn.getScene().getWindow();
                myScene.close();

            } catch (IOException | NullPointerException e) {
                Alert alertChoiceBox = new Alert(Alert.AlertType.WARNING);
                alertChoiceBox.setHeaderText(null);
                alertChoiceBox.setContentText("Nieprawidłowe hasło bądź nazwa użytkownika.");
                alertChoiceBox.show();

            }

        } else {
            Alert alertChoiceBox = new Alert(Alert.AlertType.WARNING);
            alertChoiceBox.setHeaderText(null);
            alertChoiceBox.setContentText("Wybierz konto i wprowadź pola.");
            alertChoiceBox.show();
        }
    }

    @FXML
    void initialize() {
        assert kontoChoiceBox != null : "fx:id=\"kontoChoiceBox\" was not injected: check your FXML file 'login.fxml'.";
        assert nazwaUzytkownikaTxt != null : "fx:id=\"nazwaUzytkownikaTxt\" was not injected: check your FXML file 'login.fxml'.";
        assert hasloTxt != null : "fx:id=\"hasloTxt\" was not injected: check your FXML file 'login.fxml'.";
        assert zalogujBtn != null : "fx:id=\"zalogujBtn\" was not injected: check your FXML file 'login.fxml'.";
        setChoiceBox();
    }
}
