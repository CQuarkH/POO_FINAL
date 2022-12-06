package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class StartViewController implements Initializable {

    @FXML
    private Button comenzar_button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    private void comenzar (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("user-view.fxml"));
        Parent comenzar = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(comenzar);
        scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("app.css")).toString());
        stage.setTitle("Buscar Recetas");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        Stage s = (Stage) this.comenzar_button.getScene().getWindow();
        s.close();
    }

}
