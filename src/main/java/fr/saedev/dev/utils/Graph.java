package fr.saedev.dev.utils;

import fr.saedev.dev.objects.Affectation;
import fr.saedev.dev.objects.Etablissement;
import fr.saedev.dev.objects.Personne;
import fr.ulille.but.sae2_02.graphes.CalculAffectation;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    private ArrayList<Personne> tuteur;
    private ArrayList<Personne> tutore;
    private GrapheNonOrienteValue<String> graphe;

    public Graph(ArrayList<Personne> tuteur, ArrayList<Personne> tutore) {
        this.graphe = createGraph();
        this.tuteur = tuteur;
        this.tutore = tutore;
    }

    private String personneToString(Personne per){
        return per.getPrenom() + " " + per.getNom();
    }

    public GrapheNonOrienteValue createGraph(){
        return new GrapheNonOrienteValue();
    }

    public void addSommet(Personne per){
        graphe.ajouterSommet(personneToString(per));
    }

    public void addArete(Personne per1, Personne per2, int points){
        graphe.ajouterArete(personneToString(per1), personneToString(per2), (double) per1.getPoints()+per2.getPoints());
    }

    public void addAllSommets(ArrayList<Personne> personnes){
        for (int i = 0; i<personnes.size(); i++){
            graphe.ajouterSommet(personneToString(personnes.get(i)));
        }
    }

    public void addAllSommets(){
        for (int i = 0; i<this.tuteur.size(); i++){
            graphe.ajouterSommet(personneToString(this.tuteur.get(i)));
        }
        for (int i = 0; i<this.tutore.size(); i++){
            graphe.ajouterSommet(personneToString(this.tutore.get(i)));
        }
    }

    public void addAllAretes(){
        for (int i = 0; i<this.tuteur.size(); i++){
            for (int j = 0; j<this.tutore.size(); j++){
                graphe.ajouterArete(personneToString(this.tuteur.get(i)), personneToString(this.tutore.get(j)), (double) this.tuteur.get(i).getPoints()+tutore.get(j).getPoints());
            }
        }
    }

    public void calculAffectation(Etablissement iut){
        iut.balanceLists();
        iut.triList(iut.getTuteur());
        iut.triList(iut.getTutore());
        addAllSommets();
        addAllAretes();
        List tuteurStr = new ArrayList<String>();
        List tutoreStr = new ArrayList<String>();
        for(Personne p : this.tuteur){
            tuteurStr.add(personneToString(p));
        }
        for(Personne p : this.tutore){
            tutoreStr.add(personneToString(p));
        }
        CalculAffectation<String> calcul = new CalculAffectation<>(graphe, tuteurStr, tutoreStr);
        System.out.println("\n-------------------\n");
        for(int i = 0; i<calcul.getAffectation().size(); i++){
            iut.addAffectation(new Affectation(iut.getSpecifyPersonne(calcul.getAffectation().get(i).getExtremite1()), iut.getSpecifyPersonne(calcul.getAffectation().get(i).getExtremite2())));
            System.out.println(calcul.getAffectation().get(i).getExtremite1().split(" ")[0] + " avec " + calcul.getAffectation().get(i).getExtremite2().split(" ")[0]);
        }
    }
}
