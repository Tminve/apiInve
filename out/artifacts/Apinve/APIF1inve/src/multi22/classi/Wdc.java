package APIF1inve.src.multi22.classi;

import java.util.ArrayList;

public class Wdc {

    private Scuderia constructor;
    private Pilota driver;
    private String year;
    private String points;
    private String wins;
    private String rounds;

    private String nomeCampione;



    public Wdc(Pilota driver, String year, String points, String wins, String rounds, Scuderia constructor,String nomeCampione) {
        this.driver = driver;
        this.year = year;
        this.points = points;
        this.wins = wins;
        this.rounds = rounds;
        this.constructor = constructor;
        this.nomeCampione = nomeCampione;
    }
    @Override
    public Wdc clone() throws CloneNotSupportedException {
        return (Wdc) super.clone();
    }
    public Pilota getDriver() {
        return driver;
    }

    public void setDriver(Pilota driver) {
        this.driver = driver;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getWins() {
        return wins;
    }

    public void setWins(String wins) {
        this.wins = wins;
    }

    public String getRounds() {
        return rounds;
    }

    public void setRounds(String rounds) {
        this.rounds = rounds;
    }

    public Scuderia getConstructor() {
        return constructor;
    }

    public void setConstructor(Scuderia constructor) {
        this.constructor = constructor;
    }

    public String getNomeCampione() {
        return nomeCampione;
    }

    public void setNomeCampione(String nomeCampione) {
        this.nomeCampione = nomeCampione;
    }
}
