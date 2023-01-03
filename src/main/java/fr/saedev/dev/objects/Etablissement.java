package fr.saedev.dev.objects;

import java.util.ArrayList;
import java.util.Collections;

public class Etablissement {
    private String nom;

    public String getNom() {
        return nom;
    }

    private ArrayList<Personne> personne = new ArrayList<>();
    private ArrayList<Affectation> couples = new ArrayList<>();
    private ArrayList<Personne> tuteur;
    private ArrayList<Personne> tutore;

    public Etablissement(String nom) {
        this.nom = nom;
    }
    public void addPersonne(Personne per){
        this.personne.add(per);
    }

    public void removePersonne(Personne per){
        this.personne.remove(per);
    }

    public ArrayList<Personne> getPersonne() {
        return personne;
    }


    public void addAffectation(Affectation affectation){
        this.couples.add(affectation);
    }

    public void removeAffectation(Affectation affectation){
        this.couples.remove(affectation);
    }

    public ArrayList<Affectation> getCouples() {
        return couples;
    }

    public void clearCouples(){
        this.couples.removeAll(couples);
    }

    public ArrayList<Personne> getTuteur(){
        ArrayList<Personne> tuteur = new ArrayList<>();
        for(Personne p : this.personne){
            if (p.isTuteur()){
                tuteur.add(p);
            }
        }
        this.tuteur = tuteur;
        return tuteur;
    }

    public ArrayList<Personne> triList(ArrayList<Personne> per){
        if(per.isEmpty()){
            return null;
        }
        if(per.get(0).isTutore()){
            Collections.sort(per);
            Collections.reverse(per);
        }else{
            Collections.sort(per);
        }
        return per;
    }

    public void balanceLists() {
        int nbPetit;
        int nbGrand;
        if (this.tutore.size() > this.tuteur.size()) {
            nbPetit = this.tuteur.size();
            nbGrand = this.tutore.size();
        } else if (this.tuteur.size() == this.tutore.size()) {
            nbPetit = this.tuteur.size();
            nbGrand = this.tutore.size();
        } else {
            nbPetit = this.tutore.size();
            nbGrand = this.tutore.size();
        }
        if (this.tuteur.size() == nbGrand) {
            Collections.sort(this.tuteur);
            for (int i = 0; i < nbGrand - nbPetit; i++) {
                this.tuteur.remove(i);
            }
        } else {
            for (int i = 0; i < nbGrand - nbPetit; i++) {
                this.tutore.remove(i);
            }
        }
    }

    public ArrayList<Personne> getTutore(){
        ArrayList<Personne> tutore = new ArrayList<>();
        for(Personne p : this.personne){
            if (p.isTutore()){
                tutore.add(p);
            }
        }
        this.tutore = tutore;
        return tutore;
    }

    public Personne getSpecifyPersonne(String tostring){
        String[] per = tostring.split(" ");
        for(Personne t : this.personne){
            if (t.getPrenom().equals(per[0]) && t.getNom().equals(per[1])){
                return t;
            }
        }
        return null;
    }

    public ArrayList<Personne> PlusPetitQueFiltre(String categorie, String argument){
        int args = Integer.parseInt(argument);
        ArrayList<Personne> res = new ArrayList<>();
        if(categorie.equals("Moyenne")){
            for(Personne p : this.personne){
                if(p.getMoyenne() <= args){
                    res.add(p);
                }
            }
        }else if(categorie.equals("Abscence")){
            for(Personne p : this.personne){
                if(p.getAbsences() <= args){
                    res.add(p);
                }
            }
        }else if(categorie.equals("Annee")){
            for(Personne p : this.personne){
                if(p.getAnnee() <= args){
                    res.add(p);
                }
            }
        }else if(categorie.equals("Motivation")){
            for(Personne p : this.personne){
                if(p.getMotivation() <= args){
                    res.add(p);
                }
            }
        }
        return res;
    }

    public ArrayList<Personne> egalFiltre(String categorie, String argument){
        int args = Integer.parseInt(argument);
        ArrayList<Personne> res = new ArrayList<>();
        if(categorie.equals("Moyenne")){
            for(Personne p : this.personne){
                if(p.getMoyenne() == args){
                    res.add(p);
                }
            }
        }else if(categorie.equals("Abscence")){
            for(Personne p : this.personne){
                if(p.getAbsences() == args){
                    res.add(p);
                }
            }
        }else if(categorie.equals("Annee")){
            for(Personne p : this.personne){
                if(p.getAnnee() == args){
                    res.add(p);
                }
            }
        }else if(categorie.equals("Motivation")){
            for(Personne p : this.personne){
                if(p.getMotivation() == args){
                    res.add(p);
                }
            }
        }
        return res;
    }

    public ArrayList<Personne> PlusGrandQueFiltre(String categorie, String argument){
        int args = Integer.parseInt(argument);
        ArrayList<Personne> res = new ArrayList<>();
        if(categorie.equals("Moyenne")){
            for(Personne p : this.personne){
                if(p.getMoyenne() >= args){
                    res.add(p);
                }
            }
        }else if(categorie.equals("Abscence")){
            for(Personne p : this.personne){
                if(p.getAbsences() >= args){
                    res.add(p);
                }
            }
        }else if(categorie.equals("Annee")){
            for(Personne p : this.personne){
                if(p.getAnnee() >= args){
                    res.add(p);
                }
            }
        }else if(categorie.equals("Motivation")){
            for(Personne p : this.personne){
                if(p.getMotivation() >= args){
                    res.add(p);
                }
            }
        }
        return res;
    }

    public boolean tuteurEstAff(Personne per){
        for (Affectation a:couples) {
            if(a.getTuteur().equals(per)){
                return true;
            }
        }
        return false;
    }

    public boolean tutoreEstAff(Personne per){
        for (Affectation a:couples) {
            if(a.getTutoré().equals(per)){
                return true;
            }
        }
        return false;
    }

}
