package APIF1inve.src.multi22;

import APIF1inve.src.multi22.classi.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.spec.DSAGenParameterSpec;
import java.util.ArrayList;
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
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ResourceBundle;

import static javafx.scene.control.TableView.CONSTRAINED_RESIZE_POLICY;


public class circ_driver_guiController implements Initializable
{

    @FXML
    Label lab_start;
    ObservableList<Statistica > anni = FXCollections.observableArrayList();
    @FXML
    TableColumn<Statistica, String > col_pos_finale;
    Circuito circuitoSelezionato = MainController.circuitoSelected;
    Pilota pilotaSelezionato = Globali.pilotaSelected;
    @FXML
     TableView<Statistica> table_anni = new TableView<>();
    @FXML
     TableColumn<Statistica, String> col_anno = new TableColumn<>();
    @FXML
    Label lab_nome_pilota ;
    @FXML
    Label lab_nome_circuito;
    @FXML
    Label lab_gare;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        col_anno.setCellValueFactory(new PropertyValueFactory<Statistica, String>("anno"));
        col_pos_finale.setCellValueFactory(new PropertyValueFactory<Statistica, String>("pos_finale"));
        if (MainController.circuitoSelected.getCircuitName() == null){
            lab_nome_circuito.setText("Nessun circuito selezionato");
        }else {
            lab_nome_circuito.setText(MainController.circuitoSelected.getCircuitName());
        }
        if (Globali.pilotaSelected.getNationality() ==null ){
            lab_nome_pilota.setText("Nessun pilota selezionato");
        }else {
            lab_nome_pilota.setText(Globali.pilotaSelected.getFamilyName());
            for (String a : pilotaSelezionato.getStatistica().getAnniAttivi()) {
                try {
                    getCircuitiInAnno(a);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }
        if ((MainController.circuitoSelected.getCircuitName() != null) && (Globali.pilotaSelected.getNationality() !=null )){
            lab_start.setText("Gare disputate da "+pilotaSelezionato.getFamilyName()+" in " + circuitoSelezionato.getCircuitName() + ": "+ Integer.toString(anni.size()));
        }
        table_anni.setItems(anni);

        col_anno.setStyle("-fx-alignment: CENTER;");
        col_pos_finale.setStyle("-fx-alignment: CENTER;");
    }
    public void getCircuitiInAnno(String anno) throws IOException {
        String urlString = "http://ergast.com/api/f1/"+anno+"/drivers/"+pilotaSelezionato.getDriverId()+"/circuits.json?limit=15000";
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        Globali.nAPI++;
        // Leggi la risposta dal server
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        JSONObject root = new JSONObject(content.toString());

        JSONObject mrdata = root.getJSONObject("MRData");

        JSONObject circuitTable = mrdata.getJSONObject("CircuitTable");

        JSONArray circuits = circuitTable.getJSONArray("Circuits");

        for (int i = 0 ; i < circuits.length(); i++){
            JSONObject circuit = circuits.getJSONObject(i);
            String circuitoID = circuit.getString("circuitId");
            if (circuitoID.equalsIgnoreCase(circuitoSelezionato.getCircuitId())){
                Statistica s = new Statistica();

                s.setPos_finale(getRacePosition(anno));
                s.setAnno(anno);

                anni.add(s);
            }
        }
    }

    public String getRacePosition(String anno) throws IOException {
        String posizioneFinale ="";
        String urlString = "http://ergast.com/api/f1/"+anno+"/drivers/"+pilotaSelezionato.getDriverId()+"/results.json?limit=1500";
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        Globali.nAPI++;
        // Leggi la risposta dal server
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        JSONObject root = new JSONObject(content.toString());
        JSONObject mrdata = root.getJSONObject("MRData");

       JSONObject raceTable = mrdata.getJSONObject("RaceTable");


        JSONArray races = raceTable.getJSONArray("Races");
        JSONObject race = races.getJSONObject(0);
        JSONArray results = race.getJSONArray("Results");

        JSONObject result = results.getJSONObject(0);

        return result.getString("positionText");
    }
    public void getRoundNumber(String anno) throws IOException {

        String urlString = "http://ergast.com/api/f1/"+anno+"/drivers/"+pilotaSelezionato.getDriverId()+"/results.json?limit=1500";

        URL url = new URL(urlString);


        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        Globali.nAPI++;
        // Leggi la risposta dal server
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        JSONObject root = new JSONObject(content.toString());


        JSONObject mrdata = root.getJSONObject("MRData");

        JSONObject racetable = mrdata.getJSONObject("RaceTable");

        JSONArray races = racetable.getJSONArray("Races");

        System.out.println(urlString);

        System.out.println(races);

    }
}
