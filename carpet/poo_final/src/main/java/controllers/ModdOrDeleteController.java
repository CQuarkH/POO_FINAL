package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Delete_Exception;
import model.MySQL_Query;
import model.Receta;

import java.io.IOException;
import java.util.Objects;

public class ModdOrDeleteController {

    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnModificar;
    private Receta receta_moddOrDelete;
    public void initializeAtributes(Receta receta) {
        this.receta_moddOrDelete = receta;

    }
    @FXML
    private void eliminar(ActionEvent event) throws Delete_Exception {
        MySQL_Query query = new MySQL_Query();
        query.connect();
        query.delete(receta_moddOrDelete);

        Stage stage = (Stage) this.btnEliminar.getScene().getWindow();
        stage.close();




    }
    @FXML
    private void modificar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("register-view.fxml"));
        Parent root = loader.load();
        RegisterViewController rc = loader.getController();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("app.css")).toString());
        stage.setTitle("Modificar");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        rc.initializeAtributes(receta_moddOrDelete);


        rc.getTxtField_nombre().setText(receta_moddOrDelete.getNombre().replace("\n", " "));
        rc.getTxtField_ingredientes().setText(receta_moddOrDelete.getIngredientes().replace("\n", " "));
        rc.getTxtField_etiquetas().setText(receta_moddOrDelete.getEtiquetas().replace("\n", " "));
        rc.getTxtArea_receta().setText(receta_moddOrDelete.getReceta());


        Stage stage1 = (Stage) this.btnModificar.getScene().getWindow();
        stage1.close();






    }

}
