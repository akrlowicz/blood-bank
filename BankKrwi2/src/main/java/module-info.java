module edu.ib {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.sql.rowset;


    opens edu.ib to javafx.fxml;
    exports edu.ib;
}