package projet.composant.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cours {

    @SerializedName("id")
    private int id;

    @SerializedName("ens")
    private String ens;

    @SerializedName("filiere")
    private String filiere;

    @SerializedName("matiere")
    private String matiere;

    @SerializedName("classe")
    private String classe;

    @SerializedName("vh")
    private String vh;

    public Cours() {
    }

    public Cours(int id, String ens, String filiere, String matiere, String classe, String vh) {
        this.id = id;
        this.ens = ens;
        this.filiere = filiere;
        this.matiere = matiere;
        this.classe = classe;
        this.vh = vh;
    }


    @Override
    public String toString() {
        return "cours{" +
                "id=" + id +
                ", ens= " + ens + '\'' +
                ", matiere= " + matiere + '\'' +
                ", classe= " + classe + '\'' +
                ", filiere= " + filiere + '\'' +
                ", vh='" + vh + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEns() {
        return ens;
    }

    public void setEns(String ens) {
        this.ens = ens;
    }

    public String getFiliere() {
        return filiere;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getVh() {
        return vh;
    }

    public void setVh(String vh) {
        this.vh = vh;
    }
}
