package fr.saedev.dev.objects;

public class Personne implements Comparable<Personne>{
    private String nom;
    private String prenom;
    private double moyenne;
    private int motivation;
    private int annee;
    private int absences;
    private boolean tuteur;
    private boolean tutore;
    private double points;


    public Personne(String nom, String prenom, double moyenne, int motivation, int annee, int absences, boolean tuteur, boolean tutore) {
        this.nom = nom;
        this.prenom = prenom;
        this.moyenne = moyenne;
        this.motivation = motivation;
        this.annee = annee;
        this.absences = absences;
        this.tuteur = tuteur;
        this.tutore = tutore;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public String getNom() {
        return nom;
    }

    public int getAbsences() {
        return absences;
    }

    public void setAbsences(int absences) {
        this.absences = absences;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public double getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(double moyenne) {
        this.moyenne = moyenne;
    }

    public int getMotivation() {
        return motivation;
    }

    public void setMotivation(int motivation) {
        this.motivation = motivation;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    @Override
    public String toString() {
        return "Personne{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", moyenne=" + moyenne +
                ", motivation=" + motivation +
                ", annee=" + annee +
                ", absences=" + absences +
                ", tuteur=" + tuteur +
                ", tutore=" + tutore +
                '}';
    }

    public String getStringName() {
        return this.prenom + " " + this.nom;
    }

    @Override
    public int compareTo(Personne per){
        if(this.getPoints() > per.getPoints()){
            return 1;
        }else if(this.getPoints() == per.getPoints()){
            return 0;
        }else{
            return -1;
        }
    }

    public boolean isTuteur() {
        return tuteur;
    }

    public void setTuteur(boolean tuteur) {
        this.tuteur = tuteur;
    }

    public boolean isTutore() {
        return tutore;
    }

    public void setTutore(boolean tutore) {
        this.tutore = tutore;
    }


}
