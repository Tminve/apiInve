package APIF1inve.src.multi22.classi;

import java.util.ArrayList;
import java.util.Map;

public class Statistica {

    private Pilota pilota;
    private int campionati;
    private int totalWins;
    private double totalPoints;
    private String percVittoria;
    private int totalWDCs;
    private int totalSeasons;
    private int totalPodiums;
    private String anniAttivit;
    private int totalRaces;
    private double pointsPerRace;
    private String anno;
    private String pos_finale;
    private ArrayList<String> anniAttivi = new ArrayList<>();


    public Statistica(){

    }
    public Statistica(Pilota pilota, int totalWins, double totalPoints, int totalWDCs, int totalSeasons, int totalRaces, double pointsPerRace, int totalPodiums, int campionati, String percVittoria,String anniAttivit) {
        this.pilota = pilota;
        this.totalWins = totalWins;
        this.totalPoints = totalPoints;
        this.totalWDCs = totalWDCs;
        this.totalSeasons = totalSeasons;
        this.totalRaces = totalRaces;
        this.percVittoria = percVittoria;
        this.pointsPerRace = pointsPerRace;
        this.totalPodiums = totalPodiums;
        this.campionati = campionati;
        this.anniAttivit = anniAttivit;
    }

    public Pilota getPilota() {
        return pilota;
    }

    public void setPilota(Pilota pilota) {
        this.pilota = pilota;
    }

    public int getTotalWins() {
        return totalWins;
    }

    public void setTotalWins(int totalWins) {
        this.totalWins = totalWins;
    }

    public double getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(double totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getTotalWDCs() {
        return totalWDCs;
    }

    public void setTotalWDCs(int totalWDCs) {
        this.totalWDCs = totalWDCs;
    }

    public int getTotalSeasons() {
        return totalSeasons;
    }

    public void setTotalSeasons(int totalSeasons) {
        this.totalSeasons = totalSeasons;
    }

    public int getTotalRaces() {
        return totalRaces;
    }

    public void setTotalRaces(int totalRaces) {
        this.totalRaces = totalRaces;
    }

    public double getPointsPerRace() {
        return pointsPerRace;
    }

    public void setPointsPerRace(double pointsPerRace) {
        this.pointsPerRace = pointsPerRace;
    }

    public int getTotalPodiums() {
        return totalPodiums;
    }

    public void setTotalPodiums(int totalPodiums) {
        this.totalPodiums = totalPodiums;
    }

    public int getCampionati() {
        return campionati;
    }

    public void setCampionati(int campionati) {
        this.campionati = campionati;
    }

    public String getPercVittoria() {
        return percVittoria;
    }

    public void setPercVittoria(String percVittoria) {
        this.percVittoria = percVittoria;
    }

    public String getAnniAttivit() {
        return anniAttivit;
    }

    public void setAnniAttivit(String anniAttivit) {
        this.anniAttivit = anniAttivit;
    }

    public ArrayList<String> getAnniAttivi() {
        return anniAttivi;
    }

    public void aggiungiAnno(String anno) {
        this.anniAttivi.add(anno);
    }

    public String getAnno() {
        return anno;
    }

    public void setAnno(String anno) {
        this.anno = anno;
    }

    public String getPos_finale() {
        return pos_finale;
    }

    public void setPos_finale(String pos_finale) {
        this.pos_finale = pos_finale;
    }
}
