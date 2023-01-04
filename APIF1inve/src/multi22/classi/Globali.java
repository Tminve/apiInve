package APIF1inve.src.multi22.classi;

import APIF1inve.src.multi22.classi.Pilota;
import com.sun.corba.se.spi.ior.ObjectKey;
import com.sun.xml.internal.ws.server.SDDocumentImpl;
import com.sun.xml.internal.ws.server.ServerRtException;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONObject;
import APIF1inve.src.multi22.*;

import javax.swing.text.Style;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.rmi.server.ExportException;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class Globali {
    public static ArrayList<Wdc> lst_wdcs = new ArrayList<>();

    public static Pilota pilotaSelected = new Pilota();
    public static ArrayList <String > annif1 = new ArrayList<>();
    public static ArrayList<Pilota> lst_piloti = new ArrayList<>();
    public static ArrayList<String> lst_nazioni = new ArrayList<>();
    public static ArrayList<Circuito> lst_circuiti = new ArrayList<>();
    public static HashMap<String, String> mapCircuiti = new HashMap<>();

    public static int nAPI = 0;

    public static ArrayList<Scuderia> lst_scuderie = new ArrayList<>();
    public void compila_lst_piloti() throws IOException {

        String urlString = "http://ergast.com/api/f1/drivers.json?limit=1000";
        URL url = new URL(urlString);


        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        nAPI++;

        // Leggi la risposta dal server
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();


        JSONObject root = new JSONObject(content.toString());


        //bruh

        // Get the "DriverTable" object
        JSONObject driverTable = root.getJSONObject("MRData").getJSONObject("DriverTable");

        // Get the "Drivers" array
        JSONArray drivers = driverTable.getJSONArray("Drivers");
        lst_piloti = new ArrayList<>();
        // Iterate through the "Drivers" array and add the "givenName" values to the ArrayList
        for (int i = 0; i < drivers.length(); i++) {

            JSONObject driver = drivers.getJSONObject(i);
            String givenName = driver.getString("givenName");
            String driverId = driver.getString("driverId");
            String familyName = driver.getString("familyName");
            String dateOfBirth = driver.getString("dateOfBirth");
            String nationality = driver.getString("nationality");

            Pilota p = new Pilota(givenName,driverId,familyName,dateOfBirth,nationality);

            lst_piloti.add(p);

        }



    }
    public void compila_lst_wdcs() throws IOException {

        String urlString = "http://ergast.com/api/f1/driverStandings/1.json?limit=150";
        URL url = new URL(urlString);


        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        nAPI++;
        // Leggi la risposta dal server
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();


        JSONObject root = new JSONObject(content.toString());


        //bruh

        // Get the "DriverTable" object
        JSONObject standingsTable = root.getJSONObject("MRData").getJSONObject("StandingsTable");
        // Get the "Drivers" array
        JSONArray standingsLists = standingsTable.getJSONArray("StandingsLists");


        lst_wdcs = new ArrayList<>();

        for (int i = 0; i < standingsLists.length(); i++){
            JSONObject champion = (JSONObject) standingsLists.get(i);

            String season =  champion.get("season").toString(); //Stagione
            String rounds =  champion.get("round").toString(); //gare nella stagione

            JSONArray driverStandings = champion.getJSONArray("DriverStandings");
            JSONObject driverStan = driverStandings.getJSONObject(0);

            String wins = driverStan.get("wins").toString(); //vittorie quella stagione
            String points = driverStan.get("points").toString(); //punti quella stagione

            JSONObject driver = driverStan.getJSONObject("Driver"); //Stats campione
            JSONArray constructorList = driverStan.getJSONArray("Constructors"); //Stats scuderia
            JSONObject constructor = constructorList.getJSONObject(0);

            String driverId = driver.get("driverId").toString();
            String givenName = driver.get("givenName").toString();
            String familyName = driver.get("familyName").toString();    // info pilota
            String dateOfBirth = driver.get("dateOfBirth").toString();
            String nationality = driver.get("nationality").toString();
            Pilota pilotaCampione = new Pilota(givenName,driverId,familyName,dateOfBirth,nationality);

            String constructorId = constructor.get("constructorId").toString();
            String constructorName = constructor.get("name").toString();
            String constructorNationality = constructor.get("nationality").toString();
            Scuderia scuderiaCampione = new Scuderia(constructorId,constructorName,constructorNationality);
            String nomeCampione = pilotaCampione.getFamilyName();
            Wdc worldChampion = new Wdc(pilotaCampione,season,points,wins,rounds,scuderiaCampione,nomeCampione);

            annif1.add(season);

            lst_wdcs.add(worldChampion);


        }



    }
    public void compila_lst_circuiti() throws IOException {


        String urlString = "http://ergast.com/api/f1/circuits.json?limit=15000";
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        nAPI++;
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

        for (int i = 0 ; i < circuits.length() ; i++){
            JSONObject circuit = circuits.getJSONObject(i);
            String circuitId = circuit.getString("circuitId");
            String circuitName = circuit.getString("circuitName");
            JSONObject location = circuit.getJSONObject("Location");
                String lat = location.getString("lat");
                String longi  = location.getString("long");
                String locality = location.getString("locality");
                String country = location.getString("country");

                int totalHosts = 0;

            Circuito nuovo_circuito = new Circuito(circuitId,circuitName,lat,longi,locality,country);

            if (lst_nazioni.contains(country)){}
            else {
                lst_nazioni.add(country);
            }
            lst_circuiti.add(nuovo_circuito);
        }
        creaHashmap();
    }
    public void compila_lst_scuderie() throws IOException {

        String urlString = "http://ergast.com/api/f1/constructors.json?limit=15000";
        URL url = new URL(urlString);


        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        nAPI++;
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
        JSONObject constructorTable = mrdata.getJSONObject("ConstructorTable");
        JSONArray constructors = constructorTable.getJSONArray("Constructors");
        for (int i = 0 ; i< constructors.length(); i++){
            JSONObject contructor = constructors.getJSONObject(i);
            Scuderia nuova_scuderia = new Scuderia(contructor.getString("constructorId"),contructor.getString("name"),contructor.getString("nationality"));
            lst_scuderie.add(nuova_scuderia);
        }


    }
    public String getInfo(){
        String info = "";

        info += "Ci sono " + lst_piloti.size() +" piloti, " + lst_wdcs.size() +" campioni del mondo, "+lst_scuderie.size()+" scuderie e "+lst_circuiti.size()+" circuiti";

        return info;
    }
    public Statistica getStatistichePilota(String driverId) throws IOException, CloneNotSupportedException {


//        http://ergast.com/api/f1/2010/drivers/"+driverId+"/results.json
//        http://ergast.com/api/f1/drivers/"+driverId+"/driverStandings.json?limit=15000



        String urlString = "http://ergast.com/api/f1/drivers/"+driverId+"/driverStandings.json?limit=15000";
        URL url = new URL(urlString);


        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        nAPI++;
        // Leggi la risposta dal server
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();

        JSONObject root = new JSONObject(content.toString());

        // Ottieni l'array di "StandingsLists"
        JSONArray standingsLists = root.getJSONObject("MRData")
                .getJSONObject("StandingsTable")
                .getJSONArray("StandingsLists");

        // Somma il valore di "points" per ogni oggetto "DriverStandings"



        ///AAAAA

        double totalPoints = 0;
        for (int i = 0; i < standingsLists.length(); i++) {
            JSONObject standingsList = standingsLists.getJSONObject(i);
            JSONArray driverStandings = standingsList.getJSONArray("DriverStandings");
            for (int j = 0; j < driverStandings.length(); j++) {
                JSONObject driverStanding = driverStandings.getJSONObject(j);
                double points = driverStanding.getDouble("points");
                totalPoints += points;
            }

        }


        // Somma il valore di "wins" per ogni oggetto "DriverStandings"
        int totalWins = getVittorieTot(driverId);

        ///AAAAA



        int totalWDCs = 0;
        for (Wdc campione : lst_wdcs){
            if (driverId.equalsIgnoreCase(campione.getDriver().getDriverId())){
                totalWDCs ++;
            }
        }

        pilotaSelected = null;
        int i = 0 ;
        for (Pilota pilota : lst_piloti){

            if (driverId.equalsIgnoreCase(pilota.getDriverId())){
                pilotaSelected = new Pilota();
                pilotaSelected.setAnnoCamp(pilota.getAnnoCamp());
                pilotaSelected.setDateOfBirth(pilota.getDateOfBirth());
                pilotaSelected.setDriverId(pilota.getDriverId());
                pilotaSelected.setFamilyName(pilota.getFamilyName());
                pilotaSelected.setNationality(pilota.getNationality());
                pilotaSelected.setGivenName(pilota.getGivenName());

                break;
            }

        } //prende il pilota dalla lista piloti



        // Crea una lista vuota per le stagioni
        ArrayList<String> seasons = new ArrayList<>(); //Salva in una lista tutte le stagioni del pilota
        // Aggiungi il valore di "season" per ogni oggetto "StandingsList" alla lista
        for (i = 0; i < standingsLists.length(); i++) {
            JSONObject standingsList = standingsLists.getJSONObject(i);
            String season = standingsList.getString("season");
            seasons.add(season);

        }


        int totalRaces = 0;
        for (String anno : seasons){                                            //Gare totali (cicla per ogni anno)


             totalRaces += getGarePerAnno(anno,driverId);
        }


        int totalPodiums = 0;
        for (String anno : seasons){                                            //Podi totali (cicla per ogni anno)
            totalPodiums+= getPodiPerAnno(anno,driverId);
        }

        Double perc = (double) Math.round(Double.valueOf((totalWins *  100 ) / totalRaces) * 100) / 100;
        String percVitt = Double.toString(perc)+"%";




        String anniAttivit = seasons.get(0) +"-"+seasons.get(seasons.size()-1);
        Statistica stats = new Statistica(pilotaSelected,totalWins,totalPoints,totalWDCs,seasons.size(),totalRaces,totalPoints/totalRaces,totalPodiums,totalWDCs,percVitt,anniAttivit);

        for (String a : seasons){
            stats.aggiungiAnno(a);
        }

        pilotaSelected.setStatistica(stats);

        return stats;
    }
    public int getGarePerAnno(String anno, String driverId) throws IOException {
        int totalRaces = 0;

            String urlString = "http://ergast.com/api/f1/"+anno+"/drivers/"+driverId+"/results.json?limit=1500";

            URL url = new URL(urlString);


            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            nAPI++;
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
            totalRaces = races.length();


        return totalRaces;
    }
    public int getPodiPerAnno(String anno,  String driverId) throws IOException {
        int totalPodiums = 0;



            String urlString = "http://ergast.com/api/f1/"+anno+"/drivers/"+driverId+"/results.json?limit=1500";


            URL url = new URL(urlString);


            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            nAPI++;
            // Leggi la risposta dal server
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            JSONObject root = new JSONObject(content.toString());

            // Ottieni l'array di "StandingsLists"
            JSONObject standingsLists = root.getJSONObject("MRData");
            JSONObject raceTable = standingsLists.getJSONObject("RaceTable");
            JSONArray races = raceTable.getJSONArray("Races");
            JSONObject results = races.getJSONObject(0);
            JSONArray results2 = results.getJSONArray("Results");

            JSONObject position = results2.getJSONObject(0);

            if( Integer.parseInt( position.getString("position"))<=3){
                totalPodiums++;
            }

        return totalPodiums;


    }
    public int getVittorieTot(String driverId) throws IOException {
        int totalWins = 0;

        String urlString = "http://ergast.com/api/f1/drivers/"+driverId+"/results/1.json?limit=19999";
        URL url = new URL(urlString);


        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        nAPI++;
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


        return Integer.parseInt(mrdata.getString("total"));
    }
    public void creaHashmap() throws IOException {
        mapCircuiti.put("Australia","Australia");
        mapCircuiti.put("Marocco","Morocco");
        mapCircuiti.put("Inghilterra","UK");
        mapCircuiti.put("USA","USA");
        mapCircuiti.put("uk","UK");
        mapCircuiti.put("Svezia","Sweden");
        mapCircuiti.put("Germania","Germany");
        mapCircuiti.put("Bahrain","Bahrain");
        mapCircuiti.put("Azerbaijan","Azerbaijan");
        mapCircuiti.put("Azerbaigian","Azerbaijan");
        mapCircuiti.put("Portogallo","Portugal");
        mapCircuiti.put("Svizzera","Switzerland");
        mapCircuiti.put("India","India");
        mapCircuiti.put("Spagna","Spain");
        mapCircuiti.put("Francia","France");
        mapCircuiti.put("Giappone","Japan");
        mapCircuiti.put("Argentina","Argentina");
        mapCircuiti.put("Sud africa","South Africa");
        mapCircuiti.put("Ungheria","Hungary");
        mapCircuiti.put("Italia","Italy");
        mapCircuiti.put("Brasile","Brazil");
        mapCircuiti.put("Turchia","Turkey");
        mapCircuiti.put("Arabia saudita","Saudi Arabia");
        mapCircuiti.put("Qatar","Qatar");
        mapCircuiti.put("Singapore","Singapore");
        mapCircuiti.put("Monaco","Monaco");
        mapCircuiti.put("Canada","Canada");
        mapCircuiti.put("Belgio","Belgium");
        mapCircuiti.put("Austria","Austria");
        mapCircuiti.put("Messico","Mexico");
        mapCircuiti.put("Malesia","Malaysia");
        mapCircuiti.put("Cina","China");
        mapCircuiti.put("Russia","Russia");
        mapCircuiti.put("America","United States");
        mapCircuiti.put("Emirati arabi uniti","UAE");
        mapCircuiti.put("UAE","UAE");
        mapCircuiti.put("korea del sud","Korea");
        mapCircuiti.put("Olanda","Netherlands");
        mapCircuiti.put("Paesi bassi","Netherlands");

    }
    public void setGareOspitate() throws IOException {

        for (String anno : annif1) {
            String urlString = "http://ergast.com/api/f1/"+anno+"/circuits.json?limit=2162561";
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            nAPI++;
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
            JSONObject circuitTable  = mrdata.getJSONObject("CircuitTable");
            JSONArray circuits = circuitTable.getJSONArray("Circuits");
            for (int i = 0 ; i < circuits.length(); i++){
                JSONObject circuit = circuits.getJSONObject(i);

                for (Circuito c : lst_circuiti){
                    if (c.getCircuitId().equalsIgnoreCase(circuit.getString("circuitId"))){
                        c.upTotalHosts();
                    }
                }

            }


        }
    }

}


