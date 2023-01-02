package APIF1inve.src.multi22;

import APIF1inve.src.multi22.classi.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.Random;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

import static javafx.scene.control.TableView.CONSTRAINED_RESIZE_POLICY;


public class circ_driver_guiController implements Initializable
{
    @FXML
    Label lab_nome_pilota ;
    @FXML
    Label lab_nome_circuito;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (MainController.circuitoSelected.getCircuitName() == null){
            lab_nome_circuito.setText("Nessun circuito selezionato");
        }else {
            lab_nome_circuito.setText(MainController.circuitoSelected.getCircuitName());
        }
        if (Globali.pilotaSelected.getNationality() ==null ){
            lab_nome_pilota.setText("Nessun pilota selezionato");
        }else {
            lab_nome_pilota.setText(Globali.pilotaSelected.getFamilyName());
        }

    }
}
