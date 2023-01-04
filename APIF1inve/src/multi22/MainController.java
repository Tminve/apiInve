package APIF1inve.src.multi22;

import APIF1inve.src.multi22.classi.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import java.net.URL;
import java.time.Year;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

import static javafx.scene.control.TableView.CONSTRAINED_RESIZE_POLICY;


public class MainController implements Initializable {
    private Scene scene;
    private MainApp mainApp;
    public static Circuito circuitoSelected = new Circuito();
    public Globali g;
    private int lastNApi = 0;

    ObservableList<Wdc> campioni = FXCollections.observableArrayList();
    ObservableList<Circuito> circuiti = FXCollections.observableArrayList();

    @FXML
    Label lab_circuito_selected;
    @FXML
    TextField text_input_circuito;
    @FXML
    Button button = new Button();
    @FXML
    private TableView<Circuito> table2;
    @FXML
    private TableColumn<Circuito, Integer> col_gare_ospitate;
    @FXML
    private TableColumn<Circuito, String> col_nome_circ;
    @FXML
    private TableColumn<Wdc, String> col_anno;
    @FXML
    private TableColumn<Wdc, String> col_campione;
    @FXML
    private TableView<Wdc> table;
    @FXML
    ImageView imageViewNazioni;
    @FXML
    Label lab_nazion;
    @FXML
    Label lab_attivi;
    @FXML
    Label lab_nome;

    @FXML
    Label lab_n_api;
    @FXML
    Label lab_stagioni;
    @FXML
    Label lab_gare;
    @FXML
    Label lab_perc_win;

    @FXML
    private ImageView ricercaStatusCircuito;
    @FXML
    Label lab_punti;
    @FXML
    Label lab_vittorie;
    @FXML
    Label lab_media;
    @FXML
    Label lab_out;
    @FXML
    Label lab_wdcs;
    @FXML
    TextField text_input_nazione;
    @FXML
    TextField text_nome_pilota;

    @FXML
    private void initialize() throws IOException {
        g = new Globali();


        //compila liste

        g.compila_lst_piloti();
        g.compila_lst_wdcs();
        compilaObservableCampioni();
        g.compila_lst_circuiti();
        g.compila_lst_scuderie();

        g.setGareOspitate();
    }

    public void setMainApp(MainApp mainApp) {
        //To change body of generated methods, choose Tools | Templates.
        this.mainApp = mainApp;
    }

