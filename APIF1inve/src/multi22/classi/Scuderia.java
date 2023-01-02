package APIF1inve.src.multi22.classi;

public class Scuderia {

    private String constructureId;
    private String constructureName;
    private String constructureNationality;

    public Scuderia(String constructureId, String constructureName, String constructureNationality) {
        this.constructureId = constructureId;
        this.constructureName = constructureName;
        this.constructureNationality = constructureNationality;
    }

    public String getConstructureId() {
        return constructureId;
    }

    public void setConstructureId(String constructureId) {
        this.constructureId = constructureId;
    }

    public String getConstructureName() {
        return constructureName;
    }

    public void setConstructureName(String constructureName) {
        this.constructureName = constructureName;
    }

    public String getConstructureNationality() {
        return constructureNationality;
    }

    public void setConstructureNationality(String constructureNationality) {
        this.constructureNationality = constructureNationality;
    }
}
