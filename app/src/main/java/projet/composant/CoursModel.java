package projet.composant;

public class CoursModel {
    String nomEnseignant, nomFiliere, nomClasse, nomMatiere,nomCreneau;

    public CoursModel(String nomEnseignant, String nomFiliere, String nomClasse, String nomMatiere, String nomCreneau) {
        this.nomEnseignant = nomEnseignant;
        this.nomFiliere = nomFiliere;
        this.nomClasse = nomClasse;
        this.nomMatiere = nomMatiere;
        this.nomCreneau = nomCreneau;
    }

    public String getNomEnseignant() {
        return nomEnseignant;
    }

    public void setNomEnseignant(String nomEnseignant) {
        this.nomEnseignant = nomEnseignant;
    }

    public String getNomFiliere() {
        return nomFiliere;
    }

    public void setNomFiliere(String nomFiliere) {
        this.nomFiliere = nomFiliere;
    }

    public String getNomClasse() {
        return nomClasse;
    }

    public void setNomClasse(String nomClasse) {
        this.nomClasse = nomClasse;
    }

    public String getNomMatiere() {
        return nomMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }

    public String getNomCreneau() {
        return nomCreneau;
    }

    public void setNomCreneau(String nomCreneau) {
        this.nomCreneau = nomCreneau;
    }
}