    public void clickInvia() throws IOException {
        lab_out.setText(g.getInfo());
        table.setItems(campioni);
        lab_n_api.setText(Integer.toString(Globali.nAPI));
    }
    public void clickSpecifichePilota(ActionEvent actionEvent) throws IOException, CloneNotSupportedException {

        compilaLabelsDefault();
        String inputNomePilota = text_nome_pilota.getText();
        boolean f = false;
        String pilotaTrovatoID = "";
        for (Pilota p : Globali.lst_piloti) {
            if (inputNomePilota.equalsIgnoreCase(p.getFamilyName()) || inputNomePilota.equalsIgnoreCase(p.getGivenName()) || inputNomePilota.equalsIgnoreCase(p.getDriverId()) || inputNomePilota.equalsIgnoreCase(p.getGivenName() + " " + p.getFamilyName()) || inputNomePilota.equalsIgnoreCase(p.getFamilyName() + " " + p.getGivenName())) {
                f = true;
                pilotaTrovatoID = p.getDriverId();
                break;
            }
        }
        if (f) {

            compilaLabelsDefault();

            Statistica statistica = g.getStatistichePilota(pilotaTrovatoID);

            compilaLabels(statistica);


        } else {
            if (text_nome_pilota.equals("")) {
                text_nome_pilota.setPromptText("Inserisci il pilota");
            } else {
                text_nome_pilota.setText("");
                text_nome_pilota.setPromptText("Pilota inesistente");
            }

        }

        lab_n_api.setText(Integer.toString(Globali.nAPI));
    }
    public void compilaLabels(Statistica s) {

        lab_wdcs.setText(Integer.toString(s.getTotalWDCs()));
        lab_nome.setText(s.getPilota().getGivenName() + " " + s.getPilota().getFamilyName());
        lab_stagioni.setText(Integer.toString(s.getTotalSeasons()));
        lab_gare.setText(Integer.toString(s.getTotalRaces()));
        lab_punti.setText(Double.toString(s.getTotalPoints()));
        lab_vittorie.setText(Integer.toString(s.getTotalWins()));
        double rounded = (double) Math.round(s.getPointsPerRace() * 100) / 100;
        lab_media.setText(Double.toString(rounded));
        lab_attivi.setText(s.getAnniAttivit());
        lab_nazion.setText(s.getPilota().getNationality());
        lab_perc_win.setText(s.getPercVittoria());
        Image immagine = new Image("APIF1inve/img/flag/" + s.getPilota().getNationality() + ".jpg");
        imageViewNazioni.setImage(immagine);
        lab_n_api.setText(Integer.toString(Globali.nAPI));
    }
    public void compilaLabelsDefault() {

        lab_wdcs.setText("/");
        lab_nome.setText("/");
        lab_stagioni.setText("/");
        lab_gare.setText("/");
        lab_punti.setText("/");
        lab_vittorie.setText("/");
        lab_attivi.setText("/");
        lab_media.setText("/");
        lab_nazion.setText("/");
        lab_perc_win.setText("/");
        lab_n_api.setText(Integer.toString(Globali.nAPI));
    }
    public void random(ActionEvent actionEvent) throws IOException, CloneNotSupportedException {
        Random random = new Random();

        // Generate a random integer between 1 and 100 (inclusive)
        int randomInt = random.nextInt(Globali.lst_piloti.size());
        String randomPilotaId = Globali.lst_piloti.get(randomInt).getDriverId();

        text_nome_pilota.setText(Globali.lst_piloti.get(randomInt).getFamilyName());

        compilaLabelsDefault();

        Statistica statistica = g.getStatistichePilota(randomPilotaId);

        compilaLabels(statistica);
        lab_n_api.setText(Integer.toString(Globali.nAPI));
    }
    public void clickCercaNazioni(ActionEvent actionEvent) throws IOException {

        String input = text_input_nazione.getText();

        if (input.equalsIgnoreCase("")) {
            text_input_nazione.setText("");
            text_input_nazione.setPromptText("Nazione inesistente");
        } else {
            input = input.toLowerCase();
            String firstLetter = input.substring(0, 1);
            firstLetter = firstLetter.toUpperCase();
            String restOfString = input.substring(1);
            String result = firstLetter + restOfString;


            String traduzione = Globali.mapCircuiti.get(result);
            if (traduzione == null && ( !Globali.lst_nazioni.contains(result))) {
                text_input_nazione.setText("");
                text_input_nazione.setPromptText("Nazione inesistente");
            } else {
                if (traduzione == null){
                    compilaObservableCircuiti(result);
                }else {

                    compilaObservableCircuiti(traduzione);
                }
                table2.setItems(circuiti);
            }

        }
        lab_n_api.setText(Integer.toString(Globali.nAPI));
    }
    public void compilaObservableCampioni() {

// Imposta la politica di ridimensionamento delle colonne su CONSTRAINED_RESIZE_POLICY
        table.setColumnResizePolicy(CONSTRAINED_RESIZE_POLICY);
        for (Wdc w : Globali.lst_wdcs) {
            campioni.add(w);
        }
        lab_n_api.setText(Integer.toString(Globali.nAPI));
    }
    public void compilaObservableCircuiti(String nazione) {
        circuiti.clear();
        for (Circuito c : Globali.lst_circuiti) {
            if (c.getCountry().equalsIgnoreCase(nazione)) {
                circuiti.add(c);
            }
        }
        lab_n_api.setText(Integer.toString(Globali.nAPI));
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        col_anno.setCellValueFactory(new PropertyValueFactory<Wdc, String>("year"));
        col_campione.setCellValueFactory(new PropertyValueFactory<Wdc, String>("nomeCampione"));
        col_gare_ospitate.setCellValueFactory(new PropertyValueFactory<Circuito, Integer>("gareOspitate"));
        col_nome_circ.setCellValueFactory(new PropertyValueFactory<Circuito, String>("circuitName"));
        g = new Globali();


        //compila liste

        try {
            g.compila_lst_piloti();
            g.compila_lst_wdcs();
            compilaObservableCampioni();

            g.compila_lst_circuiti();
            g.compila_lst_scuderie();
            table2.setColumnResizePolicy(CONSTRAINED_RESIZE_POLICY);
            clickInvia();
            lab_n_api.setText(Integer.toString(Globali.nAPI));
            g.setGareOspitate();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void clickMostra(ActionEvent actionEvent){
        try {
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    Parent root = FXMLLoader.load(getClass().getResource("circ_driver_gui.fxml"));
                    stage.setScene(new Scene(root));
                    stage.show();

            } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
    public void clickCercaCircuito(ActionEvent actionEvent){
        String input = text_input_circuito.getText();

        if (input.equalsIgnoreCase("")) {
            Image immagine = new Image("APIF1inve/img/status/no.jpg");
            ricercaStatusCircuito.setImage(immagine);
            lab_circuito_selected.setText("/");
            text_input_circuito.setText("");
            text_input_circuito.setPromptText("Circuito inesistente");
        } else {

            boolean esiste = false;
            for (Circuito c : Globali.lst_circuiti){
                if((c.getCircuitName().contains(input) )|| (c.getCircuitId().equalsIgnoreCase(input))){
                    esiste = true;
                    String circuitId = c.getCircuitId();
                    String circuitName = c.getCircuitName();
                    String lat = c.getLat();
                    String longi = c.getLongi();
                    String locality = c.getLocality();
                    String country = c.getCountry();
                    int gareOspitate = c.getGareOspitate();

                    circuitoSelected = new Circuito(circuitId,circuitName,lat,longi,locality,country,gareOspitate);

                    break;
                }
            }
            if (esiste){
                Image immagine = new Image("APIF1inve/img/status/ok.jpg");
                ricercaStatusCircuito.setImage(immagine);
                lab_circuito_selected.setText(circuitoSelected.getCircuitName());
            }else {
                Image immagine = new Image("APIF1inve/img/status/no.jpg");
                ricercaStatusCircuito.setImage(immagine);
                lab_circuito_selected.setText("/");
                text_input_circuito.setText("");//l
                text_input_circuito.setPromptText("Circuito inesistente");
            }
        }
        lab_n_api.setText(Integer.toString(Globali.nAPI));
    }
    public void randomNazione(ActionEvent actionEvent){
        Random random = new Random();

        int randomInt = random.nextInt(Globali.lst_nazioni.size());
        String randomNazione = Globali.lst_nazioni.get(randomInt);
        compilaObservableCircuiti(randomNazione);
        text_input_nazione.setText(randomNazione);
        table2.setItems(circuiti);
        lab_n_api.setText(Integer.toString(Globali.nAPI));
    }

}
