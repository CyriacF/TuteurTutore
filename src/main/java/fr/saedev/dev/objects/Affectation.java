package fr.saedev.dev.objects;

public class Affectation {
    private Personne tuteur;
    private Personne tutore;

    public Affectation(Personne tuteur, Personne tutoré){
        this.tuteur = tuteur;
        this.tutore = tutoré;
    }

    public Personne getTuteur() {
        return tuteur;
    }

    public void setTuteur(Personne tuteur) {
        this.tuteur = tuteur;
    }

    public Personne getTutoré() {
        return tutore;
    }

    public void setTutoré(Personne tutoré) {
        this.tutore = tutoré;
    }

    @Override
    public String toString() {
        return "Tuteur : " + this.tuteur.getPrenom() + " " + this.tuteur.getNom() + "  -  " + "Tutoré : " + this.tutore.getPrenom() + " " + this.tutore.getNom();
    }
}
