package APIF1inve.src.multi22.classi;

public class Circuito {
    private String circuitId;
    private String circuitName;
    private String lat;
    private String longi;
    private String locality;
    private String country;
    private int gareOspitate;

    public Circuito(){

    }
    public Circuito(String circuitId, String circuitName, String lat, String longi, String locality, String country) {
        this.circuitId = circuitId;
        this.circuitName = circuitName;
        this.lat = lat;
        this.longi = longi;
        this.locality = locality;
        this.country = country;
    }
    public Circuito(String circuitId, String circuitName, String lat, String longi, String locality, String country,int gareOspitate) {
        this.circuitId = circuitId;
        this.circuitName = circuitName;
        this.lat = lat;
        this.longi = longi;
        this.locality = locality;
        this.country = country;
        this.gareOspitate = gareOspitate;
    }
    public String getCircuitId() {
        return circuitId;
    }

    public void setCircuitId(String circuitId) {
        this.circuitId = circuitId;
    }

    public String getCircuitName() {
        return circuitName;
    }

    public void setCircuitName(String circuitName) {
        this.circuitName = circuitName;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public void upTotalHosts(){
        this.gareOspitate++;
    }

    public int getGareOspitate() {
        return gareOspitate;
    }
}
