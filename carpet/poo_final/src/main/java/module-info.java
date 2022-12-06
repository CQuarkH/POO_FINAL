
module controllers.poo_final_proyect {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires mysql.connector.java;
    requires java.sql;


    opens controllers to javafx.fxml;
    opens model to javafx.base;

    exports controllers;
    exports model;



}