package projet.composant.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Programmer {

    @SerializedName("id")
    private int id;

    @SerializedName("nom_enseignant")
    private String nom_enseignant;

    @SerializedName("filiere")
    private String filiere;

    @SerializedName("matiere")
    private String matiere;

    @SerializedName("classe")
    private String classe;

    @SerializedName("vh")
    private String vh;

    public Programmer() {
    }

    public Programmer(int id, String nom_enseignant, String filiere, String matiere, String classe, String vh) {
        this.id = id;
        this.nom_enseignant = nom_enseignant;
        this.filiere = filiere;
        this.matiere = matiere;
        this.classe = classe;
        this.vh = vh;
    }


    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", nom_enseignant='" + nom_enseignant + '\'' +
                ", matiere='" + matiere + '\'' +
                ", classe='" + classe + '\'' +
                ", filiere='" + filiere + '\'' +
                ", vh='" + vh + '\'' +
                '}';
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_enseignant() {
        return nom_enseignant;
    }

    public void setNom_enseignant(String nom_enseignant) {
        this.nom_enseignant = nom_enseignant;
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
