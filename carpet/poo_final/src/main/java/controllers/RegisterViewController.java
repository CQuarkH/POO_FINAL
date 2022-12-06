package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.util.stream.Collectors;

public class RegisterViewController {

    @FXML
    private Button btnRegistrar_receta;

    public TextArea getTxtArea_receta() {
        return txtArea_receta;
    }

    public TextField getTxtField_etiquetas() {
        return txtField_etiquetas;
    }

    public TextField getTxtField_ingredientes() {
        return txtField_ingredientes;
    }

    public TextField getTxtField_nombre() {
        return txtField_nombre;
    }

    @FXML
    private TextArea txtArea_receta;
    @FXML
    private TextField txtField_etiquetas;
    @FXML
    private TextField txtField_ingredientes;
    @FXML
    private TextField txtField_nombre;

    public Receta getReceta_modd() {
        return receta_modd;
    }

    public Receta getReceta() {
        return receta;
    }

    private Receta receta_modd;

    private Receta receta;

    public void initializeAtributes(Receta receta){
        this.receta_modd = receta;
    }
    @FXML
    private void addReceta(ActionEvent event) throws Update_Exception, Insert_Exception {
        MySQL_Query query = new MySQL_Query();
        query.connect();
        //condicional para modificar alguna receta ya creada
        if (receta_modd!=null){

            receta_modd.setNombre(txtField_nombre.getText());
            receta_modd.setIngredientes(txtField_ingredientes.getText());
            receta_modd.setEtiquetas(txtField_etiquetas.getText());
            receta_modd.setReceta(txtArea_receta.getText());

            query.update(receta_modd);
        }else {
            query.selectAll();
            receta = new Receta(0, txtField_nombre.getText(), txtField_ingredientes.getText(), txtField_etiquetas.getText(), txtArea_receta.getText());

            if (!query.getAllRecetas().contains(receta)){
                query.insert(receta);
            }
            else {
                System.out.println("Error! La receta ya existe");
            }

        }
        Stage stage = (Stage) this.btnRegistrar_receta.getScene().getWindow();
        stage.close();



    }

}

