package APIF1inve.src.multi22.classi;

import APIF1inve.src.multi22.*;

import java.util.ArrayList;

public class Pilota {

    private String givenName;
    private String driverId;
    private String familyName;
    private String dateOfBirth;
    private String nationality;
    private String annoCamp="";


    public Pilota(){

    }
    public Pilota(String givenName, String driverId, String familyName, String dateOfBirth, String nationality) {

        this.givenName = givenName;
        this.driverId = driverId;
        this.familyName = familyName;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
    }

    public Pilota(String familyName, String annoCamp){
        this.familyName = familyName;
        this.annoCamp = annoCamp;
    }
    public Pilota(Pilota other){
        this.givenName = other.familyName;
        this.driverId = other.driverId;
        this.familyName = other.familyName;
        this.dateOfBirth = other.dateOfBirth;
        this.nationality = other.nationality;
    }
    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getAnnoCamp() {
        return annoCamp;
    }

    public void setAnnoCamp(String annoCamp) {
        this.annoCamp = annoCamp;
    }
}
