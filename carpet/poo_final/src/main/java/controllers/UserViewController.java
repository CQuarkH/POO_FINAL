package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.MySQL_Query;
import model.Receta;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class UserViewController implements Initializable {

    @FXML
    private Button btnInsert;
    @FXML
    private Button btn_buscar;
    @FXML
    private TableColumn ingredientesCol;
    @FXML
    private TableColumn  nombreCol;
    @FXML
    private TableColumn etiquetasCol;
    @FXML
    private TableColumn  recetaCol;
    @FXML
    private TableView <Receta> tblRecetas;
    @FXML
    private TextField textField_Buscar;
    private ObservableList <Receta> recetas;
    private Receta moddOrDeleteReceta;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        recetas = FXCollections.observableArrayList();

        this.nombreCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.ingredientesCol.setCellValueFactory(new PropertyValueFactory<>("ingredientes"));
        this.etiquetasCol.setCellValueFactory(new PropertyValueFactory<>("etiquetas"));
        this.recetaCol.setCellValueFactory(new PropertyValueFactory<>("receta"));
    }
    @FXML
    private void buscarReceta(ActionEvent event) {
        MySQL_Query query = new MySQL_Query();
        query.connect();
        query.select(textField_Buscar.getText());

        recetas.clear();
        this.recetas.addAll(query.getTblRecetas());

        this.tblRecetas.setItems(recetas);
    }
    @FXML
    private void register(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("register-view.fxml"));
        Parent register = loader.load();

        RegisterViewController rg = loader.getController();

        Stage stage = new Stage();
        Scene scene = new Scene(register);
        scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("app.css")).toString());
        stage.setTitle("Registrar Receta");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();

        if (rg.getReceta() != null || rg.getReceta_modd() !=null) {
            tblRecetas.refresh();
        }
    }
    @FXML
    private void seleccionar(MouseEvent event) throws IOException {
        moddOrDeleteReceta = this.tblRecetas.getSelectionModel().getSelectedItem();
        if (moddOrDeleteReceta != null) {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("modd-delete-view.fxml"));
            Parent root = loader.load();
            ModdOrDeleteController md = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("app.css")).toString());
            stage.setTitle("Modificar o Eliminar");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

            md.initializeAtributes(moddOrDeleteReceta);
            tblRecetas.refresh();





        }
    }

}


