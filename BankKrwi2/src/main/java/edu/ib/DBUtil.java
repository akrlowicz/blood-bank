package edu.ib;

//import com.github.vldrus.sql.rowset.CachedRowSetWrapper;
import javafx.scene.control.Alert;


import javax.sql.rowset.CachedRowSet;
import java.sql.*;

/**
 * Data Base Util class
 * Responsible for connecting to database and disconnecting
 * Executing queries and procedures
 * Returns results with ResultSet class
 */
public class DBUtil {

    /**
     * User name
     */
    private String userName;
    /**
     * User password
     */
    private String userPassword;
    /**
     * Alert dialog to communicate with user
     */
    private Alert alert;

    /**
     * Connection field
     */
    private Connection conn = null;

    /**
     * Constructor of DBUtil object
     * @param userName
     * @param userPassword
     */
    public DBUtil(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;

    }

    /**
     * Connecting to database using username and password
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void dbConnect() throws SQLException, ClassNotFoundException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("No MySQL JDBC Driver found.");
            alert.show();

            e.printStackTrace();
            throw e;
        }

        try {
            conn = DriverManager.getConnection(createURL());
        } catch (SQLException e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Błąd połączenia! Spróbuj ponownie.");
            alert.show();

            e.printStackTrace();
            throw e;
        }

    }

    /**
     * Disconnecting from database
     * @throws SQLException
     */
    public void dbDisconnect() throws SQLException {

        try {

            if (conn != null && !conn.isClosed()) {

                conn.close();
                System.out.println("Disconnected");

            }
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Creating URL with username and password to connect to database
     * Use in dbConnect
     * @return url String
     */
    private String createURL() {

        StringBuilder urlSB = new StringBuilder("jdbc:mysql://");
        urlSB.append("localhost:3306/");
        urlSB.append("bank_krwi?");
        urlSB.append("useUnicode=true&characterEncoding=utf-8");
        urlSB.append("&user=");
        urlSB.append(userName);
        urlSB.append("&password=");
        urlSB.append(userPassword);
        urlSB.append("&serverTimezone=CET");

        return urlSB.toString();
    }

    /**
     * Executing procedure for utilization of blood
     * @param sqlStmt SQL statement from BankKrwiDAO Class
     * @param id id of blood type
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void procedurePrzeterminowana(String sqlStmt, Integer id) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        CallableStatement cs = null;

        try {
            dbConnect();

            cs = conn.prepareCall(sqlStmt);

            cs.setInt(1,id);

            boolean hasResults =  cs.execute();

            while (hasResults){
                rs = cs.getResultSet();

                hasResults = cs.getMoreResults();
            }

        } catch (SQLException e) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Wystąpił problem podczas operacji procedury utylizacji krwi.");
            alert.show();
            throw e;

        } finally {
            if (rs != null) {
                rs.close();
            }
            if(cs != null) {
                cs.close();
            }
        }

        dbDisconnect();
    }

    /**
     * Executing tranfusion procedure
     * @param sqlStmt  SQL statement
     * @param grupaKrwi blood type
     * @param ilosc number of units
     * @return messege if transfusion was successfull
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public String procedureTransfuzja(String sqlStmt, String grupaKrwi, Integer ilosc)throws SQLException, ClassNotFoundException {

        ResultSet rs = null;
        CallableStatement cs = null;
        String komunikat;

        try {
            dbConnect();

            cs = conn.prepareCall(sqlStmt);

            cs.setString(1,grupaKrwi);
            cs.setInt(2,ilosc);
            cs.registerOutParameter(3, Types.VARCHAR);

            boolean hasResults =  cs.execute();

            while (hasResults){
                rs = cs.getResultSet();

                hasResults = cs.getMoreResults();
            }

            komunikat = cs.getString(3);

        } catch (SQLException e) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Wystąpił problem podczas operacji procedury oddawania krwi.");
            alert.show();
            throw e;

        } finally {
            if (rs != null) {
                rs.close();
            }
            if(cs != null) {
                cs.close();
            }
        }

        dbDisconnect();

        return komunikat;
    }

    /**
     * Executing blood dontaion procedure
     * @param sqlStmt SQL statement
     * @param czyOddawal if donor was already registered
     * @param imieNazwisko name of donor
     * @param idCentrum id of blood donation center
     * @return messege whether procedure was successfull
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public String procedureOddanieKrwi(String sqlStmt,String czyOddawal,String imieNazwisko, Integer idCentrum) throws SQLException, ClassNotFoundException {

        ResultSet rs = null;
        CallableStatement cs = null;
        String komunikat;

        try {
            dbConnect();

            cs = conn.prepareCall(sqlStmt);

            cs.setString(1,czyOddawal);
            cs.setString(2,imieNazwisko);
            cs.setInt(3,idCentrum);
            cs.registerOutParameter("komunikat",Types.VARCHAR);
           // cs.registerOutParameter(4,Types.VARCHAR);

            boolean hasResults =  cs.execute();

            while (hasResults){
                rs = cs.getResultSet();

                hasResults = cs.getMoreResults();
            }

            komunikat = cs.getString("komunikat");

        } catch (SQLException e) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Wystąpił problem podczas operacji procedury oddawania krwi.");
            alert.show();
            throw e;

        } finally {
            if (rs != null) {
                rs.close();
            }
            if(cs != null) {
                cs.close();
            }
        }

        dbDisconnect();

        return komunikat;
    }

    /**
     * Executing registering donor procedure
     * @param sqlStmt SQL statement
     * @param imieNazwisko name of donor
     * @param numerTelefonu donor's phone number
     * @param dataUrodzenia donor's date of birth
     * @param waga weight of donor
     * @param wzrost height of donor
     * @param plec sex of donor
     * @return integer value representing if procedure was successfull -1/1
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int procedureZarejestrujDawce(String sqlStmt,String imieNazwisko, Integer numerTelefonu, String grupa, String rh,String dataUrodzenia,Integer waga, Integer wzrost,String plec) throws SQLException, ClassNotFoundException {


        ResultSet rs = null;
        CallableStatement cs = null;
        int komunikat = 0;

        try {
            dbConnect();

            cs = conn.prepareCall(sqlStmt);

            cs.setString(1,imieNazwisko);
            cs.setInt(2,numerTelefonu);
            cs.setString(3,grupa);
            cs.setString(4,rh);
            cs.setString(5,dataUrodzenia);
            cs.setInt(6,waga);
            cs.setInt(7,wzrost);
            cs.setString(8,plec);
            cs.registerOutParameter(9,Types.INTEGER);

            boolean hasResults =  cs.execute();

            while (hasResults){
                 rs = cs.getResultSet();

                hasResults = cs.getMoreResults();
            }

            komunikat = cs.getInt(9);

        } catch (SQLException e) {

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Wystąpił problem podczas operacji procedury rejestracji.");
            alert.show();
            throw e;

       } finally {
            if (rs != null) {
                rs.close();
            }
            if(cs != null) {
                cs.close();
            }
        }
        dbDisconnect();

        return komunikat;
    }

    /**
     * Executing SQL query
     * @param queryStmt SQL query statement
     * @return results of query with ResultSet class
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {

        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        CachedRowSet crs;

        try {

            dbConnect();

            stmt = conn.prepareStatement(queryStmt);

            resultSet = stmt.executeQuery(queryStmt);

            crs = new CachedRowSetWrapper();

            crs.populate(resultSet);

        } catch (SQLException e) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Wystąpił problem podczas wykonywania zapytania.");
            alert.show();
            throw e;

        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            dbDisconnect();
        }

        return crs;
    }



}
